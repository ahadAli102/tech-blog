package com.ahad.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionProvider {
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");  
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/tech-vlog","root","");  
		
	}

}
