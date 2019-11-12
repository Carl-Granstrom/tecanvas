package com.ltu.ideal.repository;

import com.ltu.ideal.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
