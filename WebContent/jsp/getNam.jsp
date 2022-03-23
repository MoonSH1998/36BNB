<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%

	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	UserDAO dao = new UserDAO();
	String uid = dao.get(id);
	String nam = dao.getNam_json(uid);
	out.print(nam);
    
    
  	
%>