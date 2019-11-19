package com.ltu.ladok.repository;

import com.ltu.ladok.model.CourseInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseInstanceRepository extends JpaRepository<CourseInstance, Long> {
    CourseInstance findBySemester(String semester);
    CourseInstance findBySignupCode(String signUpCode);

    @Query(value = "SELECT * FROM course_instance WHERE course_fk = :courseId", nativeQuery = true)
    List<CourseInstance> findByCourseCode(@Param("courseId") int courseId);

    @Query(value = "SELECT * FROM course_instance WHERE course_fk = :courseId AND semester = :semester", nativeQuery = true)
    CourseInstance findByCourseInstanceIdAndSemester(@Param("courseId") Long courseId,
                                                @Param("semester") String semester);
}
