package com.shopping.dao.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDbConnection {
	
	private static Connection connection;
	
	public MysqlDbConnection() {

	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String url = "jdbc:mysql://localhost:3306/project1";
		String username = "root";
		String password = "root";
		
		connection = DriverManager.getConnection(url, username, password);
		
		return connection;
	}
}
