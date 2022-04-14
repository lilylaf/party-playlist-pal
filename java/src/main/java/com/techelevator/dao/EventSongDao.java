package com.techelevator.dao;

import com.techelevator.model.Song;

public interface EventSongDao {

    //add submit dj song to event playlist
    Song submitSong(Long eventId, Long songId);
}
