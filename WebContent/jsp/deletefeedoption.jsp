<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
//feedoption -> heart, report 삭제
//feedoption은 list, jsonstr으로 db에 저장
// 해당 feedoption의 list 넣어주기
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