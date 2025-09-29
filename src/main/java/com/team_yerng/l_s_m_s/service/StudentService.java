package com.team_yerng.l_s_m_s.service;

import com.team_yerng.l_s_m_s.dto.StudentDto;
import com.team_yerng.l_s_m_s.mapper.StudentMapper;
import com.team_yerng.l_s_m_s.model.Student;
import com.team_yerng.l_s_m_s.model.User;
import com.team_yerng.l_s_m_s.repository.StudentRepository;
import com.team_yerng.l_s_m_s.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public StudentService(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    public StudentDto saveStudent(StudentDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + dto.getUserId()));

        Student student = StudentMapper.mapToStudent(dto);
        student.setUser(user);

        Student saved = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(saved);
    }

    @Transactional
    public StudentDto updateStudent(Long id, StudentDto dto) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + id));

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + dto.getUserId()));
            existing.setUser(user);
        }

        if (dto.getFirstName() != null) existing.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) existing.setLastName(dto.getLastName());
        if (dto.getPhoneNumber() != null) existing.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getStudentId() != null) existing.setStudentId(dto.getStudentId());
        if (dto.getMajor() != null) existing.setMajor(dto.getMajor());
        if (dto.getSection() != null) existing.setSection(dto.getSection());
        if (dto.getYear() != null) existing.setYear(dto.getYear());

        Student saved = studentRepository.save(existing);
        return StudentMapper.mapToStudentDto(saved);
    }


    public StudentDto findStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + id));
        return StudentMapper.mapToStudentDto(student);
    }

    public StudentDto findStudentByStudentId(String studentId) {
        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with institutional ID: " + studentId));
        return StudentMapper.mapToStudentDto(student);
    }

    public List<StudentDto> findAllStudents() {
        return studentRepository.findAll().stream()
                .map(StudentMapper::mapToStudentDto)
                .collect(Collectors.toList());
    }

    // --- DELETE ---
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }
}
