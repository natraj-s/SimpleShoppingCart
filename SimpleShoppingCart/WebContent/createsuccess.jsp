<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Abril+Fatface" rel="stylesheet">
<title>Account created.</title>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="createsuccess">
<center>
<p><b>Account created with: </b> <%= request.getAttribute("email") %></p>
<p><b><a href="/SimpleShoppingCart/Controller?action=login">Proceed to login page</a></b>
</div>
</body>
</html>