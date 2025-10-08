package com.team_yerng.l_s_m_s.service;

import com.team_yerng.l_s_m_s.dto.CategoryDto;
import com.team_yerng.l_s_m_s.mapper.CategoryMapper;
import com.team_yerng.l_s_m_s.model.Category;
import com.team_yerng.l_s_m_s.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    // Create using query
    public void create(CategoryDto dto) {
        categoryRepository.createCategory(dto.getName(), dto.getCode(), dto.getDescription());
    }

    // Update using query
    public boolean update(Long id, CategoryDto dto) {
        Optional<Category> existingOpt = categoryRepository.findById(id);
        if (existingOpt.isEmpty()) {
            return false;
        }

        Category category = existingOpt.get();

        // Update only fields that are not null
        if (dto.getName() != null) category.setName(dto.getName());
        if (dto.getCode() != null) category.setCode(dto.getCode());
        if (dto.getDescription() != null) category.setDescription(dto.getDescription());

        categoryRepository.save(category); // save updated entity
        return true;
    }


    // Delete using query
    public void delete(Long id) {
        categoryRepository.deleteCategory(id);
    }

    public Optional<CategoryDto> findOneCategory(Long id) {
        return categoryRepository.findCategoryById(id)
                .map(categoryMapper::toDto);
    }


    // Get all with search + pagination
    public Page<CategoryDto> getAll(String name, String code, String description, Pageable pageable) {
        return categoryRepository.findAllWithSearch(name, code, description, pageable)
                .map(categoryMapper::toDto);
    }
}
