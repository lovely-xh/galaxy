package com.galaxy.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 *
 */
public class App {
	
	private static Connection connection = null;
	
	private static Statement statement = null;
	
	private static Statement getStatement() {
		try {
			if (connection == null) {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp_db?useSSL=false", "root", "admin");
				
				statement = connection.createStatement();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return statement;
	}

	public static void insert() {
		String sql = "insert into jsp(id, name, password, email) values"
				 + "(3, 'liuxiaohong', '654321', 'lxh@iflytek.com');";
		try {
			int count = getStatement().executeUpdate(sql);
			System.out.println("插入了 " + count + " 条记录");
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void update() {
		String sql = "update jsp set email='llxxhh@xx.com' where name = 'liuxiaohong'";
		try {
			int count = getStatement().executeUpdate(sql);
			System.out.println("更新了 " + count + " 条记录");
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void delete() {
		String sql = "delete from jsp where name = 'liuxiaohong'";
		try {
			int count = getStatement().executeUpdate(sql);
			System.out.println("删除了 " + count + " 条记录");
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
//		insert();
//		
//		update();
		
		delete();
		
//		String sql = "SELECT * FROM jsp";
//
//		
//		Statement statement = null;
//		ResultSet resultSet = null;
//
//		try {
//			
//			statement = connection.createStatement();
//			resultSet = statement.executeQuery(sql);
//
//			while (resultSet.next()) {
//				System.out.print(resultSet.getInt("id") + " ");
//				System.out.print(resultSet.getString("name") + " ");
//				System.out.print(resultSet.getString("password") + " ");
//				System.out.print(resultSet.getString("email") + " ");
//				System.out.println();
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		} finally {
//			try {
//				resultSet.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				statement.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
}
