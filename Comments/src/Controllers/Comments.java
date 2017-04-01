package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import includes.Sessions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





/**
 * Servlet implementation class Comments
 */
public class Comments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /** 
     * @see HttpServlet#HttpServlet()
     */
    public Comments() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		if(request.getParameter("setAllRead") != null && request.getParameter("setAllRead").equals("true")){
			String name = (String)request.getSession().getAttribute("name");
			try {
				if(beans.Comments.updateReadStatus(name))
					out.write("succes");
				else
					out.write("error on updating readStatus");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*response.setContentType("application/html");
		PrintWriter out = response.getWriter();
		if(!((request.getParameter("comment").isEmpty())|| (request.getParameter("comment").equals(" "))|| (request.getParameter("comment").equals("")))){
			String name = (String)request.getSession().getAttribute("name");
			String comment = request.getParameter("comment");
			Date comment_time = new Date();
			try {
				beans.Comments comments = new beans.Comments(name,comment,comment_time);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}else{
			out.write("No comment");
		}*/
	}
}
