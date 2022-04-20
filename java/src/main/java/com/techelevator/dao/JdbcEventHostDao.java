package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.EventHost;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
@CrossOrigin
public class JdbcEventHostDao implements EventHostDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcEventHostDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    public void deleteHostFromEvent(Long eventId, Long hostId){
        String sql =  "DELETE FROM event_host WHERE user_id = ? AND event_id = ?;";
        int numRows = jdbcTemplate.update(sql, eventId, hostId);
    }
}
