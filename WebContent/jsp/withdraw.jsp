<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
request.setCharacterEncoding("utf-8");
	
	String uid = request.getParameter("id");
	String uname = request.getParameter("name");
	String ups = request.getParameter("ps");

		UserDAO dao = new UserDAO();
		if (dao.exists(uid) == false) {
		out.print("NE");
		return;
		}
		if (dao.delete(uid, uname, ups)) {
		out.print("OK");
		return;
	}
		else {
		out.print("ER");
	}
	%>