package servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FacultyManager;
import dao.LoginManager;
import dao.NoSuchRow;
import dao.UserManager;
import entity.Faculty;
import entity.Login;
import entity.User_;


@WebServlet("/benutzerBA")
public class BenutzerBA extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager manager = new UserManager("webProj");
	FacultyManager managerF = new FacultyManager("webProj");
	LoginManager managerL = new LoginManager("webProj");
    
    public BenutzerBA() {
    }

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int id = new Integer(request.getParameter("id"));
			User_ user = (User_) manager.findByPrimaryKey(id);
			Collection<Faculty> facCol = (Collection<Faculty>) managerF.list();
			
					
			request.setAttribute("facCol", facCol);
			request.setAttribute("benutzer", user);
			request.getRequestDispatcher("/intern/benutzerBA.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginName, username, personalnummer, vorUserID, vorPersoalnummer ;
		int fakultaet=0;
		loginName = request.getParameter("loginName");
		username =  request.getParameter("name");
		vorUserID = request.getParameter("id");
		personalnummer = request.getParameter("personalNummer");
		
		// Benutzer der geändert werden soll
		User_ user = new User_();
		try {
			user = (User_) manager.findByPrimaryKey(Integer.parseInt(vorUserID));
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchRow e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		vorPersoalnummer = user.getPersonalnummer();
		
		try {
			
			fakultaet = Integer.parseInt(request.getParameter("fakultaet"));
			
			//Debug
			System.out.println("BenuterBA.java: übergebener username: "+username);
			System.out.println("BenuterBA.java: vorheriger username: "+user.getName());
			System.out.println("BenuterBA.java: übergebener loginName: "+loginName);
			
			
			//	Änderungen in username aber	Name schon vergeben?	
			if((!username.equals(user.getName()) && manager.usernameExists(username))) {
				System.out.println("BenuterBA.java: username vergeben");
				response.sendRedirect("./intern/AnlegeFehler.jsp");
			}
			else{
				user.setName(username);
				
				//Debug
				System.out.println("BenuterBA.java: übergebener personalnummer: "+personalnummer);
				System.out.println("BenuterBA.java: vorheriger personalnummer: "+vorPersoalnummer);
				System.out.println("BenuterBA.java: vergleich: "+(personalnummer.equals(vorPersoalnummer)));
				
				
				
				// Änderungen in Personalnummer aber Nummer schon vergeben?
				if((!personalnummer.equals(vorPersoalnummer)) && manager.persoNoExists(Integer.parseInt(personalnummer))) {
					System.out.println("BenuterBA.java: personalnummer vergeben");
					response.sendRedirect("./intern/AnlegeFehler.jsp");
				}
				else{
					user.setPersonalnummer(personalnummer);
					
					Faculty f = (Faculty) managerF.findByPrimaryKey(fakultaet);
					user.setFaculty(f);
					Login l = (Login) managerL.findByPrimaryKey(user.getLogin().getLoginID());
					l.setLusername(loginName);
					user.setLogin(l);
					manager.save(user);
						
					response.sendRedirect("./intern/benutzer.jsp");	
				
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("./intern/AnlegeFehler.jsp");
		}
	}
}
