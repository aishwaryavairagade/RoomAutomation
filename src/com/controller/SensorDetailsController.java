package com.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.pojos.SensorDetails;
import com.service.SensorService;

@Path("/sensordetails")

public class SensorDetailsController {

	SensorService sensorService;

	/**
	 * @param sensorDetails
	 * @return
	 * Add new sensor to existing room
	 */
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addSensorDetails(SensorDetails sensorDetails) {
		sensorService=new SensorService();
		int sensorid = sensorService.generateSensorId();
		sensorService.addSensorDetailsSevice(sensorid, sensorDetails.getRoomid(), sensorDetails.isIsenabled(),
				sensorDetails.getType());
		return "Added ";
	}
}
