<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
	//내가 작선한 글이 몇 개인지 알려주는 함수
	//프로필에서 사용예정 id만 넣어주면 반환해줌
	request.setCharacterEncoding("utf-8");
    String uid = (String)session.getAttribute("uid");
    String uni = (String)session.getAttribute("uni");
    
    UserDAO dao = new UserDAO();
    
    int myFeed = dao.countMyFeed(uid);
    int myReport = dao.countMyReport(uid);
    int myHeart = dao.countMyHeart(uid);
    
    out.print(myFeed);
    out.print(myReport);
    out.print(myHeart);
    
    
  	
%>