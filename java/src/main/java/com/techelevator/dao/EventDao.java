package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.EventNotFoundException;

import java.util.List;

public interface EventDao {

    List<Event> listOfEvents();
    Event getEventById(Long id) throws EventNotFoundException;
}
