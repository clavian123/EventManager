//package com.app.eventmanager.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.app.eventmanager.model.Event;
//import com.app.eventmanager.repository.EventRepository;
//
//@Service
//public class EventServiceImpl implements EventService{
//	
//	@Autowired
//	EventRepository eventRepository;
//
//	@Override
//	public List<Event> findAll() {
//		return eventRepository.findAll();
//	}
//
//	@Override
//	public Event findById(Long id) {
//		return eventRepository.getOne(id);
//	}
//
//	@Override
//	public Event update(Long id, Event event) {
//		event.getId();
//		return eventRepository.save(event);
//	}
//
//	@Override
//	public Event create(Event event) {
//		return eventRepository.save(event);
//	}
//
//	@Override
//	public void delete(Long id) {
//		eventRepository.deleteById(id);
//		
//	}
//	
//}
