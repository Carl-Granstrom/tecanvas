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

/**
 * Uses Spring test framework and junit 4 tests to add test data to the database and to test the REST-API.
 */
@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class DBLoader {

    @Autowired
    CourseInstanceRepository courseInstanceRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ExaminationRepository examinationRepository;

    @Autowired
    StudentGradeRepository studentGradeRepository;

    @Test
    public void testExampledata() {

        //Databaser 2 - D0005N

        //Create StudentGrade objects
        StudentGrade sg1 = new StudentGrade("8309290313", "G");
        StudentGrade sg2 = new StudentGrade("8507200311", "VG");
        studentGradeRepository.save(sg1);
        studentGradeRepository.save(sg2);

        StudentGrade sg3 = new StudentGrade("8309290313", "G");
        StudentGrade sg4 = new StudentGrade("8507200311", "G");
        StudentGrade sg5 = new StudentGrade("9201010111", "U");
        studentGradeRepository.save(sg3);
        studentGradeRepository.save(sg4);
        studentGradeRepository.save(sg5);

        StudentGrade sg6 = new StudentGrade("8507200311", "VG");
        StudentGrade sg7 = new StudentGrade("8309290313", "VG");
        StudentGrade sg8 = new StudentGrade("9201010111", "VG");
        studentGradeRepository.save(sg6);
        studentGradeRepository.save(sg7);
        studentGradeRepository.save(sg8);

        List<StudentGrade> sgList = new ArrayList<>();
        sgList.add(sg1);
        sgList.add(sg2);

        List<StudentGrade> sgList2 = new ArrayList<>();
        sgList2.add(sg3);
        sgList2.add(sg4);
        sgList2.add(sg5);

        List<StudentGrade> sgList3 = new ArrayList<>();
        sgList3.add(sg6);
        sgList3.add(sg7);
        sgList3.add(sg8);

        //create examinations for 1st course instance of Databaser 2
        Examination e1 = new Examination("0001", sgList);
        Examination e2 = new Examination("0002", sgList2);
        Examination e3 = new Examination("0003", sgList3);
        examinationRepository.save(e1);
        examinationRepository.save(e2);
        examinationRepository.save(e3);
        List<Examination> examList1 = new ArrayList<>();
        //create first course instance
        CourseInstance ci1 = courseInstanceRepository.save(
                new CourseInstance("LTU-12345", "HT18", examList1));

        //Create StudentGrade objects
        StudentGrade sg9 = new StudentGrade("8309290313", "U");
        StudentGrade sg10 = new StudentGrade("8507200311", "G");
        studentGradeRepository.save(sg9);
        studentGradeRepository.save(sg10);
        List<StudentGrade> sgList4 = new ArrayList<>();
        sgList4.add(sg9);
        sgList4.add(sg10);

        //create examinations for 2nd course instance of Databaser 2
        Examination e4 = new Examination("0001", sgList4);
        Examination e5 = new Examination("0002", new ArrayList<StudentGrade>());
        Examination e6 = new Examination("0003", new ArrayList<StudentGrade>());
        examinationRepository.save(e4);
        examinationRepository.save(e5);
        examinationRepository.save(e6);
        List<Examination> examList2 = new ArrayList<>();
        //create second course instance
        CourseInstance ci2 = courseInstanceRepository.save(
                new CourseInstance("LTU-12346", "VT19", examList2));

        //create student grades for the course
        StudentGrade sg11 = new StudentGrade("8309290313", "G");
        StudentGrade sg12 = new StudentGrade("8507200311", "VG");
        StudentGrade sg13 = new StudentGrade("9201010111", "U");
        studentGradeRepository.save(sg11);
        studentGradeRepository.save(sg12);
        studentGradeRepository.save(sg13);
        Set<StudentGrade> sgList5 = new HashSet<>();
        sgList5.add(sg11);
        sgList5.add(sg12);
        sgList5.add(sg13);

        //create the course
        Set<CourseInstance> courseInstances1 = new LinkedHashSet<>();
        courseInstances1.add(ci1);
        courseInstances1.add(ci2);
        Course c1 = new Course("Databaser 2", "D0005N", courseInstances1, sgList5);
        courseRepository.save(c1);
    }

}