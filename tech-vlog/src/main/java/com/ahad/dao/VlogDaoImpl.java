package com.ahad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ahad.entity.Vlog;
import com.ahad.util.DatabaseConnectionProvider;

public class VlogDaoImpl implements VlogDao {
	private String INSERT_SQL = "INSERT INTO `vlog_table` (`title`, `description`, `time`, `email`) VALUES (?, ?, ?, ?);";

	@Override
	public int addVlog(Vlog vlog) {
		System.out.println("adding vlog");
		Connection conn = null;
		PreparedStatement stmt = null;
		int response = -1;
		try {
			conn = DatabaseConnectionProvider.getConnection();
			stmt = conn.prepareStatement(INSERT_SQL);
			stmt.setString(1, vlog.getTitle());
			stmt.setString(2, vlog.getDescription());
			stmt.setLong(3, System.currentTimeMillis());
			stmt.setString(4, vlog.getEmail());
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

}
