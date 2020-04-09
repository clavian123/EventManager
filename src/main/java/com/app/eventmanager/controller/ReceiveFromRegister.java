package com.app.eventmanager.controller;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.eventmanager.model.SendSampleResponse;
import com.app.eventmanager.model.Event;
import com.app.eventmanager.model.ReceivePostRequest;
import com.app.eventmanager.model.ReceivePostResponse;
import com.app.eventmanager.model.Reward;
import com.app.eventmanager.model.ReceiveSampleResponse;
import com.app.eventmanager.repository.EventRepository;
import com.app.eventmanager.repository.RewardRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ReceiveFromRegister {
	private static Logger log = Logger.getLogger(ReceiveFromRegister.class);
	
	@Autowired
	EventRepository eventRepository;
	@Autowired
	RewardRepository rewardRepository;
	
	String text = null;
	
	@RequestMapping("/sample")
	public ReceiveSampleResponse Sample(@RequestParam(value = "TransactionTypeCode",
	defaultValue = "TransactionTypeCode") String TransactionTypeCode) {
		ReceiveSampleResponse response = new ReceiveSampleResponse();
		response.setTransactionTypeCode(TransactionTypeCode);
		return response;

	}
	
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public ReceivePostResponse Test(@RequestBody ReceivePostRequest inputPayload) throws JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		final String uri = "http://localhost:8080/backsend";
		
		ObjectMapper mapper = new ObjectMapper();
		
		ReceivePostResponse response = new ReceivePostResponse();
		response.setTransactionTypeCode(inputPayload.getTransactionTypeCode());
		Set<Event> event = this.eventRepository.findByCode();
		Set<Reward> reward = this.rewardRepository.findByAll();
		String json = mapper.writeValueAsString(reward);
		if(inputPayload.getTransactionTypeCode().equals("registration")) {
			
			for(Event e : event) {
				log.debug(e.getId()+e.getCode()+e.getDescription()+e.getName());
				text = e.getCode() + " " + e.getEvent_end() + " " + e.getEvent_start();
			}
			if(event != null) {
				SendSampleResponse backSampleResponse = new SendSampleResponse();
				backSampleResponse.setValidation("isValid");
				backSampleResponse.setSetOfReward(json);
				SendSampleResponse addRest = restTemplate.postForObject( uri, backSampleResponse, SendSampleResponse.class);
				log.debug(addRest.getValidation());
			}
		}
		
		response.setExtra(text);
		return response;
	}

}
