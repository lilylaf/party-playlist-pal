package com.techelevator.dao;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@Component
@CrossOrigin
public class JdbcDjLibraryDao implements DjLibraryDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcDjLibraryDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

}
