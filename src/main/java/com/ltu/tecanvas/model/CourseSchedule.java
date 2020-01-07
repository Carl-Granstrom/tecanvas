package com.ltu.tecanvas.model;

import lombok.*;

import javax.persistence.Entity;
import java.util.List;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseSchedule {
    List<String> columnheaders;
    List<ResponseInfo> info;
    List<CalendarEventTE> reservations;
}