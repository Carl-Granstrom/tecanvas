package com.ltu.ladok.repository;

import com.ltu.ladok.model.StudentGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentGradeRepository extends JpaRepository<StudentGrade, Long> {

    @Query(value = "SELECT * FROM student_grade WHERE examination_fk = :examinationId", nativeQuery = true)
    List<StudentGrade> findByExaminationId(@Param("examinationId") Long examinationId);

    @Query(value = "SELECT * FROM student_grade WHERE course_fk = :courseId", nativeQuery = true)
    List<StudentGrade> findByCourseId(@Param("courseId") Long courseId);

}
