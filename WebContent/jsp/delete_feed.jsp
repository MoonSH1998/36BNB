<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
request.setCharacterEncoding("utf-8");
	//FeedDAO dao = new FeedDAO();

	//	String no = request.getParameter("no");
	//	out.print((new FeedDAO()).delete_feed(no));
		
		String no = request.getParameter("no");
		FeedDAO dao = new FeedDAO();
	
		 	if (dao.delete_feed(no)) {
			out.print("OK"); // response.sendRedirect("main.jsp");
			return;
			}
			else {
			out.print("ER");
			}
		 	
		 	
		 	
		 			 	
		 	
		 	
		 	
		 	
	%>
	
	