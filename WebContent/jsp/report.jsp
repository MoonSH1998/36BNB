<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
request.setCharacterEncoding("utf-8");
	String no = request.getParameter("no");
    String report_title = request.getParameter("report_titile");
    String report_content = request.getParameter("report_content");

    FeedDAO dao = new FeedDAO();
   		 int code = dao.report(report_title, report_content);
    if (code == 1) {
       out.print("NE"); // out.print("아이디가 존재하지 않습니다.");
    }
    else if (code == 2) {
       out.print("PE"); // out.print("패스워드가 일치하지 않습니다.");
    }
    else if (code == 0) {
    	session.setAttribute("id", uid);
    	out.print("OK");
    	}

%>

