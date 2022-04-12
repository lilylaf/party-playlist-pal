package com.techelevator.controller;

import com.techelevator.dao.SongDao;
import com.techelevator.model.Song;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SongController {

    private SongDao songDao;

    public SongController(SongDao songDao) {
        this.songDao = songDao;
    }

    //todo -> as an unauthorized user, I want to see a list of songs for each dj
        //get user_id (role of dj)
        //get List<Song> djSongList
        //how do we want to go about creating this??

}
