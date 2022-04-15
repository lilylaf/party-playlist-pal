package com.techelevator.controller;


import com.techelevator.dao.EventDao;
import com.techelevator.model.Event;
import com.techelevator.model.EventNotFoundException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class EventController {

    /*
        This Controller is for returning Event objects such as events or event details
     */

    private EventDao eventDao;

    public EventController(EventDao eventDao){
        this.eventDao = eventDao;
    }


    //as an unauthorized guest, I need to view a list of events
    @PreAuthorize("permitAll")
    @RequestMapping(value="/events", method = RequestMethod.GET) //todo -> do we want to pull DJ username in this as well?
    public List<Event> getAllEvents(){
        return eventDao.listOfEvents();
    }

    //as an unauthorized guest, I need to view a specific event
    @PreAuthorize("permitAll")
    @RequestMapping(value="/event/{id}", method = RequestMethod.GET)
    public Event selectedEvent(@PathVariable Long id) throws EventNotFoundException {
        return eventDao.getEventById(id); //insert method here
    }

    //as an authorized DJ, I need to be able to create an event
    @PreAuthorize("hasRole('DJ')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/event", method= RequestMethod.POST)
    public Event createEvent(@Valid @RequestBody Event event) throws EventNotFoundException {
        return eventDao.create(event);
    }


    //as an authorized DJ, I need to be able to delete an event
    @PreAuthorize("hasRole('ROLE_DJ')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "event/{id}", method= RequestMethod.DELETE)
    public ResponseEntity deleteEvent(@PathVariable Long id) throws EventNotFoundException {
         return eventDao.deleteEvent(id);
    }





    //todo -> as an authorized Host OR an authorized DJ, I need to be able to update event details
    //@PreAuthorize("hasRole('ROLE_DJ'),('ROLE_HOST')")
    //@RequestMapping(value="", method = RequestMethod.POST)
        //Parameters: user_id, event_id
        //Return: updated event object
        //method location: EventDao, JdbcEventDao
        //additional concerns:


}
