package com.ltu.ladok.test;

import com.ltu.ladok.model.Course;
import com.ltu.ladok.model.CourseInstance;
import com.ltu.ladok.model.Examination;
import com.ltu.ladok.model.StudentGrade;
import com.ltu.ladok.repository.CourseRepository;
import com.ltu.ladok.repository.CourseInstanceRepository;
import com.ltu.ladok.repository.ExaminationRepository;
import com.ltu.ladok.repository.StudentGradeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Uses Spring test framework and junit 4 tests to test the native queries of the CourseInstanceRepository.
 */
@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class NativeQueryTest {

    @Autowired
    CourseInstanceRepository courseInstanceRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ExaminationRepository examinationRepository;

    @Autowired
    StudentGradeRepository studentGradeRepository;

    @Test
    public void findByCourseCodeTest() {
        List<CourseInstance> ciList = courseInstanceRepository.findByCourseCode(27);
        assertEquals("LTU-12345", ciList.get(0).getSignupCode());
        assertEquals("LTU-12346", ciList.get(1).getSignupCode());
    }

    @Test
    public void findByCourseInstanceIdAndSemesterTest() {
        CourseInstance ci =  courseInstanceRepository.findByCourseInstanceIdAndSemester(27L, "VT19");
        assertEquals("LTU-12346", ci.getSignupCode());
    }

}