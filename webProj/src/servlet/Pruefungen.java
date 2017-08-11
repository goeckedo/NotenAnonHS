package servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ExamManager;
import dao.UserManager;
import entity.Exam;
import entity.User_;

/**
 * Servlet implementation class Pruefungen
 */
@WebServlet("/pruefungen")
public class Pruefungen extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ExamManager manager = new ExamManager("webProj");
	UserManager managerU = new UserManager("webProj");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pruefungen() {
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
		int id;
		if(request.getParameter("name") != null){
			String name = request.getParameter("name");
			User_ user = managerU.getUserByName(name).iterator().next();
			id = user.getUserID();
		}else{
			id = new Integer(request.getParameter("id"));
		}
		System.out.println("In Prüfungen, übergebene ID = " + id);
		//getUser().getID
		
		Collection<Exam> pruefungen;
		int cnt = 0;
		String payload = "[";
		
		//User_ user;
		//user = (User_) manager.getUserByID(id);
		
		pruefungen = (Collection<Exam>) manager.getExamByPruefer(id);
		for(Exam p : pruefungen){
			System.out.println(p.toString());
			payload += "{";
			payload += "\"exam\": \"" + p.getSortname() + "\",";
			payload += "\"id\": \"" + p.getExamID() + "\",";
			payload += "\"pNR\": \"" + p.getpNR() + "\"";
			payload += "}";	
			if (cnt < pruefungen.size() - 1) payload += ",\n";
			cnt++;
		}
		payload += "]";
		System.out.println("Payload = " + payload);
		response.getWriter().write(payload);
		
	}

}
