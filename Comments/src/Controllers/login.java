package Controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Users;

/**
 * Servlet implementation class login
 */
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!((request.getParameter("email").isEmpty()) && (request.getParameter("password").isEmpty()))){
			String tempemail = request.getParameter("email");
			String temppassword = request.getParameter("password");
			Users user = new Users(tempemail,temppassword);
			HttpSession session = request.getSession();
			try {
				if(user.validate()){
					
					session.setAttribute("email", tempemail);
					session.setAttribute("password", temppassword);
					session.setAttribute("name", user.getName());
					session.setAttribute("loggedIn", "true");
					response.sendRedirect("/Comments/index.jsp");
				}else{
					session.setAttribute("loggedIn", "false");
					response.sendRedirect("/Comments/login.jsp");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
	}
		
}
