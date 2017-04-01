package beans;


import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;




import org.json.JSONArray;
import org.json.JSONObject;

import includes.Database;

public class Comments {
	private String comment;
	private String name;
	private int id;
	private String commentFor;
	private Date comment_time;
	private String read;
	public static String error = "Commentbox";
	
	
	public Comments(String name,String comment,Date comment_time,String commentFor,String read) throws SQLException{
		this.name = name;
		this.comment = comment;
		this.comment_time = comment_time;
		this.commentFor = commentFor;
		this.read = read;
		
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCommentFor() {
		return commentFor;
	}

	public void setCommentFor(String commentFor) {
		this.commentFor = commentFor;
	}

	public static int comment(String name,String comment,Date commentTime,String commentFor,String read) throws SQLException{
		String sql = "INSERT INTO comments VALUES(id,?,?,?,?,?)";
		int result = queryUpdate(prepare(sql,name,comment,commentTime,commentFor,read));  
		if(result == 0){
			error = "Comment cannot be done!";
		}
		String query = "SELECT id FROM comments WHERE comment = ? AND comment_time = ?";
		ResultSet rs =  querySelect(prepare(query,comment,commentTime));
		int id = -1;
		while(rs.next()){
			id = rs.getInt(1);
		}
		return id;
		
	}
	
	public static PreparedStatement prepare(String sql,String comment,Date comment_time){
		try {
			PreparedStatement st = Database.conn.prepareStatement(sql);
			st.setString(1,comment);
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String formattedTime = formatDate.format(comment_time);
			st.setString(2,formattedTime);
			return st;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static PreparedStatement prepare(String sql,String name,String comment,Date comment_time,String commentFor,String read){
		try {
			PreparedStatement st = Database.conn.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2,comment);
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String formattedTime = formatDate.format(comment_time);
			st.setString(3,formattedTime);
			st.setString(4,commentFor);
			st.setString(5,read);
			return st;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
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
	
	public static int queryUpdate(PreparedStatement stm) throws SQLException{
			return stm.executeUpdate();
	}
	public static ResultSet querySelect(PreparedStatement stm) throws SQLException{
		return stm.executeQuery();
	}
	public static ArrayList<Comment>  showComments() throws SQLException{
		ArrayList<Comment> newComments = new ArrayList<Comment>();
		String sql = "SELECT * FROM comments ORDER BY id DESC";
		ResultSet rs = querySelect(prepare(sql));
		while(rs.next()){
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String comment = rs.getString("comment");
			Date timeOfComment = rs.getTimestamp("comment_time");
			String commentFor = rs.getString("commentFor");
			String read = rs.getString("read");
			Comment newComment = new Comment(id,name,comment,timeOfComment,commentFor,read);
			newComments.add(newComment);
			
			}
		return newComments;
	}
	public static int countOfComments() throws SQLException{
		String sql = "SELECT COUNT(*) FROM comments";
		ResultSet rs = querySelect(prepare(sql));
		while(rs.next()){
			return rs.getInt(1);
		}
		return 0;
	}
	public static int countOfReplies() throws SQLException{
		String sql = "SELECT COUNT(*) FROM replies";
		ResultSet rs = querySelect(prepare(sql));
		while(rs.next()){
			return rs.getInt(1);
		}
		return 0;
	}
	public static ArrayList<Comment>  showNewComments() throws SQLException{
		ArrayList<Comment> newComments = new ArrayList<Comment>();
		String sql = "SELECT * FROM comments ORDER BY id DESC";
		ResultSet rs = querySelect(prepare(sql));
		while(rs.next()){
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String comment = rs.getString("comment");
			Date timeOfComment = rs.getDate("comment_time");
			String commentFor = rs.getString("commentFor");
			String read = rs.getString("read");
			Comment newComment = new Comment(id,name,comment,timeOfComment,commentFor,read);
			newComments.add(newComment);
			
			}
		return newComments;
	}
	public Date getCommentTime(){
		return comment_time;
	}
	public static PreparedStatement prepare(String sql,String msg){
		try {
			PreparedStatement st = Database.conn.prepareStatement(sql);
			st.setString(1,msg);
			return st;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void message(String msg) throws SQLException{
		String sql = "INSERT INTO ws VALUES(?)";
		int result = queryUpdate(prepare(sql,msg));
	}
	public static String  getMessages() throws SQLException{
		JSONArray stats = new JSONArray();
		String sql = "SELECT * FROM  ws";
		ResultSet rs = querySelect(prepare(sql));
		while(rs.next()){
			stats.put(rs.getNString("message"));
		}
		return stats.toString();
	}
	
	public static int noOfUnreadComments(String user) throws SQLException{
		String sql = "SELECT COUNT(*) FROM comments WHERE comments.read = 'false' AND comments.commentFor = ?";
		ResultSet rs = querySelect(prepare(sql,user));
		int unreadComments = 0;
		while(rs.next()){
			unreadComments = rs.getInt(1);
		}
		return unreadComments;
	}
	
	public static boolean updateReadStatus(String name) throws SQLException{
		String sql = "UPDATE comments set comments.read = 'true' WHERE comments.commentFor = ?";
		if(queryUpdate(prepare(sql,name)) > 0){
			return true;
		}
		return false;
	}
	public static Comment getCommentById(int commentId) throws SQLException{
		String query = "SELECT * FROM comments where id =" + commentId;
		ResultSet rs = querySelect(prepare(query));
		Comment selectedComment = null;
		while(rs.next()){
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String comment = rs.getString("comment");
			Date timeOfComment = rs.getDate("comment_time");
			String commentFor = rs.getString("commentFor");
			String read = rs.getString("read");
			selectedComment = new Comment(id,name,comment,timeOfComment,commentFor,read);
		}
		return selectedComment;
	}

}
