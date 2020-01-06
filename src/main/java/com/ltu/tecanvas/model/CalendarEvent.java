package com.ltu.tecanvas.model;

import lombok.*;

import javax.persistence.Entity;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalendarEvent {
    Long id;
    Date startdate;
    String starttime;
    Date enddate;
    String endtime;
    List<String> columns;
}
