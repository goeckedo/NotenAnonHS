package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ExamDetailsManager;
import entity.ExamDetails;
import entity.Faculty;
import entity.Login;
import entity.User_;

/**
 * Servlet implementation class SaveNoten
 */
@WebServlet("/saveNoten")
public class SaveNoten extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ExamDetailsManager managerED = new ExamDetailsManager("webProj");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveNoten() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Collection!!!
		System.out.println("In SaveNoten Servlet Par: "+request.getParameter("id")+" , "+request.getParameter("note")+" , "+request.getParameter("noteFest"));
		
		int lottoID, note;
		
		//Auf Fehler prüfen
		try {
			
		// Request auslesen
		lottoID = Integer.parseInt(request.getParameter("id"));
		note = Integer.parseInt(request.getParameter("note"));
			if(note == 100 ||note == 130||note == 170||note == 200) {
			
				ExamDetails e = (ExamDetails) managerED.findByLottoID(lottoID);
				e.setBewertung(note);
				managerED.save(e);

				response.sendRedirect("./intern/benutzer.jsp");	
			}
			else{
				response.sendRedirect("./intern/NotenFehler.jsp");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("./intern/AnlegeFehler.jsp");
		}			
	}
}
