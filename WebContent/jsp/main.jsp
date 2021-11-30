<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
//main페이지 (homescreen) 이동 시 feed들을 불러와주는 jsp입니다.
//아이디의 uni값 적용 후 불러오게 수정해야해서 조금만 기다려주세요
out.print((new FeedDAO()).getList());

%>