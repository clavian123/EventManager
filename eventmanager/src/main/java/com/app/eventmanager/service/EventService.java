package com.app.eventmanager.service;

import java.util.List;

import com.app.eventmanager.model.Event;

public interface EventService {
	List<Event> findAll();
	
	Event findById(Long id);
	
	Event update(Long id, Event event);
	
	Event create(Event event);
	
	void delete(Long id);

}
