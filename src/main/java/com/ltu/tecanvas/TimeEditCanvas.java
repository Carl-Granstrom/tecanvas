package com.ltu.tecanvas;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltu.tecanvas.model.CalendarEvent;
import com.ltu.tecanvas.model.CourseSchedule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class TimeEditCanvas {

    public static void main(String[] args){
        URL url = null;
        Map<String,Object> map = null;

        try{
            url = new URL("https://cloud.timeedit.net/ltu/web/schedule1/ri167XQQ565Z50Qv8Q043gZ6y5Y120276Y75Y.json");
        }
        catch(MalformedURLException e) {
            System.out.println(e);
        }

        //mapping domain using Jackson ObjectMapper
        ObjectMapper domainMapper = new ObjectMapper();
        domainMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        try {
            CourseSchedule courseSchedule = domainMapper.readValue(url, CourseSchedule.class);
            System.out.println();
            System.out.println();
            System.out.println("Domain map:");
            System.out.println("Printing event ID:s");
            for (CalendarEvent ce : courseSchedule.getReservations()){
                System.out.println(ce.getId());
            }


        }

        catch(IOException e) {
            System.out.println(e);
        }
    }

    //posting to Canvas


}
