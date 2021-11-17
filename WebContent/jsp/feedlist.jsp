<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
//관리자페이지전용	
/* String uid = request.getParameter("uid");
FeedDAO dao = new FeedDAO();
out.print(dao.getList(uid));
*/
out.print((new FeedDAO()).getList());

%>