package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.EventNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseStatus;

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


    @Override
    public ResponseEntity deleteEvent(Long id) throws EventNotFoundException {


        String sql = "BEGIN TRANSACTION;\n" +
                "DELETE FROM event_host\n" +
                "WHERE event_id = ?;\n" +
                "DELETE FROM event_song\n" +
                "WHERE event_id = ?;\n" +
                "DELETE FROM event_genre\n" +
                "WHERE event_id = ?;\n" +
                "DELETE FROM event\n" +
                "WHERE event_id = ?;\n" +
                "COMMIT TRANSACTION;";

        //get count from events table
        String getCount = "SELECT COUNT(event_id)\n" +
                "FROM event";
        int count = jdbcTemplate.update(getCount);
        int numRows = jdbcTemplate.update(sql, id, id, id, id);
        //get count from events table
        String newCount = "SELECT COUNT(event_id)\n" +
                "FROM event";
        int getNewCount = jdbcTemplate.update(newCount);

        if(count != getNewCount){ //why is this not
            return new ResponseEntity(HttpStatus.NO_CONTENT); //this should be a 200 message
        } else {
            throw new EventNotFoundException();
        }

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
