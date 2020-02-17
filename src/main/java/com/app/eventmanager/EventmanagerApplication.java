package com.app.eventmanager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.app.eventmanager.model.Event;
import com.app.eventmanager.repository.EventRepository;




@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.app.eventmanager.repository")
public class EventmanagerApplication implements CommandLineRunner{
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private Date join = null;
	private String name = null;
	
	@Autowired
	private EventRepository eventRepository;

	public static void main(String[] args) throws Exception{
		SpringApplication.run(EventmanagerApplication.class, args);
		System.out.println("Welcome Home");

	}

	@Override
	public void run(String... args) throws Exception {
		List<Event> event = this.eventRepository.findByCode();
		System.out.println(String.format("Found %d event!", event.size()));
		
		validation();

	}
	
	public void validation() throws Exception {
		List<Event> event = this.eventRepository.findByCode();
		
		//Ambil data tanggal join dari register
		name = "Gerson";
		join = sdf.parse("2020-02-20 07:00:00");
		System.out.println(name + " | Join date: " + sdf.format(join));
		
		for(Event e : event) {
			if(join.compareTo(e.getEvent_start()) >= 0  && join.compareTo(e.getEvent_end()) <= 0) {
				System.out.println("Congrast! You got: " + e.getDescription());
			}
		}
	}

}
