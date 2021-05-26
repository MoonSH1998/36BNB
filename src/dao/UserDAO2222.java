


package dao;

import java.sql.*;
import java.util.ArrayList;
import javax.naming.NamingException;
import util.ConnectionPool;


	public class UserDAO2222 {
		
		

		public boolean insert(String uname, String ustu_num, String ubirth, String uphone_num, String uemail, String ups) throws NamingException, SQLException {
			Connection conn = null;
            PreparedStatement stmt = null;
               try {
                     conn = ConnectionPool.get();
                     conn.setAutoCommit(false);
                     String sql = "INSERT INTO user(name, stu_num, birth, phone_num, email, ps) VALUES(?, ?, ?, ?, ?, ?)";
                     stmt = conn.prepareStatement(sql);
                     stmt.setString(1, uname); 
                     stmt.setString(2, ustu_num);
                     stmt.setString(3, ubirth);
                     stmt.setString(4, uphone_num);
                     stmt.setString(5, uemail);
                     stmt.setString(6, ups);
                     int count = stmt.executeUpdate();
                     conn.commit(); 
                     return (count == 1) ? true : false;
                     } finally {
                     if (stmt != null) stmt.close();
                     if (conn != null) conn.close();
                     }
         }
		public int login(String uid, String ups) throws NamingException, SQLException {
		    Connection conn = null;
		    PreparedStatement stmt = null;
		    ResultSet rs = null;
		    try {
		        String sql = "SELECT id, password FROM user WHERE id = ?";

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
			public int signup(String uid, String uname, String ustu_num, String ubirth, String uphone_num, String ups) throws NamingException, SQLException 
			{
				Connection conn = null;
	            PreparedStatement stmt = null;
	            ResultSet rs = null;
	               try {
	                     conn = ConnectionPool.get();
	                     conn.setAutoCommit(false);
	                     String sql = "INSERT INTO user(id, name, stu_num, birth, phone_num, ps) VALUES(?, ?, ?, ?, ?, ?)";
	                     stmt = conn.prepareStatement(sql);
	                     stmt.setString(1, uid);
	                     stmt.setString(2, uname);
	                     stmt.setString(3, ustu_num);
	                     stmt.setString(4, ubirth);
	                     stmt.setString(5, uphone_num);
	                     stmt.setString(6, ups);
	                     rs = stmt.executeQuery();
	                     
	                    if (!rs.next())  return 1;//가입가능11
	 					if (uid.equals(rs.getString("id"))) return 2; //존재
	 				
	 					} finally {
	 							if(rs!=null) rs.close();
	 							if(stmt!=null) stmt.close();
	 							if (conn != null) conn.close();
	 			}
	 				 return -1;
	 			}
	 		
				
				//				Connection conn = null;
//				PreparedStatement stmt = null;
//				ResultSet rs = null;
//				
//				try {
//					String sql = "SELECT id FROM user WHERE stu_num = ?";
//					conn = ConnectionPool.get();
//					stmt = conn.prepareStatement(sql);
//					stmt.setString(1, uid);
//					rs = stmt.executeQuery();
//				
//					if (!rs.next()) return 1;//가입가능11
//					if (ustu_num.equals(rs.getString("stu_num"))) return 2; //존재
//				
//					} finally {
//							if(rs!=null) rs.close();
//							if(stmt!=null) stmt.close();
//							if (conn != null) conn.close();
//			}
//				 return -1;
//			}
		
		
			
			@SuppressWarnings("resource")
			public int delete(String uid, String uname, String ups) throws NamingException, SQLException {
	            Connection conn = null;
	            PreparedStatement stmt = null;
	            ResultSet rs = null;
	            
	            int x = -1;
	            String dbps = "";
	            try {
	                  String search_sql = "SELECT id, name, ps FROM user WHERE id= ? name = ? ps =? ";
	               String del_sql = "DELETE FROM user WHERE id= ? name = ? ps =?";
	               
	               conn = ConnectionPool.get();
	               conn.setAutoCommit(false);
	               
	               stmt = conn.prepareStatement(search_sql);
	               stmt.setString(1, uid);
                   stmt.setString(2, uname);
                   stmt.setString(3, ups);
	               rs = stmt.executeQuery();
	               
	               if(rs.next()) 
	                  dbps = rs.getString("ps");
	               if(dbps.equals("ps"));
	                  
	                     stmt = conn.prepareStatement(del_sql);
	                     stmt.setString(1, uid);
	                     stmt.setString(2, uname);
	                     stmt.setString(3, ups);
	                     stmt.executeUpdate();
	                     conn.commit();
	                     x = 1;
	               
	               return x;
	               
	            } finally {
	               if (stmt != null) stmt.close(); 
	               if (conn != null) conn.close();
	            }
	            
	         } 
			
			public ArrayList<UserObj> getList() throws NamingException, SQLException {
				Connection conn = ConnectionPool.get();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				try {
				    String sql = "insert into user (name, stu_num, birth, phone_num, email, ps) VALUES(?, ?, ?, ?, ?, ?)";
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
							