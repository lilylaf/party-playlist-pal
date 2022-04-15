package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.EventNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface EventDao {

    List<Event> listOfEvents();
    Event getEventById(Long id) throws EventNotFoundException;
    Event create(Event event) throws EventNotFoundException;
    ResponseEntity deleteEvent(Long id) throws EventNotFoundException;
}
