<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
request.setCharacterEncoding("utf-8");
		
	String no = request.getParameter("no");
	String id = request.getParameter("id");
	
	FeedDAO dao = new FeedDAO();
	
	if (dao.checkHeart(id,no) == true )
	{
		out.print("OK");
	}
	else
	{
		out.print("NO");
	} 	
%>
	
	