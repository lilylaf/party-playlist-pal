package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.EventNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@Component
@CrossOrigin
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
    public Event getEventById(Long id) throws EventNotFoundException {

        String sql = "SELECT event_id, user_id, event_name, information, picture\n" +
                "FROM event\n" +
                "WHERE event_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        if (results.next()){
            return mapRowToEvent(results);
        } else {
            throw new EventNotFoundException();

        }
    }

    @Override
    public Event create(Event event) throws EventNotFoundException {
        Event newEvent = null;

        String sql= "INSERT INTO event (user_id, event_name, information)\n" +
                "VALUES (?, ?, ?) RETURNING event_id;";

        Long newEventId = jdbcTemplate.queryForObject(sql, Long.class, event.getUserId(), event.getName(), event.getInformation());

        newEvent = getEventById(newEventId);

        return newEvent;
    }


    private Event mapRowToEvent(SqlRowSet rowSet){
        Event event = new Event();

        event.setId(rowSet.getLong("event_id"));
        event.setUserId(rowSet.getLong("user_id"));
        event.setName(rowSet.getString("event_name"));
        event.setInformation(rowSet.getString("information"));

        //todo deal with the picture at some point

        return event;
    }


}
