package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import dto.UserDTO;
import dto.WorkDTO;

public class DBManager extends DAO {
	
	
	// 一致するユーザーを探し値を返す
	public UserDTO getWorkUser(String userId, String password) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM users WHERE user_id = ? AND password = ?";
		UserDTO user = null;
		
		try {
			
			conn = getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				user = new UserDTO();
				
				user.setUserId(rset.getString(2));
				user.setName(rset.getString(3));
				user.setPassword(rset.getString(4));
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(conn);
		}
		
		return user;
	}
	
	
	
	
	// 時間を取得
	public WorkDTO getWorkDTO(UserDTO user) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM works WHERE user_id=? ORDER BY date DESC limit 1";
		WorkDTO work = null;
		
		try {
			
			conn = getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			// Calendar calendar = Calendar.getInstance();
			// SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			
			pstmt.setString(1, user.getUserId());
			// pstmt.setString(2, date.format(calendar.getTime()));
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				work = new WorkDTO();
				
				work.setUserId(rset.getString(2));
				work.setDate(rset.getString(3));
				work.setStartTime(rset.getString(4));
				work.setEndTime(rset.getString(5));
				work.setBreakTime(rset.getString(6));
				work.setEndBreakTime(rset.getString(7));
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(conn);
		}
		return work;
	}
	
	
	
	
	// 時間をセット
	public boolean setStartTime(UserDTO user) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		boolean result = false;
		String sql = "INSERT INTO works(user_id , date , start_time)VALUES(? , ? , ?)";
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat stime = new SimpleDateFormat("HH:mm:ss");
			
			// SQL文に値をセット
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, date.format(calendar.getTime()));
			pstmt.setString(3, stime.format(calendar.getTime()));
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt == 1) {
				result = true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return result;
		
		
	}
	
	
	
	
	public boolean setEndTime(UserDTO user , WorkDTO work) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		boolean result = false;
		String sql = "UPDATE works SET end_time = ? WHERE user_id = ? AND date = ?";
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat etime = new SimpleDateFormat("HH:mm:ss");
			
			pstmt.setString(1, etime.format(calendar.getTime()));
			pstmt.setString(2, user.getUserId());
			pstmt.setString(3, work.getDate());
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt == 1) {
				result = true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return result;
	}
	
	
	
	
	public boolean setBreakTime(UserDTO user, WorkDTO work) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		boolean result = false;
		String sql = "UPDATE works SET break_time = ? WHERE user_id = ? AND date = ?";
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat btime = new SimpleDateFormat("HH:mm:ss");
			
			pstmt.setString(1, btime.format(calendar.getTime()));
			pstmt.setString(2, user.getUserId());
			pstmt.setString(3, work.getDate());
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt == 1) {
				result = true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return result;
		
	}
	
	
	
	
	public boolean setEBreakTime(UserDTO user, WorkDTO work) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		boolean result = false;
		String sql = "UPDATE works SET end_break_time = ? WHERE user_id = ? AND date = ?";
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat ebtime = new SimpleDateFormat("HH:mm:ss");
			
			pstmt.setString(1, ebtime.format(calendar.getTime()));
			pstmt.setString(2, user.getUserId());
			pstmt.setString(3, work.getDate());
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt == 1) {
				result = true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return result;
		
	}
	
	
	
	
	// 社員情報をリストとして取得
	public ArrayList<UserDTO> getUserList() {
		
		Connection conn = null;
		Statement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		
		try {
			
			conn = getConnection();
			pstmt = conn.createStatement();
			
			String sql = "SELECT * FROM users ORDER BY user_id ASC";
			rset = pstmt.executeQuery(sql);
			
			while(rset.next()) {
				
				UserDTO user = new UserDTO();
				
				user.setUserId(rset.getString(2));
				user.setName(rset.getString(3));
				user.setPassword(rset.getString(4));
				
				list.add(user);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(conn);
		}
		
		return list;
		
	}
	
	
	
	
	public ArrayList<WorkDTO> getWorkList() {
		
		Connection conn = null;
		Statement pstmt = null;
		ResultSet rset = null;
			
		ArrayList<WorkDTO> list = new ArrayList<WorkDTO>();
			
		try {
				
			conn = getConnection();
			pstmt = conn.createStatement();
				
			String sql = "SELECT * FROM works AS m WHERE date = ( SELECT MAX(date) FROM works AS s WHERE m.user_id = s.user_id) ORDER BY user_id ASC";
			rset = pstmt.executeQuery(sql);
			
			while(rset.next()) {
					
				WorkDTO work = new WorkDTO();
					
				work.setUserId(rset.getString(2));
				work.setDate(rset.getString(3));
				work.setStartTime(rset.getString(4));
				work.setEndTime(rset.getString(5));
				work.setBreakTime(rset.getString(6));
				work.setEndBreakTime(rset.getString(7));
				
				list.add(work);
			}
				
		} catch(SQLException e) {
				e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(conn);
		}
		
		return list;
		
	}
	
	
	
	
	
	
}
