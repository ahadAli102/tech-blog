package com.ahad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ahad.entity.Vlog;
import com.ahad.util.DatabaseConnectionProvider;

public class VlogDaoImpl implements VlogDao {
	private String INSERT_SQL = "INSERT INTO `vlog_table` (`title`, `description`, `time`, `email`) VALUES (?, ?, ?, ?);";
	private String GET_VLOG = "SELECT * from `vlog_table` WHERE vlog_table.email = ? ORDER BY vlog_table.id DESC";

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

	@Override
	public List<Vlog> getVlogs(String email) {
		List<Vlog> vlogs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DatabaseConnectionProvider.getConnection();
			stmt = conn.prepareStatement(GET_VLOG);
			stmt.setString(1, email);
			System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				if(vlogs == null) {
					vlogs = new ArrayList<Vlog>();
					System.out.println("vlog dao init vlogs");
				}
				Vlog vlog = new Vlog(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getString("email"));
				
				long time = rs.getLong("time");
				SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy 'at' hh:mm aaa");    
				Date resultdate = new Date(time);
				vlog.setLastUpdate(sdf.format(resultdate));
				vlogs.add(vlog);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("user dao getUser classnotfound");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("user dao getUser sql");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("user dao getUser exception");
			e.printStackTrace();
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
		return vlogs;
	}

}
