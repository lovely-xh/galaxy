package com.galaxy.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {

	private static String driver;
	private static String dburl;
	private static String user;
	private static String pwd;
	
	private static ConnectionFactory connectionFactory = null;
	
	private Connection connection = null;
	
	static {
		Properties properties = new Properties();
		try {
			InputStream inStream = ConnectionFactory.class.getClassLoader()
				.getResourceAsStream("config.properties");
			properties.load(inStream);
		} catch (Exception e) {
			System.out.println("===========配置问件读取错误==========");
			e.printStackTrace();
		}
		
		driver = properties.getProperty("driver");
		dburl = properties.getProperty("dburl");
		user = properties.getProperty("user");
		pwd = properties.getProperty("password");
		System.out.println("===========配置问件读取完成==========");
	}
	
	private ConnectionFactory() {
	}
	
	public ConnectionFactory getConnectionFactory() {
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		}
		
		return connectionFactory;
	}
	
	public Connection connect() {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(dburl, user, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
}
