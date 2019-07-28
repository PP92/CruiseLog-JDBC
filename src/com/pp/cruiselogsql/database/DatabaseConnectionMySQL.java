package com.pp.cruiselogsql.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.TimeZone;

public class DatabaseConnectionMySQL {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/cruise_log2?useUnicode=true&serverTimezone="+ TimeZone.getDefault().getID();
			//&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "admin123";

	private static Connection connection = null;

	public static Connection getConnection() {
		if (connection == null) {
			try {
				// Loading The Driver Class
				Class.forName(DRIVER);
				
				// Getting the connection Object
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		return connection;
	}
}