package com.pp.cruiselogsql.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnectionH2 {
	private static final String DRIVER = "org.h2.Driver";
	private static final String URL = "jdbc:h2:file:~/CruiseDBjdbc/dbjdbc";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "";

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