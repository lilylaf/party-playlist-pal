package com.techelevator.dao;

import com.techelevator.model.Song;
import com.techelevator.model.User;

import java.util.List;

public interface SongDao {

    List<Song> djSongList(User id);
}

