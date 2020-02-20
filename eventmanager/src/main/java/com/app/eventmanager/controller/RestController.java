package com.app.eventmanager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.eventmanager.model.RestModel;
import com.app.eventmanager.service.RestService;


@org.springframework.web.bind.annotation.RestController
public class RestController {
	RestService restService = new RestService();
	 
	@RequestMapping(value = "/rest", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<RestModel> getRest() {
		List<RestModel> listOfRest = restService.getAllRest();
		return listOfRest;
	}
 
	@RequestMapping(value = "/rest/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public RestModel getCountryById(@PathVariable int id) {
		return restService.getRestModel(id);
	}
 
	@RequestMapping(value = "/rest", method = RequestMethod.POST, headers = "Accept=application/json")
	public RestModel addRest(@RequestBody RestModel restModel) {
		return restService.addRest(restModel);
	}
 
	@RequestMapping(value = "/rest", method = RequestMethod.PUT, headers = "Accept=application/json")
	public RestModel updateRest(@RequestBody RestModel restModel) {
		return restService.updateRest(restModel);
 
	}
 
	@RequestMapping(value = "/rest/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteRest(@PathVariable("id") int id) {
		restService.deleteRest(id);
 
	} 

}
