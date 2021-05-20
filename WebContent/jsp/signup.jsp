<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
	request.setCharacterEncoding("utf-8");
	
	String uname = request.getParameter("name");
	String ustu_num = request.getParameter("stu_num");
	String ubirth = request.getParameter("birth");
	String uphone_num = request.getParameter("phone_num");
	String uemail = request.getParameter("email");
	String ups = request.getParameter("ps");
	
				
			UserDAO dao = new UserDAO();
				int code = UserDAO(uname, ustu_num, ubirth, uphone_num, uemail, ups);
			if (code == 1) {
				out.print("OK"); 
			}
			else if (code == 2) {
				out.print("EX"); 
			}
			else { 
				session.setAttribute("stu_num", ustu_num);
				out.print("ER");
			}
			%>
			