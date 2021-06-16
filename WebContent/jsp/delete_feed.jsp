<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
request.setCharacterEncoding("utf-8");
	
		String no = request.getParameter("no");
		out.print((new FeedDAO()).delete_feed(no));

	%>
	
	