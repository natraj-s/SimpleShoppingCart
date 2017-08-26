<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Abril+Fatface" rel="stylesheet">
<title>Welcome to ShoppingLite | Login</title>
</head>
<body>
<%@ include file="header.jsp" %>
<center>
<form action="/SimpleShoppingCart/Controller?action=dologin" method="post">
<input type="hidden" name="action" value="dologin" />

<table class="loginform">
<tr>
<td class="email"><label>Email address: </label></td>
<td class="emailinput"><input type="text" name="email" value="<%= request.getAttribute("email") %>" />
</tr>

<tr>
<td colspan="2" class="emailMessage">
<%= request.getAttribute("emailmessage") %>
</td>
</tr>

<tr>
<td class="password"><label>Password: </label></td>
<td class="passwordinput"><input type="password" name="password" value="<%= request.getAttribute("password") %>" />
</tr>

<tr>
<td colspan="2" class="pwdMessage">
<%= request.getAttribute("pwdmessage") %>
</td>
</tr>

<tr class="login">
<td colspan="2">
<input type="submit" value="Login" />
</td>
</tr>

<tr>
<td colspan="2" class="validationMessage">
<p><%= request.getAttribute("validationmessage") %></p>
</td>
</tr>

<tr class="createaccountmsg">
<td colspan="2">
Or if you haven't registered, you can <a href="<%= response.encodeUrl(request.getContextPath() + "/Controller?action=createaccount") %>">create an account here</a>
</td>
</tr>
</table>



</form>
</center>

</body>
</html>