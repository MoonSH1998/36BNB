<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
request.setCharacterEncoding("utf-8");
	
//heart클릭 시 추가 삭제
//글 no만 여기로 전달해주시면 됩니다. -> ajax OK = 추가완료 DE = 삭제 ER = 에러
 	String no = request.getParameter("no");
	String id = (String)session.getAttribute("id");
	
		FeedoptionDAO dao = new FeedoptionDAO();

		if (dao.feedHeart(no,id) == 1 )
		{
			out.print("OK");
		}
		else if (dao.feedHeart(no,id) == 2 )
		{
			out.print("DE");
		}
		else
		{
			out.print("ER");
		}
%>