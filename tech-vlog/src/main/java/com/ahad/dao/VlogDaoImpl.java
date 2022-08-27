package com.ahad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ahad.entity.User;
import com.ahad.entity.Vlog;
import com.ahad.entity.VlogRating;
import com.ahad.util.DatabaseConnectionProvider;

public class VlogDaoImpl implements VlogDao {
	private static final String INSERT_SQL = "INSERT INTO `vlog_table` (`title`, `description`, `time`, `email`) VALUES (?, ?, ?, ?);";
	private static final String GET_AUTHOR_VLOG = "SELECT * from `vlog_table` WHERE vlog_table.email = ? ORDER BY vlog_table.id DESC";
	private static final String GET_VLOG_BY_ID = "SELECT * from `vlog_table` WHERE vlog_table.id = ?";
	private static final String GET_USER_VLOG_RATING = "SELECT COUNT(vlog_rating_table.vlog_id) as user_vote from vlog_rating_table WHERE vlog_rating_table.vlog_id = ? AND vlog_rating_table.email = ?;";
	private static final String RATE_VLOG = "INSERT INTO `vlog_rating_table`(`vlog_id`, `email`, `rating`) VALUES (?, ?, ?)";
	private static final String RATING_OF_VLOG = "SELECT AVG(vlog_rating_table.rating) AS avg_rating, COUNT(vlog_rating_table.vlog_id) AS total_votes FROM vlog_rating_table WHERE vlog_rating_table.vlog_id = ?;";
	private static final String VLOG_AUTHOR = "SELECT * FROM user_table WHERE email=?";
	private static final String DELETE_VLOG= "DELETE FROM vlog_table WHERE vlog_table.id = ?";
	private static final String EDIT_VLOG = "UPDATE `vlog_table` SET `title`= ?,`description`= ?,`time`= ? WHERE vlog_table.id = ?";
	private static final String GET_ALL_VLOG = "SELECT * from `vlog_table` ORDER BY vlog_table.id DESC";
	private static final String GET_QUERY_VLOG = "SELECT * from `vlog_table` WHERE vlog_table.title LIKE %?% OR vlog_table.description ORDER BY vlog_table.id DESC";

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
	public List<Vlog> getAuthorVlogs(String email) {
		List<Vlog> vlogs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DatabaseConnectionProvider.getConnection();
			stmt = conn.prepareStatement(GET_AUTHOR_VLOG);
			stmt.setString(1, email);
			System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (vlogs == null) {
					vlogs = new ArrayList<Vlog>();
					System.out.println("vlog dao init vlogs");
				}
				String description = rs.getString("description");
				boolean big = false;
				if (description.length() > 130) {
					description = description.substring(0, 115).concat("   read more...");
					big = true;
				}
				Vlog vlog = new Vlog(rs.getInt("id"), rs.getString("title"), description, rs.getString("email"));

				long time = rs.getLong("time");
				SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy 'at' hh:mm aaa");
				Date resultdate = new Date(time);
				vlog.setLastUpdate(sdf.format(resultdate));
				vlog.setBig(big);
				vlogs.add(vlog);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("vlog dao get vlogs classnotfound");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("vlog dao get vlogs sql");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("vlog dao get vlogs exception");
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				System.out.println("vlog dao get vlogs stmt close");
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println("vlog dao get vlogs conn close");
				e.printStackTrace();
			}
		}
		return vlogs;
	}

	@Override
	public Vlog getVlog(int id) {
		Vlog vlog = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DatabaseConnectionProvider.getConnection();
			stmt = conn.prepareStatement(GET_VLOG_BY_ID);
			stmt.setInt(1, id);
			System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String description = rs.getString("description");
				vlog = new Vlog(rs.getInt("id"), rs.getString("title"), description, rs.getString("email"));
				long time = rs.getLong("time");
				SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy 'at' hh:mm aaa");
				Date resultdate = new Date(time);
				vlog.setLastUpdate(sdf.format(resultdate));
			}
		} catch (ClassNotFoundException e) {
			System.out.println("vlog dao getVlog classnotfound");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("vlog dao getVlog sql");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("vlog dao getVlog exception");
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				System.out.println("vlog dao getVlog stmt close");
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println("vlog dao getVlog conn close");
				e.printStackTrace();
			}
		}
		return vlog;
	}

	@Override
	public boolean isRatingExist(VlogRating vr) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DatabaseConnectionProvider.getConnection();
			stmt = conn.prepareStatement(GET_USER_VLOG_RATING);
			stmt.setInt(1, vr.getVlogId());
			stmt.setString(2, vr.getRaterEmail());
			System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int vote = rs.getInt("user_vote");
				return vote != 0;
			}
		} catch (ClassNotFoundException e) {
			System.out.println("vlog dao isRatingExist classnotfound");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("vlog dao isRatingExist sql");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("vlog dao isRatingExist exception");
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				System.out.println("vlog dao isRatingExist stmt close");
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println("user dao isRatingExistr conn close");
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public void rateVlog(VlogRating vr) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DatabaseConnectionProvider.getConnection();
			stmt = conn.prepareStatement(RATE_VLOG);
			stmt.setInt(1, vr.getVlogId());
			stmt.setString(2, vr.getRaterEmail());
			stmt.setInt(3, vr.getVlogRating());
			System.out.println(stmt);
			stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	}

	@Override
	public Map<String, Object> getVlogRating(int id) {
		Map<String, Object> rating = new HashMap<String, Object>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DatabaseConnectionProvider.getConnection();
			stmt = conn.prepareStatement(RATING_OF_VLOG);
			stmt.setInt(1, id);
			System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				rating.put("avg_rating", rs.getObject("avg_rating"));
				rating.put("total_votes", rs.getInt("total_votes"));
			}
		} catch (ClassNotFoundException e) {
			System.out.println("vlog dao getVlogRating classnotfound");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("vlog dao getVlogRating sql");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("vlog dao getVlogRating exception");
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				System.out.println("vlog dao getVlogRating stmt close");
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println("user dao getVlogRating conn close");
				e.printStackTrace();
			}
		}
		return rating;
	}

	@Override
	public User getVlogAuthor(String email) {
		User vlogAuthor = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DatabaseConnectionProvider.getConnection();
			stmt = conn.prepareStatement(VLOG_AUTHOR);
			stmt.setString(1, email);
			System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				vlogAuthor = new User();
				vlogAuthor.setName(rs.getString("name"));
				vlogAuthor.setEmail(rs.getString("email"));
				System.out.println(rs.getString("name")+"    "+rs.getString("email"));
			}
		} catch (ClassNotFoundException e) {
			System.out.println("vlog dao get vlogs classnotfound");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("vlog dao get vlogs sql");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("vlog dao get vlogs exception");
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				System.out.println("vlog dao get vlogs stmt close");
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println("vlog dao get vlogs conn close");
				e.printStackTrace();
			}
		}
		return vlogAuthor;
	}

	@Override
	public int deleteVlog(int vlogId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int response = -1;
		try {
			conn = DatabaseConnectionProvider.getConnection();
			stmt = conn.prepareStatement(DELETE_VLOG);
			stmt.setInt(1, vlogId);
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
	public int editvlog(int vlogId, String title, String description) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int response = -1;
		try {
			conn = DatabaseConnectionProvider.getConnection();
			stmt = conn.prepareStatement(EDIT_VLOG);
			stmt.setString(1, title);
			stmt.setString(2, description);
			stmt.setLong(3, System.currentTimeMillis());
			stmt.setInt(4, vlogId);
			System.out.println(stmt);
			response = stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	public List<Vlog> getVlogs() {

		List<Vlog> vlogs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DatabaseConnectionProvider.getConnection();
			stmt = conn.prepareStatement(GET_ALL_VLOG);
			System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (vlogs == null) {
					vlogs = new ArrayList<Vlog>();
					System.out.println("vlog dao init vlogs");
				}
				String description = rs.getString("description");
				boolean big = false;
				if (description.length() > 130) {
					description = description.substring(0, 115).concat("   read more...");
					big = true;
				}
				Vlog vlog = new Vlog(rs.getInt("id"), rs.getString("title"), description, rs.getString("email"));

				long time = rs.getLong("time");
				SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy 'at' hh:mm aaa");
				Date resultdate = new Date(time);
				vlog.setLastUpdate(sdf.format(resultdate));
				vlog.setBig(big);
				vlogs.add(vlog);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("vlog dao get vlogs classnotfound");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("vlog dao get vlogs sql");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("vlog dao get vlogs exception");
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				System.out.println("vlog dao get vlogs stmt close");
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println("vlog dao get vlogs conn close");
				e.printStackTrace();
			}
		}
		return vlogs;
	}

	@Override
	public List<Vlog> getVlogs(String query) {
		// TODO Auto-generated method stub
		return null;
	}

}
