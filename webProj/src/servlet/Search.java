package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlet.JqXHRErrorFactory;
import dao.*;
import entity.ExamDetails;
import entity.User_;
import java.util.*;


@WebServlet("/Search")

public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager manager = new UserManager("webProj");

	  public Search() {
	        super();
	  }
	        
	  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  doPost(request, response);
	  }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Servlet geladen");
		
            String pid = "";
            if (request.getParameter("pid") != null){ pid = request.getParameter("pid")+"";	pid.trim();	}			//Sucheingabe
            
            //im String steht nichts drin? Evtl Fehler bei der Übergabe -> input pid kann vllt keinen int einlesen?
            System.out.println("sent pid: " + pid);
            
        	String payload = "[";            
            if (!pid.isEmpty()) {

            	int count = 0;
            
            	Collection<ExamDetails> coll = (Collection<ExamDetails>) manager.sucheExam(pid);  
     
            	System.out.println("Ausgabe vor JSON");
 
            	for(ExamDetails a: coll){
            		System.out.println(a.toString());
    			
            		payload += "{";
            		payload += "\"bewertung\": \"" + a.getBewertung() + "\",";
            		payload += "\"examLotto\": \"" + a.getExamLotto() + "\"";
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