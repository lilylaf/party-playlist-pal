package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.EventHost;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.PreparedStatement;

@Component
@CrossOrigin
public class JdbcEventHostDao implements EventHostDao{

    private JdbcTemplate jdbcTemplate;
    public JdbcEventHostDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public EventHost addHost(Long eventId, Long[] hosts) {
        EventHost eh = new EventHost();


        for (Long host : hosts){
            String sql = "INSERT INTO event_host(event_id, user_id)\n" +
                    "VALUES (?, ?);";
            jdbcTemplate.update(sql,eventId, host);

        }

        return null;
        //todo -> this method needs to be finished
    }
}
