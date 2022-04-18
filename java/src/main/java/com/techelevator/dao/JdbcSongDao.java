package com.techelevator.dao;

import com.techelevator.model.DjLibrary;
import com.techelevator.model.EventNotFoundException;
import com.techelevator.model.Song;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@Component
@CrossOrigin
public class JdbcSongDao implements SongDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcSongDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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

    @Override
    public List<Song> eventPlaylist(Long id) {
        List<Song> eventPlaylistSongs = new ArrayList<>();

        String sql = "SELECT song_id, song_name, artist_name, featured_artist\n" +
                "FROM song\n" +
                "NATURAL JOIN event_song\n" +
                "WHERE event_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()) {
            eventPlaylistSongs.add(mapRowToSong(results));
        }

        return eventPlaylistSongs;
    }

    @Override
    public Song submitSong(Long eventId, Long songId) {


        return null;
    }

    @Override
    public void deleteSongFromLibrary(Long songId, Long userId) {
        String sql = "DELETE FROM dj_library " +
                "WHERE user_id = ? AND song_id = ?;";

        int numRows = jdbcTemplate.update(sql, userId, songId);
    }

//    @Override
//    public DjLibrary addSong(Long userId, Long songId) {
//        DjLibrary newSong = null;
//
//        String sql = "String sql = INSERT INTO dj_library (user_id, song_id) " +
//                "VALUES (?,?);";
//
//        SqlRowSet results = jdbcTemplate.queryForObject(sql, userId, songId);
//
//        return newSong;
//    }


    private Song mapRowToSong(SqlRowSet rowSet){
        Song s = new Song();

        s.setId(rowSet.getLong("song_id"));
        s.setName(rowSet.getString("song_name"));
        s.setArtistName(rowSet.getString("artist_name"));
        s.setFeatured(rowSet.getString("featured_artist"));

        return s;
    }

    private DjLibrary mapRowToSongEvent(SqlRowSet rowSet){
        DjLibrary djSong = new DjLibrary();

        djSong.setUserId(rowSet.getLong("user_id"));
        djSong.setSongId(rowSet.getLong("song_id"));

        return djSong;
    }

}