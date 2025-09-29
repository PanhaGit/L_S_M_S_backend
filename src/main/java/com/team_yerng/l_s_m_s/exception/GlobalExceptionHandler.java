package com.team_yerng.l_s_m_s.exception;

import com.team_yerng.l_s_m_s.utils.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Helper method to build standard API error responses
    private <T> ResponseEntity<ApiResponse<T>> buildErrorResponse(HttpStatus status, String message) {
        ApiResponse<T> response = ApiResponse.error(status.value(), message);
        return new ResponseEntity<>(response, status);
    }

    // Handle Entity Not Found (404)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleEntityNotFound(EntityNotFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // Handle Validation Errors (400 Bad Request)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return buildErrorResponse(HttpStatus.BAD_REQUEST, errors);
    }

    // Handle Access Denied (403 Forbidden)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Object>> handleAccessDeniedException(AccessDeniedException ex) {
        return buildErrorResponse(HttpStatus.FORBIDDEN, "Access denied: " + ex.getMessage());
    }

    // Handle Illegal Arguments (400 Bad Request)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // Handle custom exceptions (Email already exists, etc.)
    @ExceptionHandler({
            EmailAlreadyExistsException.class,
            UserNotFoundException.class,
            InvalidCredentialsException.class,
            InvalidRefreshTokenException.class,
            RefreshTokenExpiredException.class
    })
    public ResponseEntity<ApiResponse<Object>> handleAuthExceptions(Exception ex) {
        HttpStatus status;
        if (ex instanceof EmailAlreadyExistsException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof InvalidCredentialsException || ex instanceof InvalidRefreshTokenException || ex instanceof RefreshTokenExpiredException) {
            status = HttpStatus.UNAUTHORIZED;
        } else if (ex instanceof UserNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return buildErrorResponse(status, ex.getMessage());
    }

    // Handle Generic Exceptions (500 Internal Server Error)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error: " + ex.getMessage());
    }
}