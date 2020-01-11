package com.ltu.tecanvas.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltu.tecanvas.model.CalendarEventCanvas;
import com.ltu.tecanvas.model.CalendarEventTE;
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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class TransferController {

    //todo Possibly change to return a list of response entities instead of just the final one
    @GetMapping(value = "/transfer", produces = "application/json")
    public ResponseEntity<String> transfer() {

        //Retrieve the course schedule(static URL)
        CourseSchedule cs = getStaticCourseSchedule();

        //Map CourseSchedule to a list of CanvasEvents
        List<CalendarEventCanvas> eventList = new ArrayList<>();

        int lektionNummer = 0;
        int seminarieNummer = 0;
        int forelasningsNummer = 0;
        int presentationNummer = 0;
        for (CalendarEventTE event : cs.getReservations()){
            //concatenate date and time to datetime for start time
            LocalDate startDate = LocalDate.parse(event.getStartdate());
            String startTimeString = event.getStarttime();
            LocalDateTime startDateTime = convertTime(startDate, startTimeString);

            //concatenate date and time for end time
            LocalDate endDate = LocalDate.parse(event.getEnddate());
            String endTimeString = event.getEndtime();
            LocalDateTime endDateTime = convertTime(endDate, endTimeString);

            String titleStart = event.getColumns().get(3);
            if (event.getColumns().get(3).contains("Lekt")){
                lektionNummer++;
                titleStart += " " + lektionNummer;
            } else if (event.getColumns().get(3).contains("Sem")){
                seminarieNummer++;
                titleStart += " " + seminarieNummer;
            } else if (event.getColumns().get(3).contains("För")){
                forelasningsNummer++;
                titleStart += " " + forelasningsNummer;
            } else if (event.getColumns().get(3).contains("Pre")){
                presentationNummer++;
                titleStart += " " + presentationNummer;
            }
            //create the rest of the CalendarEventCanvas-object
            CalendarEventCanvas cec = new CalendarEventCanvas("user_64496", event.getColumns().get(1), titleStart + " - " + event.getColumns().get(5), startDateTime, endDateTime);

            //add this event to the list of events
            eventList.add(cec);
        }

        //loop through all events in the list and send them to canvas api via rest template
        ResponseEntity<String> response = null;

        for (CalendarEventCanvas cec : eventList){
            //create rest template
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            //create headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.set(HttpHeaders.AUTHORIZATION, "Bearer 3755~U8jxZ5WEIp4O9EDLFduET8hRvxlkMmTfY2wGvDQp6cBKqjLrIdGZW3GomgrvouKJ");

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("calendar_event[context_code]", cec.getContext_code());
            map.add("calendar_event[location_name]", cec.getLocation_name());
            map.add("calendar_event[title]", cec.getTitle());
            map.add("calendar_event[start_at]", cec.getStart_at().toString());
            map.add("calendar_event[end_at]", cec.getEnd_at().toString());

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
            response = restTemplate.postForEntity(
                    "https://ltu.instructure.com/api/v1/calendar_events",
                    request,
                    String.class);
        }

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
