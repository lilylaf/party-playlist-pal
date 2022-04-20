package com.techelevator.dao;

import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.sql.RowSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
@CrossOrigin
public class JdbcSongDao implements SongDao {

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

//    @Override
//    public Song submitSong(Long eventId, Long songId) {
//        return null;
//    }

//    @Override
//    public Song submitSong(Long id) {
//
//
//        return null;
//    }

    @Override
    public void deleteSongFromLibrary(Long songId, Long userId) {
        String sql = "DELETE FROM dj_library " +
                "WHERE user_id = ? AND song_id = ?;";

        int numRows = jdbcTemplate.update(sql, userId, songId);
    }

    @Override
    public Song addSong(Long id, Long userId) {
        Song addedSong = new Song();

        String sql = "INSERT INTO dj_library (user_id, song_id) " +
                "VALUES (?,?);";

        try {
            jdbcTemplate.update(sql, userId, id);
            addedSong.setId(id);
        } catch (Exception e) {
            System.out.println("Do Not Add Repeat Songs");
        }

        return addedSong;

    }

    //this needs to have that weird batch update stuff
    @Override
    public List<Song> addSongsFromGenreToDjLibrary(String name, Long id) { //add principle principle

        String sqlA = "SELECT song_id \n" +
                "FROM song\n" +
                "NATURAL JOIN song_genre\n" +
                "WHERE genre_name = ?";

        //store first sql statement in a list of ints
        List<Long> songsInGenre = new ArrayList<>();

        SqlRowSet resultsA = jdbcTemplate.queryForRowSet(sqlA, name);
        while (resultsA.next()) {
            songsInGenre.add(resultsA.getLong("song_id"));
        }

        String sqlB = "SELECT song_id \n" +
                "FROM dj_library \n" +
                "WHERE user_id = ?;";

        //second query to get all dj songs //store second list of int
        List<Long> existingSongsInLibrary = new ArrayList<>();

        SqlRowSet resultsB = jdbcTemplate.queryForRowSet(sqlB, id);
        while (resultsB.next()) {
            existingSongsInLibrary.add(resultsB.getLong("song_id"));
        }

        HashSet<Long> existingSongs = new HashSet<>(existingSongsInLibrary);
        List<Long> songsToAdd = new ArrayList<>();

        for (Long song: songsInGenre) {
            if (!existingSongs.contains(song)){
                songsToAdd.add(song);
            }
        }


        String sql = "INSERT INTO dj_library(user_id, song_id)\n" +
                "VALUES (?, ?);";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Long songId = songsToAdd.get(i);
                ps.setLong(1, id);
                ps.setLong(2, songId);
            }

            @Override
            public int getBatchSize() {
                return songsToAdd.size();
            }
        });

        return djSongList(id);
    }


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