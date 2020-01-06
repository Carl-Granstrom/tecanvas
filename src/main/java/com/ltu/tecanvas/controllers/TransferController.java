package com.ltu.tecanvas.controllers;

import com.ltu.tecanvas.model.CourseSchedule;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class TransferController {
    @PostMapping(value = "/createPerson", consumes = "application/json", produces = "application/json")

    public CourseSchedule createPerson(@RequestBody CourseSchedule person) {
        return null;
    }
}
