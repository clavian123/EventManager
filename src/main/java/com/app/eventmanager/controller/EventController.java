package com.app.eventmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.eventmanager.model.Event;
import com.app.eventmanager.repository.EventRepository;


@RestController
@RequestMapping(value = "/event", method = RequestMethod.GET)
public class EventController {
	
	@Autowired
	EventRepository eventRepository;
	
	@GetMapping(value = "/all")
	public @ResponseBody Iterable<Event> findAll(){
		return eventRepository.findAll();
	}
	
	@GetMapping(value = "/registration")
	public @ResponseBody Iterable<Event> findByCode(){
		return eventRepository.findByCode();
	}
	
	@PostMapping(value = "/load")
	public List<Event> persist(@RequestBody final Event event){
		eventRepository.save(event);
		return eventRepository.findAll();
	}

}
