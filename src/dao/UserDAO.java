package dao;
import java.sql.*;
import javax.naming.NamingException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import util.ConnectionPool;

public class UserDAO {
	public boolean insert(String uid, String jsonstr) throws NamingException, SQLException {
				Connection conn = ConnectionPool.get();
				PreparedStatement stmt = null;
					
				try {
							String sql = "INSERT INTO user(id, jsonstr) VALUES(?, ?)";
							stmt = conn.prepareStatement(sql);
							stmt.setString(1, uid);
							stmt.setString(2, jsonstr);
							
							int count = stmt.executeUpdate();
							return (count == 1) ? true : false;
					
				} finally {
							if (stmt != null) stmt.close();
							if (conn != null) conn.close();
					}
				}
	//uni정보를 받아오는 함수
	public String getUni(String id) throws NamingException, SQLException, ParseException {
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			String sql = "select jsonstr from user where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			String jsonstr = rs.getString("jsonstr");
			JSONObject obj = (JSONObject) (new JSONParser()).parse(jsonstr);
			String uni = obj.get("uni").toString();
			return uni;
		} 
		finally 
		{
			if (rs != null) rs.close();
			if (stmt != null) stmt.close(); 
			if (conn != null) conn.close();
		}
	}
	
	public String getUni_json(String jsonstr) throws NamingException, SQLException, ParseException {
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			JSONObject obj = (JSONObject) (new JSONParser()).parse(jsonstr);
			String uni = obj.get("uni").toString();
			return uni;
		} 
		finally 
		{
			if (rs != null) rs.close();
			if (stmt != null) stmt.close(); 
			if (conn != null) conn.close();
		}
	}
	
	//회원가입 시 회원가입하려는 uni가 회원가입가능한 학교인지 체크->1반환시 성공
	public boolean checkUni(String uni) throws NamingException, SQLException{
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			String sql = "SELECT * FROM uni_list WHERE uniList = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, uni);	
			rs = stmt.executeQuery();
			return !(rs.next());
		} 
		finally 
		{
			if (rs != null) rs.close();
			if (stmt != null) stmt.close(); 
			if (conn != null) conn.close();
		}
	}
	
	public boolean delete(String uid) throws NamingException, SQLException {
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			try {
				String sql = "DELETE FROM user WHERE id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, uid);
		
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
		
		public int login(String uid, String ups) throws NamingException, SQLException, ParseException {
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
			String sql = "SELECT id, jsonstr FROM user WHERE id = ?";
			conn = ConnectionPool.get();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, uid);
			
			rs = stmt.executeQuery();
			if (!rs.next()) return 1;
			
			String jsonstr = rs.getString("jsonstr");
			JSONObject obj = (JSONObject) (new JSONParser()).parse(jsonstr);
			String ps = obj.get("ps").toString();
			if (uid.equals("admin")&ups.equals(ps)) return 3;
			if (!ups.equals(ps)	) return 2;
			if (!uid.equals("admin")&ups.equals(ps)) return 0;
	    } finally {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close(); 
				if (conn != null) conn.close();
		    }
			return 0;
		}	

		public String getList() throws NamingException, SQLException{
				Connection conn = ConnectionPool.get();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				try {
					String sql = "SELECT jsonstr FROM user ";
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
		public String get(String userId) throws NamingException, SQLException {
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
			String sql = "SELECT jsonstr FROM user WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			
			rs = stmt.executeQuery();
			return rs.next() ? rs.getString("jsonstr") : "{}";
			} finally {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
			}
		}
		
		//내가 쓴 글 개수 반환 함수 마이 페이지에서 쓸 예정 jsp:countMyFeed, 파라미터 : String id
		public int countMyFeed(String userId) throws NamingException, SQLException {
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try 
				{
				String sql = "select count(*) from feed where id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, userId);
				rs = stmt.executeQuery();
				return rs.next() ? rs.getInt(1) : 0;
			} finally {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
			}
		}
		public int countMyReport(String userId) throws NamingException, SQLException {
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try 
				{
				String sql = "select count(*) from feedReport where fid = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, userId);
				rs = stmt.executeQuery();
				return rs.next() ? rs.getInt(1) : 0;
			} finally {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
			}
		}
		public int countMyHeart(String userId) throws NamingException, SQLException {
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try 
				{
				String sql = "select count(*) from feedHeart where fid = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, userId);
				rs = stmt.executeQuery();
				return rs.next() ? rs.getInt(1) : 0;
			} finally {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
			}
		}
	}