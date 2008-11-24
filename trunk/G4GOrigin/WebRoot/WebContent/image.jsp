
<%@page import="com.g4g.utils.ImageUtils"%>
<%@page import="java.io.File"%>

<%String path =(String) session.getAttribute("uploadedFile"); 
	File file = new File(path);
	byte[] photoData = ImageUtils.getBytesFromFile(file);
   response.setContentType("image/jpg");
   ServletOutputStream outputStream = response.getOutputStream();
   outputStream.write(photoData);
   outputStream.flush();
   outputStream.close();
   out.clear();
   out = pageContext.pushBody();
   file.delete();
%>