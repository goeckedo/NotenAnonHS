package servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FacultyManager;
import entity.Faculty;

/**
 * Servlet implementation class initAddUser
 */
@WebServlet(
        name = "InitAddUser",
        description = "Servlet to initiate AddUser page",
        urlPatterns = "/initAddUser"
)
public class InitAddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    FacultyManager manager = new FacultyManager("webProj");

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<Faculty> f = (Collection<Faculty>) manager.list();
		request.setAttribute("facCol", f);

		request.getRequestDispatcher("./intern/benutzerAnlegen.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
