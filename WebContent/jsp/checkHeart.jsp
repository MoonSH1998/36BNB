<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
//이 게시글이 내가 heart했는지 check 입력값으로 id, no받아오면 OK,NO반환
request.setCharacterEncoding("utf-8");
	
	String no = request.getParameter("no");
	String id = request.getParameter("id");
	
	FeedoptionDAO dao = new FeedoptionDAO();
	
	if (dao.checkHeart(id,no) == true )
	{
		out.print("OK");
	}
	else
	{
		out.print("NO");
	}
%>
	
	