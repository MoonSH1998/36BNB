<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
//글 삭제, 해당 글의 no 받아오기 -> OK. ER반환
request.setCharacterEncoding("utf-8");
		
	String no = request.getParameter("list");
	
	FeedoptionDAO dao = new FeedoptionDAO();
	
	 	dao.delReport(no);
%>
	
	