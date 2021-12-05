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

	public boolean insertprofile(String jsonstr) throws NamingException, SQLException, ParseException{
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		try {
			synchronized(this) {
				JSONParser parser = new JSONParser();
				JSONObject obj = (JSONObject) (new JSONParser()).parse(jsonstr);
				String uid = (String) obj.get("id");
				
				String sql = "select jsonstr from user where id = ?;";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, uid);
				rs = stmt.executeQuery();
				stmt.close();
				if (rs.next())
				{
					String json = rs.getString("jsonstr");
					
					JSONObject obj1 = (JSONObject) (new JSONParser()).parse(json);
					String name  = obj1.get("name").toString();
					String uni = obj1.get("uni").toString();
					String stu_num = obj1.get("stu_num").toString();
					String birth = obj1.get("birth").toString();
					String sex = obj1.get("sex").toString();
					String phone_num = obj1.get("phone_num").toString();
					String ps = obj1.get("ps").toString();
					
					obj.put("uni", uni);
				}
				
				sql = "UPDATE user SET jsonstr = ? where id = ?;";
				stmt1 = conn.prepareStatement(sql);
				stmt.setString(1, obj.toJSONString());
				stmt1.setString(2, uid);
				int count = stmt1.executeUpdate();
				stmt1.close(); rs.close();
				return (count == 1) ? true : false;
				

				
				
				
				//String sql = "UPDATE user SET jsonstr = JSON_SET(jsonstr, '$.images', '?') where id = ?;";
				//stmt = conn.prepareStatement(sql);
				//stmt.setString(1, images);
				//stmt.setString(2, uid);
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
		
		//���� �� �� ���� ��ȯ �Լ� ���� ���������� �� ���� jsp:countMyFeed, �Ķ���� : String id
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
		//jsonstr�޾ƿͼ� uni�� �����ִ� �޼ҵ�
		public String getUni_json(String jsonstr) throws NamingException, SQLException, ParseException
		{
			JSONObject obj = (JSONObject) (new JSONParser()).parse(jsonstr);
			String uni = obj.get("uni").toString();
			return uni;
		}
		
		public String get_id(String jsonstr) throws NamingException, SQLException, ParseException
		{
			JSONObject obj = (JSONObject) (new JSONParser()).parse(jsonstr);
			String id = obj.get("id").toString();
			return id;
		}
		
		
		//ȸ������ �� �Էµ� uni������ ȸ������ ������ uni list�ȿ� �ִ��� Ȯ���ϴ� �޼ҵ�
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