package com.techelevator.controller;

import com.techelevator.dao.DjLibraryDao;
import com.techelevator.dao.EventSongDao;
import com.techelevator.dao.GenreDao;
import com.techelevator.dao.SongDao;
import com.techelevator.model.EventHost;
import com.techelevator.model.Genre;
import com.techelevator.model.Song;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
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



    //as an unauthorized guest, I need to see a list of a DJ's song library
    @PreAuthorize("permitAll")
    @RequestMapping(value="/dj/{id}/songs", method= RequestMethod.GET)
    public List<Song> getListOfSongs(@PathVariable Long id) {
        return songDao.djSongList(id);
    }

    //as an unauthorized guest, I need to view the songs in the event playlist
    @PreAuthorize("permitAll")
    @RequestMapping(value="event/{id}/songs", method = RequestMethod.GET)
    public List<Song> getEventPlaylist(@PathVariable Long id) {
        return songDao.eventPlaylist(id);
    }

    //as an unauthorized guest, I need to submit a song from the dj_library to event_song
//    @PreAuthorize("permitAll")
//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(value="", method = RequestMethod.POST)
//    public Song submitToEventPlaylist(@PathVariable Long eventId, Long songId){
//        return songDao //insert method here ;
//    }




    //todo -> ******************************************************* THIS NEEDS TO BE AUTHORIZED FOR A DJ
    //as an authorized DJ, I need to see a list of my current genres
//    @PreAuthorize("hasRole('ROLE_DJ'),('ROLE_HOST')") //not sure if this syntax is correct for 2 roles
//    @RequestMapping(value="dj/{id}/genres", method = RequestMethod.GET)
//    public List<Genre> getGenresByDj(@PathVariable Long id) { //do we need Principal principal in here?
//        return genreDao.listOfDjLibraryGenres(id);
//    }

    //as an unauthorized guest, I want to be able to submit a song from dj_library to event playlist
//    @RequestMapping(value="", method = RequestMethod.POST)
//    public Song submitSongToEventPlaylist(Long songId, Long eventId){
//        return eventSongDao.submitSong(songId, eventId);
//    }


    //todo -> as an authorized DJ, I can delete all songs of a genre from my dj-Library
    //@PreAuthorize("hasRole('ROLE_DJ')")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    //@RequestMapping(value="", method= RequestMethod.DELETE)
        //Parameters: user_id, and genre_name
        //Return: void, will not return anything
        //method location: DjLibraryDao/JdbcDjLibraryDao
        //additional concerns: website may need to refresh, or call the get again after this is done to update dj_library


    //todo -> as an authorized DJ, I need to be able to add all songs of a genre to dj_library
    //@PreAuthorize("hasRole('ROLE_DJ')")
    //@ResponseStatus(HttpStatus.CREATED)
    //@RequestMapping(value="", method = RequestMethod.POST)
        //Parameters: user_id, genre_name
        //Return: List<Song> addedSongsFromGenre
        //method location: SongDao/JdbcSongDao
        //additional concerns: do we want to return a list of songs, or return the entire dj_library to refresh the page?


    //todo -> as an authorized DJ, I can delete a song from my dj-Library
    //@PreAuthorize("hasRole('ROLE_DJ')")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    //@RequestMapping(value="", method= RequestMethod.DELETE)
        //Parameters: user_id, song_id
        //Return: void, will not return anything
        //method location: SongDao/JdbcSongDao
        //additional concerns:


    //todo -> as an authorized DJ, I can add a song to my dj-Library
    //@PreAuthorize("hasRole('ROLE_DJ')")
    //@ResponseStatus(HttpStatus.CREATED)?
    //@RequestMapping(value="", method = RequestMethod.POST)
        //Parameters: user_id, song_id
        //Return: Song s
        //method location: SongDao/JdbcSongDao
        //additional concerns: not sure if this one is mapped out correctly



}
