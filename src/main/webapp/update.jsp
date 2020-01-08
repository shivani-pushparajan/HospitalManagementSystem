<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Updated</title>
</head>
<body>
<%if(request.getSession().getAttribute("username")==null)
	{
	response.sendRedirect("login.jsp");
	}%>
Values updated successfully!!!!
<%@ include file="logout.jsp" %>
</body>
</html>