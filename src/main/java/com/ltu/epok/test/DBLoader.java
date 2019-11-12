package com.ltu.epok.test;

import com.ltu.epok.model.Course;
import com.ltu.epok.model.CourseInstance;
import com.ltu.epok.repository.CourseRepository;
import com.ltu.epok.repository.CourseInstanceRepository;
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

    @Test
    public void testExampledata() {
        CourseInstance ci1 = courseInstanceRepository.save(
                new CourseInstance("LTU-12345", "HT18"));
        CourseInstance ci2 = courseInstanceRepository.save(
                new CourseInstance("LTU-12346", "VT19"));
        List<CourseInstance> courseInstances1 = new ArrayList<>();
        courseInstances1.add(ci1);
        courseInstances1.add(ci2);
        Course c1 = new Course("Databaser 2", "D0005N", courseInstances1);
        courseRepository.save(c1);

        CourseInstance ci3 = courseInstanceRepository.save(
                new CourseInstance("LTU-33333", "HT17"));
        CourseInstance ci4 = courseInstanceRepository.save(
                new CourseInstance("LTU-44444", "HT19"));
        List<CourseInstance> courseInstances2 = new ArrayList<>();
        courseInstances2.add(ci3);
        courseInstances2.add(ci4);
        Course c2 = new Course("Data Mining", "D0025E", courseInstances2);
        courseRepository.save(c2);
    }

}