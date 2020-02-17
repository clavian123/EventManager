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
	@Column(name = "id")
	private Long id;
	@Column(name = "code")
	private String code;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "event_start")
	private Date event_start;
	@Column(name = "event_end")
	private Date event_end;
	
	@ManyToMany (cascade = CascadeType.PERSIST)
	@JoinTable(name = "event_reward", joinColumns = { @JoinColumn(name = "id_reward", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "id_event", nullable = false) })
	private Set<Reward> reward= new HashSet<>();
	
	public Event() {

	}
	
	public Event(Long id, String code, String description, Date event_start, Date event_end) {
		this.id = id;
		this.code = code;
		this.description = description;
		this.event_start = event_start;
		this.event_end = event_end;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return event_start;
	}

	public void setEvent_start(Date event_start) {
		this.event_start = event_start;
	}

	public Date getEvent_end() {
		return event_end;
	}

	public void setEvent_end(Date event_end) {
		this.event_end = event_end;
	}
	
	
}
