package com.techelevator.dao;

import com.techelevator.model.Song;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcSongDao implements SongDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcSongDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


//methods here


private Song mapRowToSong(SqlRowSet rowset){
    Song s = new Song();

    s.setSongId(rowset.getLong("song_id"));
    s.setArtistId(rowset.getLong("artist_id"));
    s.setSongName(rowset.getString("songName"));
    //todo --> add featured_artist
    return s;
}

}