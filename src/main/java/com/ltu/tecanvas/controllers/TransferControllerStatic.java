package com.ltu.tecanvas.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltu.tecanvas.model.CalendarEventCanvas;
import com.ltu.tecanvas.model.CalendarEventTE;
import com.ltu.tecanvas.model.CourseSchedule;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class TransferControllerStatic {

    @GetMapping(value = "/transfer/static", produces = "application/json")
    public ResponseEntity<String> transfer() {

        //Retrieve the course schedule(static URL)
        CourseSchedule cs = getStaticCourseSchedule();


        //Creating Canvas event
        CalendarEventCanvas cec = new CalendarEventCanvas(
                "user_64496",
                "zoom",
                "Hej",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1));

        //create rest template
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

    private CourseSchedule getStaticCourseSchedule(){

        URL url = null;
        Map<String,Object> map = null;

        try{
            url = new URL("https://cloud.timeedit.net/ltu/web/schedule1/ri167XQQ565Z50Qv8Q043gZ6y5Y120276Y75Y.json");
        }
        catch(MalformedURLException e) {
            System.out.println(e);
        }

        //mapping domain using Jackson ObjectMapper
        CourseSchedule courseSchedule = null;
        ObjectMapper domainMapper = new ObjectMapper();
        domainMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        try {
            courseSchedule = domainMapper.readValue(url, CourseSchedule.class);
            System.out.println();
            System.out.println();
            System.out.println("Domain map:");
            System.out.println("Printing event ID:s");
            for (CalendarEventTE ce : courseSchedule.getReservations()){
                System.out.println(ce.getId() + ", start time: " + ce.getStarttime() + ", end time: " + ce.getEndtime());
            }
        }

        catch(IOException e) {
            System.out.println(e);
        }
        return courseSchedule;
    }

    private LocalDateTime convertTime(LocalDate date, String time){
        return LocalDateTime.of(date, LocalTime.parse(time));
    }

}
