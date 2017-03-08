package com.service;

import org.apache.log4j.Logger;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.db.DbConnection;
import com.pojos.RoomDetails;

public class SensorService {

	private final static Logger LOGGER = Logger.getLogger(SensorService.class);

	/**
	 * @return Generate incremented unique sensor Id
	 */
	public int generateSensorId() {
		int i, sensorid = 1;

		ResultSet results = DbConnection.SESSION.execute("select MAX(sensorid) from sensordetails");

		if (!results.isExhausted()) {
			for (Row row : results) {
				i = row.getInt(0);
				sensorid = i + 1;
			}
		}
		return sensorid;
	}

	/**
	 * @param roomDetails
	 *            Delete SensorDetails from database once the room is deleted
	 */
	public void deleteSensorDetailsService(RoomDetails roomDetails) {
		try {
			
			PreparedStatement prepared = DbConnection.SESSION
					.prepare("SELECT sensorid FROM sensordetails WHERE roomid=? ALLOW FILTERING ");
			BoundStatement bound = prepared.bind(roomDetails.getRoomid());

			ResultSet results = DbConnection.SESSION.execute(bound);

			for (Row row : results) {
				prepared = DbConnection.SESSION.prepare("DELETE  FROM sensordetails where sensorid=? ");
				bound = prepared.bind(row.getInt("sensorid"));
				DbConnection.SESSION.execute(bound);
			}
			LOGGER.info("All Sensors for roomid " + roomDetails.getRoomid() + "deleted successfully");
		} catch (RuntimeException e) {
			LOGGER.error("Cannot delete sesnors for room " + roomDetails.getRoomid() + " " + e);
		}
	}

	/**
	 * @param sensorDetails
	 *            Add new sensor to database
	 */
	public void addSensorDetailsSevice(int sensorId, int roomId, boolean isEnabled, String type) {
		PreparedStatement prepared = DbConnection.SESSION
				.prepare("INSERT INTO sensordetails (sensorid, roomid, isenabled, type) VALUES (?,?,?,?)");
		BoundStatement bound = prepared.bind(sensorId, roomId, isEnabled, type);
		DbConnection.SESSION.execute(bound);
	}
}
