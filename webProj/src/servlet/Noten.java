package servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ExamDetailsManager;
import dao.ExamManager;
import dao.NoSuchRow;
import entity.Exam;
import entity.ExamDetails;

/**
 * Servlet implementation class Noten
 */
@WebServlet("/noten")
public class Noten extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ExamManager managerE = new ExamManager("webProj");
	ExamDetailsManager managerED = new ExamDetailsManager("webProj");
	 
    public Noten() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int examID = new Integer(request.getParameter("id"));
		
		
		System.out.println("in Noten.java mit id : "+examID);
		 		try {
			// für Header
			Exam exam = (Exam)managerE.findByPrimaryKey(examID);
			System.out.println("exam geholt");
			request.setAttribute("exam", exam);
			
			//für Tabelle
			Collection<ExamDetails> examDetails = managerED.getExamDetails(exam);
			System.out.println("size: "+examDetails.size());
			
			//Debug
			for(ExamDetails e : examDetails){
				System.out.println(e.toString());
			}

			request.setAttribute("examDetails", examDetails);
			
			// weiterleiten
			request.getRequestDispatcher("/intern/noten.jsp").forward(request, response);
			
		} catch (NoSuchRow e) {
			e.printStackTrace();
			// Redirect an Fehler
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
