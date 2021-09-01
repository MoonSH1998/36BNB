package dao;

import java.sql.*;
import javax.naming.NamingException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import util.ConnectionPool;

public class FeedoptionDAO {
	
	public boolean report(String jsonstr) throws NamingException, SQLException , ParseException{
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