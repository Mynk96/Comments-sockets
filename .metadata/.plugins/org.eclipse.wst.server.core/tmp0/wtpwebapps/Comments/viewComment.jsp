<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.*" %>
<%@page import="beans.Comments"%>
<%@page import="beans.Comment"%>
<%@ page import = "beans.ReplyData" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%int commentId = Integer.parseInt(request.getParameter("commentId")); %>
<%Comment viewComment = Comments.getCommentById(commentId); %>
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
<% if(!session.getAttribute("name").equals(null)){ %>
var userName = "<%= session.getAttribute("name") %>";
<%}%>
</script>
 


<link rel = "stylesheet" type = "text/css" href = "main.css">

<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Catamaran">
<title>ViewComment</title>


</head>
<body>
	<%	
		if((session.getAttribute("loggedIn") == null) || (session.getAttribute("loggedIn").equals("false")) ){ %>
			<jsp:include page = "header.jsp"></jsp:include>
		<%} else {%>
			<jsp:include page="user_header.jsp"></jsp:include>
		<%} %>
		<div class = "container">
			<h2>Comment:</h2>
		<div class = "row mayank">
				<div class = "col-lg-12">
					<strong class = "edited-font"><%=viewComment.getName()%></strong><code style="margin-left:5px;"><%=viewComment.getCommentFor() %></code><span class = "pull-right"><%= viewComment.getCommentTime() %></span>
					<p class = "comment"><%=viewComment.getComment()%></p>
		<%	ArrayList<ReplyData> replies  = viewComment.showReplies(); %>
		<% for (ReplyData reply : replies){ %>
					<div class = "well well-sm reply-well">
						<a href="#"><%= reply.getName() %></a><span class = "pull-right"><%=reply.time()%></span>
						<p><%= reply.getReply()%></p>
					</div>
					
			<%} %>
			<div class = "comment-box" hidden>
					<form class = "replyForm">
			<div class = "form-group">
				<textarea class = "form-control commentsReply" value = " " name = "reply"></textarea>
			</div>
			<div class = "form-group">
				<input type = "hidden" value = <%=viewComment.getId()%> name = "commentId"/>
			</div>
			</form>
				</div>
				<% if((session.getAttribute("loggedIn")!= null)  && session.getAttribute("loggedIn").equals("true")){ %>
				<button class = "reply btn btn-primary reply-button" onclick = "toogleAndSubmit(this)">Reply</button>
		<%} %>
		</div>
		</div> 
		
		</div>

</body>
<script src = "functions.js" type = "text/javascript"></script>
</html>