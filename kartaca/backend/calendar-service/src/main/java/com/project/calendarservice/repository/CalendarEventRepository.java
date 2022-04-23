package com.project.calendarservice.repository;

import com.project.calendarservice.model.CalendarEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarEventRepository extends JpaRepository<CalendarEvent,Long> {

    List<CalendarEvent> findByCalendarId(Long calendarId);
}
