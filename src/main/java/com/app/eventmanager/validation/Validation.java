package com.app.eventmanager.validation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import com.app.eventmanager.model.Event;
import com.app.eventmanager.model.RestModel;
import com.app.eventmanager.repository.EventRepository;

public class Validation {
	@Autowired
	EventRepository eventRepository;
	Date join;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	RestModel restModel;
	
	public boolean isValid() {
		return true;
	}

	public Validation() throws Exception {
		Set<Event> event = eventRepository.findByCode();
		join = sdf.parse("2020-02-20 07:00:00");
		System.out.println(String.format("Found %d event!", event.size()));
		
		for(Event e : event) {
			if(restModel.getCreatedDate().compareTo(e.getEvent_start()) >= 0  && restModel.getCreatedDate().compareTo(e.getEvent_end()) <= 0) {
				System.out.println("Congrast! You got: " + e.getDescription());
				isValid();
			}
		}
	}
	

		



}
