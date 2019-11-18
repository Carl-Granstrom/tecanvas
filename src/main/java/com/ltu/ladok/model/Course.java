package com.ltu.ladok.model;


import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "course_id", updatable = false, nullable = false)
    private Long id;

    @NotBlank(message = "Namn är obligatoriskt")
    @Basic
    @Column(name = "course_name")
    private String name;

    @NotBlank(message = "Kurskod är obligatoriskt")
    @Basic
    @Column(name = "course_code")
    private String courseCode;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_fk")
    private Set<CourseInstance> courseInstances;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_FK")
    private Set<StudentGrade> studentGrades;

    @NotNull
    private LocalDate createdAt;

    public Course(String name,
                  String courseCode,
                  Set<CourseInstance> courseInstances,
                  Set<StudentGrade> studentGrades){
        this.name = name;
        this.courseCode = courseCode;
        this.courseInstances = courseInstances;
        this.studentGrades = studentGrades;
    }

    // ********************** Accessor Methods ********************** //

    // ********************** Model Methods ********************** //

    @PrePersist
    void createdAt() {
        this.createdAt = LocalDate.now();
    }


    // ********************** Common Methods ********************** //
}