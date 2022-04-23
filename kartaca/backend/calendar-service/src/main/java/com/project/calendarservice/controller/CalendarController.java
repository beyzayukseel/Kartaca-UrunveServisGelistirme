package com.project.calendarservice.controller;

import com.project.calendarservice.model.Calendar;
import com.project.calendarservice.service.CalendarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping( "/calendars")
public class CalendarController{

    public final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping( "/addition")
    public ResponseEntity<Calendar> addCalendar(@RequestBody Calendar calendar){
        return ResponseEntity.ok(calendarService.addCalendar(calendar));
    }

    @DeleteMapping( "/deletion/{calendarId}")
    public void deleteCalendar(@PathVariable Long calendarId){
         calendarService.deleteCalendar(calendarId);
    }

    @GetMapping( "/{calendarId}")
    public ResponseEntity<Optional<Calendar>> getCalendar(@PathVariable Long calendarId){
        return ResponseEntity.ok(calendarService.getCalendar(calendarId));
    }
}
