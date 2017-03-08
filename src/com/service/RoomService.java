package com.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.db.DbConnection;
import com.pojos.RoomDetails;

public class RoomService {

	private final static Logger LOGGER = Logger.getLogger(RoomService.class);

	/**
	 * @param roomDetails
	 *            Add new room to database
	 */
	public void addRoomService(RoomDetails roomDetails) {
		try {
			PreparedStatement prepared = DbConnection.SESSION
					.prepare("INSERT INTO room (roomid, buildg, floor, noofsensors) VALUES (?,?,?,?)");
			BoundStatement bound = prepared.bind(roomDetails.getRoomid(), roomDetails.getBuildg(),
					roomDetails.getFloor(), roomDetails.getNoOfSensors());
			DbConnection.SESSION.execute(bound);
			LOGGER.info("Room added successfully");
		} catch (RuntimeException e) {
			LOGGER.error("Cannot add room : " + e);
		}
	}

	/**
	 * @param roomDetails
	 * @return Check whether the room with given roomid exists in database
	 */
	public int checkRoom(RoomDetails roomDetails) {
		PreparedStatement prepared = DbConnection.SESSION.prepare("SELECT * FROM room where roomid=?");
		BoundStatement bound = prepared.bind(roomDetails.getRoomid());
		ResultSet results = DbConnection.SESSION.execute(bound);
		if (results.isExhausted()) {
			return 1;
		}
		return 0;
	}

	/**
	 * @param roomDetails
	 *  Delete existing room from database
	 */
	public void deleteRoomService(RoomDetails roomDetails) {
		try {
			PreparedStatement prepared = DbConnection.SESSION.prepare("DELETE  FROM room where roomid = ? ");
			BoundStatement bound = prepared.bind(roomDetails.getRoomid());
			DbConnection.SESSION.execute(bound);
			LOGGER.info("Room Deleted successfully");
		} catch (RuntimeException e) {
			LOGGER.error("Cannot Delete room" +e);
		}
	}

	/**
	 * @param roomDetails
	 * @return List<RoomSensor> Get all rooms from database
	 */
	public List<RoomDetails> viewRoomService(RoomDetails roomDetails) {

		List<RoomDetails> list = new ArrayList<>();
		try {
			PreparedStatement prepared = DbConnection.SESSION
					.prepare("select *  from room where buildg=? AND floor=? ALLOW FILTERING");
			BoundStatement bound = prepared.bind(roomDetails.getBuildg(), roomDetails.getFloor());
			ResultSet results = DbConnection.SESSION.execute(bound);
			for (Row row : results) {
				RoomDetails room = new RoomDetails();
				room.setRoomid(row.getInt("roomid"));
				room.setBuildg(row.getString("buildg"));
				room.setFloor(row.getInt("floor"));
				room.setNoOfSensors(row.getInt("noofsensors"));
				list.add(room);
				LOGGER.info("Show all rooms");
			}
		} catch (RuntimeException e) {
			LOGGER.error("Cannot get list of all the rooms :" + e);
		}
		return list;

	}
}
