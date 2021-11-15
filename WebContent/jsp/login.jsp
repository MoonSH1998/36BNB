<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
//로그인 시 입력되는 아이디 패스워드를 id, ps로 넘겨주기
//입력되는 id와 ps값에 따라 NE, AD, PE, OK가 출력
//AD: 관리자 모드로 admin.html이동, PE: 패스워드 불일치, NE: 아이디 존재x, OK: 일반 사용자 로그인 성공
request.setCharacterEncoding("utf-8");
    String uid = request.getParameter("id");
    String ups = request.getParameter("ps");

    UserDAO dao = new UserDAO();
   		 int code = dao.login(uid, ups);
    if (code == 1) {
       out.print("NE"); // out.print("아이디가 존재하지 않습니다.");
    }
    else if (code == 3){
    	session.setAttribute("id", uid);
    	out.print("AD");
    }
    else if (code == 2) {
       out.print("PE"); // out.print("패스워드가 일치하지 않습니다.");
    }
    else if (code == 0) {
    	session.setAttribute("id", uid);
    	out.print("OK");
    	}
%>
