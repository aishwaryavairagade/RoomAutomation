package com.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.pojos.Sensor;
import com.service.SensorModuleServie;

import java.util.ArrayList;

@Path("/sensor")

public class SensorController {
	static int sensorid = 0;

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	// method to add a new sensor
	public String addSensor(Sensor sensor) {
		SensorModuleServie sensormodule = new SensorModuleServie();
		sensormodule.addSensor(sensor);
		return "Added Successfully";
	}

	@GET
	@Path("/view")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	// method to view the list of sensors
	public List<Sensor> viewSensor() {
		SensorModuleServie sensormodule = new SensorModuleServie();
		List<Sensor> list =sensormodule.viewSensor();
		
		
		return list;
	}
}
