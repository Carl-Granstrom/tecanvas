package com.ltu.tecanvas.model;

import lombok.*;

import javax.persistence.Entity;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalendarEventTE {
    Long id;
    String startdate;
    String starttime;
    String enddate;
    String endtime;
    List<String> columns;
}
