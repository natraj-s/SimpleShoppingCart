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
<%@page import="java.util.Date, java.text.SimpleDateFormat" %>
<% 
Date today = new Date();
SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy");
%>
<center>

<div class="outerheader">
	<div class="innerheader" >
		<div class="header">
			<a href="/SimpleShoppingCart/index.jsp" class="header">Welcome to SHOPPINGLite</a>
		</div>
		<div class="info2">
			<p>UI Designed by <b>Natraj</b></p>
			<p>Version 1.0</p>
			<p>Today is: <%= dateFormat.format(today) %></p>
		</div>

	</div>
</div>
</center>
</body>
</html>