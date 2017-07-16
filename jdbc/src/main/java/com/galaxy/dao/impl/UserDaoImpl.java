package com.galaxy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.galaxy.dao.UserDao;
import com.galaxy.entity.User;
import com.galaxy.util.ConnectionFactory;

public class UserDaoImpl implements UserDao {

	public void get(Connection connection, User user) throws SQLException {
		PreparedStatement pStatement = 
				connection.prepareCall("SELECT from jsp WHERE id=" + user.getId());
		pStatement.execute();
	}

	public void save(Connection connection, User user) throws SQLException {
		PreparedStatement pStatement = 
				connection.prepareCall("INSERT INTO jsp(name, password, email) VALUES (?,?,?)");
		pStatement.setString(1, user.getName());
		pStatement.setString(2, user.getPassword());
		pStatement.setString(3, user.getEmail());
		pStatement.execute();
	}

	public void update(Connection connection, User user) throws SQLException {
		PreparedStatement pStatement = 
				connection.prepareStatement("UPDATE INTO SET name = ?, password = ?, email = ? WHERE id = ?");
		pStatement.setString(1, user.getName());
		pStatement.setString(2, user.getPassword());
		pStatement.setString(3, user.getEmail());
		pStatement.setLong(4, user.getId());
		pStatement.execute();

	}

	public void delete(Connection connection, User user) throws SQLException {
		PreparedStatement pStatement = 
				connection.prepareStatement("DELETE FROM jsp WHERE id = ?");
		pStatement.setLong(1, user.getId());
		pStatement.execute();
	}

	public static void main(String[] args) throws SQLException {
		Connection connection = ConnectionFactory.getConnectionFactory().doConnection();
		
		User lxh = new User();
		lxh.setId(13856560338L);
		lxh.setName("LingXiaoHong");
		lxh.setEmail("lxh@qq.com");
		lxh.setPassword("love");
		
		UserDaoImpl uDaoImpl = new UserDaoImpl();
		uDaoImpl.save(connection, lxh);
		
//		connection.commit();
	}
}
