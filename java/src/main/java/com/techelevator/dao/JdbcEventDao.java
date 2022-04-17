package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.EventHost;
import com.techelevator.model.EventNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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

        String sql = "SELECT event_id, user_id, event_name, information " +
                "FROM event " +
                "WHERE event_id =?;";

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


        int numRows = jdbcTemplate.update(sql, id, id, id, id);


        if(numRows > 0){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            throw new EventNotFoundException();
        }

    }

    @Override
    public Event addHost(Long eventId, List<Long> hosts) throws EventNotFoundException {
        EventHost eh = new EventHost();
        String sql = "INSERT INTO event_host(event_id, user_id)\n" +
                "VALUES (?, ?);";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Long hostId = hosts.get(i);
                ps.setLong(1, eventId);
                ps.setLong(2, hostId);
            }

            @Override
            public int getBatchSize() {
                return hosts.size();
            }
        });
        Event updatedEvent = getEventById(eventId);

        return updatedEvent;

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Event HostUpdateEvent(String eventName, String information, Long id) throws EventNotFoundException{

        String sql = "UPDATE event \n" +
                "SET event_name = ?, information = ? \n" +
                "FROM event_host \n" +
                "WHERE event.event_id = ?;";

        int numRows = jdbcTemplate.update(sql, eventName, information, id); //why is this a type int

        Event updatedEvent = getEventById(id);

        return updatedEvent;

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Event DjUpdateEvent(Event event, Long id) throws EventNotFoundException {

        String sql = "UPDATE event \n" +
                "SET event_name = ?, information = ? \n" +
                "WHERE event_id = ?;";

        int numRows = jdbcTemplate.update(sql, event, id); //why is this a type int

        Event updatedEvent = getEventById(id);

        return updatedEvent;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
