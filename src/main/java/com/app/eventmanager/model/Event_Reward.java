//package com.app.eventmanager.model;
//
//import java.util.Set;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//import javax.persistence.JoinColumn;
//
//@Entity
//@Table(name = "eventmanager")
//public class Event_Reward {
//	
//	@Id
//	@GeneratedValue
//	@Column(name = "id_reward")
//	private Integer id_reward;
//	@Column(name = "id_event")
//	private Integer id_event;
//	
//	@ManyToMany
//	@JoinTable(name = "event_reward", joinColumns = { @JoinColumn(name = "id_reward", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "id_event", nullable = false) })
//	private Set<Event> event;
//
//	public Integer getId_reward() {
//		return id_reward;
//	}
//
//	public void setId_reward(Integer id_reward) {
//		this.id_reward = id_reward;
//	}
//
//	public Integer getId_event() {
//		return id_event;
//	}
//
//	public void setId_event(Integer id_event) {
//		this.id_event = id_event;
//	}
//
//	public Set<Event> getEvent() {
//		return event;
//	}
//
//	public void setEvent(Set<Event> event) {
//		this.event = event;
//	}
//	
//	
//
//}
