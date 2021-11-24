<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
request.setCharacterEncoding("utf-8");
	
//heart클릭 시 추가 삭제
//글 no만 여기로 전달해주시면 됩니다. -> ajax OK = 추가완료 DE = 삭제 ER = 에러
 	String no = request.getParameter("no");
 	String fid = request.getParameter("fid");
 	String id = request.getParameter("id");
	
		FeedoptionDAO dao = new FeedoptionDAO();
		int state = dao.feedHeart(no, fid, id);
		
		if(state==1)
		{
			if(dao.delHeart(no, fid, id) == true)
			{
				out.print("DE");
			}
			else
			{
				out.print("ER");
			}
		}
		else
		{
			if(dao.insertHeart(no, fid, id)==true)
			{
				out.print("OK");
			}
			else
			{
				out.print("ER");
			}
		}
%>