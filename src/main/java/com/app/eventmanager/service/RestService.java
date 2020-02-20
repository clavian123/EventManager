package com.app.eventmanager.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.app.eventmanager.model.RestModel;


public class RestService {
	static HashMap<Integer, RestModel> restIdMap = getRestIdMap();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public RestService() {
		super();
		
		if(restIdMap==null)
		{
			Date join = null;
			try {
				join = sdf.parse("2020-02-20 07:00:00");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			restIdMap=new HashMap<Integer,RestModel>();
			RestModel firstRest=new RestModel(1, "QWQ", join, "QWERTY");
			restIdMap.put(1,firstRest);
		}
	}
	
	public List<RestModel> getAllRest()
	{
		List<RestModel> rest = new ArrayList<RestModel>(restIdMap.values());
		return rest;
	}
 
	public RestModel getRestModel(int id)
	{
		RestModel restModel= restIdMap.get(id);
		return restModel;
	}
	public RestModel addRest(RestModel restModel)
	{
		restModel.setId(getMaxId()+1);
		restIdMap.put(restModel.getId(), restModel);
		return restModel;
	}
 
	private static int getMaxId() {
		int max=0;
		for (int id:restIdMap.keySet()) { 
			if(max<=id)
				max=id;
		} 
		return max;
	}

	public RestModel updateRest(RestModel restModel)
	{
		if(restModel.getId()<=0)
			return null;
		restIdMap.put(restModel.getId(), restModel);
		return restModel;
 
	}
	public void deleteRest(int id)
	{
		restIdMap.remove(id);
	}

	private static HashMap<Integer, RestModel> getRestIdMap() {
		return restIdMap;
	}
	
}
