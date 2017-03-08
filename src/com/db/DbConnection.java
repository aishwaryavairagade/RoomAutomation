package com.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class DbConnection {
	private final static Logger LOGGER=Logger.getLogger(DbConnection.class);
	private final static Properties CONFIG = new Properties();
	private static InputStream IS = DbConnection.class.getResourceAsStream("/cassandra.properties");
	
	public static final Cluster CLUSTER = Cluster.builder().addContactPoint(DbConnection.getIP()).build();
	public static final Session SESSION = CLUSTER.connect(DbConnection.getKeyspaceName());

	private DbConnection(){
	}
	public static String getIP() {

		try {
			
			CONFIG.load(IS);
		} catch (IOException e) {
			LOGGER.debug("error in  getip"+e);
		
		}
		
		return CONFIG.getProperty("localIp");
	}

	public static String getKeyspaceName() {

		try {

			CONFIG.load(IS);
		} catch (IOException e) {

			LOGGER.debug("error in get keyspace"+e);
		}
		
		return CONFIG.getProperty("keyspace");
	}

}
