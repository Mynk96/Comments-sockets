package test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

import beans.Comments;
import beans.ReplyData;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.*;

@ServerEndpoint(value = "/commentsocket")
public class CommentSocket{
    //notice:not thread-safe
    private static ConcurrentLinkedQueue<Session> sessionList = new ConcurrentLinkedQueue<Session>();
    
    @OnOpen
    public void onOpen(Session session) throws EncodeException, SQLException{
        try{
            sessionList.add(session);
            //asynchronous communication
            session.getBasicRemote().sendText("hello");
           
        }catch(IOException e){}
    }
    
    @OnClose
    public void onClose(Session session){
        sessionList.remove(session);
    }
    
    @OnMessage
    public void onMessage(String msg) throws SQLException{
        try{
        	JSONObject data = new JSONObject(msg);
        	if(data.getString("content").equals("comment")){
        		String name = data.getString("user");
        		String comment = data.getString("comment");
        		Date comment_time = new Date();
        		String commentFor = data.getString("commentFor");
        		String read = data.getString("read");
        		String content = data.getString("content");
        		int commentId = 0;
        		
        		try {
        			commentId = Comments.comment(name,comment,comment_time,commentFor,read);
        			
        		} catch (SQLException e) {
        			e.printStackTrace();
        		}
        		data.put("commentId", commentId);
        		msg = data.toString();
        	}
        	if(data.getString("content").equals("reply")){
        		Date replyTime = new Date();
        		String name = data.getString("user");
        		String reply = data.getString("reply");
        		int commentId = data.getInt("commentId");
        		ReplyData sendReply = new ReplyData(name,reply,commentId,replyTime);
        		try {
        			sendReply.doReply();
        		} catch (SQLException e) {
        			e.printStackTrace();
        		}
        	}
        	
        	for(Session session : sessionList){
                //asynchronous communication
                
            	session.getBasicRemote().sendText(msg); 
            	}
        }catch(IOException e){}
    }
}