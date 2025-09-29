package com.team_yerng.l_s_m_s.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class StudentDto {
    private Long id;
    private Long userId;       // maps to user_id automatically
    private String firstName;  // maps to first_name
    private String lastName;   // maps to last_name
    private String studentId;  // maps to student_id
    private String phoneNumber; // maps to phone_number
    private String major;
    private String section;
    private String year;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
