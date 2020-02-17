package com.app.eventmanager.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.app.eventmanager.controller.request.AddRequest;
import com.app.eventmanager.controller.response.AddResponse;
import com.app.eventmanager.model.Event;
import com.app.eventmanager.repository.EventRepository;

public class Validation {
	public static AddResponse addRequestValid(AddRequest request) {
		AddResponse response = new AddResponse();
		
		Event evt = request.getEvent();
		
		EventRepository eventRepository = new EventRepository() {
			
			@Override
			public <S extends Event> Optional<S> findOne(Example<S> example) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <S extends Event> Page<S> findAll(Example<S> example, Pageable pageable) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <S extends Event> boolean exists(Example<S> example) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public <S extends Event> long count(Example<S> example) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public <S extends Event> S save(S entity) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Optional<Event> findById(Long id) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean existsById(Long id) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void deleteById(Long id) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void deleteAll(Iterable<? extends Event> entities) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void deleteAll() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void delete(Event entity) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public long count() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Page<Event> findAll(Pageable pageable) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <S extends Event> S saveAndFlush(S entity) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <S extends Event> List<S> saveAll(Iterable<S> entities) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Event getOne(Long id) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void flush() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public List<Event> findAllById(Iterable<Long> ids) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <S extends Event> List<S> findAll(Example<S> example, Sort sort) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <S extends Event> List<S> findAll(Example<S> example) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<Event> findAll(Sort sort) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<Event> findAll() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void deleteInBatch(Iterable<Event> entities) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void deleteAllInBatch() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public List<Event> findByCode() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		List<Event> event = eventRepository.findByCode();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date join = null;
		try {
			join = sdf.parse("2020-02-20 07:00:00");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		for(Event e : event) {
			if(join.compareTo(e.getEvent_start()) >= 0  && join.compareTo(e.getEvent_end()) <= 0) {
				System.out.println("Congrast! You got: " + e.getDescription());
			}
		}
		
		response.setSuccess(true);
		return response;
		
	}

}
