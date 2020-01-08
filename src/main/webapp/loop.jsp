<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loop JSP</title>
</head>
<body>
<%! String[] persons={"Shivani","Chandra","Vanmathi","Selva"};%>
<%request.setAttribute("persons",persons);         %>
 <c:forEach items="${persons}" var = "person">
         Person <c:out value = "${person}"></c:out><br>
 </c:forEach>
 Array values are printed. 
</body>
</html>