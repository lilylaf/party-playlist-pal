package com.techelevator.dao;


import com.techelevator.model.Genre;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@Component
@CrossOrigin
public class JdbcGenreDao implements GenreDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcGenreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public List<Genre> listOfDjLibraryGenres(Long id) {
        List<Genre> genreList = new ArrayList<>();

        String sql = "SELECT DISTINCT genre_name\n" +
                "FROM song_genre\n" +
                "NATURAL JOIN song\n" +
                "NATURAL JOIN dj_library\n" +
                "WHERE user_id = ? AND song_genre.song_id = dj_library.song_id;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()) {
            genreList.add(mapRowToGenre(results));
        }

        return genreList;
    }

    @Override
    public List<Genre> eventGenres(Long id) {
        List<Genre> genreList = new ArrayList<>();

        String sql = "SELECT DISTINCT genre_name " +
                "FROM event_genre " +
                "WHERE event_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()) {
            genreList.add(mapRowToGenre(results));
        }

        return genreList;
    }


    private Genre mapRowToGenre(SqlRowSet rowSet) {
        Genre g = new Genre();

        g.setName(rowSet.getString("genre_name"));

        return g;
    }

}
