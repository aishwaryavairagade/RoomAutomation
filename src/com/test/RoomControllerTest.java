package com.test;

/*testing commit*/
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.controller.RoomController;
import com.pojos.RoomDetails;

public class RoomControllerTest {
	private RoomController roomController;
	private RoomDetails roomDetails;

	@Before
	public void setup() {
		List<String> list = new ArrayList<>();
		list.add("temp");
		roomController = new RoomController();
		roomDetails = new RoomDetails();
		roomDetails.setRoomid(112);
		roomDetails.setBuildg("CT1");
		roomDetails.setFloor(3);
		roomDetails.setNoOfSensors(1);
		roomDetails.setSensors(list);
	}

	@Test
	public void addExistingRoomTest() {
		roomController = new RoomController();
		String result = roomController.addRoom(roomDetails);
		Assert.assertEquals(result, "Room Doesnot exists");

	}

	@Test
	public void addRoomTest() {
		roomController = new RoomController();
		roomDetails.setRoomid(103);
		String result = roomController.addRoom(roomDetails);
		Assert.assertEquals(result, "Added Successfully");
	}

	@Test
	public void deleteRoomTest() {
		roomController = new RoomController();
		roomDetails.setRoomid(1111);
		String result = roomController.deleteRoom(roomDetails);
		Assert.assertEquals(result, "Room Doesnot exists");

	}
	
	@Test
	public void deleteExistingRoomTest() {
		roomController = new RoomController();
		String result = roomController.deleteRoom(roomDetails);
		Assert.assertEquals(result, "Deleted Successfully");

	}
	

	@Test
	public void viewRoomTest() {
		roomController = new RoomController();
		List<RoomDetails>list = roomController.viewRoom(roomDetails);
		Assert.assertNotNull(list);

	}

}
