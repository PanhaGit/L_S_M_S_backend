package com.team_yerng.l_s_m_s.controllers;

import com.team_yerng.l_s_m_s.dto.CategoryDto;
import com.team_yerng.l_s_m_s.service.CategoryService;
import com.team_yerng.l_s_m_s.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // Get all with search + pagination
    @GetMapping
    public ApiResponse<List<CategoryDto>> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String description,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<CategoryDto> pageResult = categoryService.getAll(name, code, description, PageRequest.of(page, size));

        ApiResponse.Pagination pagination = ApiResponse.Pagination.builder()
                .page(pageResult.getNumber())
                .size(pageResult.getSize())
                .totalElements(pageResult.getTotalElements())
                .totalPages(pageResult.getTotalPages())
                .build();

        return ApiResponse.success(pageResult.getContent(), pagination);
    }


    // Get by ID
    @GetMapping("/{id}")
    public ApiResponse<CategoryDto> getById(@PathVariable Long id) {
        return categoryService.findOneCategory(id)
                .map(ApiResponse::success)
                .orElse(ApiResponse.error(404, "Category not found"));
    }

    // Create
    @PostMapping("/create")
    public ApiResponse<CategoryDto> create(@RequestBody CategoryDto dto) {
        categoryService.create(dto);
        return ApiResponse.created(dto, "Category created successfully");
    }

    // Update
    @PutMapping("/update/{id}")
    public ApiResponse<CategoryDto> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryDto dto
    ) {
        boolean updated = categoryService.update(id, dto);
        if (updated) {
            dto.setId(id);
            return ApiResponse.success(dto);
        } else {
            return ApiResponse.error(404, "Category not found");
        }
    }



    // Delete
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ApiResponse.success(null);
    }
}
