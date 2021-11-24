<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="util.*" %>
<%
request.setCharacterEncoding("utf-8");

	String sql = "select * from feedHeart order by list desc";

	String uid = request.getParameter("id");
	String jsonstr = request.getParameter("jsonstr"); 
	UserDAO dao = new UserDAO();
	String uni = dao.getUni_json(jsonstr);
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
		out.print("OK");
	}
	else
	{
		out.print("ER");
	}
%>