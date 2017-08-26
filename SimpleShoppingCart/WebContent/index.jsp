<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Abril+Fatface" rel="stylesheet">
<title>Welcome to ShoppingLite</title>
</head>
<body>
<%@ include file="header.jsp" %>
<center>
<table class="splash">
<tr>
<td class="splashLogin">
<a href="/SimpleShoppingCart/Controller?action=login">Login</a>
</td>
<td class="splashNew">
<a href="/SimpleShoppingCart/Controller?action=createaccount">Create New Account</a>
</td>
</tr>
</table>


</center>

</body>
</html>