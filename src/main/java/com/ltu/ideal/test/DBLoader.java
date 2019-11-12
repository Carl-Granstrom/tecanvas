package com.ltu.ideal.test;

import com.ltu.ideal.model.Course;
import com.ltu.ideal.model.Student;
import com.ltu.ideal.repository.CourseRepository;
import com.ltu.ideal.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Uses Spring test framework and junit 4 tests to add test data to the database and to test the REST-API.
 */
@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class DBLoader {

    Long stud4id;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Test
    public void testExampledata() {
        Student student1 = studentRepository.save(
                new Student("Carl", "Granstrom", "198309290313", "cargra5"));
        Student student2 = studentRepository.save(
                new Student("Hulk", "Hogan", "200712220417"));
        Student student3 = studentRepository.save(
                new Student("Hannah", "Montana", "200003047222"));
        Student student4 = studentRepository.save(
                new Student("Gurra", "Grankott", "200001010101"));
        stud4id = student4.getId();
        System.out.println(stud4id);

        for (Student s : studentRepository.findAll()){
            System.out.println(s.getId());
        }

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        Course c1 = new Course("Databaser 2", "D0005N", students);
        courseRepository.save(c1);
        students.add(student3);
        students.add(student4);
        Course c2 = new Course("Data Mining", "D0025E", students);
        courseRepository.save(c2);
    }

}