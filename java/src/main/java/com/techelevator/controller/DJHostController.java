package com.techelevator.controller;

import com.techelevator.dao.DjHostDao;
import com.techelevator.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class DJHostController {

    /*
      This Controller is for returning objects that are users, such as DJ or Host
    */


    private DjHostDao djHostDao;

    public DJHostController(DjHostDao djHostDao){
        this.djHostDao = djHostDao;
    }


    //return list of djs
    //currently not authorized
    @PreAuthorize("permitAll")
    @RequestMapping(value="/djs", method= RequestMethod.GET)
    public List<User> getListOfDjs() {
        return djHostDao.listOfDjs();
    }

    //return list of hosts
    //currently not authorized
    @PreAuthorize("hasRole('DJ')")
    @RequestMapping(value="/hosts", method = RequestMethod.GET)
    public List<User> getListOfHosts(){
        return djHostDao.listOfHosts();
    }

    //todo -> as an authorized DJ, I need to be able to assign a host to an event
    //@PreAuthorize("hasRole('ROLE_DJ')")
    //@ResponseStatus(HttpStatus.CREATED)
    //@RequestMapping(value="", method = RequestMethod.POST)
        //Parameters: user_id(auth dj), user_id(host), event_id
        //Return: EventHost object
        //method location: EventHostDao/JdbcEventHost
        //additional concerns:
}
