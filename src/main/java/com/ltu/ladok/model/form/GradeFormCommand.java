package com.ltu.ladok.model.form;

import lombok.*;

import java.time.LocalDate;

/**
 * This class holds the {@code StudentGrade} submission form data for the TeacherGUI.
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GradeFormCommand {

    public GradeFormCommand(String courseCode, String semester, String grade, LocalDate date, String firstName, String lastName){
        this.courseCode = courseCode;
        this.semester = semester;
        this.grade = grade;
        this.date = date;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public GradeFormCommand(String courseCode, String grade, LocalDate date, String firstName, String lastName){
        this.courseCode = courseCode;
        this.semester = semester;
        this.grade = grade;
        this.date = date;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //Used to retrieve signupCode from epok
    String courseCode;
    String semester;

    //Used (together with signupCode) to retrieve personnummer from idealrestapi
    String ideal;

    //a String representing the grade
    String grade;

    //grade submission date
    LocalDate date;

    //Because of the "flat" representation of student details the name has to be submitted separately as
    // idealrestapi does not return the whole student object, only personnummer.
    //todo idealrestapi can already return the whole student object, but that is not according to specifications
    String firstName;
    String lastName;

    @Override
    public String toString(){
        return
                "courseCode: " + courseCode +
                ", semester: " + semester +
                ", ideal: " + ideal +
                ", grade: " + grade +
                ", date: " + date +
                ", firstName: " + firstName +
                ", lastName: " + lastName
                ;
    }
}
