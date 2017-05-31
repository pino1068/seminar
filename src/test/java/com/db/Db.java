package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {

	private static Connection _connection;
	
	public static Connection connection(){
		try {
			if(_connection == null || _connection.isClosed()){
				_connection = DriverManager.getConnection("jdbc:sqlite:src/main/db/test.db");
				_connection.setAutoCommit(false);
			}
			return _connection;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void close(){
		try {
			if(_connection != null && !_connection.isClosed()){
				_connection.rollback();
				_connection.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
