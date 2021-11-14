<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
//글 삭제, 해당 글의 no 받아오기 -> OK. ER반환
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
	
	