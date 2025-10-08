package com.team_yerng.l_s_m_s.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private String code;
}
