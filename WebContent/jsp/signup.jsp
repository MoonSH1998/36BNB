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
	
		응답 메세지는 OK:로그인 NU:해당 학교 없음->요청메세지를 보내세요 ER->에러 EX->이미 존재하는 아이디
	}
	*/
	
	//String uni = request.getParameter("uni");
	String uid = request.getParameter("id");
	String jsonstr = request.getParameter("jsonstr"); 
	UserDAO dao = new UserDAO();
	String uni = dao.getUni(jsonstr);
	if (dao.exists(uid))
	{
		out.print("EX");
	}
	
	else if ( dao.checkUni(uni) )
	{
		out.print("NU");
	}
	
	else if (dao.insert(uid, jsonstr) == true )
	{
		session.setAttribute("id", uid);
		session.setAttribute("uni", uni);
		out.print("OK");
	}
	else
	{
		out.print("ER");
	}
%>