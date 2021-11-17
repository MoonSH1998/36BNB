package dao;

import java.sql.*;
import javax.naming.NamingException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import util.ConnectionPool;

	public class FeedoptionDAO
	{
		
		public int feedHeart(String no, String id) throws NamingException, SQLException{
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try
			{
				synchronized(this)
				{
					String sql = "SELECT * FROM feedHeart where id = ? and no = ?";
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
					// heart�������� ���� �� ���� �� return 1
					if(!rs.next())
					{
						sql = "insert into feedHeart values(?, ?)";
						stmt.setString(1, no);
						stmt.setString(2, id);
						return 1;
					}
					//Heart �̹� ������ �� Ŭ�� �� heart �����ϱ� return 2
					else if(rs.next())
					{
						sql = "delete from feedHeart where no = ? and id = ?";
						stmt.setString(1, no);
						stmt.setString(2, id);
						return 2;
					}
					else
					{
						return 0;
					}
				}
			}
			finally
			{
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			}
		}
		public boolean feedReport(String no, String id, String content) throws NamingException, SQLException{
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try
			{
				synchronized(this)
				{
					String sql = "insert into feedHeart values(?, ?, ?)";
					stmt.setString(1, no);
					stmt.setString(2, id);
					stmt.setString(3, content);
					
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
					return ((!rs.next())) ? false : true;
				}
			}
			finally
			{
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			}
		}
					
					
	
	
	public boolean feedoption(String jsonstr) throws NamingException, SQLException , ParseException{
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
		synchronized(this) {
	
		String sql = "SELECT list FROM feedoption ORDER BY list DESC LIMIT 1";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		int max = (!rs.next()) ? 0 : rs.getInt("list");
		stmt.close(); rs.close();
		JSONParser parser = new JSONParser();
		JSONObject jsonobj = (JSONObject)parser.parse(jsonstr);
		jsonobj.put("list", max + 1);
	
	
		// phase 2. add "user" property ------------------------------
		String uid = jsonobj.get("id").toString();
		sql = "SELECT jsonstr FROM user WHERE id = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, uid);
		rs = stmt.executeQuery();
		if (rs.next()) {
		String usrstr = rs.getString("jsonstr");
		JSONObject usrobj = (JSONObject) parser.parse(usrstr);
		}
		stmt.close(); rs.close();
		
		sql = "INSERT INTO feedoption(list, jsonstr) VALUES(?, ?)";
stmt = conn.prepareStatement(sql); 
stmt.setInt(1, max + 1);
stmt.setString(2, jsonobj.toJSONString());

int count = stmt.executeUpdate();
return (count == 1) ? true : false;
}
}finally {
if (rs != null) rs.close();
if (stmt != null) stmt.close();
if (conn != null) conn.close();
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getListfeedoption() throws NamingException, SQLException {
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		ResultSet rs = null;
	try {
		String sql = "SELECT jsonstr FROM feedoption";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		
		String str = "[";
		int cnt = 0;
		while(rs.next()) {
		if (cnt++ > 0) str += ", ";
		str += rs.getString("jsonstr");
		}
		return str + "]";

	} finally {
		if (rs != null) rs.close(); 
		if (stmt != null) stmt.close(); 
		if (conn != null) conn.close();
	}
}
	// Check_already_heart_by Moon 1111
	public boolean checkHeart(String no, String uid) throws NamingException, SQLException, ParseException {
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
		String sql = "SELECT jsonstr FROM feedoption";
		conn = ConnectionPool.get();
		stmt = conn.prepareStatement(sql);
		
		String jsonstr = rs.getString("jsonstr");
		JSONObject obj = (JSONObject) (new JSONParser()).parse(jsonstr);
		String type = obj.get("type").toString();
		String id = obj.get("id").toString();
		String fno = obj.get("no").toString();
		if (uid.equals((id))&type.equals("heart")&no.equals((fno))) return true;
		else return false;
		} finally {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close(); 
			if (conn != null) conn.close();
	    }
	}		
	
	public boolean deletereport(String list) throws NamingException, SQLException {
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		try {
			String sql = "DELETE FROM feedoption WHERE list = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, list);
	
			int count = stmt.executeUpdate();
			return (count == 1) ? true : false;
		} finally {
			if (stmt != null) stmt.close(); 
			if (conn != null) conn.close();
		}
	}
	
	
}