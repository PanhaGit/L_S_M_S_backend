package com.team_yerng.l_s_m_s.repository;

import com.team_yerng.l_s_m_s.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // Add this method to search by the unique student_id field
    Optional<Student> findByStudentId(String studentId);
}