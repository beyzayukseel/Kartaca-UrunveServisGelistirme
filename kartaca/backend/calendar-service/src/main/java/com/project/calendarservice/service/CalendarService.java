package com.project.calendarservice.service;

import com.project.calendarservice.model.Calendar;
import com.project.calendarservice.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class CalendarService {

    private final CalendarRepository calendarRepository;
    

    public Calendar addCalendar(Calendar calendar) {
       return calendarRepository.save(calendar);
    }

    public void deleteCalendar(Long calendarId) {
        calendarRepository.deleteById(calendarId);
    }

    public Optional<Calendar> getCalendar(Long calendarId) {
        return calendarRepository.findById(calendarId);
    }
}
