<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<link rel="stylesheet" type="text/css" href="simplePagination.css">
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Abril+Fatface" rel="stylesheet">
<script type="text/javascript" src="jquery-3.2.1.js"></script>
<script type="text/javascript" src="script.js"></script>
<script type="text/javascript" src="jquery.simplePagination.js"></script>
<title>Welcome to your account</title>
</head>
<body>
<input type="hidden" name="action" value="myaccount" />
<%@ include file="header.jsp" %>
<%@ include file="accountheader.jsp" %>
<%@page import = "java.util.Arrays, java.util.*, database.UserAccount" %>
<% 
String allitems = (String)request.getAttribute("allitems");
String allnames = (String)request.getAttribute("allnames");
String alldescs = (String)request.getAttribute("alldescs");
if(allitems == "" || allitems == null) {
	UserAccount ua = new UserAccount();
	allitems = ua.getItems(email);
	if(allnames == "" || allnames == null) {
		allnames = ua.getNames(allitems);
	}
	
	if(alldescs == "" || alldescs == null) {
		alldescs = ua.getDescriptions(allitems);
	}
	
	ua.closeConnection();
}
%>
<center>
<table class="items">

<% if(allitems == "" || allitems == null) { %>
	<tr id="itemrow">
	<td class="emptyitem">
	<a href="/SimpleShoppingCart/AccountController?action=add">Add Item</a>
	</td>
	</tr>
<% } 
else { 
	List<String> itemslist = Arrays.asList(allitems.split(","));
	List<String> nameslist = Arrays.asList(allnames.split(","));
	List<String> descslist = Arrays.asList(alldescs.split(":::"));
%>
<tr class="removefromcart">
<td colspan="4">
<form action="/SimpleShoppingCart/AccountController?action=removeitems" method="post">
<input type="submit" value="Remove from your watchlist" id="removefromwatchlist" class="hidden"/>
<input type="hidden" id="removelist" name="removelist" value="" />
</form>
</td>
</tr>
<%
	for(Integer i = 1; i <= itemslist.size() + 1; i++) {	
%>

<!--  New row every 3 boxes condition -->
<% 	
		if((i-1)%3 == 0 && itemslist.iterator().hasNext()) { %>
			<tr id="itemrow" class="paginate">
<% 	
		} 
%>
<!--  End condition -->
<%
	if(i == 1) {
%>
<td class="emptyitem" id="<%= i-1 %>">
	<a href="/SimpleShoppingCart/AccountController?action=add">Add Item</a>
</td>
<td class="spacecells"></td>
<%
	}
	else {
		String filename = "images/" + itemslist.get(i - 2) + ".jpg";
		String itemid = itemslist.get(i - 2);
		String itemname = nameslist.get(i-2);
		String desc = descslist.get(i-2);
%>

<td class="myitem" id="<%= itemid %>">
<img src="<%= filename %>" id="<%= filename %>"/>
<p><label class="title"><%= itemname %>: </label></p>
<p><%=desc %></p>
</td>

<% if(i%3 != 0) { %>
	<td class="spacecells"></td>
<% } %>
<%
	}
%>

<%	
		if(i%3 == 0 || !itemslist.iterator().hasNext()) { 
%>
			</tr>
<% 	
	} 
%>
<%
	}
%>
<%
	}
%>

</table>
<div id="page-nav"></div>

<div id="test2"><%= allitems %></div>

</center>
</body>
</html>