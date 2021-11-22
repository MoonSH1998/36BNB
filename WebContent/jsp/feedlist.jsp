<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
//관리자페이지전용	
/* String uid = request.getParameter("uid");
FeedDAO dao = new FeedDAO();
out.print(dao.getList(uid));
*/
UserDAO dao = new UserDAO();
String id = request.getParameter("id");
//String uid = (String)session.getAttribute("uid");

//String json = dao.get(id);
//String uni = dao.getUni_json(json);
out.print((new FeedDAO()).getList());

%>