package com.ltu.ideal.model;


import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @GeneratedValue
    @Id
    @Column(name = "course_id", updatable = false, nullable = false)
    private Long id;

    @Basic
    @Column(name = "course_name")
    private String name;

    @Basic
    @Column(name = "course_code")
    private String courseCode;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Student> students;

    @NotNull
    private LocalDate createdAt;

    public Course(String name, String courseCode, List<Student> students){
        this.name = name;
        this.courseCode = courseCode;
        this.students = students;
    }

    // ********************** Accessor Methods ********************** //

    // ********************** Model Methods ********************** //

    @PrePersist
    void createdAt() {
        this.createdAt = LocalDate.now();
    }


    // ********************** Common Methods ********************** //
}