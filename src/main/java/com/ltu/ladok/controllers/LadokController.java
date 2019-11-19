package com.ltu.ladok.controllers;

import com.ltu.ladok.model.Course;
import com.ltu.ladok.model.CourseInstance;
import com.ltu.ladok.repository.CourseRepository;
import com.ltu.ladok.repository.CourseInstanceRepository;
import com.ltu.ladok.repository.ExaminationRepository;
import com.ltu.ladok.repository.StudentGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class LadokController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseInstanceRepository courseInstanceRepository;

    @Autowired
    private ExaminationRepository examinationRepository;

    @Autowired
    private StudentGradeRepository studentGradeRepository;

    @GetMapping("/ladok_courses")
    public String getAllCourses(Model model){
        model.addAttribute("courses", courseRepository.findAll());
        return "list_courses";
    }

    @PostMapping("/ladok_courses")
    public String postCourse(@Valid Course course, BindingResult result, Model model) {
        if (result.hasErrors()) { return "add_course"; }
        courseRepository.save(course);
        return "redirect:list_courses";
    }

    @GetMapping("/ladok_courses/submit/{courseCode}")
    public String getCourseInstances(@PathVariable(value = "courseCode") String courseCode,
                                Model model){
        model.addAttribute("course", courseRepository.findByCourseCode(courseCode));
        model.addAttribute("instances", courseInstanceRepository.findAll());
        return "list_instances";
    }

    @GetMapping("/ladok_courses/submit/{courseCode}/{semester}")
    public String getInstanceExaminations(@PathVariable(value = "courseCode") String courseCode,
                                 @PathVariable(value = "semester") String semester,
                                 Model model){
        Course course = courseRepository.findByCourseCode(courseCode);
        model.addAttribute("course", course);
        CourseInstance courseInstance = courseInstanceRepository.findByCourseInstanceIdAndSemester(course.getId(), semester);
        model.addAttribute("instance", courseInstance);
        model.addAttribute("examinations", examinationRepository.findByCourseInstanceId(courseInstance.getId()));
        return "list_examinations";
    }

    @GetMapping("/ladok_courses/submit/{courseCode}/{semester}/{provNummer}")
    public String getCourseGrades(@PathVariable(value = "courseCode") String courseCode,
                                  @PathVariable(value = "semester") String semester,
                                  @PathVariable(value = "provNummer") String provnummer,
                                  Model model){
        Course course = courseRepository.findByCourseCode(courseCode);
        model.addAttribute("course", course);
        CourseInstance courseInstance = courseInstanceRepository.findByCourseInstanceIdAndSemester(course.getId(), semester);
        model.addAttribute("instance", courseInstance);
        model.addAttribute("exam", examinationRepository.findByCourseInstanceIdAndProvNummer(courseInstance.getId(), provnummer));
        //TODO Fix this to only return the correct studentgrades by adding corect method to the StudentGradeRepository
        model.addAttribute("student_grades", studentGradeRepository.findAll());
        return "grades_form";
    }

}