package com.ltu.ladok.model;

import lombok.*;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @GeneratedValue
    @Id
    @Column(name = "student_id", updatable = false, nullable = false)
    private Long id;

    @Basic
    @NotBlank(message = "firstName is required")
    @Column(name = "first_name")
    private String firstName;

    @Basic
    @NotBlank(message = "lastName is required")
    @Column(name = "last_name")
    private String lastName;

    @Basic
    @NotBlank(message = "personnummer is required")
    @Column(name = "personnummer", unique = true)
    private String personnummer;

    @Basic
    @Column(name = "ideal_id")
    private String ideal;

    @NotNull
    private LocalDate createdAt;


    public Student(String firstName, String lastName, String personnummer) {
        SecureRandom random = new SecureRandom();
        this.firstName = firstName;
        this.lastName = lastName;
        this.personnummer = personnummer;
        //autogenerating an email from the first three letters of the first and last name
        //uses SecureRandom to generate a random number between 1 and 9
        //todo check logic for names with less than 3 letters
        String first3 = this.firstName.substring(0, 3).toLowerCase();
        String last3 = this.lastName.substring(0, 3).toLowerCase();
        //Todo generating a new number if number is already taken
        this.ideal = first3 + last3 + (random.nextInt(9) + 1);
        this.createdAt = LocalDate.now();
    }

    public Student(String firstName, String lastName, String personnummer, String ideal) {
        SecureRandom random = new SecureRandom();
        this.firstName = firstName;
        this.lastName = lastName;
        this.personnummer = personnummer;
        //autogenerating an email from the first three letters of the first and last name
        //uses SecureRandom to generate a random number between 1 and 9
        //todo check logic for names with less than 3 letters
        String first3 = this.firstName.substring(0, 3).toLowerCase();
        String last3 = this.lastName.substring(0, 3).toLowerCase();
        //Todo generating a new number if number is already taken
        this.ideal = ideal;
        this.createdAt = LocalDate.now();
    }

    // ********************** Accessor Methods ********************** //

    // ********************** Model Methods ********************** //

    @PrePersist
    void createdAt() {
        this.createdAt = LocalDate.now();
    }

    // ********************** Common Methods ********************** //
}