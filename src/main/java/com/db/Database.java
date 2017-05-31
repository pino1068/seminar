package com.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class Database {

	private final Connection _connection;

	public Database(DataSource ds) {
		try {
			_connection = ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void setAutoCommit(boolean b) throws SQLException {
		_connection.setAutoCommit(b);
	}

	public Connection connect() {
		return _connection;
	}

	public void commit() throws SQLException {
		_connection.commit();
	}

	public void rollback() {
		try {
			_connection.rollback();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void close() {
		try {
			_connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
