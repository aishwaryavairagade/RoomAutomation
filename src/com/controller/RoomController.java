package com.controller;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.pojos.*;
import com.service.*;

@Path("/room")

public class RoomController {

	RoomService roomService;
	SensorService sensorService;
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addRoom(RoomDetails roomDetails) {
		roomService = new RoomService();
		sensorService = new SensorService();
		int i, sensorid, flag;
		flag = roomService.checkRoom(roomDetails);
		if (flag == 0) {
			return "Room Already Exists";
		} else {
			roomService.addRoomService(roomDetails);
			sensorid = sensorService.generateSensorId();
			for (i = 0; i < roomDetails.getNoOfSensors(); i++) {
				sensorService.addSensorDetailsSevice(sensorid, roomDetails.getRoomid(), true,
						roomDetails.getSensors().get(i));

				sensorid++;
			}
			return "Added Successfully";
		}
	}

	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteRoom(RoomDetails roomDetails) {
		int flag;
		roomService = new RoomService();
		sensorService = new SensorService();
		flag = roomService.checkRoom(roomDetails);
		if (flag == 1) {
			roomService.deleteRoomService(roomDetails);
			sensorService.deleteSensorDetailsService(roomDetails);
			return "Deleted Successfully";
		} else {
			return "Room Doesnot exists";
		}
	}

	@POST
	@Path("/view")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<RoomDetails> viewRoom(RoomDetails roomDetails) {
		roomService = new RoomService();
		List<RoomDetails> list = roomService.viewRoomService(roomDetails);
		return list;
	}
}
