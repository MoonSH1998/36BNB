package dao;
import java.sql.*;

import javax.naming.NamingException;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import util.ConnectionPool;


	public class FeedDAO 
	{
		public boolean insert(String jsonstr, String uni) throws NamingException, SQLException , ParseException
		{
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try
			{
				synchronized(this)
				{
					String sql = "SELECT no FROM feed ORDER BY no DESC LIMIT 1";
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
					int max = (!rs.next()) ? 0 : rs.getInt("no");
					stmt.close(); rs.close();
					JSONParser parser = new JSONParser();
					JSONObject jsonobj = (JSONObject) parser.parse(jsonstr);
					jsonobj.put("no", max + 1);
		
					String uid = jsonobj.get("id").toString();
					sql = "SELECT jsonstr FROM user WHERE id = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, uid);
					rs = stmt.executeQuery();
					if (rs.next())
					{
						String usrstr = rs.getString("jsonstr");
						JSONObject usrobj = (JSONObject) parser.parse(usrstr);
						usrobj.remove("password");
						jsonobj.put("user", usrobj);
					}
					stmt.close(); rs.close();
					sql = "INSERT INTO feed(no, id, uni, jsonstr) VALUES(?, ?, ?, ?)";
					stmt = conn.prepareStatement(sql); 
					stmt.setInt(1, max + 1);
					stmt.setString(2, uid);
					stmt.setString(3, uni);
					stmt.setString(4, jsonobj.toJSONString());
					
					int count = stmt.executeUpdate();
					return (count == 1) ? true : false;
				}
			}
			finally
			{
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			}
		}
		/*
		//insert uni적용 완료_1116
		public boolean insert(String jsonstr, String uni) throws NamingException, SQLException , ParseException{
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try
			{
				synchronized(this)
				{
					String sql = "SELECT no FROM feed ORDER BY no DESC LIMIT 1";
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
					int max = (!rs.next()) ? 0 : rs.getInt("no");
					
					stmt.close(); rs.close();
					JSONParser parser = new JSONParser();
					JSONObject jsonobj = (JSONObject) parser.parse(jsonstr);
					jsonobj.put("no", max + 1);

					String uid = jsonobj.get("id").toString();
					conn = ConnectionPool.get();
					sql = "SELECT jsonstr FROM user WHERE id = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, uid);
					rs = stmt.executeQuery();
			
					String usrstr = rs.getString("jsonstr");
					JSONObject usrobj = (JSONObject)parser.parse(usrstr);
					usrobj.remove("ps");
					jsonobj.put("user", usrobj);
			
					sql = "INSERT INTO feed(no, id, uni, jsonstr) VALUES(?, ?, ?, ?)";
					stmt = conn.prepareStatement(sql); 
					stmt.setInt(1, max + 1);
					stmt.setString(2, uid);
					stmt.setString(3, uni);
					stmt.setString(4, jsonobj.toJSONString());
					int count = stmt.executeUpdate();
					return (count == 1) ? true : false;
				}
			}
			finally
			{
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			}
		}
		*/
		public boolean deletefeed(String no) throws NamingException, SQLException
		{
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			try
			{
				String sql = "DELETE FROM feed WHERE no = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, no);
				int count = stmt.executeUpdate();
				return (count == 1) ? true : false;
			}
			finally
			{
				if (stmt != null) stmt.close(); 
				if (conn != null) conn.close();
			}
		}
			
		public String getList() throws NamingException, SQLException
		{
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try
			{
				String sql = "SELECT jsonstr FROM feed";
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				String str = "[";
				int cnt = 0;
				while(rs.next())
				{
					if (cnt++ > 0) str += ", ";
					str += rs.getString("jsonstr");
				}
				return str + "]";
			}
			finally
			{
				if (rs != null) rs.close(); 
				if (stmt != null) stmt.close(); 
				if (conn != null) conn.close();
			}
		}
			
		public String getGroup(String maxNo, String uni) throws NamingException, SQLException
		{
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try
			{
				String sql = "SELECT jsonstr FROM feed where uni = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, uni);
				if (maxNo != null)
				{
					sql += " WHERE no < ?";
					stmt.setString(1, maxNo);
				}
				sql += " ORDER BY no DESC LIMIT 5 ";
				rs = stmt.executeQuery();
				String str = "[";
				int cnt = 0;
				while(rs.next())
				{
					if (cnt++ > 0) str += ", ";
					str += rs.getString("jsonstr");
				}
				return str + "]";
			}
			finally
			{
				if (rs != null) rs.close(); 
				if (stmt != null) stmt.close(); 
				if (conn != null) conn.close();
			}
		}		
	
	
}