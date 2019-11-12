package com.ltu.ladok.controllers;

import com.ltu.ladok.model.Course;
import com.ltu.ladok.model.CourseInstance;
import com.ltu.ladok.repository.CourseRepository;
import com.ltu.ladok.repository.CourseInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseInstanceRepository courseInstanceRepository;

    @GetMapping("/epok_courses")
    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    @PostMapping("/epok_courses")
    public Course createCourse(@Valid @RequestBody Course course){
        return courseRepository.save(course);
    }

    @GetMapping("/epok_courses/{id}")
    public Optional<Course> getCourseById(@PathVariable(value = "id") Long courseId){
        return courseRepository.findById(courseId);
    }

    @GetMapping("/epok_courses/bycode/{courseCode}")
    public Course getCourseByCourseCode(@PathVariable(value = "courseCode") String courseCode){
        return courseRepository.findByCourseCode(courseCode);
    }

    //TODO Modify to not return null but instead a 404
    @GetMapping("/epok_courses/bycode/{courseCode}/{semester}")
    public String getSignupCode(
            @PathVariable(value = "courseCode") String courseCode,
            @PathVariable(value = "semester") String semester){
        Optional<CourseInstance> optionalCourseInstance;
        Course course = courseRepository.findByCourseCode(courseCode);
        List<CourseInstance> courseInstanceList = course.getCourseInstances();
        for (CourseInstance ci : courseInstanceList) {
            if (ci.getSemester().equals(semester)) {
                return ci.getSignupCode();
            }
        }
        return null;
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable(value = "id") Long courseId){
        Optional<Course> c = courseRepository.findById(courseId);
        if (c.isPresent()){
            courseRepository.delete(c.get());
        }
    }
}