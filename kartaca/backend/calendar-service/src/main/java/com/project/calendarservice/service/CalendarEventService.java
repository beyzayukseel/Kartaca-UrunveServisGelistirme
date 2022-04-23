package com.project.calendarservice.service;

import com.project.calendarservice.model.CalendarEvent;
import com.project.calendarservice.repository.CalendarEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CalendarEventService {

    private final CalendarEventRepository calendarEventRepository;

    public void deleteEvent(Long eventId) {
        calendarEventRepository.deleteById(eventId);
    }

    public CalendarEvent addEvent(CalendarEvent calendarEvent) {
        return calendarEventRepository.save(calendarEvent);
    }

    public List<CalendarEvent> getAllEventsInCalendar(Long calendarId) {
        return calendarEventRepository.findByCalendarId(calendarId);
    }
}
