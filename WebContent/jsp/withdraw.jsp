<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
//회원 탈퇴, 관리자페이지 전용입니다.
request.setCharacterEncoding("utf-8");
	
	String uid = request.getParameter("id");

		UserDAO dao = new UserDAO();
		if (dao.exists(uid) == false) {
		out.print("NE");
		return;
		}
		if (dao.delete(uid)) {
		out.print("OK");
		return;
	}
		else {
		out.print("ER");
	}
	%>