package com.ltu.ladok.controllers;

import com.ltu.ladok.model.Course;
import com.ltu.ladok.model.CourseInstance;
import com.ltu.ladok.model.Examination;
import com.ltu.ladok.model.StudentGrade;
import com.ltu.ladok.model.form.GradeFormCommand;
import com.ltu.ladok.model.form.GradeFormCommandRest;
import com.ltu.ladok.repository.CourseInstanceRepository;
import com.ltu.ladok.repository.CourseRepository;
import com.ltu.ladok.repository.ExaminationRepository;
import com.ltu.ladok.repository.StudentGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

/**
 * This controller handles the use of the specificed form data to make calls to the Ideal and Epok REST-api:s and
 * update a grade that way.
 */
@Controller
public class LadokRestController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseInstanceRepository courseInstanceRepository;

    @Autowired
    private ExaminationRepository examinationRepository;

    @Autowired
    private StudentGradeRepository studentGradeRepository;

    @GetMapping("/form")
    public String getForm(Model model){
        model.addAttribute("formCommand", new GradeFormCommandRest());
        return "submit_form_rest";
    }

    @PostMapping("/submit_form")
    public String postFormCommandObject(@ModelAttribute("formCommand") GradeFormCommandRest form){

        //*** Calling Epok REST-API ***//
        RestTemplate epokRestCall = new RestTemplate();
        String epokUrl = "http://localhost:8081/epok_courses/bycode";
        ResponseEntity<String> epokResponse = epokRestCall.getForEntity(
                epokUrl + "/" + form.getCourseCode() + "/" + form.getSemester(),
                String.class);

        //save the body of the response from epok as the signupCode(anmälningskod)
        String signupCode = epokResponse.getBody();

        //*** Calling Ideal REST-API ***//
        RestTemplate idealRestCall = new RestTemplate();
        String idealUrl = "http://localhost:8080/pnr";
        ResponseEntity<String> idealResponse = idealRestCall.getForEntity(
                idealUrl + "/" + signupCode + "/" + form.getIdeal(),
                String.class);

        //save the body of the response from ideal as the personnummer
        String personnummer = idealResponse.getBody();

        //String personnummer, String grade, String firstName, String lastName, LocalDate date
        StudentGrade submittedGrade = new StudentGrade(
                personnummer,
                form.getGrade(),
                form.getFirstName(),
                form.getLastName(),
                form.getDate()
        );

        //get the correct Course, CourseInstance and Examination
        Course course =
                courseRepository.findByCourseCode(form.getCourseCode());
        CourseInstance courseInstance =
                courseInstanceRepository.findByCourseInstanceIdAndSemester(course.getId(), form.getSemester());
        Examination exam =
                examinationRepository.findByCourseInstanceIdAndProvNummer(courseInstance.getId(), form.getProvNummer());

        //add the StudentGrade to the Examination
        exam.getStudentGrades().add(submittedGrade);
        //save the examination(this also saves the StudentGrade because of cascade.ALL on Examination.students)
        examinationRepository.save(exam);

        return "redirect:/form";
    }

    //might try using this kind of postmapping as well
    @PostMapping("/form/{courseCode}/{semester}/{ideal}/{provnummer}/{date}/{grade}")
    public String postFormPath(
            @PathVariable(value = "courseCode") Long courseCode,
            @PathVariable(value = "semester") String semester,
            @PathVariable(value = "ideal") String ideal,
            @PathVariable(value = "provnummer") String provnummer,
            @PathVariable(value = "date") String date,
            @PathVariable(value = "grade") String grade
    ){


        return "redirect:/form";
    }
}
