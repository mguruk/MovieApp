package org.manish.appdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	// TODO : Add Database properties
	private static final String URL = "jdbc:mysql://localhost:3306/movie_record";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	private static Connection connection = null;

	
	public static Connection getDbConnection() throws ClassNotFoundException {
		try {
			 Class.forName("com.mysql.jdbc.Driver"); 
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void closeDbConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


}
