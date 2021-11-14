<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
//여기는 수정해야함 -> uni에 맞게 뽑아와야함,,
String maxNo = request.getParameter("maxNo");
out.print((new FeedDAO()).getGroup(maxNo));
%>