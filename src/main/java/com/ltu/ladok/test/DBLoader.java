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

import java.time.LocalDate;
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
        StudentGrade sg1 = new StudentGrade("8309290313", "G", "Carl", "Granström", LocalDate.now());
        StudentGrade sg2 = new StudentGrade("8507200311", "VG", "Alfons", "Åberg", LocalDate.now());
        studentGradeRepository.save(sg1);
        studentGradeRepository.save(sg2);

        StudentGrade sg3 = new StudentGrade("8309290313", "G", "Carl", "Granström", LocalDate.now());
        StudentGrade sg4 = new StudentGrade("8507200311", "G", "Alfons", "Åberg", LocalDate.now());
        StudentGrade sg5 = new StudentGrade("9201010111", "U", "Pelle", "Kanin", LocalDate.now());
        studentGradeRepository.save(sg3);
        studentGradeRepository.save(sg4);
        studentGradeRepository.save(sg5);

        StudentGrade sg6 = new StudentGrade("8507200311", "VG", "Alfons", "Åberg", LocalDate.now());
        StudentGrade sg7 = new StudentGrade("8309290313", "VG", "Carl", "Granström", LocalDate.now());
        StudentGrade sg8 = new StudentGrade("9201010111", "VG", "Pelle", "Kanin", LocalDate.now());
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
        examList1.add(e1);
        examList1.add(e2);
        examList1.add(e3);
        //create first course instance
        CourseInstance ci1 = courseInstanceRepository.save(
                new CourseInstance("LTU-12345", "HT18", examList1));

        //Create StudentGrade objects
        StudentGrade sg9 = new StudentGrade("8309290313", "U", "Carl", "Granström", LocalDate.now());
        StudentGrade sg10 = new StudentGrade("8507200311", "G", "Alfons", "Åberg", LocalDate.now());
        StudentGrade sg102 = new StudentGrade("8507200311", null, "Alfons", "Åberg", LocalDate.now());
        StudentGrade sg103 = new StudentGrade("8507200311", null, "Alfons", "Åberg", LocalDate.now());
        studentGradeRepository.save(sg9);
        studentGradeRepository.save(sg10);
        studentGradeRepository.save(sg102);
        studentGradeRepository.save(sg103);
        List<StudentGrade> sgList4 = new ArrayList<>();
        sgList4.add(sg9);
        sgList4.add(sg10);
        List<StudentGrade> sgList42 = new ArrayList<>();
        sgList42.add(sg102);
        List<StudentGrade> sgList43 = new ArrayList<>();
        sgList43.add(sg103);

        //create examinations for 2nd course instance of Databaser 2
        Examination e4 = new Examination("0001", sgList4);
        Examination e5 = new Examination("0002", sgList42);
        Examination e6 = new Examination("0003", sgList43);
        examinationRepository.save(e4);
        examinationRepository.save(e5);
        examinationRepository.save(e6);
        List<Examination> examList2 = new ArrayList<>();
        examList2.add(e4);
        examList2.add(e5);
        examList2.add(e6);
        //create second course instance
        CourseInstance ci2 = new CourseInstance("LTU-12346", "VT19", examList2);
        courseInstanceRepository.save(ci2);

        //create student grades for the course
        StudentGrade sg11 = new StudentGrade("8309290313", "G", "Carl", "Granström", LocalDate.now());
        StudentGrade sg12 = new StudentGrade("8507200311", "VG", "Alfons", "Åberg", LocalDate.now());
        StudentGrade sg13 = new StudentGrade("9201010111", "U", "Pelle", "Kanin", LocalDate.now());
        StudentGrade sg14 = new StudentGrade("7503112211", null, "Klas", "Klättermus", LocalDate.now());
        StudentGrade sg15 = new StudentGrade("7110141222", null, "Maja", "Gräddnos", LocalDate.now());
        StudentGrade sg16 = new StudentGrade("5408181333", null, "Nils Karlson", "Pyssling", LocalDate.now());
        studentGradeRepository.save(sg11);
        studentGradeRepository.save(sg12);
        studentGradeRepository.save(sg13);
        studentGradeRepository.save(sg14);
        studentGradeRepository.save(sg15);
        studentGradeRepository.save(sg16);
        Set<StudentGrade> sgList5 = new HashSet<>();
        sgList5.add(sg11);
        sgList5.add(sg12);
        sgList5.add(sg13);
        sgList5.add(sg14);
        sgList5.add(sg15);
        sgList5.add(sg16);

        //create the course
        Set<CourseInstance> courseInstances1 = new LinkedHashSet<>();
        courseInstances1.add(ci1);
        courseInstances1.add(ci2);
        Course c1 = new Course("Databaser 2", "D0005N", courseInstances1, sgList5);
        courseRepository.save(c1);
    }

}