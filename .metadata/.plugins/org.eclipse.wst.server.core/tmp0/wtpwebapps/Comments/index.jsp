<%@page import="beans.Comments"%>
<%@page import="java.beans.Beans"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "includes.Sessions"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>

</script>
<script src = "functions.js" type = "text/javascript"></script> 


<link rel = "stylesheet" type = "text/css" href = "main.css">

<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Catamaran">
<title>Comments</title>


</head>

<body>
	<%	
		if((session.getAttribute("loggedIn") == null) || (session.getAttribute("loggedIn").equals("false")) ){ %>
			<jsp:include page = "header.jsp"></jsp:include>
		<%} else {%>
			<jsp:include page="user_header.jsp"></jsp:include>
		<%} %>

	
	<% if((session.getAttribute("loggedIn") != null)  && session.getAttribute("loggedIn").equals("true")){ %>
		<jsp:include page = "doComment.jsp"></jsp:include>
		<%} %>
		
	<div class = "container">
		
		<div id = "error" class = "alert alert-danger" hidden></div>
	</div>
	<div class = "container abc">
			<h3>Comments by me:</h3>
				
	</div>
	<div class = "container" id = "myComments">
	</div>
	<button class = "btn btn-primary viewMore" onclick = "viewNewComments()" hidden>New Comments For You</button>
	<div class = "container abc">
			<h3>Comments by others:</h3>
				
	</div>
	<div class = "container" id = "newComments">
	</div>

	
</body>

</html>
