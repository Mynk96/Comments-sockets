<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "java.util.*" %>
<%@page import="beans.Users"%>
<%ArrayList<String> users = Users.getAllUsers(); %>
<div class = "container">
		<h2>Comments Section</h2>
	</div>
	<div class = "container">	
		<h4>Add a new Comment:</h4>
	</div>
<div class = "container">
		<form id = "testform" name = "testForm">
			<div class = "form-group">
				<label class = "control-label" for = "comment">Comment:</label>
			</div>	
			<div>
				<textarea class = "form-control" rows = "5" cols = "100" id = "comment" name = "comment"></textarea>
			</div>
			<div class="form-group">
  				<label for="commentFor">For:</label>
  				
  				<select class="form-control" id="commentFor">
    				<%for (String user:users) {%>
    				<option><%=user%></option>
    				<%} %>
  				</select>
			</div>
			<div class = "input-group-btn">
				<input class = "form-control btn-primary" type = "button" id ="test" value = "Comment" onclick = "validateForm()">
			</div>	
		</form>
	</div>