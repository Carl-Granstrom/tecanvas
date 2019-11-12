package com.ltu.ladok.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Klassen håller ett personnummer med tillhörande betyg för en examination.
 *
 * @author Carl Granström
 */
@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentGrade {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "student_grade_id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column(name = "personnummer")
    private String personnummer;

    //TODO implement validation to restrict the field to legal grade values,
    @Column(name = "grade")
    private String grade;

    public StudentGrade(String personnummer, String grade) {
        this.personnummer = personnummer;
        this.grade = grade;
    }
}
