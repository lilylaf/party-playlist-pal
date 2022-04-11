package com.techelevator.controller;


import com.techelevator.dao.EventDao;
import com.techelevator.model.Event;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class EventController {

    private EventDao eventDao;

    public EventController(EventDao eventDao){
        this.eventDao = eventDao;
    }


    @RequestMapping(value="/events", method = RequestMethod.GET )
    public List<Event> getAllEvents(){
        return eventDao.listOfEvents();
    }


}
