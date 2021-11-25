<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
//이건 관리자페이지에 쓰이는 내용입니다.
request.setCharacterEncoding("utf-8");
	out.print((new UserDAO()).getReport());
%>