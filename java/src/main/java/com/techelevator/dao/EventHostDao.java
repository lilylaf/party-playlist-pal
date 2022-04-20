package com.techelevator.dao;

import com.techelevator.model.EventHost;

public interface EventHostDao {

void deleteHostFromEvent(Long songId, Long eventId);
}
