package com.techelevator.dao;

import com.techelevator.model.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcEventDao implements EventDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcEventDao(JdbcTemplate jdbctemplate) {this.jdbcTemplate = jdbctemplate;}

    @Override
    public List<Event> listOfEvents() {
        List<Event> eventList = new ArrayList<>();

        String sql = "SELECT event_id, user_id, event_name, information, picture\n" +
                     "FROM event\n" +
                     "ORDER BY event_id ASC;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            eventList.add(mapRowToEvent(results));
        }

        return eventList;
    }

    @Override
    public Event getEventById(Long id) {

        String sql = "SELECT event_id, user_id, event_name, information, picture\n" +
                "FROM event\n" +
                "WHERE event_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()){
            return mapRowToEvent(results);
        } else {
            return null;
        }
    }


    private Event mapRowToEvent(SqlRowSet rowSet){
        Event event = new Event();

        event.setEventId(rowSet.getLong("event_id"));
        event.setEventName(rowSet.getString("event_name"));
        event.setInformation(rowSet.getString("information"));

        //todo deal with the picture at some point

        return event;
    }


}
