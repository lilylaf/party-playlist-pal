package com.techelevator.controller;

import com.techelevator.dao.DjLibraryDao;
import com.techelevator.dao.EventSongDao;
import com.techelevator.dao.GenreDao;
import com.techelevator.dao.SongDao;
import com.techelevator.model.Genre;
import com.techelevator.model.Song;
import com.techelevator.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SongController {

    /*
        This Controller is for returning an object that is a song, including a playlist or a dj library
        Genres are directly related to songs/playlists, so genre endpoints will go in here
        Dj library end points will go here as well
     */

    private SongDao songDao;
    private GenreDao genreDao;
    private EventSongDao eventSongDao;
    private DjLibraryDao djLibraryDao;

    public SongController(SongDao songDao, GenreDao genreDao, EventSongDao eventSongDao, DjLibraryDao djLibraryDao) {
        this.songDao = songDao;
        this.genreDao = genreDao;
        this.eventSongDao = eventSongDao;
        this.djLibraryDao = djLibraryDao;
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

    //THIS NEEDS TO BE AUTHORIZED FOR A DJ
    //as an authorized DJ, I need to see a list of my current genres
    @RequestMapping(value="dj/{id}/genres", method = RequestMethod.GET)
    public List<Genre> getGenresByDj(@PathVariable Long id) {
        return genreDao.listOfDjLibraryGenres(id);
    }

    //as an unauthroized user, I want to be able to submit a song from dj_library to event playlist
    @RequestMapping(value="", method = RequestMethod.POST)
    public Song submitSongToEventPlaylist(Long songId, Long eventId){
        return eventSongDao.submitSong(songId, eventId);
    }



    //todo -> as an authorized DJ, I can delete all songs of a genre from my dj-Library

    //todo -> as an authorized DJ, I need to be able to add all songs of a genre to dj_library



    //todo -> as an authorized DJ, I can delete a song from my dj-Library

    //todo -> as an authorized DJ, I can add a song to my dj-Library
}
