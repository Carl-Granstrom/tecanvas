package com.ltu.tecanvas.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltu.tecanvas.model.CalendarEventCanvas;
import com.ltu.tecanvas.model.CourseSchedule;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
public class TransferController {

    @GetMapping(value = "/transfer", produces = "application/json")
    public ResponseEntity<String> transfer() {

        //Creating Canvas event
        CalendarEventCanvas cec = new CalendarEventCanvas(
                "user_64496",
                "zoom",
                "Hej",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                "Testpass");

        //create resttemplate
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        //create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer 3755~U8jxZ5WEIp4O9EDLFduET8hRvxlkMmTfY2wGvDQp6cBKqjLrIdGZW3GomgrvouKJ");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("calendar_event[context_code]", "user_64496");
        map.add("calendar_event[title]", "Hej");
        map.add("calendar_event[start_at]", LocalDateTime.now().toString());
        map.add("calendar_event[end_at]", LocalDateTime.now().plusHours(1).toString());
        map.add("calendar_event[location_name]", "Test");

        map.forEach((k, v)-> System.out.println(k+v));

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(
                "https://ltu.instructure.com/api/v1/calendar_events",
                request,
                String.class);

        return response;
    }
}
