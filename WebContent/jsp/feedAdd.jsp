<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%@ page import="util.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.*"%>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*"%>
<%	


String jsonstr = null, ufname = null;
byte[] ufile = null;
ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
List items = sfu.parseRequest(request);
Iterator iter = items.iterator();
while(iter.hasNext()) {
FileItem item = (FileItem) iter.next();
String name = item.getFieldName();
if(item.isFormField()) {
String value = item.getString("utf-8");
if (name.equals("jsonstr")) jsonstr = value;
}else {
	if (name.equals("images")) {
		ufname = item.getName();
		ufile = item.get();
		String root = application.getRealPath(java.io.File.separator);
		FileUtil.saveImage(root, ufname, ufile);
		}
	}
}
	
		
		
		FeedDAO dao = new FeedDAO();
		UserDAO dao1 = new UserDAO();
		String id = dao1.get_id(jsonstr);
		String json = dao1.get(id);
		String uni = dao1.getUni_json(json);
		if (dao.insert(jsonstr, id, uni)) {
		out.print("OK"); // response.sendRedirect("main.jsp");
		}
		else {
		out.print("ER");
		}
			
			
			%>