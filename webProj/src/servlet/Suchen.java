package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlet.JqXHRErrorFactory;
import dao.*;
import entity.User_;
import java.util.*;


@WebServlet("/Suchen")

public class Suchen extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager manager = new UserManager("webProj");

	  public Suchen() {
	        super();
	  }
	        
	  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  doPost(request, response);
	  }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Servlet geladen");
		
            String pid = "";
            if (request.getParameter("pid") != null) pid = request.getParameter("pid").trim();					//Sucheingabe
            
            System.out.println("sent pid: " + pid);
            
        	String payload = "[";            
            if (!pid.isEmpty()) {

            	int count = 0;
            
            	Collection<User_> coll = (Collection<User_>) manager.sucheUser(pid);  
     
            	System.out.println("Ausgabe vor JSON");
 
            	for(User_ u: coll){
            		System.out.println(u.toString());
    			
            		payload += "{";
            		payload += "\"name\": \"" + u.getName() + "\",";
            		payload += "\"username\": \"" + u.getLogin().getUsername() + "\",";
            		payload += "\"id\": \"" + u.getUserID() + "\",";
            		payload += "\"faculty\": \"" + u.getFaculty().getName() + "\"";
            		payload += "}";
    			
            		if (count < coll.size() - 1) payload += ",\n";
            		count++;
            	}
    	
            } //Ende if
            else {
            	System.out.println("Gesuchten User eingeben!");
            } //Ende else

            payload += "]";
    		
        	System.out.println("Nach JSON:\n" + payload);
        	response.getWriter().write(payload);	
            
	} //Ende doPost
	
} //Ende class