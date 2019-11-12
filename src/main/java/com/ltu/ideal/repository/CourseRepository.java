package com.ltu.ideal.repository;

import com.ltu.ideal.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByCourseCode(String courseCode);
}
