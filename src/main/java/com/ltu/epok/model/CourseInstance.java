package com.ltu.epok.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Klassen representerar ett tillfälle då en kurs ges.
 *
 * @author Carl Granström
 */
@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseInstance {

    @GeneratedValue
    @Id
    @Column(name = "course_instance_id", updatable = false, nullable = false)
    private Long id;

    @Basic
    @NotBlank(message = "anmälningskod krävs")
    @Column(name = "signup_code")
    private String signupCode;

    @Basic
    @NotBlank(message = "termin krävs")
    @Column(name = "semester")
    private String semester;

    @NotNull
    private LocalDate createdAt;


    public CourseInstance(String signupCode, String semester) {
        this.signupCode = signupCode;
        this.semester = semester;
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