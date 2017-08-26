<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<title>Welcome to the SHOPPINGLite | Select an item to add</title>
</head>
<body>
<input type="hidden" name="action" value="add" />
<%@ include file="header.jsp" %>
<%@ include file="accountheader.jsp" %>
<%@page import = "java.util.Arrays, java.util.*" %>
<center>

<%
	List<String> images = (ArrayList<String>)request.getAttribute("images");
	String useritems = (String)request.getAttribute("useritems");
	List<String> useritemslist = new ArrayList<String>();
	if(useritems != null && useritems != "") {
		useritemslist = new ArrayList<String>(Arrays.asList(useritems.split(",")));
	}
%>


<table class="items">
<tr class="addtocart">
<td colspan="5">
<form action="/SimpleShoppingCart/AccountController?action=additems" method="post" name="form1">
<input type="submit" value="Add to your watchlist" id="addsubmit"/>
<input type="hidden" id="test" name="listofitems" value="" />
</form>
</td>
</tr>

<% 	for(Integer i = 1; i <= images.size(); i++) { %>

<% 	if((i-1)%3 == 0) { %>
<tr id="itemrow" class="paginate">
<% 	} %>
<% 	
if(images != null) {
	String filename = images.get(i - 1); 
%>
	<%
	if( (useritems != null && useritems != "") && useritemslist.contains(i.toString())) {
	%>
			<td class="highlighted" id="<%= new Integer(i).toString() %>">
	<%
	}
	else {
	%>
			<td class="additem" id="<%= new Integer(i).toString() %>">
	<%
		}
	%>
			<img src="<%= filename %>" id="<%= new Integer(i).toString() %>"/>
			</td>
<% } %>

<td class="spacecells"></td>

<%	if(i%3 == 0) { %>
</tr>
<% 	} %>
<% 	} %>


</table>

<div id="page-nav"></div>
<div id="test"></div>
</center>
</body>
</html>