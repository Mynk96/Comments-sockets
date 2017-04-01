/**
 * All the functions 
 */
function createNotification(username,commentId){
	console.log(commentId);
	var listElement = document.createElement("li");
	listElement.setAttribute("class", "list-group-item");
	var linkElement = document.createElement("a");
	linkElement.setAttribute("href","/Comments/viewComment.jsp?commentId="+commentId);
	linkElement.innerHTML =  "   "  +  "You have a comment from " +" " + username;
	listElement.appendChild(linkElement);
	document.getElementById("notifyTab").insertBefore(listElement,document.getElementById("notifyTab").childNodes[0]);
}
function createReplyNotification(username,commentId){
	var listElement = document.createElement("li");
	listElement.setAttribute("class", "list-group-item");
	var linkElement = document.createElement("a");
	linkElement.setAttribute("href","/Comments/viewComment.jsp?commentId="+commentId);
	linkElement.innerHTML = username + " Replied to your comment";
	listElement.appendChild(linkElement);
	document.getElementById("notifyTab").insertBefore(listElement,document.getElementById("notifyTab").childNodes[0]);
}
var ws = new WebSocket("ws://localhost:8080/Comments/commentsocket");
            ws.onopen = function(){
            	
            };
            ws.onmessage = function(message){
            	console.log(message.data);
            	var mess = JSON.parse(message.data);
            	if(mess.content === "comment"){
            	if(mess.user != null && mess.comment != null){
            		
            		if(mess.commentFor === userName || mess.commentFor === "All" || mess.user === userName){
            			$(".viewMore").fadeIn();
            			document.getElementById("unread").innerHTML = parseInt(document.getElementById("unread").innerHTML)+1 ;
            			window.createNotification(mess.user,mess.commentId);
            	
            		}
            	}
            	$("#comment").val(" ");
            	}
            	if(mess.content === "reply"){
            		$(".viewMore").fadeIn();
            		if(mess.user != null && mess.reply != null && mess.user != userName){
        			document.getElementById("unread").innerHTML = parseInt(document.getElementById("unread").innerHTML)+1 ;
        			console.log(mess.commentId);
        			window.createReplyNotification(mess.user,mess.commentId);
            		
            		}
            	}
            	
            };
            function postCommentToServer(){
            	
            	var data = {
            			content :   'comment',
            			comment : 	document.getElementById("comment").value,
            			user : 	  	userName,
            			commentFor:	document.getElementById("commentFor").value,
            			read:		'false',
            			unreadNumber:parseInt(document.getElementById("unread").innerHTML) + 1,
            			
            	};
            	ws.send(JSON.stringify(data));
                
            }
            function postReplyToServer(index,value){
            	var replyForm = $('form:eq(' + (index+1) + ' )');
        		var formArray = replyForm.serializeArray();
            	var data = {
            			content:	'reply',
            			user : 	  	userName,
            			index : index,
            			reply:formArray[0].value,
            			commentId:parseInt(formArray[1].value)
            	};
            	ws.send(JSON.stringify(data));
            }
            function closeConnect(){
                ws.close();
            }

$(document).ready(function(){
	

	$("#newComments").load("comments.jsp");
	document.getElementById("unread").innerHTML = unreadComments;
});







function validateForm(){
		var x = document.forms["testform"]["comment"].value;
		if(x == ""){
			$("#error").show();
			document.getElementById("error").innerHTML = "NO Comment";
		}
		else{
			$("#error").hide();
			postCommentToServer();
		}
	}
function submitForm(){
	$(document).ready(function(){
				$.post("http://localhost:8080/Comments/Comments",$("#testform").serialize(), function(data,status){
				$("#comment").val("");
				});
		});
}
function logout(){
	$(document).ready(function(){
		$.get("http://localhost:8080/Comments/logout",{logout:"yes"});	
	});
	
}
function isEmpty(value){
	if(value.length == 0){
		return true;
	} else if(value == "") {
		return true;
	}else if (value == this.default){
		return true;
	}else{
		return false;
	}
}

function toogleAndSubmit(object){
	var comments = document.getElementsByClassName("comment-box");
	var textAreaValue = document.getElementsByClassName("commentsReply");
	var index = $(".reply").index(object);
	console.log(index);
	var value = $(textAreaValue[index]).val();
	if(isEmpty(value)){
		$(comments[index]).toggle();
	}else{
		if(window.location.href == "http://localhost:8080/Comments/index.jsp"){
			postReplyToServer(index,object);
			$(textAreaValue[index]).val(" ");
			$(comments[index]).toggle();
		}else{
			postReplyToServer(index-1,object);
			$(textAreaValue[index]).val(" ");
			$(comments[index]).toggle();
		}
	}
}
function viewNewComments(){
	$(document).ready(function(){
			$("html body").animate({scrollTop:0},600);
			$("#newComments").load("comments.jsp");
			$(".viewMore").fadeOut();
	});
}

$(document).on("click", "#reset", function() {
	
	$.get("http://localhost:8080/Comments/Comments",{setAllRead : "true"},function(data,status){
		$(this).addClass("list-group-item-success");
		document.getElementById("unread").innerHTML = 0;
		console.log(status);
	});
	
});
$("li").click(function(){
	$(this).addClass("list-group-item-success");
});