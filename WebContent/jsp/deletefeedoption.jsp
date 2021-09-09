<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
request.setCharacterEncoding("utf-8");
	
	String list = request.getParameter("list");
	
	FeedoptionDAO dao = new FeedoptionDAO();
	
		 	if (dao.deletereport(list))
		 	{
				out.print("OK"); 
			return;
			}
			else
			{
				out.print("ER");
			}
%>