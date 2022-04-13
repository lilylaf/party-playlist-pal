package com.techelevator.controller;

import com.techelevator.dao.SongDao;
import com.techelevator.model.Song;
import com.techelevator.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SongController {

    private SongDao songDao;

    public SongController(SongDao songDao) {
        this.songDao = songDao;
    }

    // list of djs
    @RequestMapping(value="/djs", method= RequestMethod.GET)
    public List<User> getListOfDjs() {
        return songDao.listOfDjs();
    }
    //list of songs per dj
    @RequestMapping(value="/dj/{id}/songs", method= RequestMethod.GET)
    public List<Song> getListOfSongs(@PathVariable Long id) {
        return songDao.djSongList(id);
    }

    //as an unauthorized user, I want to display a list of songs for an event
    @RequestMapping(value="event/{id}/songs", method = RequestMethod.GET)
    public List<Song> getEventPlaylist(@PathVariable Long id) {
        return songDao.eventPlaylist(id);
    }
}
