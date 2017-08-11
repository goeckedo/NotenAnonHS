package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Auf alles Seiten einfügen
@WebServlet("/logout")
public class Logout extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public Logout() {
	        // TODO Auto-generated constructor stub
	    }

	         
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			//session
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");		
		}
}
