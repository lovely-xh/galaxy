package com.galaxy.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	private static String driver;
	private static String dburl;
	private static String user;
	private static String pwd;
	
	private static ConnectionFactory connectionFactory = new ConnectionFactory();
	
	private Connection connection = null;
	
	static {
		Properties properties = new Properties();
		try {
			InputStream inStream = ConnectionFactory.class.getClassLoader()
				.getResourceAsStream("config.properties");
			properties.load(inStream);
			
			driver = properties.getProperty("driver");
			dburl = properties.getProperty("dburl");
			user = properties.getProperty("user");
			pwd = properties.getProperty("password");
			System.out.println("===========配置问件读取完成==========");
		} catch (Exception e) {
			System.out.println("===========配置问件读取错误==========");
			e.printStackTrace();
		}
	}
	
	private ConnectionFactory() {
	}
	
	public static ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}
	
	public Connection doConnection() {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(dburl, user, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public static void main(String[] args) {
		ConnectionFactory cFactory = ConnectionFactory.getConnectionFactory();
		
		Connection connection = cFactory.doConnection();
		
		try {
			System.out.println(connection.getAutoCommit());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
