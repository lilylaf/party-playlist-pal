package com.techelevator.dao;

import com.techelevator.model.DjLibrary;
import com.techelevator.model.Song;
import com.techelevator.model.User;

import java.util.List;

public interface SongDao {

    List<Song> djSongList(Long id);
    List<Song> eventPlaylist(Long id);
    Song submitSong(Long eventId, Long songId);
    void deleteSongFromLibrary(Long userId, Long songId);
//    DjLibrary addSong(DjLibrary song);
}

