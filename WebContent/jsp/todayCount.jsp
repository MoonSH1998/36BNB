<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
	//내가 작선한 글이 몇 개인지 알려주는 함수
	//프로필에서 사용예정 id만 넣어주면 반환해줌
	request.setCharacterEncoding("utf-8");
	
	FeedDAO dao = new FeedDAO();
	String today = request.getParameter("today");
	today = today + "00";
	int todayCount = dao.todayCount(today);
    out.print(todayCount);
 
%>