
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>upload image</title>
</head>
<body>
<center>
<h1>STEPS 1/2</h1>
<h1>upload product image from...</h1>
 <form action="FileUploadHandler" enctype="multipart/form-data" method="post">
<br>
 Select<input type="file" name="file2" /><br>
 <input type="submit" value="upload" />
 </form> 
 <%
 String file_name=(String)request.getParameter("filename");
 if(file_name!=null){
 out.println(file_name+" File uploaded successfuly");
 }
 %>
</center>
</body>
</html>
