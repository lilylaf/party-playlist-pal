package com.techelevator.dao;

import com.techelevator.model.User;

import java.util.List;

public interface DjHostDao {

    List<User> listOfDjs();
    List<User> listOfHosts();

}
