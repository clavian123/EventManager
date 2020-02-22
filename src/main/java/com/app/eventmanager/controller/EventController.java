package com.app.eventmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.eventmanager.model.Event;
import com.app.eventmanager.repository.EventRepository;


@RestController
@RequestMapping(value = "/event")
public class EventController {
	@Autowired
	EventRepository eventRepository;
	
	@GetMapping(value = "all")
	public ResponseEntity<List<Event>> getAllEvent(@RequestParam(required = false) String title) {
		try {
			List<Event> event = eventRepository.findAll();

			if (event.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			
			return new ResponseEntity<>(event, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				}
		}
	
	  @GetMapping("/{id}")
	  public ResponseEntity<Event> getTutorialById(@PathVariable("id") Long id) {
	    Optional<Event> tutorialData = eventRepository.findById(id);

	    if (tutorialData.isPresent()) {
	      return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

}
