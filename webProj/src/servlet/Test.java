package servlet;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginManager;
import entity.Login;


/**
 * Servlet implementation class test
 */
@WebServlet("/test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LoginManager manager = new LoginManager("webProj");

    /**
     * Default constructor. 
     */
    public Test() {
        // TODO Auto-generated constructor stub
    }

         
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Test");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//session
		HttpSession session = request.getSession();
		session.setAttribute("username",request.getParameter("username"));
		
		Collection<Login> loginData = manager.getUserByName(request.getParameter("username").toLowerCase());
		System.err.println(Arrays.toString(loginData.toArray()));
	
		//User Pw aus Datenbank
		String username = loginData.iterator().next().getUsername(); 
		String password = loginData.iterator().next().getPassword();
		
		
		//DEBUG
		System.out.println(loginData.iterator().next().getUsername());
		System.out.println(loginData.iterator().next().getPassword());
		System.out.println(dao.GenericManager.hash1(request.getParameter("password")));
		
		//Der Login
		if(loginData != null && username.equals(request.getParameter("username")) && password.equals(dao.GenericManager.hash1(request.getParameter("password")))){
			//in Log datei schreiben
			System.out.println("login success");
			
			//TODO Session aufbauen mit erfolg von oben runter
			
			response.sendRedirect("startseite.html");
		} else{
			System.out.println("login denied");
			response.sendRedirect("index.html");
		}
				
	}
	
}