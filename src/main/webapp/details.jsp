<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Details</title>
</head>
<body>
<%
if(request.getSession().getAttribute("username")==null)
{
	response.sendRedirect("login.jsp");	
}
%>
<jsp:useBean id="user" class="global.coda.hospitalmanagementsystem.model.User" type="global.coda.hospitalmanagementsystem.model.User" scope="session"/>
<%--<jsp:getProperty name="user" property="username"/>--%>
<form action="/HospitalManagementSystem/UpdateServlet" method="post">
UserId <input type="text" name="userid" value=<%=request.getAttribute("userId") %>><br>
Name <input type="text" name="username" value=<jsp:getProperty name="user" property="username"/>><br>
AccountNumber(Non-editable) <input type="text" name="accountnumber" value=<jsp:getProperty name="user" property="accountNumber"/>><br>
RoleId <input type="text" name="roleid" value=${sessionScope.user.roleId} /><br>
<input type="submit" value="Edit">
</form>

<%@ include file="logout.jsp" %>
</body>
</html>