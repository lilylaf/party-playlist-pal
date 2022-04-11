package com.techelevator.controller;


import com.techelevator.dao.EventDao;
import com.techelevator.model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class EventController {

    private EventDao eventDao;

    public EventController(EventDao eventDao){
        this.eventDao = eventDao;
    }

    //as an unauthorized user, I want to view a list of events
    @RequestMapping(value="/events", method = RequestMethod.GET)
    public List<Event> getAllEvents(){
        return eventDao.listOfEvents();
    }

    //as an unauthorized user, I want to get an event by ID for the Guest Event Page
    @RequestMapping(value="/events/{eventId}", method = RequestMethod.GET)
    public Event selectedEvent(@PathVariable Long eventId){
        return eventDao.getEventById(eventId); //insert method here
    }
}
