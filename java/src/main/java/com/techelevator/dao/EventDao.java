package com.techelevator.dao;

import com.techelevator.model.Event;

import java.util.List;

public interface EventDao {

    List<Event> listOfEvents();
    Event getEventById(Long id);
}
