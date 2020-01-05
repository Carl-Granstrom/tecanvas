package com.ltu.tecanvas.model.form;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "course_id", updatable = false, nullable = false)
    private Long id;

    //natural id?
    private Long timeEditId;

    private LocalDate startDate;

    //Uses the Epoch, todo think about conversion!
    private Time startTime;

    private LocalDate endDate;

    private Time endTime;

    private String lokal;

    private List<String> lärare;

    private String aktivitet;

    //Del 1 av "Kurs/Program, Kurs" i json-svar från TimeEdit
    private String kursKod;
    //Del 2 av "Kurs/Program, Kurs" i json-svar från TimeEdit
    private String kursNamn;

    //Kiruna/Luleå/Piteå/Skellefteå todo anticipate problems with å/ä/ö
    private String campus;

    //unsure about use todo storing all these fields as additional information with some formatting in Canvas
    private String text1;

    //unsure about use todo storing all these fields as additional information with some formatting in Canvas
    private String text2;

    //unsure about use todo storing all these fields as additional information with some formatting in Canvas
    private String syfte;

    //this "Kurs/Program" seems to duplicate information in "Kurs/Program, Kurs"
    private String kursProgram;

    //unsure about use todo storing all these fields as additional information with some formatting in Canvas
    private String kund;

    //unsure about use todo storing all these fields as additional information with some formatting in Canvas
    private String utrustning;
}
