package servlet;

import java.io.IOException;
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
 * Servlet implementation class activ
 */
@WebServlet("/activ")
public class Activ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LoginManager manager = new LoginManager("webProj");  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Activ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//aktuellen Status abfragen
		
		HttpSession session = request.getSession();
		
		if (((String) session.getAttribute("user"))== null){
			session.setAttribute("user", request.getUserPrincipal().getName());
		} 
		//Als Object!!??  Aus DB!!!!!!
		Collection<Login> loginData = manager.getUserByName("admin");
		String activ = loginData.iterator().next().getAktiv();
		
		//parameterübergabe
		System.out.println(request.getAttribute("User"));
		
		//Benutzer INFORMIEREN!!
		if (activ == "1"){
			manager.setInactiv("admin");
		}
		else if (activ == "0"){
			manager.setActiv("admin");
		}
		else {}
			//error
	}

}
