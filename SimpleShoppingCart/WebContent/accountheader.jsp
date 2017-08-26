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
<center>
<div class="outeracctheader">
	<div class="inneracctheader">
		<div class="accountheader">
			<%@page import="javax.servlet.http.Cookie, java.lang.Integer"  %>
			<%
			// String email = (String)request.getAttribute("email");
			String user = "";
			String email = "";
			Cookie cookies[] = request.getCookies();
			if(cookies != null) {
				for(Cookie oneCookie : cookies) {
					if(oneCookie.getName().equals("user")) {
						email = oneCookie.getValue();
					}
				}
				
				if((!email.equals("") || email != null) && email.lastIndexOf('@') != -1 ) {
					user = email.substring(0, email.lastIndexOf('@'));
					user = user.toUpperCase();
				}
				
			}
			%>
			<a href="/SimpleShoppingCart/myaccount.jsp">Welcome <%= user %></a>
		</div>
		<div class="accountlogoutlink">
			<a href="/SimpleShoppingCart/Controller?action=logout">Logout</a>
		</div>
	</div>
</div>
</center>

</body>
</html>