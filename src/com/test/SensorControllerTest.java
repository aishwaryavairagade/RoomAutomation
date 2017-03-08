package com.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.controller.SensorController;
import com.pojos.Sensor;

public class SensorControllerTest {
	private SensorController sensorController;
	private Sensor sensor;
	@Before
	public void setup(){
		sensorController = new SensorController();
		sensor = new Sensor();
		sensor.setFrequency(2);
		sensor.setSensorid(1);
		sensor.setSensortype("Temperature");
	}
	
	@Test
	public void addSensorTest(){
		sensorController = new SensorController();
		String result = sensorController.addSensor(sensor);
		Assert.assertEquals(result, "Added Successfully");
		
	}
	
	@Test
	public void viewSensorTest(){
		sensorController = new SensorController();
		List<Sensor> result=sensorController.viewSensor();
		Assert.assertNotNull(result);
	}
}
