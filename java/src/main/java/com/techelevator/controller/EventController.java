package com.techelevator.controller;


import com.techelevator.dao.EventDao;
import com.techelevator.dao.EventHostDao;
import com.techelevator.dao.JdbcEventHostDao;
import com.techelevator.model.Event;
import com.techelevator.model.EventHost;
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
    private EventHostDao eventHostDao;

    public EventController(EventDao eventDao){
        this.eventDao = eventDao;
    }

    //as an unauthorized guest, I need to view a list of events
    @PreAuthorize("permitAll")
    @RequestMapping(value="/events", method = RequestMethod.GET)
    public List<Event> getAllEvents(){
        return eventDao.listOfEvents();
    }

    //as an authorized dj, I need to view a list of my events
    @PreAuthorize("hasRole('DJ')")
    @RequestMapping(value="/dj/{id}/events", method = RequestMethod.GET)
    public List<Event> getEventsByUserId(@PathVariable Long id){
        return eventDao.eventsByDjId(id);
    }

    //as an unauthorized guest, I need to view a specific event
    @PreAuthorize("permitAll")
    @RequestMapping(value="/events/{id}", method = RequestMethod.GET)
    public Event selectedEvent(@PathVariable Long id) throws EventNotFoundException {
        return eventDao.getEventById(id); //insert method here
    }

    //Get events by hostID
    @PreAuthorize("permitAll")
    @RequestMapping(value="/events/host/{id}", method = RequestMethod.GET)
    public List<Event> getEventsByHostId(@PathVariable Long id){
        return eventDao.eventsByHostId(id);
    }

    //as an authorized DJ, I need to be able to create an event
    @PreAuthorize("hasRole('DJ')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/events", method= RequestMethod.POST)
    public Event createEvent(@Valid @RequestBody Event event) throws EventNotFoundException {
        return eventDao.create(event);
    }


    //as an authorized DJ, I need to be able to delete an event hasRole('DJ')
    @PreAuthorize("permitAll")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/events/{id}", method= RequestMethod.DELETE)
    public void deleteEvent(@PathVariable Long id) {
         eventDao.deleteEvent(id);
    }

    @PreAuthorize("hasAnyRole('DJ','HOST')")
    @RequestMapping(value="/events/{id}", method = RequestMethod.PUT)
    public Event updateEvent(@RequestBody Event event, @PathVariable Long id) throws EventNotFoundException{
        return eventDao.updateEvent(event, id);
    }

    
    @PreAuthorize("hasRole('DJ')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value="/events/{id}/hosts", method = RequestMethod.POST)
    public Event addHostsToEvent(@PathVariable Long id, @Valid @RequestBody List<Long> hosts) throws EventNotFoundException {
        return eventDao.addHost(id, hosts);
    }


    //DONE BY DES
    @PreAuthorize("hasRole('DJ')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value="/events/{eventId}", method = RequestMethod.DELETE)
    public void deleteHostFromEvent(@PathVariable("eventId") Long eventId, @RequestBody Long id) {
        eventHostDao.deleteHostFromEvent(eventId, id);


    }

}











