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



    @RequestMapping(value="", method= RequestMethod.GET)
    public List<Song> getListOfSongs(@PathVariable User id) {
        return songDao.djSongList(id);
    }
    //todo -> how do we authorize the id to be role of dj? or is that a filter

}
