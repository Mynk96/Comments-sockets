	package beans;

import includes.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReplyData {
	private String name;
	private String reply;
	private int commentId;
	public static String error = "";
	private Date replyTime;
	
	public ReplyData(String name,String reply,int commentId,Date replyTime){
		this.name = name;
		this.reply = reply;
		this.commentId = commentId;
		this.setReplyTime(replyTime);
	}
	
	
	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public ReplyData(String name,String reply){
		this.name = name;
		this.reply = reply;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public boolean doReply() throws SQLException{
		String sql = "INSERT INTO replies VALUES(id,?,?,?,?)";
		int result = queryUpdate(prepare(sql,getCommentId(),getName(),getReply(),getReplyTime()));  
		if(result == 0){
			error = "reply cannot be done!";
			return false;
			
		}
		return true;
		
	}
	public static PreparedStatement prepare(String sql,int commentId,String name,String reply,Date replyTime){
		try {
			PreparedStatement st = Database.conn.prepareStatement(sql);
			st.setInt(1, commentId);
			st.setString(2,name);
			st.setString(3,reply);
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String formattedTime = formatDate.format(replyTime);
			st.setString(4,formattedTime);
			return st;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static int queryUpdate(PreparedStatement stm) throws SQLException{
		return stm.executeUpdate();
	}


	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}


	public Date getReplyTime() {
		return replyTime;
	}
	public String time(){
		SimpleDateFormat formatted = new SimpleDateFormat("dd MMM YYYY HH:mma");
		return formatted.format(replyTime);
	}
	public static PreparedStatement prepare(String sql){
		try {
			PreparedStatement st = Database.conn.prepareStatement(sql);
			return st;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static ResultSet querySelect(PreparedStatement stm) throws SQLException{
		return stm.executeQuery();
	}
	
	
}
