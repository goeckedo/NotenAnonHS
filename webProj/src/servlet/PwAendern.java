package servlet;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import dao.*;
import entity.*;


/**
 * Servlet implementation class test
 */
@WebServlet(name = "PwAendern", urlPatterns={"/pwAendern"})
public class PwAendern extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LoginManager manager = new LoginManager("webProj");


    /**
     * Default constructor. 
     */
    public PwAendern() {
        // TODO Auto-generated constructor stub
    }

         
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Test");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in doPost");
		//user aus session
		/*
		HttpSession session = request.getSession();
		String aktUser = (String) session.getAttribute("user");
		*/
		
		//Ohne Session
		String aktUser = request.getUserPrincipal().getName();
		
		Collection<Login> loginData = manager.getUserByName(aktUser);
		System.err.println(Arrays.toString(loginData.toArray()));
	
		//User Pw aus Datenbank
		String username = loginData.iterator().next().getUsername(); 
		String password = loginData.iterator().next().getPassword();
				
		//DEBUG
		System.out.println("user "+loginData.iterator().next().getUsername());
		System.out.println("pw "+loginData.iterator().next().getPassword());
		System.out.println(request.getParameter("passwordOld"));
				
		//ÜbergabeParameter
		String pwOld = request.getParameter("passwordOld");
		String pwNew = request.getParameter("password");
		String pwRep = request.getParameter("passwordRep");
		
		if(loginData != null &&  password.equals(pwOld)){
			System.out.println("pw Alt korrekt");
			//Prüfen ob pw neu gleich
			if(pwNew.equals(pwRep)){
				System.out.println("pw Neu gleich "+pwNew);
				//pw ändern
				manager.changePw(username, pwNew);	
				
			response.sendRedirect("intern/pwAendernErfolg.jsp"); //ändern
			System.out.println("PW erfolgreich geaendert");
			}
			else{	
				response.sendRedirect("intern/pwAendernFehler.jsp");
				System.out.println("Fehlerhafte Eingabe");
			}
		}
		else{	
			response.sendRedirect("intern/pwAendernFehler.jsp");
			System.out.println("Fehlerhafte Eingabe");
		} 	
		
		//Authentifizierung mit hash
		/*
		if(loginData != null &&  password.equals(dao.GenericManager.hash1(pwOld))){
			System.out.println("pw Alt korrekt");
			//Prüfen ob pw neu gleich
			if(pwNew.equals(pwRep)){
				System.out.println("pw Neu gleich "+pwNew);
				//pw ändern
				manager.changePw(username, pwNew);
				
				
				//TODO <script>function myFunction() {alert("Das Passwort wurde erfolgreich geändert");}</script>
				
				
			response.sendRedirect("pwAendern.html"); //ändern
			}
			else{	
				//response.sendRedirect("pwAendern");
				response.sendError(0);
				System.out.println("Fehlerhafte Eingabe");
			}
		}
		else{	
			//response.sendRedirect("pwAendern");
			response.sendError(0);
			System.out.println("Fehlerhafte Eingabe");
		} 	
		*/
				
	}
	
}

/*package servlet;
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

@WebServlet("/pwAendern")
public class PwAendern extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LoginManager manager = new dao.LoginManager("webProj");

   
    public PwAendern() {
        // TODO Auto-generated constructor stub
    }

         
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Test");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//user aus session
		HttpSession session = request.getSession();
		String aktUser = (String) session.getAttribute("username");
		
		Collection<Login> loginData = manager.getUserByName(aktUser);
		System.err.println(Arrays.toString(loginData.toArray()));
	
		//User Pw aus Datenbank
		String username = loginData.iterator().next().getUsername(); 
		String password = loginData.iterator().next().getPassword();
				
		//DEBUG
		System.out.println("user "+loginData.iterator().next().getUsername());
		System.out.println("pw "+loginData.iterator().next().getPassword());
		System.out.println(dao.GenericManager.hash1(request.getParameter("passwordOld")));
		
		//ÜbergabeParameter
		String pwOld = request.getParameter("passwordOld");
		String pwNew = request.getParameter("password");
		String pwRep = request.getParameter("passwordRep");
		
		//Authentifizierung
		if(loginData != null &&  password.equals(dao.GenericManager.hash1(pwOld))){
			System.out.println("pw Alt korrekt");
			//Prüfen ob pw neu gleich
			if(pwNew.equals(pwRep)){
				System.out.println("pw Neu gleich "+pwNew);
				//pw ändern
				manager.changePw(username, pwNew);
				
				
				//TODO <script>function myFunction() {alert("Das Passwort wurde erfolgreich geändert");}</script>
				
				
			response.sendRedirect("pwAendern.html"); //ändern
			}
			else{	
				//response.sendRedirect("pwAendern");
				response.sendError(0);
				System.out.println("Fehlerhafte Eingabe");
			}
		}
		else{	
			//response.sendRedirect("pwAendern");
			response.sendError(0);
			System.out.println("Fehlerhafte Eingabe");
		} 	
				
	}
	
}*/
