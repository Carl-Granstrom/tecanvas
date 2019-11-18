package com.ltu.ladok.repository;

import com.ltu.ladok.model.CourseInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseInstanceRepository extends JpaRepository<CourseInstance, Long> {
    CourseInstance findBySemester(String semester);
}
