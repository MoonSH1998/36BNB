
<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
	request.setCharacterEncoding("utf-8");
	
	String uname = request.getParameter("name");
	String ustu_num = request.getParameter("stu_num");
	String ups = request.getParameter("ps");
	
			UserDAO dao = new UserDAO();
				int code = dao.withdraw(uname, ustu_num, ups);
			if (code == 1) {
				out.print("OK"); 
			}
			else if (code == 2) {
				out.print("NA"); 
			}
			else if (code == -1) { 
				out.print("ER");
			}
			%>
			