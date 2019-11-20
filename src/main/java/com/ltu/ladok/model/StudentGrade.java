package com.ltu.ladok.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Klassen håller ett namn och ett personnummer med tillhörande betyg för en examination.
 *
 * TODO Domänmodellen behöver egentligen arbetas om så att denna information representeras av en
 * TODO Student- och en Grade-klass istället. Det får ske i mån av tid, denna "platta" representation duger
 * TODO tills vidare.
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
    @Column(name = "date")
    private LocalDate date;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "personnummer")
    private String personnummer;

    //TODO implement validation to restrict the field to legal grade values,
    @Column(name = "grade")
    private String grade;

    public StudentGrade(String personnummer, String grade, String firstName, String lastName, LocalDate date) {
        this.personnummer = personnummer;
        this.grade = grade;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
    }
}
