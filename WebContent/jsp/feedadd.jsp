<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
request.setCharacterEncoding("utf-8");
	
	String uid = request.getParameter("id");
	String uname = request.getParameter("name");
	String ustu_num = request.getParameter("stu_num");
	String ubirth = request.getParameter("birth");
	String uphone_num = request.getParameter("phone_num");
	String ups = request.getParameter("ps");

		UserDAO dao = new UserDAO();
		if (dao.exists(uid) == true) {
		out.print("EX");
		return;
		}
		if (dao.insert(uid, uname, ustu_num, ubirth, uphone_num, ups)) {
		out.print("OK");
	}
		else {
		out.print("ER");
	}
	%>