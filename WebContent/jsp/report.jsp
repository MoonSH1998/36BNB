<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
request.setCharacterEncoding("utf-8");
	

  
    String id = request.getParameter("id");
	String jsonstr = request.getParameter("jsonstr");

		FeedoptionDAO dao = new FeedoptionDAO();
		
		
		if (dao.report(jsonstr) == true ) {
			out.print("OK");
	}
		else {
		out.print("ER");
	}
    
    
%>

