<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
request.setCharacterEncoding("utf-8");
		
	String no = request.getParameter("no");
	
	FeedDAO dao = new FeedDAO();
	
	 	if (dao.deletefeed(no))
	 	{
			out.print("OK"); // response.sendRedirect("main.jsp");
		return;
		}
		else
		{
			out.print("ER");
		} 	
%>
	
	