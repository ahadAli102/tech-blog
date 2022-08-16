package com.ahad.dao;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ahad.entity.User;
import com.ahad.util.DatabaseConnectionProvider;

public class UserDaoImpl implements UserDao {
	private static final String INSERT_SQL = "INSERT INTO `user_table` (`name`, `email`, `password`, `condition`) VALUES (?, ?, ?, ?)";
	private static final String GET_USER = "SELECT * FROM user_table WHERE email=? AND password=? ";
	private static final String INSERT_IMAGE = "INSERT INTO `profile_image` (`id`, `name`, `type`, `image`,  `email`) VALUES (NULL, ?, ?, ?, ?)";

	@Override
	public int addUser(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int response = -1;
		try {
			conn = DatabaseConnectionProvider.getConnection();
			stmt = conn.prepareStatement(INSERT_SQL);
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
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return response;
	}

	@Override
	public User getUser(String email, String password) {
		System.out.println("Get User");
		User user = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DatabaseConnectionProvider.getConnection();
			stmt = conn.prepareStatement(GET_USER);
			stmt.setString(1, email);
			stmt.setString(2, password);
			System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user = new User(rs.getString("name"), rs.getString("email"), rs.getString("password"),
						rs.getString("condition"));
			}
		} catch (ClassNotFoundException e) {
			System.out.println("user dao getUser classnotfound");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("user dao getUser sql");
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				System.out.println("user dao getUser stmt close");
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println("user dao getUser conn close");
				e.printStackTrace();
			}
		}

		return user;
	}

	@Override
	public int insertProfileImage(byte[] image, String fileName, String type, String email) {
		int response = -1;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DatabaseConnectionProvider.getConnection();
			stmt = conn.prepareStatement(INSERT_IMAGE);
			stmt.setString(1, fileName);
			stmt.setString(2, type);
			int length1 = image.length;
			InputStream is = new ByteArrayInputStream(image);
			stmt.setBinaryStream(3, is, length1);
			stmt.setString(4, email);
			System.out.println(stmt);
			response = stmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			System.out.println("user dao getUser classnotfound");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("user dao getUser sql");
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				System.out.println("user dao getUser stmt close");
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println("user dao getUser conn close");
				e.printStackTrace();
			}
		}

		return response;
	}

}
