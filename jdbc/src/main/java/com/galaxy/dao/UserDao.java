package com.galaxy.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.galaxy.entity.User;

public interface UserDao {

	public void get(Connection connection, User user) throws SQLException;
	
	public void save(Connection connection, User user) throws SQLException;
	
	public void update(Connection connection, User user) throws SQLException;
	
	public void delete(Connection connection, User user) throws SQLException;
}
