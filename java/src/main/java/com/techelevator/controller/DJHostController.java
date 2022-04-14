package com.techelevator.controller;

import com.techelevator.dao.DjHostDao;
import com.techelevator.model.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
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
    @RequestMapping(value="/djs", method= RequestMethod.GET)
    public List<User> getListOfDjs() {
        return djHostDao.listOfDjs();
    }

    //return list of hosts
    //currently not authorized
    @RequestMapping(value="/hosts", method = RequestMethod.GET)
    public List<User> getListOfHosts(){
        return djHostDao.listOfHosts();
    }

    //todo -> as an authorized DJ, I need to be able to assign a host to an event
}
