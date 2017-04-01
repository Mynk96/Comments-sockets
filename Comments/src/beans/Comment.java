package beans;

import includes.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Comment {
	private int id;
	private String name;
	private String comment;
	private Date comment_time;
	private String commentFor;
	public String read;
	
	public Comment(int id,String name,String comment,Date comment_time,String commentFor,String read){
		this.id = id;
		this.name = name;
		this.comment = comment;
		this.comment_time = comment_time;
		this.commentFor = commentFor;
		this.read = read;
	}
	public String getCommentFor() {
		return commentFor;
	}
	public void setCommentFor(String commentFor) {
		this.commentFor = commentFor;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<ReplyData> showReplies() throws SQLException{
		ArrayList<ReplyData> replies = new ArrayList<ReplyData>();
		String sql = "SELECT * FROM replies WHERE comment_id = ?";
		ResultSet rs = querySelect(prepare(sql,getId()));
		while(rs.next()){
			String name = rs.getString("name");
			String reply = rs.getString("reply");
			Date replyTime = rs.getTimestamp("replyTime");
			int comment_id = rs.getInt("comment_id");
			ReplyData commentReply = new ReplyData(name,reply,comment_id,replyTime);
			replies.add(commentReply);
		}
		return replies;
	}
	public PreparedStatement prepare(String sql,int comment_id){
		try {
			PreparedStatement st = Database.conn.prepareStatement(sql);
			st.setInt(1,comment_id);
			return st;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public ResultSet querySelect(PreparedStatement stm) throws SQLException{
		return stm.executeQuery();
	}
	public String getCommentTime() {
		SimpleDateFormat formatDate = new SimpleDateFormat("dd MMM YYYY HH:mma");
		return formatDate.format(comment_time);
	}

}
