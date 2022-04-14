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


    //as an unauthorized guest, I need to view a list of events
    @RequestMapping(value="/events", method = RequestMethod.GET) //todo -> do we want to pull DJ username in this as well?
    public List<Event> getAllEvents(){
        return eventDao.listOfEvents();
    }

    //as an unauthorized guest, I need to view a specific event
    @RequestMapping(value="/event/{id}", method = RequestMethod.GET)
    public Event selectedEvent(@PathVariable Long id) throws EventNotFoundException {
        return eventDao.getEventById(id); //insert method here
    }

    //todo -> as an authorized DJ, I need to be able to create an event
    //@PreAuthorize("hasRole('ROLE_DJ')")
    //@ResponseStatus(HttpStatus.CREATED)
    //@RequestMapping(value = "", method= RequestMethod.POST)
        //Parameters: user_id,
        //Return: Event Object
        //method location: EventDao/JdbcEventDao
        //additional concerns:


    //todo -> as an authorized DJ, I need to be able to delete an event
    //@PreAuthorize("hasRole('ROLE_DJ')")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    //@RequestMapping(value = "", method= RequestMethod.DELETE)
        //Parameters: user_id, event_id
        //Return: void, no return
        //method location: EventDao/JdbcEventDao
        //additional concerns:


    //todo -> as an authorized Host OR an authorized DJ, I need to be able to update event details
    //@PreAuthorize("hasRole('ROLE_DJ'),('ROLE_HOST')")
    //@RequestMapping(value="", method = RequestMethod.POST)
        //Parameters: user_id, event_id
        //Return: updated event object
        //method location: EventDao, JdbcEventDao
        //additional concerns:


}
