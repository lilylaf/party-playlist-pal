package com.techelevator.dao;

import com.techelevator.model.Song;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSongDao implements SongDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcSongDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    //methods here
    //select * from song where dj_id =?
    @Override
    public List<Song> djSongList(User id) {
        List<Song> songs = new ArrayList<>();

        String sql = "";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()) {
            songs.add(mapRowToSong(results));
        }
        return songs;
    }


    private Song mapRowToSong(SqlRowSet rowSet){
        Song s = new Song();

        s.setId(rowSet.getLong("song_id"));
        s.setArtistName(rowSet.getLong("artist_name"));
        s.setName(rowSet.getString("song_name"));
        s.setFeatured(rowSet.getString("featured_artist"));
        //todo -> check with Lindsey to verify column name for featured
        return s;
}


}