<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%
session.invalidate();
// out.print("로그아웃 하였습니다.");
String str = "<p align=center><br>로그아웃을 완료하였습니다.<br><br>";
str += "<a href='/mysns/login.html'><button>로그인하기</button></a></p>";
//로그인 페이지로 넘어가지 않는 문제
out.print(str);
%>

