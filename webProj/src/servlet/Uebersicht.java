package servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ExamManager;
import dao.UserManager;
import entity.Exam;
import entity.Faculty;
import entity.User_;

/**
 * Servlet implementation class uebersicht
 */
@WebServlet("/uebersicht")
public class Uebersicht extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager managerU = new UserManager("webProj");
	ExamManager em = new ExamManager("webProj");

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Uebersicht() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
			//HttpSession session = request.getSession();
			String username = request.getUserPrincipal().getName();
			
			User_ user = managerU.getUserByName(username).iterator().next();
			Faculty faculty = user.getFaculty();
			Collection<Exam> exams = em.getExamsByUserId(user.getUserID());
				
			request.setAttribute("username", user.getName());
			request.setAttribute("examList", exams);
			
			
			request.setAttribute( "",user.getUserID());
			request.setAttribute("faculty", faculty.getToken());
			System.out.println("faculty "+faculty.getToken());
			request.getRequestDispatcher("./intern/uebersicht.jsp").forward(request, response);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
