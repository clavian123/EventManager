package com.app.eventmanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.app.eventmanager.repository")
public class EventmanagerApplication implements CommandLineRunner{

	public static void main(String[] args) throws Exception{
		SpringApplication.run(EventmanagerApplication.class, args);
		System.out.println("halo2");

	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
