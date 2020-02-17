package com.app.eventmanager.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.eventmanager.model.Event;

public interface EventRepository extends JpaRepository<Event, Long>{
	@Query("select o from Event o where o.code like 'registration%'")
	List<Event> findByCode();
	
	
}
