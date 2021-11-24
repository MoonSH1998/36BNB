<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%@ page import="util.*" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*"%>
	
<%	
			
			

			

/*
request.setCharacterEncoding("utf-8");
String uid = null, ucon = null, ufname = null;
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
/if (name.equals("id")) uid = value;
/else if (name.equals("content")) ucon = value;
if (name.equals("jsonstr")) jsonstr = value;
}else {
	if (name.equals("image")) {
		ufname = item.getName();
		ufile = item.get();
		String root = application.getRealPath(java.io.File.separator);
		FileUtil.saveImage(root, ufname, ufile);
		}
		}
		}
		FeedDAO dao = new FeedDAO();
		//if (dao.insert(uid, ucon, ufname)) {
		if (dao.insert(jsonstr, "1", "1")) {
		out.print("OK"); // response.sendRedirect("main.jsp");
		}
		else {
		out.print("ER");
		}

*/









			
			String uid = null, ucon = null;
			
			String jsonstr = null, ufname = null;
			byte[] ufile = null;
			request.setCharacterEncoding("utf-8");
			ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
			List items = sfu.parseRequest(request);
			Iterator iter = items.iterator();
			while(iter.hasNext())
			{
				FileItem item = (FileItem) iter.next();
				String name = item.getFieldName();
				if(item.isFormField())
				{
					String value = item.getString("utf-8");
					if (name.equals("jsonstr"))
						{
							jsonstr = value;
						}
				}
				else
				{
					if (name.equals("image"))
					{
						ufname = item.getName();
						ufile = item.get();
						String root = application.getRealPath(java.io.File.separator);
						FileUtil.saveImage(root, ufname, ufile);
					}
				}
			}
			
			String id = request.getParameter("id");
			FeedDAO dao = new FeedDAO();
			UserDAO dao1 = new UserDAO();
			String json = dao1.get(id);
			String uni = dao1.getUni_json(json);
		    
			
			if (dao.insert(jsonstr, id, uni) == true)
		 	{
				out.print("OK"); // response.sendRedirect("main.jsp");
			}
			else
			{
				out.print("ER");
			}
			
			%>