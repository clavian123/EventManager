package com.app.eventmanager.controller.request;

import com.app.eventmanager.model.Event;

public class AddRequest {
	private Event event;
	
	public Event getEvent() {
		return event;
	}
	
	public void setEvent(Event event) {
		this.event = event;
	}
}
