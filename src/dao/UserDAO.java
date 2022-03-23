package dao;
import java.sql.*;
import javax.naming.NamingException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import util.ConnectionPool;

public class UserDAO {
	private Connection conn;//�����ͺ��̽��� �����ϰ� ���ִ� �ϳ��� ��ü
	private PreparedStatement pstmt;//
	private ResultSet rs;//������ ���� �� �ִ� ��ü

	
	/*
	public UserDAO() {//mysql�� ������ �ϰ� ����,�ڵ����� �����ͺ��̽� Ŀ�ؼ��� �Ͼ
		try {//����ó��
			String dbURL = "jdbc:mysql://localhost:3306/mysns?serverTimezone=UTC";
			String dbID="root";
			String dbPasseord="Ahehfdl7!";
			Class.forName("com.mysql.jdbc.Driver");//mysql����̹��� ã�´�.
			//����̹��� mysql�� ������ �� �ֵ��� �Ű�ü ������ �ϴ� �ϳ��� ���̺귯��
			conn=DriverManager.getConnection(dbURL,dbID,dbPasseord);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	*/
	/*
	public boolean insertprofile(String jsonstr, String id) throws NamingException, SQLException, ParseException{
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			synchronized(this) {
			String sql = "UPDATE user SET jsonstr = ? where id = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, jsonstr);
			stmt.setString(2, id);
			
			int count = stmt.executeUpdate();
			return (count == 1) ? true : false;
			}
			} finally {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
			}
		}
		*/
/*
		public boolean insertprofile(String jsonstr1) throws NamingException, SQLException, ParseException{
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		try {
			synchronized(this) {
					JSONObject obj1 = (JSONObject) (new JSONParser()).parse(jsonstr1);
					String uid = obj1.get("id").toString();
					obj1.remove("id");
					
					String sql = "select jsonstr from user where id = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, uid);
					rs = stmt.executeQuery();		
					if (!rs.next()) return false;
					String jsonstr = rs.getString("jsonstr");
					JSONObject obj = (JSONObject) (new JSONParser()).parse(jsonstr);
					
					if(obj.get("images")!=null)
					{
						obj.remove("images");
					}
					obj.put(jsonstr, obj1.toJSONString());
					
					sql = "UPDATE user SET jsonstr = ? where id = ?";
				
				stmt1 = conn.prepareStatement(sql);
				stmt1.setString(1, obj.toJSONString());
				stmt1.setString(2, uid);
				int count = stmt1.executeUpdate();
				stmt1.close(); 
				return (count == 1) ? true : false;
	
			}
			} finally {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (stmt1 != null) stmt1.close();
			if (conn != null) conn.close();
			}
		}
	
String json = response.toJSONString()

��ó: https://kingpodo.tistory.com/11 [ŷ������ �ڵ�]
		

	public boolean insertprofile(String jsonstr1) throws NamingException, SQLException, ParseException{
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			synchronized(this) {
					JSONObject obj1 = (JSONObject) (new JSONParser()).parse(jsonstr1);
					String uid = obj1.get("id").toString();
					String images = obj1.get("images").toString();
					
					String sql = "update user set jsonstr = json_set(jsonstr, '$.images', ? ) where id = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, uid);
					int count = stmt.executeUpdate();
					stmt.close(); 
					return (count == 1) ? true : false;
					
	
			}
			} finally {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
			}
		}
	*/
	
	public boolean insertprofile(String jsonstr1) throws NamingException, SQLException, ParseException{
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		try {
			synchronized(this) {
					JSONObject obj1 = (JSONObject) (new JSONParser()).parse(jsonstr1);
					String uid = obj1.get("id").toString();
					
					String sql = "select jsonstr from user where id = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, uid);
					rs = stmt.executeQuery();		
					if (!rs.next()) return false;
					String jsonstr = rs.getString("jsonstr");
					
					JSONObject obj = (JSONObject) (new JSONParser()).parse(jsonstr);
					String uni = obj.get("uni").toString();
					String name = obj.get("name").toString();
					String stu_num = obj.get("stu_num").toString();
					String birth = obj.get("birth").toString();
					String sex = obj.get("sex").toString();
					String phone_num = obj.get("phone_num").toString();
					String ps = obj.get("ps").toString();
					obj1.put("uni", uni);
					obj1.put("name", name);
					obj1.put("stu_num", stu_num);
					obj1.put("birth", birth);
					obj1.put("sex", sex);
					obj1.put("phone_num", phone_num);
					obj1.put("ps", ps);
					
					String json = obj1.toJSONString();
					
					sql = "UPDATE user SET jsonstr = ? where id = ?";
				
				stmt1 = conn.prepareStatement(sql);
				stmt1.setString(1, json);
				stmt1.setString(2, uid);
				int count = stmt1.executeUpdate();
				stmt1.close(); 
				return (count == 1) ? true : false;
	
			}
			} finally {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (stmt1 != null) stmt1.close();
			if (conn != null) conn.close();
			}
		}
	
	
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
		
		public String getReport() throws NamingException, SQLException{
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				String sql = "SELECT jsonstr FROM feedReport";
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
		
		public int countMyHeart(String id) throws NamingException, SQLException {
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try 
				{
				String sql = "select count(*) from feedHeart where id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, id);
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
		public String getUni_json(String jsonstr) throws NamingException, SQLException, ParseException
		{
			JSONObject obj = (JSONObject) (new JSONParser()).parse(jsonstr);
			String uni = obj.get("uni").toString();
			return uni;
		}
		
		public String getNam_json(String jsonstr) throws NamingException, SQLException, ParseException
		{
			JSONObject obj = (JSONObject) (new JSONParser()).parse(jsonstr);
			String nam = obj.get("name").toString();
			return nam;
		}
		
		public String get_id(String jsonstr) throws NamingException, SQLException, ParseException
		{
			JSONObject obj = (JSONObject) (new JSONParser()).parse(jsonstr);
			String id = obj.get("id").toString();
			return id;
		}
		
		
		public int checkUni(String uni) throws NamingException, SQLException {
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try 
				{
				String sql = "select * from uni_list where uniList = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, uni);
				rs = stmt.executeQuery();
				return rs.next() ? 1 : 0;
			} finally {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
			}
		}
		
		public boolean requ_uni(String uni) throws NamingException, SQLException
		{
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try
			{
				String sql = "select * from add_uni where uni = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, uni);
				rs = stmt.executeQuery();
				int a = rs.next() ? 1 : 0;
				rs.close(); stmt.close();
				
				if(a==1)
				{
					sql = "update add_uni set cnt = cnt+1 where uni = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, uni);
					int count = stmt.executeUpdate();
					stmt.close();
					return (count == 1) ? true : false;
				}
				else
				{
						sql = "insert into add_uni values(? , 1)";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, uni);
						int count = stmt.executeUpdate();
						stmt.close();
						return (count == 1) ? true : false;
				}
			} finally {
						if (stmt != null) stmt.close();
						if (conn != null) conn.close();
						if (rs != null) stmt.close();
				}
			}
		
		
		
		
		
	}