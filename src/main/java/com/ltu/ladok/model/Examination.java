package com.ltu.ladok.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

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

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "examination_id", updatable = false, nullable = false)
    private Long id;

    @Basic
    @Column(name = "provnummer")
    private String provNummer;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "examination_fk")
    private List<StudentGrade> studentGrades;

    public Examination(String provnummer, List<StudentGrade> studentGrades) {
        this.provNummer = provnummer;
        this.studentGrades = studentGrades;
    }
}
