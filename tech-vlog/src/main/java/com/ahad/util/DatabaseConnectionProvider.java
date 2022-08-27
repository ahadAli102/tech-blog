package com.ahad.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Author MD. AHAD ALI
 * Email: linkonahad10@gmail.com
 */

public class DatabaseConnectionProvider {
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		System.out.println("GET DATABASE CONNECTION");
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/tech-vlog", "root", "");

	}

}
