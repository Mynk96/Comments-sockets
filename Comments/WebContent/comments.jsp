<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import = "java.util.*" %>
<%@ page import = "beans.Comments" %>
<%@ page import = "beans.Comment" %>
<%@ page import = "beans.ReplyData" %>
<%@page import = "includes.Sessions"  %>

<% ArrayList<Comment> comments = Comments.showComments();%>

<!DOCTYPE html PUBLIC "-/ /W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<% for (Comment comment : comments){ %>
			<% if((comment.getCommentFor().equals("All")) || (comment.getCommentFor().equals(session.getAttribute("name"))) || comment.getName().equals(session.getAttribute("name"))) { %>				
			<div class = "row mayank">
				<div class = "col-lg-12">
					<strong class = "edited-font"><%=comment.getName()%></strong><code style="margin-left:5px;"><%=comment.getCommentFor() %></code><span class = "pull-right"><%=comment.getCommentTime() %></span>
					<p class = "comment"><%=comment.getComment()%></p>	
				
			
		
		
		<%	ArrayList<ReplyData> replies  = comment.showReplies(); %>
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
				<input type = "hidden" value = <%=comment.getId()%> name = "commentId"/>
			</div>
			</form>
				</div>
				<% if((session.getAttribute("loggedIn")!= null)  && session.getAttribute("loggedIn").equals("true")){ %>
				<button class = "reply btn btn-primary reply-button" onclick = "toogleAndSubmit(this)">Reply</button>
		<%} %>
		<%if(comment.getName().equals(session.getAttribute("name")) && comment.read.equals("true")){%>
			<br><kbd style = "margin-bottom:5px;">Seen</kbd><br>
			<%} %>
		</div>
		
		</div> 

	<%} %>
	<%} %>