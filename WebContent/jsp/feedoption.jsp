<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
request.setCharacterEncoding("utf-8");
	

  String jsonstr = request.getParameter("jsonstr");

		FeedoptionDAO dao = new FeedoptionDAO();

		if (dao.feedoption(jsonstr) == 2 )
		{
			out.print("EX");
		}
		else if (dao.feedoption(jsonstr) == 1 )
		{
			out.print("OK");
		}
		else
		{
			out.print("ER");
		}
%>