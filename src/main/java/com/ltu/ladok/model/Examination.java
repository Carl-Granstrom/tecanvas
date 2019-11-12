package com.ltu.ladok.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Klassen representerar en examination i en kurs.
 *
 * @author Carl Granström
 */
@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Examination {

    @GeneratedValue
    @Id
    @Column(name = "examination_id", updatable = false, nullable = false)
    private Long id;

    @Basic
    @Column(name = "provnummer")
    private String provnummer;

    @OneToMany(fetch = FetchType.EAGER)
    @Column(name = "student_grade")
    private List<StudentGrade> studentGrade;
}
