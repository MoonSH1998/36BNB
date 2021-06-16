package dao;

import java.sql.*;
import javax.naming.NamingException;

import org.json.simple.parser.ParseException;

import util.ConnectionPool;

public class ReportDAO {
	
		public String insert(String uid, String report_no) throws NamingException, SQLException, ParseException {
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
		String sql = "SELECT id FROM friend WHERE id = ? AND report_no = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, uid);
		stmt.setString(2, report_no);
		rs = stmt.executeQuery();
		if (rs.next()) return "EX";
		stmt.close();
		sql = "INSERT INTO friend VALUES(?, ?)";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, uid);
		stmt.setString(2, report_no);
		int count = stmt.executeUpdate();
		return (count == 1) ? "OK" : "ER";
		} finally {
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
		if (stmt != null) conn.close();
		}
		}
		
		
		public String remove(String uid, String report_no) throws NamingException, SQLException, ParseException {
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			try {
			String sql = "DELETE FROM report WHERE id = ? AND report_no = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, uid);
			stmt.setString(2, report_no);
			int count = stmt.executeUpdate();
			return (count == 1) ? "OK" : "ER";
			} finally {
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
				}
		}
		
		public String getList(String uid) throws NamingException, SQLException, ParseException {
			Connection conn = ConnectionPool.get();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
			String sql = "SELECT report_no FROM report WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, uid);
			rs = stmt.executeQuery();
			String str = ""; int cnt = 0;
			while(rs.next()) {
			if (cnt++ > 0) str += ",";
			str += "\"" + rs.getString("report_no") + "\"";
			}
			if (cnt == 0) return "[]";
			rs.close(); stmt.close();
			sql = "SELECT jsonstr FROM user WHERE id IN (" + str + ")";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			str = "["; cnt = 0;
			while(rs.next()) {
			if (cnt++ > 0) str += ",";
			str += rs.getString("jsonstr");
			}
			str += "]";
			return str;
			} finally {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
			}
			}
			}