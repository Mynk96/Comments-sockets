<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "Controllers.login" %>
<%@ page import = "beans.Users" %>

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

<title>Login</title>
</head>

<body class = "container">
		<h2>Teammates Prototype</h2>
	<form action = "/Comments/login" method = "post">
		<div class = "form-group">
			<label class = "control-label" for = "email">Email:</label>
			<div>
				<input type = "email" name = "email" class = "form-control">
			</div>
		<label class = "control-label" for = "password">Password:</label>
			<div>
				<input type = "password" name = "password" class = "form-control">
			</div>
			<div>
				<h4 style="color:red"><%= Users.error %></h4>
			</div>
			<div style = "margin-top:0.5em;">
				<input type = "submit" name = "submit" value = "Login" class = "btn btn-success">
			</div>
			
		</div>
	</form>
</body>
</html>