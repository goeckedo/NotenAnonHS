package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NoSuchRow;
import dao.UserManager;
/**
 * Servlet implementation class Delete
 */
@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager manager = new UserManager("webProj");
     
    public Delete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Sicherheitsabfrage!!
		
		int id = new Integer(request.getParameter("id"));
		System.out.println("In Prüfungen, übergebene ID = " + id);
				
		try {
			manager.delete(id);
		} catch (NoSuchRow e) {
			e.printStackTrace();
		}
		response.sendRedirect("./intern/benutzer.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
