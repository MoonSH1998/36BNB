package dao;

import java.sql.*;
import javax.naming.NamingException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import util.ConnectionPool;

	public class FeedoptionDAO
	{
		
		public int feedHeart(String no, String fid, String id) throws NamingException, SQLException , ParseException{
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try
			{
				synchronized(this)
				{
					String sql = "SELECT * from feedHeart where no = ? and id = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, no);
					stmt.setString(2, id);
					rs = stmt.executeQuery();
					int exsist = (!rs.next()) ? 0 : 1;
					stmt.close(); rs.close();
					return exsist;
				}
			}
			finally
			{
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			}
		}
		public boolean insertHeart(String no, String fid, String id) throws NamingException, SQLException{
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try
			{
				String sql = "SELECT list FROM feedHeart ORDER BY list DESC LIMIT 1";
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				int max = (!rs.next()) ? 0 : rs.getInt("list");
				stmt.close(); rs.close();
				
				sql = "INSERT INTO feedHeart(list, no, fid, id) VALUES(?, ?, ?, ?)";
				stmt = conn.prepareStatement(sql); 
				stmt.setInt(1, max + 1);
				stmt.setString(2, no);
				stmt.setString(3, fid);
				stmt.setString(4, id);
				
				int count = stmt.executeUpdate();
				return (count == 1) ? true : false;
			}
			finally
			{
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			}
		}
		
		public void ch_report1(String no) throws NamingException, SQLException{
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try
			{
				String sql = "UPDATE feedReport SET state = '신고확인' where list = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, no);
				stmt.executeUpdate();
				stmt.close();
			}
			finally
			{
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			}
		}
		
		
		public void ch_report(String no) throws NamingException, SQLException{
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try
			{
				String sql = "update feedReport set jsonstr= JSON_SET(jsonstr, '$.state', '신고확인') where list = ?";
				stmt = conn.prepareStatement(sql); 
				stmt.setString(1, no);
				stmt.executeUpdate();
				stmt.close();
				
			}
			finally
			{
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			}
		}
		
		public void ok_report(String no) throws NamingException, SQLException{
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try
			{
				String sql = "update feedReport set jsonstr= JSON_SET(jsonstr, '$.state', '처리완료') where list = ?";
				stmt = conn.prepareStatement(sql); 
				stmt.setString(1, no);
				stmt.executeUpdate();
				stmt.close();
				
			}
			finally
			{
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			}
		}
		public void ok_report1(String no) throws NamingException, SQLException{
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try
			{
				String sql = "UPDATE feedReport SET state = '처리완료' where list = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, no);
				stmt.executeUpdate();
				stmt.close();
			}
			finally
			{
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			}
		}
		public int delReport(String no) throws NamingException, SQLException{
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try
			{
				String sql = "delete from feedReport where list = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, no);
				stmt.executeUpdate();
				return 1;
			}
			finally
			{
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			}
		}
		
		public boolean delHeart(String no, String fid, String id) throws NamingException, SQLException{
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try
			{
				String sql = "SELECT list from feedHeart where (no,id) = (?,?)";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, no);
				stmt.setString(2, id);
				rs = stmt.executeQuery();
				int del = (!rs.next()) ? null : rs.getInt("list");
				stmt.close(); rs.close();
				
				sql = "delete from feedHeart where list = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, del);
				int count = stmt.executeUpdate();
				return (count == 1) ? true : false;
			}
			finally
			{
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			}
		}
		
		public boolean feedReport(String jsonstr) throws NamingException, SQLException , ParseException{
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try
			{
				synchronized(this)
				{
					String sql = "SELECT list FROM feedReport ORDER BY list DESC LIMIT 1";
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
					int max = (!rs.next()) ? 0 : rs.getInt("list");
					stmt.close(); rs.close();
					JSONParser parser = new JSONParser();
					JSONObject jsonobj = (JSONObject)parser.parse(jsonstr);
					jsonobj.put("list", max + 1);
					stmt.close(); rs.close();
					
					sql = "INSERT INTO feedReport(list, fid, jsonstr, state) VALUES(?, ?, ?, ?)";
					stmt = conn.prepareStatement(sql); 
					String fid = jsonobj.get("fid").toString();
					stmt.setInt(1, max + 1);
					stmt.setString(2, fid);
					stmt.setString(3, jsonobj.toJSONString());
					stmt.setString(4, "신고접수");
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
		public boolean checkHeart(String no, String uid) throws NamingException, SQLException
		{
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try
			{
				String sql = "SELECT * FROM feedHeart where no = ? and id = ?";
				conn = ConnectionPool.get();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, no);
				stmt.setString(2, uid);
				rs = stmt.executeQuery();
				
				if (rs.next())
				{
					return true;
				}
				else 
				{
					return false;
				}
			}
			finally
			{
				if (rs != null) rs.close();
				if (stmt != null) stmt.close(); 
				if (conn != null) conn.close();
		    }
		}
		
		public String check_state(String no) throws NamingException, SQLException, ParseException {
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try
			{
				String sql="select json_extract(jsonstr, '$.content') as s FROM (select * from feedReport where list = ?)f";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, no);
				rs = stmt.executeQuery();
				String a;
				a = (!rs.next()) ? rs.getString("s") : rs.getString("s"); 
				return a;
				
			} 
			finally 
			{
				if (rs != null) rs.close();
				if (stmt != null) stmt.close(); 
				if (conn != null) conn.close();
			}
		}
		/*
		public String check_state1(String no) throws NamingException, SQLException, ParseException {
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try
			{
				String sql = "select"
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, no);
				rs = stmt.executeQuery();
				return id;
			} 
			finally 
			{
				if (rs != null) rs.close();
				if (stmt != null) stmt.close(); 
				if (conn != null) conn.close();
			}
		}
	*/
	/*
	// Check_already_heart_by Moon 1111
	
	
		
public String getListfeedoption() throws NamingException, SQLException
{
Connection conn = ConnectionPool.get();
PreparedStatement stmt = null;
ResultSet rs = null;
try
{
	String sql = "SELECT jsonstr FROM feedoption";
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
*/		
	/*
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
	public int feedHeart(String no, String fid, String id) throws NamingException, SQLException{
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try
			{
				synchronized(this)
				{
					String sql = "SELECT * FROM feedheart where id = ? and no = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, id);
					stmt.setString(2, no);
					rs = stmt.executeQuery();
					int count = rs.next() == true ? 1 : 0;
					// heart존재하지 않을 때 저장 후 return 1
					if(count == 0)
					{
						sql = "insert into feedHeart values(?, ?, ?)";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, no);
						stmt.setString(2, fid);
						stmt.setString(3, id);
						stmt.executeUpdate();
						return 1;
					}
					//Heart 이미 존재할 때 클릭 시 heart 삭제하기 return 2
					else if(count==1)
					{
						deletefeedheart(no, id);
						//sql = "delete from feedheart where no=? and id=?";
						//stmt = conn.prepareStatement(sql);
						//stmt.setString(1, no);
						//stmt.setString(2, id);
						//stmt.executeUpdate();
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
		
		public boolean feedReport(String jsonstr) throws NamingException, SQLException{
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try
			{
				synchronized(this)
				{
					String sql = "insert into feedReport values(?, ?, ?, ?, ?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, no);
					stmt.setString(2, fid);
					stmt.setString(3, id);
					stmt.setString(4, "신고접수");
					stmt.setString(5, content);
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
		
		public int feedHeart(String no, String fid, String id) throws NamingException, SQLException , ParseException{
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ResultSet rs1 = null;
			try
			{
				synchronized(this)
				{
					String sql = "SELECT * from feedHeart where no = ? and id = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, no);
					stmt.setString(2, id);
					rs = stmt.executeQuery();
					int exsist = (!rs.next()) ? 0 : 1;
					stmt.close();
					rs = null;
					if (exsist==0)
					{
						return insertHeart(no, fid, id);
					}
					else
					{
						sql = "SELECT list from feedHeart where (no,id) = (?,?)";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, no);
						stmt.setString(2, id);
						rs1 = stmt.executeQuery();
						int del = (!rs1.next()) ? null : rs1.getInt("list");
						stmt.close(); rs1.close();
						return delHeart(del);
					}
				}
			}
			finally
			{
				if (rs != null) rs.close();
				if (rs1 != null) rs1.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			}
		}
		public int insertHeart(String no, String fid, String id) throws NamingException, SQLException{
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try
			{
				String sql = "SELECT list FROM feedHeart ORDER BY list DESC LIMIT 1";
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				int max = (!rs.next()) ? 0 : rs.getInt("list");
				stmt.close(); rs.close();
				
				sql = "INSERT INTO feedHeart(list, no, fid, id) VALUES(?, ?, ?, ?)";
				stmt = conn.prepareStatement(sql); 
				stmt.setInt(1, max + 1);
				stmt.setString(2, no);
				stmt.setString(3, fid);
				stmt.setString(4, id);
				
				int count = stmt.executeUpdate();
				return (count == 1) ? 1 : 0;
			}
			finally
			{
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			}
		}
		
	*/
	
	
}