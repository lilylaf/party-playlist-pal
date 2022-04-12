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


    @Override
    public List<User> listOfDjs() {
        List<User> djs = new ArrayList<>();

        String sql = "SELECT user_id, username\n" +
                "FROM users\n" +
                "WHERE role = 'ROLE_DJ'\n" +
                "ORDER BY user_id ASC;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            djs.add(mapRowToUser(results));
        }
        return djs;
    }

    @Override
    public List<Song> djSongList(Long id) {
        List<Song> songs = new ArrayList<>();

        String sql = "SELECT song_id, song_name, artist_name, featured_artist\n" +
                "FROM song\n" +
                "NATURAL JOIN dj_library \n" +
                "NATURAL JOIN users \n" +
                "WHERE dj_library.user_id = ?" +
                "ORDER BY song_id ASC;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()) {
            songs.add(mapRowToSong(results));
        }
        return songs;
    }




    private Song mapRowToSong(SqlRowSet rowSet){
        Song s = new Song();

        s.setId(rowSet.getLong("song_id"));
        s.setName(rowSet.getString("song_name"));
        s.setArtistName(rowSet.getString("artist_name"));
        s.setFeatured(rowSet.getString("featured_artist"));

        return s;
    }
    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        return user;
    }
}