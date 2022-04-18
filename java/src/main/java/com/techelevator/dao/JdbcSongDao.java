package com.techelevator.dao;

import com.techelevator.model.DjLibrary;
import com.techelevator.model.EventNotFoundException;
import com.techelevator.model.Song;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    public DjLibrary addSong(Long id, Long userId) {
        DjLibrary addedSong = new DjLibrary();

        String sql = "INSERT INTO dj_library (user_id, song_id) " +
                "VALUES (?,?);";

        jdbcTemplate.update(sql, userId, id);


        addedSong.setUserId(userId);
        addedSong.setSongId(id);

        return addedSong;

        //error message I am getting:
        //"message": "PreparedStatementCallback; SQL [INSERT INTO dj_library (user_id, song_id) VALUES (?,?);];
        // ERROR: duplicate key value violates unique constraint \"pk_dj_library\"\n
        // Detail: Key (user_id, song_id)=(3, 22) already exists.; nested exception is org.postgresql.util.PSQLException:
        // ERROR: duplicate key value violates unique constraint \"pk_dj_library\"\n
        // Detail: Key (user_id, song_id)=(3, 22) already exists
    }

    //this needs to have that weird batch update stuff
    @Override
    public List<Song> addSongsFromGenreToDjLibrary(String name) { //add principle principle

        String sql = "SELECT song_id \n" + //store in list of int
                "FROM song\n" +
                "NATURAL JOIN song_genre\n" +
                "WHERE genre_name = ?";
        //this will get all songs of a genre

        //second query to get all dj songs //store second list of int

        //remove dups

        //empty list
        //while results.next()
            //list.add(results.getLong("song_id"))

        //insert is the prepared statement:
        //
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                //
                //user_id
                //song_id
            }

            @Override
            public int getBatchSize() {
                return 0; //return list/array of new id's .size/.length
            }
        });


        return null; //return new dj library
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