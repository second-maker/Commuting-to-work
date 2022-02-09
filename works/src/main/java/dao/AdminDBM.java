package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import dto.AdminDTO;
import dto.WorkDTO;

public class AdminDBM  extends DAO {
	
	
	//ログイン
	public AdminDTO getLoginUser(String adminId, String password) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM admins WHERE admin_id = ? AND password = ?";
		
		AdminDTO admin = null;
		
		try {
			
			conn = getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, adminId);
			pstmt.setString(2, password);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				admin = new AdminDTO();
				
				admin.setUserId(rset.getString(2));
				admin.setName(rset.getString(3));
				admin.setPassword(rset.getString(4));
				
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
			close(conn);
			
			
		}
		
		return admin;
		
	}
	
	
	
	// 新規登録
	public boolean setRegist(String userId, String password, String name) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		boolean result = false;
		String sql = "INSERT INTO users(user_id, name, password)VALUES(?,?,?);";
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			// SQL文に値をセット
			pstmt.setString(1, userId);
			pstmt.setString(2, name);
			pstmt.setString(3, password);
			
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
	
	
	// 新規で日付もつける。
	public boolean setDate(String userId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		boolean result = false;
		String sql = "INSERT INTO works(user_id)VALUES(?)";
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
//			Calendar calendar = Calendar.getInstance();
//			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
//			SimpleDateFormat etime = new SimpleDateFormat("HH:mm:ss");
			
			
			pstmt.setString(1, userId);
//			pstmt.setString(2, date.format(calendar.getTime()));
//			pstmt.setString(3, etime.format(calendar.getTime()));
			
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
	
	
	
	
	// 編集
	public ArrayList<WorkDTO> getWorksList(String userName) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM users u INNER JOIN works w ON u.user_id = w.user_id HAVING u.name = ? ORDER BY w.date DESC LIMIT 3;";
		
		ArrayList<WorkDTO> list = null;
		
		try {
			
			conn = getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			rset = pstmt.executeQuery();
			
			
				
			list = new ArrayList<WorkDTO>();
			
			while(rset.next()) {
				
				WorkDTO work = new WorkDTO();
				
				work.setDate(rset.getString(7));
				work.setStartTime(rset.getString(8));
				work.setEndTime(rset.getString(9));
				work.setBreakTime(rset.getString(10));
				work.setEndBreakTime(rset.getString(11));
				
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
	
	
	
	// editList.jsp 入力確認
	public WorkDTO getSelectWork(String userName, String date) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		WorkDTO work = null;
		String sql = "SELECT * FROM users u INNER JOIN works w ON u.user_id = w.user_id HAVING u.name = ? AND w.date = ?;";
		
		try {
			
			conn = getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, date);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				work = new WorkDTO();
				
				work.setUserId(rset.getString(6));
				work.setDate(rset.getString(7));
				work.setStartTime(rset.getString(8));
				work.setEndTime(rset.getString(9));
				work.setBreakTime(rset.getString(10));
				work.setEndBreakTime(rset.getString(11));
				
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
	
	
	
	// 編集 UPDATE
	public boolean changeWork1(String time , String date, WorkDTO work) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		boolean result = false;
		String sql = "UPDATE works SET start_time = ? WHERE date = ? AND user_id = ?;";
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, time);
			pstmt.setString(2, date);
			pstmt.setString(3, work.getUserId());
			
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
	
	
	public boolean changeWork2(String time , String date, WorkDTO work) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		boolean result = false;
		String sql = "UPDATE works SET end_time = ? WHERE date = ? AND user_id = ?;";
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, time);
			pstmt.setString(2, date);
			pstmt.setString(3, work.getUserId());
			
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
	
	
	public boolean changeWork3(String time , String date, WorkDTO work) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		boolean result = false;
		String sql = "UPDATE works SET break_time = ? WHERE date = ? AND user_id = ?;";
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, time);
			pstmt.setString(2, date);
			pstmt.setString(3, work.getUserId());
			
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
	
	
	public boolean changeWork4(String time , String date, WorkDTO work) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		boolean result = false;
		String sql = "UPDATE works SET end_break_time = ? WHERE date = ? AND user_id = ?;";
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, time);
			pstmt.setString(2, date);
			pstmt.setString(3, work.getUserId());
			
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
	
	
	
	
	
	
	//編集 DELETE
	public boolean deleteWork1(String date, WorkDTO work) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		boolean result = false;
		String sql = "UPDATE works SET start_time = null WHERE date = ? AND user_id = ?;";
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, date);
			pstmt.setString(2, work.getUserId());
			
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
	
	
	public boolean deleteWork2(String date, WorkDTO work) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		boolean result = false;
		String sql = "UPDATE works SET end_time = null WHERE date = ? AND user_id = ?;";
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, date);
			pstmt.setString(2, work.getUserId());
			
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
	
	
	public boolean deleteWork3(String date, WorkDTO work) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		boolean result = false;
		String sql = "UPDATE works SET break_time = null WHERE date = ? AND user_id = ?;";
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, date);
			pstmt.setString(2, work.getUserId());
			
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
	
	
	public boolean deleteWork4(String date, WorkDTO work) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		boolean result = false;
		String sql = "UPDATE works SET end_break_time = null WHERE date = ? AND user_id = ?;";
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, date);
			pstmt.setString(2, work.getUserId());
			
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
	
	
	
	
	// calcPeriod での BETWEEN////////////////////////////////////
	public ArrayList<WorkDTO> getCalcList(String userName, String start, String end) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM users u INNER JOIN works w ON u.user_id = w.user_id HAVING u.name = ? AND w.date BETWEEN ? AND ?;";
		
		ArrayList<WorkDTO> list = null;
		
		try {
			
			conn = getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, start);
			pstmt.setString(3, end);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				list = new ArrayList<WorkDTO>();
				
				while(rset.next()) {
					
					WorkDTO work = new WorkDTO();
					
					work.setDate(rset.getString(7));
					work.setStartTime(rset.getString(8));
					work.setEndTime(rset.getString(9));
					work.setBreakTime(rset.getString(10));
					work.setEndBreakTime(rset.getString(11));
					
					list.add(work);
					
					
				}
				
				
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
	
	
	
	
	
	// 給与計算　DB使わない
	
	public int CalcSalary(String strStartTime, String strEndTime) {
		
		int sum = 0;
		
		if(strEndTime.equals(null)) {
			
			strStartTime = strStartTime.replace(":", "");
			
			int StartTime = Integer.parseInt(strStartTime);
			
			// HH mm をそれぞれ抽出
			int sHH = StartTime / 10000;
			int smmss = StartTime % 10000;
			// 053031 の時 3031 なので
			int smm = smmss / 100;
			
			int sTime = sHH * 60 + smm;
			
			double workTime = sTime / 60 * 900;
			
			// 時給900円
			sum += (int)workTime;
			
			
		} else if(strStartTime.equals(null)) {
			
			
			strEndTime = strEndTime.replace(":", "");
			
			int EndTime = Integer.parseInt(strEndTime);
			
			
			int eHH = EndTime / 10000;
			int emmss = EndTime % 10000;
			int emm = EndTime / 100;

			int eTime = eHH * 60 + emm;
			
			// 日付をまたいでるので23:59 2359 → 1439 から引く
			
			int over = 1439;
			
			
			double workTime = (over - eTime) / 60 * 900;
			
			// 時給900円
			sum += (int)workTime;
			
			
		} else {
			
			
			strStartTime = strStartTime.replace(":", "");
			strEndTime = strEndTime.replace(":", "");
			
			//18:00:00 → 180000
			int StartTime = Integer.parseInt(strStartTime);
			int EndTime = Integer.parseInt(strEndTime);
			
			// HH mm をそれぞれ抽出
			int sHH = StartTime / 10000;
			int smmss = StartTime % 10000;
			// 183031 の時 3031 なので
			int smm = smmss / 100;
			
			int eHH = EndTime / 10000;
			int emmss = EndTime % 10000;
			int emm = EndTime / 100;
			
			int sTime = sHH * 60 + smm;
			int eTime = eHH * 60 + emm;
			
			// 360 
			double workTime = (eTime - sTime) / 60 * 900;
			
			// 時給900円
			sum += (int)workTime;
			
			
		}
		
		return sum;
		
	}
	
	
	
	// 名前だけでuser_id 取るやつ
	public String getUserId(String userName) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM users WHERE name = ?;";
		
		String userId = null;
		
		try {
			
			conn = getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				userId = rset.getString(2);
				
			}
			
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(rset);
			close(pstmt);
			close(conn);
			
		}
		
		return userId;
		
		
	}
	
	
	
	// 給料ぶち込むやつ
	public boolean setSalary(String userId ,int sum) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		boolean result = false;
		String sql = "INSERT INTO salary(id, user_id, salary_day, money)VALUE(0, ?, ?, ?);";
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			
			pstmt.setString(1, userId);
			pstmt.setString(2, date.format(calendar.getTime()));
			pstmt.setInt(3, sum);
			
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
	
	
	
	// 給料一覧
	public ArrayList<ArrayList<String>> getCalcTable() {
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		
		Connection conn = null;
		Statement pstmt = null;
		ResultSet rset = null;
		
		try {
			
			conn = getConnection();
			pstmt = conn.createStatement();
			
			String sql = "SELECT u.id,u.name,MAX(salary_day),money FROM users u RIGHT JOIN salary s ON s.user_id = u.user_id GROUP BY u.id,u.name;";
			rset = pstmt.executeQuery(sql);
			
			while(rset.next()) {
				
				ArrayList<String> array = new ArrayList<>();
				
				array.add(rset.getString(2));
				array.add(rset.getString(3));
				array.add(rset.getString(4));
				
				list.add(array);
				
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