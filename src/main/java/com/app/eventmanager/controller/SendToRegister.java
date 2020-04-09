package com.app.eventmanager.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendToRegister {
	@RequestMapping("/backsample")
	public com.app.eventmanager.model.SendResponse BackSample(@RequestParam(value = "Validation",
	defaultValue = "Validation") String Validation, String SetOfReward) {
		com.app.eventmanager.model.SendResponse backResponse = new com.app.eventmanager.model.SendResponse();
		backResponse.setValidation(Validation);
		backResponse.setSetOfReward(SetOfReward);
		return backResponse;

	}
	
	@RequestMapping(value = "/backsend", method = RequestMethod.POST)
	public com.app.eventmanager.model.SendResponse Test(@RequestBody com.app.eventmanager.model.SendRequest inputBackPayload) {
		com.app.eventmanager.model.SendResponse backResponse = new com.app.eventmanager.model.SendResponse();
		backResponse.setValidation(inputBackPayload.getValidation());
		backResponse.setSetOfReward(inputBackPayload.getSetOfReward());
		backResponse.setExtra("Message");
		return backResponse;
	}

}
