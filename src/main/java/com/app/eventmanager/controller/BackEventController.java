package com.app.eventmanager.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackEventController {
	@RequestMapping("/backsample")
	public com.app.eventmanager.model.BackResponse BackSample(@RequestParam(value = "Validation",
	defaultValue = "Validation") String Validation, String SetOfReward) {
		com.app.eventmanager.model.BackResponse backResponse = new com.app.eventmanager.model.BackResponse();
		backResponse.setValidation(Validation);
		backResponse.setSetOfReward(SetOfReward);
		return backResponse;

	}
	
	@RequestMapping(value = "/backsend", method = RequestMethod.POST)
	public com.app.eventmanager.model.BackResponse Test(@RequestBody com.app.eventmanager.model.BackRequest inputBackPayload) {
		com.app.eventmanager.model.BackResponse backResponse = new com.app.eventmanager.model.BackResponse();
		backResponse.setValidation(inputBackPayload.getValidation());
		backResponse.setSetOfReward(inputBackPayload.getSetOfReward());
		backResponse.setExtra("Hai");
		return backResponse;
	}

}
