package com.team_yerng.l_s_m_s.controllers;

import com.team_yerng.l_s_m_s.dto.StudentDto;
import com.team_yerng.l_s_m_s.service.StudentService;
import com.team_yerng.l_s_m_s.utils.ApiResponse; // Import the utility class
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StudentDto>> createStudent(@RequestBody StudentDto studentDto) {
        StudentDto savedStudent = studentService.saveStudent(studentDto);
        return new ResponseEntity<>(ApiResponse.created(savedStudent,"Create successful"), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentDto>> getStudentById(@PathVariable("id") Long id) {
        StudentDto studentDto = studentService.findStudentById(id);
        return ResponseEntity.ok(ApiResponse.success(studentDto));
    }

    @GetMapping("/studentId/{studentId}")
    public ResponseEntity<ApiResponse<StudentDto>> getStudentByStudentId(@PathVariable("studentId") String studentId) {
        StudentDto studentDto = studentService.findStudentByStudentId(studentId);
        return ResponseEntity.ok(ApiResponse.success(studentDto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentDto>>> getAllStudents() {
        List<StudentDto> students = studentService.findAllStudents();
        return ResponseEntity.ok(ApiResponse.success(students));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentDto>> updateStudent(@PathVariable("id") Long id, @RequestBody StudentDto studentDto) {
        StudentDto updatedStudent = studentService.updateStudent(id, studentDto);
        return ResponseEntity.ok(ApiResponse.success(updatedStudent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .success(true)
                .code(200)
                .message("Resource deleted successfully")
                .data(null)
                .build());
    }

}