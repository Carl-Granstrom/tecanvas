package com.ltu.ladok.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

    @GeneratedValue
    @Id
    @Column(name = "student_grade_id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column(name = "personnummer", unique = true)
    private String personnummer;

    //TODO implement validation to restrict the field to legal grade values,
    //TODO length validation is only a part of the solution
    @Length(min = 1, max = 2)
    @Column(name = "grade")
    private String grade;
}
