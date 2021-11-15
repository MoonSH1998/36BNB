<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
	//입력된 아이디에 따라 아이디와 함께 저장된 jsonstr을 출력해주는 jsp입니다.
	out.print((new UserDAO()).get(request.getParameter("id")));
%>