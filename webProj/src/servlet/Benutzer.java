package servlet;


import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
import entity.User_;

/**
 * Servlet implementation class Benutzer
 */
@WebServlet("/benutzer")
public class Benutzer extends HttpServlet {
	private static final long serialVersionUID = 1L;
     UserManager manager = new UserManager("webProj");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Benutzer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In Benutzer.java");
		
		//list aus DB
		Collection<User_> coll = (Collection<User_>) manager.list();
		
		String payload = "[";
		System.out.println("Ausgabe von getBenuter in Benutzer.java: ");
		
		int cnt = 0;
		
		for(User_ u : coll){
			System.out.println(u.toString());
			payload += "{";
			payload += "\"name\": \"" + u.getName() + "\",";
			payload += "\"username\": \"" + u.getLogin().getUsername() + "\",";
			payload += "\"faculty\": \"" + u.getFaculty().getName() + "\",";
			payload += "\"id\": \"" + u.getUserID() + "\"";
			payload += "}";
			
			if (cnt < coll.size() - 1) payload += ",\n";
			cnt++;
		}
		
		payload += "]";
		
		System.out.println("Converted to JSON:\n" + payload);
		response.getWriter().write(payload);			
	}

}
