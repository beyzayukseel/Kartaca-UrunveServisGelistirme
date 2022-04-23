package com.project.calendarservice.controller;

import com.project.calendarservice.model.CalendarEvent;
import com.project.calendarservice.service.CalendarEventService;
import com.project.calendarservice.service.CalendarService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping( "/calendars/events")
public class CalendarEventController {

    private final CalendarEventService calendarEventService;

    public CalendarEventController(CalendarEventService calendarEventService) {
        this.calendarEventService = calendarEventService;
    }

    @PostMapping(  "/add")
    public ResponseEntity<CalendarEvent> addEvent(@RequestBody CalendarEvent calendarEvent){
        return ResponseEntity.ok(calendarEventService.addEvent(calendarEvent));
    }

    @DeleteMapping( "/delete/{calendarEventId}")
    public void deleteEvent(@PathVariable Long calendarEventId){
        calendarEventService.deleteEvent(calendarEventId);
    }

   // public ResponseEntity<CalendarEvent> updateEvent(CalendarEvent calendarEvent){
   //     return ResponseEntity.ok(calendarEventService.updateEvent());
   // }

    @GetMapping( "/events/{calendarId}")
    public ResponseEntity<List<CalendarEvent>> getAllEventsInCalendar(@Valid @PathVariable Long calendarId)
            throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(calendarEventService.getAllEventsInCalendar(calendarId));
    }

}
