<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
//maxno와 uni정보를 받아와 feed들을 뽑아오기.
//maxno에 대한 정보는 main.html에 정의되어있음.
//(maxno=현재 mainpage에서 뽑은 글의 제일 작은 no) ex 10 11 12 13 14글들을 뽑아왔으면 다음엔 9까지의 5개를 내림차순으로 뽑아와야하니 maxno=10이 됩니다.

out.print((new FeedDAO()).getGroup(request.getParameter("maxNo"),(String)session.getAttribute("uni")));
%>