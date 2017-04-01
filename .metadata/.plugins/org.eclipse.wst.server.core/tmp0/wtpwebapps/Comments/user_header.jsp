<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import = "java.util.*" %>
 <%@page import="beans.Comments"%>
 <%@page import="beans.Comment"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
	<% if(!session.getAttribute("name").equals(null)){ %>
		var userName = "<%= session.getAttribute("name") %>";
	<%}%>
	var unreadComments = <%=beans.Comments.noOfUnreadComments((String)session.getAttribute("name"))%>;
</script>
<% ArrayList<Comment> commentsNotification = Comments.showComments(); %>
<%int counter = 0; %>
<%if(commentsNotification.size() < 10){
		counter = commentsNotification.size();
	}else{
		counter = 10;
}%>
<div class = "container">
		<div class = "dropdown">
			<button class ="btn btn-default dropdown-toggle pull-right" type = "button" data-toggle = "dropdown"><span class = "glyphicon glyphicon-user" id ="user"></span></button>
		<ul class = "dropdown-menu pull-right"  style = "margin-top:33px;">
			<li><a href = "/Comments/logout">Logout<span class = "test glyphicon glyphicon-log-out"></span>(${sessionScope.name})</a></li>
			
		</ul>
		</div>
		
		<div class = "dropdown">
			
			<button class = "btn btn-default dropdown-toggle pull-right" id = "reset" type = "button"  data-toggle ="dropdown"><span class = "glyphicon glyphicon-bell"></span><span id = "unread" class = "badge" style = "margin-top:-15px;"></span></button>
			<%if(!commentsNotification.isEmpty()){ %>
			<ul class = "dropdown-menu pull-right list-group" id = "notifyTab" style = "margin-top:33px; margin-right:3em;">
					<% for(int i = 0; i<counter; i++) { %>
						<%if((commentsNotification.get(i).getCommentFor().equals(session.getAttribute("name"))) || commentsNotification.get(i).getCommentFor().equals("All")) {%>
							<%if(commentsNotification.get(i).read.equals("false")){ %>
								<li class = "list-group-item notification"><a href="/Comments/viewComment.jsp?commentId=<%=commentsNotification.get(i).getId()%>">You have a comment from <b><%=commentsNotification.get(i).getName()%></b></a></li>
							<%} %>
							<%if(commentsNotification.get(i).read.equals("true")){ %>
								<li class = "list-group-item list-group-item-success" ><a href = "/Comments/viewComment.jsp?commentId=<%=commentsNotification.get(i).getId()%>">You have a comment from <b><%=commentsNotification.get(i).getName()%></b></a></li>
							<%} %>
						<%} %>
					<%}%>
					
			</ul>
			<%} %>
			
		</div>
		
		
	</div>
