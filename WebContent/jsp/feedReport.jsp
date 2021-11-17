<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
request.setCharacterEncoding("utf-8");
	
//신고하기 누를 시 no와 contnet를 전달 0-> content는 report누를 시 관리자에게 전할 신고 내용을 입력해서 받아오는 형식으로
//둘 다 String형식으로 주시면됩니다 / id는 세션에 저장되어있어 안주셔도돼요
//OK받을 시 신고 입력 / ER받을 시 er 뜨게 하면 됩니다.
	String no = request.getParameter("no");
	String id = (String)session.getAttribute("id");
	String content = request.getParameter("content");
			
		FeedoptionDAO dao = new FeedoptionDAO();

		if (dao.feedReport(no,id,content) == true )
		{
			out.print("OK");
		}
		
		else
		{
			out.print("ER");
		}
%>