package com.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.controller.SensorDetailsController;

import com.pojos.SensorDetails;

public class SensorDetailsControllerTest {
	SensorDetailsController sensorDetailsController;
	SensorDetails sensorDetails;
	@Before
	public void setup(){
		sensorDetailsController = new SensorDetailsController();
		sensorDetails = new SensorDetails();
		sensorDetails.setRoomid(101);
		sensorDetails.setSensorid(1);
		sensorDetails.setIsenabled(true);
		sensorDetails.setType("temp");
	}
	
	@Test
	public void addSensorDetailsTest(){
		sensorDetailsController=new SensorDetailsController();
		String result = sensorDetailsController.addSensorDetails(sensorDetails);
		Assert.assertEquals(result, "Added");
		
	}	

}
