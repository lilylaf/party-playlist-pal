package com.techelevator.controller;


import com.techelevator.dao.EventDao;
import com.techelevator.model.Event;
import com.techelevator.model.EventNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class EventController {

    /*
        This Controller is for returning Event objects such as events or event details
     */

    private EventDao eventDao;

    public EventController(EventDao eventDao){
        this.eventDao = eventDao;
    }

    //todo -> do we want to pull DJ username in this as well?
    //as an unauthorized user, I want to view a list of events
    @RequestMapping(value="/events", method = RequestMethod.GET)
    public List<Event> getAllEvents(){
        return eventDao.listOfEvents();
    }

    //as an unauthorized user, I want to get an event by ID for the Guest Event Page
    @RequestMapping(value="/event/{id}", method = RequestMethod.GET)
    public Event selectedEvent(@PathVariable Long id) throws EventNotFoundException {
        return eventDao.getEventById(id); //insert method here
    }

      //todo -> as an authorized DJ, I need to be able to create an event
//    @RequestMapping(value = "", method= RequestMethod.POST)
//    public Boolean createEvent(){
//        return null;
//    }

      //todo -> as an authorized DJ, I need to be able to delete an event
//    @RequestMapping(value = "", method= RequestMethod.DELETE)
//    public Event deleteEvent(@PathVariable Long id){ //pass in event id
//        return null;
//    }

    //todo -> as an authorized Host OR an authorized DJ, I need to be able to update event details



}
