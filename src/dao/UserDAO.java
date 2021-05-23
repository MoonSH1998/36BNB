package dao;

import java.sql.*;
import java.util.ArrayList;
import javax.naming.NamingException;
import util.ConnectionPool;


	public class UserDAO {
		
		
		public boolean insert(String uname, String ustu_num, String ubirth, String uphone_num, String uemail, String ups) throws NamingException, SQLException {
				Connection conn = ConnectionPool.get();
				PreparedStatement stmt = null;
					try {
							String sql = "INSERT INTO user(name, stu_num, birth, phone_num, email, ps) VALUES(?, ?, ?, ?, ?, ?)";
							stmt = conn.prepareStatement(sql);
							stmt.setString(1, uname); 
							stmt.setString(2, ustu_num);
							stmt.setString(3, ubirth);
							stmt.setString(4, uphone_num);
							stmt.setString(5, uemail);
							stmt.setString(6, ups);
							int count = stmt.executeUpdate();
							return (count == 1) ? true : false;
							} finally {
							if (stmt != null) stmt.close();
							if (conn != null) conn.close();
							}
			}
			public int signup(String ustu_num) throws NamingException, SQLException 
			{
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				
				try {
					String sql = "SELECT stu_num FROM user WHERE stu_num = ?";
					conn = ConnectionPool.get();
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, ustu_num);
					rs = stmt.executeQuery();
				
					if (!rs.next()) 	return 1; //가입가능
					if (ustu_num.equals(rs.getString("stu_num"))) return 2; //존재
				
					} finally {
							if(rs!=null) rs.close();
							if(stmt!=null) stmt.close();
							if (conn != null) conn.close();
			}
				 return -1;
			}
			
	
			public int signup_ex(String uname, String ustu_num, String ubirth, String uphone_num, String uemail, String ups) throws NamingException, SQLException 
			{
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				try {
				String sql = "SELECT stu_num FROM user WHERE stu_num = ?";
				conn = ConnectionPool.get();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, ustu_num);
				rs = stmt.executeQuery();
				if (!rs.next()) return 1;
				else if (ustu_num.equals(rs.getString("stu_num"))) return 2;
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
