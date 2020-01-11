package com.ltu.tecanvas.model;

import lombok.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalendarEventCanvas {
    String context_code;
    String location_name;
    String title;
    LocalDateTime start_at;
    LocalDateTime end_at;


}

/**
 *           https://canvas.instructure.com/api/v1/calendar_events
 *           3755~U8jxZ5WEIp4O9EDLFduET8hRvxlkMmTfY2wGvDQp6cBKqjLrIdGZW3GomgrvouKJ
 *
 *           https://ltu.instructure.com/api/v1/users/64496
 */
