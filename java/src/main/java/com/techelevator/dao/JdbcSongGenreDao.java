package com.techelevator.dao;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@Component
@CrossOrigin
public class JdbcSongGenreDao implements SongGenreDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcSongGenreDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
}
