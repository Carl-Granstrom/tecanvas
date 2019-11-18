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
    public String registerResult(@Valid Course course, BindingResult result, Model model) {
        if (result.hasErrors()) { return "add_course"; }
        courseRepository.save(course);
        return "redirect:list_courses";
    }

    @GetMapping("/ladok_courses/submit/{courseCode}")
    public String getAllCourses(@PathVariable(value = "courseCode") String courseCode,
                                Model model){
        System.out.println(courseCode);
        model.addAttribute("course", courseRepository.findByCourseCode(courseCode));
        model.addAttribute("instances", courseInstanceRepository.findAll());
        return "list_instances";
    }

}