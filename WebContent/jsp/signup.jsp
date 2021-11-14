<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
request.setCharacterEncoding("utf-8");
	/*
	
	회원가입 페이지에서 받아오는 데이터 정보
	{id,usrobj}으로 묶으시고 여기로 보내주시면 됩니다.	
	
	var usrobj = {id: id, name: name, uni: uni, stu_num: stu_num,  birth: birth,sex: sex, phone_num: phone_num, ps: ps};
	var params = {id: id, jsonstr: JSON.stringify(usrobj)};
	var url ="jsp/signup.jsp";
	AJAX.call(url, params, function(data) {
	var code = data.trim();
	if(code == "EX") {
	alert("이미 가입한 회원입니다.");
	}
	else if(code == "ER") {
	alert("회원가입 처리중 에러가 발생하였습니다.");
	}
	else {
	alert("회원 가입이 완료되었습니다.");
	window.location.href = "main.html";
	}
	});}
	
	*/
	String uid = request.getParameter("id");
	String jsonstr = request.getParameter("jsonstr");

		UserDAO dao = new UserDAO();
		if (dao.exists(uid)) {
		out.print("EX");
		return;
		}
		if (dao.insert(uid, jsonstr) == true ) {
			session.setAttribute("id", uid);
			out.print("OK");
	}
		else {
		out.print("ER");
	}
%>