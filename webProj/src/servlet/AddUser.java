package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FacultyManager;
import dao.LoginManager;
import dao.UserManager;
import entity.Faculty;
import entity.Login;
import entity.User_;

import javax.servlet.ServletException;
import java.io.IOException;


@WebServlet(
        name = "AddUser",
        description = "Servlet to handle adding user to the db",
        urlPatterns = "/addUser"
)
public class AddUser extends HttpServlet{
	private static final long serialVersionUID = 768123678123231L;
	UserManager manager = new UserManager("webProj");
	FacultyManager managerF = new FacultyManager("webProj");
	LoginManager managerL = new LoginManager("webProj");	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String lusername, lpassword, lastname, firstname, lpasswordRep;
		int facultyid=0, personalnummer=0;
		
		// Request auslesen
		lusername = request.getParameter("username");
		lpassword = request.getParameter("passwort");
		lpasswordRep = request.getParameter("passwordRep");
		lastname = request.getParameter("nachname");
		firstname = request.getParameter("vorname");
		
		//Auf Fehler prüfen
		try {
		personalnummer = Integer.parseInt(request.getParameter("personalNummer"));
		facultyid = Integer.parseInt(request.getParameter("fakultaet"));
		System.out.println("lusername: "+ lusername);
		System.out.println("lpasswordRep: "+ lpasswordRep);
		
		//Verliert ganze input maske | Fakultät collection senden
			if(manager.persoNoExists(personalnummer) || manager.usernameExists(lusername) || !lpassword.equals(lpasswordRep)) {
				response.sendRedirect("./intern/AnlegeFehler.jsp");
			}
			else{
				String name = (lastname + firstname);
				System.err.println(name);
				Faculty f = (Faculty) managerF.findByPrimaryKey(facultyid);
				Login l = new Login(lusername, lpassword, f);
				managerL.save(l);
				User_ user = new User_(name, request.getParameter("personalNummer"), f, l);
				manager.save(user);
			
				System.out.println("Username: " + lusername);
				System.out.println("Nachname: " + lastname);
				System.out.println("Vorname: " + firstname);
		
				response.sendRedirect("./intern/benutzer.jsp");	
			}
		
		}
		catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("./intern/AnlegeFehler.jsp");
		}		
	}
}
