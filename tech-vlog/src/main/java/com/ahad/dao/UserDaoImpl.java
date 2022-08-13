package com.ahad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ahad.entity.User;
import com.ahad.util.DatabaseConnectionProvider;

public class UserDaoImpl implements UserDao {
	private static final String INSERT_SQL = "INSERT INTO `user_table` (`name`, `email`, `password`, `condition`) VALUES (?, ?, ?, ?)";

	@Override
	public int addUser(User user) {
		Connection conn = null;
		int response = -1;
		try {
			conn = DatabaseConnectionProvider.getConnection();
			PreparedStatement stmt = conn.prepareStatement(INSERT_SQL);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getCondition());
			System.out.println(stmt);
			response = stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return response;
	}

}
