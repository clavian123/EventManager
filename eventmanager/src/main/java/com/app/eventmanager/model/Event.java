package com.app.eventmanager.model;

import java.util.Date;
import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "event")
public class Event {
	
	@Id
	@GeneratedValue
	@Column(name = "id_event")
	private Long idEvent;
	@Column(name = "code")
	private String code;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "event_start")
	private Date eventStart;
	@Column(name = "event_end")
	private Date eventEnd;
	
	@ManyToMany (cascade = CascadeType.PERSIST)
	@JoinTable(name = "event_reward", joinColumns = { @JoinColumn(name = "id_reward", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "id_event", nullable = false) })
	private Set<Reward> reward= new HashSet<>();
	
	public Event() {

	}
	
	public Event(Long idEvent, String code, String description, Date eventStart, Date eventEnd) {
		this.idEvent = idEvent;
		this.code = code;
		this.description = description;
		this.eventStart = eventStart;
		this.eventEnd = eventEnd;
	}

	public Long getId() {
		return idEvent;
	}

	public void setId(Long id) {
		this.idEvent = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEvent_start() {
		return eventStart;
	}

	public void setEvent_start(Date eventStart) {
		this.eventStart = eventStart;
	}

	public Date getEvent_end() {
		return eventEnd;
	}

	public void setEvent_end(Date eventEnd) {
		this.eventEnd = eventEnd;
	}
	
	
}
