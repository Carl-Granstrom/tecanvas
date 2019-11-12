package com.ltu.ladok.repository;

import com.ltu.ladok.model.StudentGrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentGradeRepository extends JpaRepository<StudentGrade, Long> {
}
