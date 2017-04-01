package test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import beans.Comments;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/wschat")
public class WsChat{
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
    public void onMessage(String msg) throws SQLException, EncodeException{
        try{
            for(Session session : sessionList){
                //asynchronous communication
            	session.getBasicRemote().sendText(msg);
            	
                Comments.message(msg);
            }
        }catch(IOException e){}
    }
}