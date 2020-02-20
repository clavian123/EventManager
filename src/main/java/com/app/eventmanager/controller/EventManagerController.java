package com.app.eventmanager.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.eventmanager.model.BackSampleResponse;
import com.app.eventmanager.model.Event;
import com.app.eventmanager.model.PostRequest;
import com.app.eventmanager.model.PostResponse;
import com.app.eventmanager.model.Reward;
import com.app.eventmanager.model.SampleResponse;
import com.app.eventmanager.repository.EventRepository;
import com.app.eventmanager.repository.RewardRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@RestController
public class EventManagerController {
	@Autowired
	EventRepository eventRepository;
	@Autowired
	RewardRepository rewardRepository;
	
	String text = null;
	
	@RequestMapping("/sample")
	public SampleResponse Sample(@RequestParam(value = "TransactionTypeCode",
	defaultValue = "TransactionTypeCode") String TransactionTypeCode) {
		SampleResponse response = new SampleResponse();
		response.setTransactionTypeCode(TransactionTypeCode);
		return response;

	}
	
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public PostResponse Test(@RequestBody PostRequest inputPayload) throws JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		final String uri = "http://localhost:8080/backsend";
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		PostResponse response = new PostResponse();
		response.setTransactionTypeCode(inputPayload.getTransactionTypeCode());
		Set<Event> event = this.eventRepository.findByCode();
		Set<Reward> reward = this.rewardRepository.findByAll();
		String json = mapper.writeValueAsString(reward);
		if(inputPayload.getTransactionTypeCode().equals("registration")) {
			
			for(Event e : event) {
				System.out.println(e.getId()+e.getCode()+e.getDescription()+e.getName());
				text = e.getCode() + " " + e.getEvent_end() + " " + e.getEvent_start();
			}
			if(event != null) {
				BackSampleResponse backSampleResponse = new BackSampleResponse();
				backSampleResponse.setValidation("isValid");
				backSampleResponse.setSetOfReward(json);
				BackSampleResponse addRest = restTemplate.postForObject( uri, backSampleResponse, BackSampleResponse.class);
				System.out.println(addRest.getValidation());
			}
		}
		
		response.setExtra(text);
		return response;
	}

}
