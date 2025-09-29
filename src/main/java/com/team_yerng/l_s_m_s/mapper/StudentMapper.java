package com.team_yerng.l_s_m_s.mapper;

import com.team_yerng.l_s_m_s.dto.StudentDto;
import com.team_yerng.l_s_m_s.model.Student;
import com.team_yerng.l_s_m_s.model.User;

public class StudentMapper {

    public static StudentDto mapToStudentDto(Student student) {
        if (student == null) {
            return null;
        }

        StudentDto dto = new StudentDto();
        dto.setId(student.getId());

        if (student.getUser() != null) {
            dto.setUserId(student.getUser().getId());
        }

        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setStudentId(student.getStudentId());
        dto.setPhoneNumber(student.getPhoneNumber());
        dto.setMajor(student.getMajor());
        dto.setSection(student.getSection());
        dto.setYear(student.getYear());
        dto.setCreatedAt(student.getCreatedAt());
        dto.setUpdatedAt(student.getUpdatedAt());

        return dto;
    }

    public static Student mapToStudent(StudentDto dto) {
        if (dto == null) {
            return null;
        }

        User userPlaceholder = null;
        if (dto.getUserId() != null) {
            userPlaceholder = new User();
            userPlaceholder.setId(dto.getUserId());
        }

        Student student = new Student();

        // Only set ID if updating
        if (dto.getId() != null) {
            student.setId(dto.getId());
        }

        student.setUser(userPlaceholder);
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setStudentId(dto.getStudentId());
        student.setPhoneNumber(dto.getPhoneNumber());
        student.setMajor(dto.getMajor());
        student.setSection(dto.getSection());
        student.setYear(dto.getYear());

        return student;
    }
}
