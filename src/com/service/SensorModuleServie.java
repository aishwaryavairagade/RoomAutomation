package com.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.db.DbConnection;
import com.pojos.Sensor;

public class SensorModuleServie {

	private final static Logger LOGGER = Logger.getLogger(SensorService.class);
	private int sensorid;

	public void addSensor(Sensor sensor) {
		try {
			ResultSet results = DbConnection.SESSION.execute("select MAX(sensorid) from sensor");

			if (results.isExhausted()) {
				sensorid = 1;
			} else {
				for (Row row : results) {
					int i = row.getInt(0);
					sensorid = i + 1;
				}
			}

			PreparedStatement pre = DbConnection.SESSION
					.prepare("INSERT INTO sensor (sensorid, sensortype, frequency) VALUES (?,?,?)");
			BoundStatement bound1 = pre.bind(sensorid, sensor.getSensortype(), sensor.getFrequency());
			DbConnection.SESSION.execute(bound1);
			sensorid++;
			LOGGER.info("Added Successfully");
		} catch (RuntimeException e) {
			LOGGER.error("Cannot add new sensor" +e);
		}
	}

	public List<Sensor> viewSensor() {
		List<Sensor> list = new ArrayList<>();
		try {

			ResultSet results = DbConnection.SESSION.execute("select * from sensor");
			for (Row row : results) {

				Sensor sensor = new Sensor();
				sensor.setSensorid(row.getInt("sensorid"));
				sensor.setSensortype(row.getString("sensortype"));
				sensor.setFrequency(row.getInt("frequency"));

				list.add(sensor);
				LOGGER.info("List");

			}
			
		} catch (RuntimeException e) {
			LOGGER.error("Cannot show the list of sensors" + e);

		}
		return list;
	}
}