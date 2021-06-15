<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
request.setCharacterEncoding("utf-8");
	
	String no = request.getParameter("no");

		FeedDAO dao = new FeedDAO();
		
		if (dao.delete_feed(no) == true) {
		out.print("OK");
		return;
	}
		else {
		out.print("ER");
	}
	%>