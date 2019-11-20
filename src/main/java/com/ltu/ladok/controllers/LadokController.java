package com.ltu.ladok.controllers;

import com.ltu.ladok.model.Course;
import com.ltu.ladok.model.CourseInstance;
import com.ltu.ladok.model.Examination;
import com.ltu.ladok.model.StudentGrade;
import com.ltu.ladok.model.form.GradeFormCommand;
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
import java.time.LocalDate;
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

    /**
     * Entry point for the Teacher GUI
     *
     * @param model the model used by the html-view
     * @return the html-view
     */
    @GetMapping("/ladok_courses")
    public String getAllCourses(Model model){
        model.addAttribute("courses", courseRepository.findAll());
        return "list_courses";
    }

    /**
     * Second step in the Teacher GUI: After selecting a {@code Course}, returns the {@code CourseInstance}s
     *
     * @param courseCode used to retrieve only the selected {@code Course}
     * @param model the model used by the html-view
     * @return the html-view
     */
    @GetMapping("/ladok_courses/submit/{courseCode}")
    public String getCourseInstances(@PathVariable(value = "courseCode") String courseCode,
                                     Model model){
        //add the Course to model for use in the thymeleaf-view
        model.addAttribute("course", courseRepository.findByCourseCode(courseCode));

        //add the List of CourseInstances to model for use in the thymeleaf-view
        model.addAttribute("instances", courseInstanceRepository.findAll());
        return "list_instances";
    }

    /**
     * Alternative step three in the Teacher GUI: After selecting a {@code Course}, returns the {@code StudentGrade}s
     * associated with it
     *
     * @param courseCode used to retrieve only the selected {@code Course}
     * @param model the model used by the html-view
     * @return the html-view
     */
    @GetMapping("/ladok_courses/submit/coursegrade/{courseCode}")
    public String getCourseGrades(@PathVariable(value = "courseCode") String courseCode,
                                  Model model){
        //add the Course to model for use in the thymeleaf-view
        Course course = courseRepository.findByCourseCode(courseCode);
        model.addAttribute("course", course);

        //add the List of CourseInstances to model for use in the thymeleaf-view
        model.addAttribute("grades", studentGradeRepository.findByCourseId(course.getId()));
        return "list_grades_course";
    }

    /**
     * Alternative step four in the Teacher GUI: After selecting a {@code Course}, returns the {@code StudentGrade}s
     * associated with it
     *
     * @param courseCode used to retrieve only the selected {@code Course}
     * @param model the model used by the html-view
     * @return the html-view
     */
    @GetMapping("/ladok_courses/submit/coursegrade/{courseCode}/{id}")
    public String getCourseGradesForm(@PathVariable(value = "courseCode") String courseCode,
                                      @PathVariable(value = "id") Long id,
                                      Model model){
        //add the Course to model for use in the thymeleaf-view
        Course course = courseRepository.findByCourseCode(courseCode);
        model.addAttribute("course", course);

        //add the List of CourseInstances to model for use in the thymeleaf-view
        model.addAttribute("grades", studentGradeRepository.findByCourseId(course.getId()));

        //add the StudentGrade to model
        StudentGrade grade = studentGradeRepository.getOne(id);
        model.addAttribute("grade", grade);

        model.addAttribute("formCommand", new GradeFormCommand(course.getCourseCode(),
                grade.getGrade(), LocalDate.now(), grade.getFirstName(), grade.getLastName()));

        return "submit_form_course";
    }

    /**
     * Alternative step five in the Teacher GUI: After selecting a {@code Course}, returns the {@code StudentGrade}s
     * associated with it
     *
     * @param courseCode used to retrieve only the selected {@code Course}
     * @param model the model used by the html-view
     * @return the html-view
     */
    @PostMapping("/ladok_courses/submit/coursegrade/{courseCode}/{id}")
    public String postCourseGrade(@ModelAttribute("formCommand") GradeFormCommand form,
                                  @PathVariable(value = "courseCode") String courseCode,
                                  @PathVariable(value = "id") Long id,
                                  Model model){
        //add the Course to model for use in the thymeleaf-view
        Course course = courseRepository.findByCourseCode(courseCode);
        model.addAttribute("course", course);

        //retrieve, update and save the StudentGrade object
        StudentGrade gradeUpdate = studentGradeRepository.getOne(id);
        gradeUpdate.setDate(form.getDate());
        gradeUpdate.setFirstName(form.getFirstName());
        gradeUpdate.setLastName(form.getLastName());
        gradeUpdate.setGrade(form.getGrade());
        studentGradeRepository.save(gradeUpdate);
        model.addAttribute("grade", gradeUpdate);
        return "show_course_grade_posting";
    }

    /**
     * Third step in the Teacher GUI: After selecting a {@code Course} and a {@code CourseInstance},
     * returns the {@code Examination}s
     *
     * @param semester used to retrieve only the selected {@code CourseInstance}
     * @param courseCode used to retrieve only the selected {@code Course}
     * @param model the model used by the html-view
     * @return the html-view
     */
    @GetMapping("/ladok_courses/submit/{courseCode}/{semester}")
    public String getInstanceExaminations(@PathVariable(value = "courseCode") String courseCode,
                                 @PathVariable(value = "semester") String semester,
                                 Model model){
        //add the Course to model for use in the thymeleaf-view
        Course course =
                courseRepository.findByCourseCode(courseCode);
        model.addAttribute("course", course);

        //add the CourseInstance to model for use in the thymeleaf-view
        CourseInstance courseInstance =
                courseInstanceRepository.findByCourseInstanceIdAndSemester(course.getId(), semester);
        model.addAttribute("instance", courseInstance);

        //add the List of Examinations to model for use in the thymeleaf-view
        List<Examination> examinations =
                examinationRepository.findByCourseInstanceId(courseInstance.getId());
        model.addAttribute("examinations", examinations);

        return "list_examinations";
    }

    /**
     * Third step in the Teacher GUI: After selecting a {@code Course} and a {@code CourseInstance},
     * returns the {@code Examination}s
     *
     * @param provnummer used to retrieve only the selected {@code Examination}
     * @param semester used to retrieve only the selected {@code CourseInstance}
     * @param courseCode used to retrieve only the selected {@code Course}
     * @param model the model used by the html-view
     * @return the html-view
     */
    @GetMapping("/ladok_courses/submit/{courseCode}/{semester}/{provNummer}")
    public String getExaminationGrades(@PathVariable(value = "courseCode") String courseCode,
                                  @PathVariable(value = "semester") String semester,
                                  @PathVariable(value = "provNummer") String provnummer,
                                  Model model){
        //add the Course to model for use in the thymeleaf-view
        Course course =
                courseRepository.findByCourseCode(courseCode);
        model.addAttribute("course", course);

        //add the CourseInstance to model for use in the thymeleaf-view
        CourseInstance courseInstance =
                courseInstanceRepository.findByCourseInstanceIdAndSemester(course.getId(), semester);
        model.addAttribute("instance", courseInstance);

        //add the Examination to model for use in the thymeleaf-view
        Examination examination =
                examinationRepository.findByCourseInstanceIdAndProvNummer(courseInstance.getId(), provnummer);
        model.addAttribute("exam", examination);

        //add the List of StudentGrades to model for use in the thymeleaf-view
        model.addAttribute("student_grades", studentGradeRepository.findByExaminationId(examination.getId()));

        return "list_grades";
    }

    /**
     * Fourth step in the Teacher GUI: After selecting a {@code Course}, {@code CourseInstance} and {@code Examination}
     * returns the {@code StudentGrade} and send the user to the {@code submit_form} to change the details
     *
     * @param provnummer used to retrieve only the selected {@code Examination}
     * @param semester used to retrieve only the selected {@code CourseInstance}
     * @param courseCode used to retrieve only the selected {@code Course}
     * @param model the model used by the html-view
     * @return the html-view
     */
    @GetMapping("/ladok_courses/submit/{courseCode}/{semester}/{provNummer}/{id}")
    public String getGradesForm(@PathVariable(value = "courseCode") String courseCode,
                                  @PathVariable(value = "semester") String semester,
                                  @PathVariable(value = "provNummer") String provnummer,
                                  @PathVariable(value = "id") Long id,
                                  Model model){
        //add the Course to model for use in the thymeleaf-view
        Course course =
                courseRepository.findByCourseCode(courseCode);
        model.addAttribute("course", course);

        //add the CourseInstance to model for use in the thymeleaf-view
        CourseInstance courseInstance =
                courseInstanceRepository.findByCourseInstanceIdAndSemester(course.getId(), semester);
        model.addAttribute("instance", courseInstance);

        //add the Examination to model for use in the thymeleaf-view
        Examination examination =
                examinationRepository.findByCourseInstanceIdAndProvNummer(courseInstance.getId(), provnummer);
        model.addAttribute("exam", examination);

        //add the List of StudentGrades to model for use in the thymeleaf-view
        model.addAttribute("student_grades", studentGradeRepository.findByExaminationId(examination.getId()));

        //add the selected StudentGrade object to the model
        StudentGrade grade = studentGradeRepository.getOne(id);
        model.addAttribute("grade", grade);

        //add the GradeFormCommand-object to allow for form submission

        model.addAttribute("formCommand", new GradeFormCommand(course.getCourseCode(), courseInstance.getSemester(),
                grade.getGrade(), LocalDate.now(), grade.getFirstName(), grade.getLastName()));

        return "submit_form";
    }

    @PostMapping("/ladok_courses/submit/{courseCode}/{semester}/{provNummer}/{id}")
    public String postGrade(
            @ModelAttribute("formCommand") GradeFormCommand form,
            @PathVariable(value = "courseCode") String courseCode,
            @PathVariable(value = "id") Long id,
            Model model){

        //add the Course to model for use in the thymeleaf-view
        Course course =
                courseRepository.findByCourseCode(courseCode);
        model.addAttribute("course", course);

        //retrieve, update and save the StudentGrade object
        StudentGrade gradeUpdate = studentGradeRepository.getOne(id);
        gradeUpdate.setDate(form.getDate());
        gradeUpdate.setFirstName(form.getFirstName());
        gradeUpdate.setLastName(form.getLastName());
        gradeUpdate.setGrade(form.getGrade());
        studentGradeRepository.save(gradeUpdate);
        model.addAttribute("grade", gradeUpdate);

        return "show_grade_posting";
    }
}