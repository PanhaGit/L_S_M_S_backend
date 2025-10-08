package com.team_yerng.l_s_m_s.mapper;

import com.team_yerng.l_s_m_s.dto.CategoryDto;
import com.team_yerng.l_s_m_s.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .code(category.getCode())
                .description(category.getDescription())
                .build();
    }

    public Category toEntity(CategoryDto dto) {
        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .code(dto.getCode())
                .description(dto.getDescription())
                .build();
    }
}
