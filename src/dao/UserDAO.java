package dao;
import java.sql.*;
import java.util.ArrayList;
import javax.naming.NamingException;
import util.ConnectionPool;


	public class UserDAO {
		
	
		public boolean insert(String uid, String uname, String ustu_num, String ubirth, String uphone_num, String ups) throws NamingException, SQLException {
				Connection conn = ConnectionPool.get();
				PreparedStatement stmt = null;
					try {
							String sql = "INSERT INTO user(id, name, stu_num, birth, phone_num, ps) VALUES(?, ?, ?, ?, ?, ?)";
							stmt = conn.prepareStatement(sql);
							stmt.setString(1, uid);
							stmt.setString(2, uname); 
							stmt.setString(3, ustu_num);
							stmt.setString(4, ubirth);
							stmt.setString(5, uphone_num);
							stmt.setString(6, ups);
							int count = stmt.executeUpdate();
							return (count == 1) ? true : false;
							} finally {
							if (stmt != null) stmt.close();
							if (conn != null) conn.close();
							}
			}
		public boolean delete(String uid, String uname, String ups) throws NamingException, SQLException {
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			try {
				String sql = "DELETE FROM user WHERE id = ? and name = ? and ps =?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, uid);
				stmt.setString(2, uname);
				stmt.setString(3, ups);
		
				int count = stmt.executeUpdate();
				return (count == 1) ? true : false;
				
			} finally {
				if (stmt != null) stmt.close(); 
				if (conn != null) conn.close();
			}
		}
		
		public boolean exists(String uid) throws NamingException, SQLException {
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				String sql = "SELECT id FROM user WHERE id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, uid);
		
				rs = stmt.executeQuery();
				return rs.next();
				
			} finally {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close(); 
				if (conn != null) conn.close();
			}
		}
		
	
		
		public int login(String uid, String ups) throws NamingException, SQLException {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
			String sql = "SELECT id, ps FROM user WHERE id = ?";
			conn = ConnectionPool.get();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, uid);
			rs = stmt.executeQuery();
			if (!rs.next()) return 1;
			if (!ups.equals(rs.getString("ps"))) return 2;
			return 0;
		
		    } finally {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close(); 
				if (conn != null) conn.close();
		    }
		}	
							
			public ArrayList<UserObj> getList() throws NamingException, SQLException {
				Connection conn = ConnectionPool.get();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				try {
				    String sql = "SELECT * FROM user ORDER BY ts DESC";
				    stmt = conn.prepareStatement(sql);
				    rs = stmt.executeQuery();
				    
				    ArrayList<UserObj> users = new ArrayList<UserObj>();
			        while(rs.next()) {
			        	users.add(new UserObj(rs.getString("name"), rs.getString("stu_nume"), rs.getString("birth"), rs.getString("phone_num"), rs.getString("email"), rs.getString("ps")));
			        }
				    return users;
					
				} finally {
					if (rs != null) rs.close(); 
					if (stmt != null) stmt.close(); 
					if (conn != null) conn.close();
				}
			}
		}