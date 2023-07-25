package ecorse.com.todoapp.userregistration.ctrl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecorse.com.todoapp.userregistration.dao.UserDao;
import ecorse.com.todoapp.userregistration.model.User;

@WebServlet("/register")
public class UserController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8421917254017293483L;
	private UserDao userDao;

	public void init() {
		userDao = new UserDao();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		register(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("register.jsp");
	}
	
	private void register(HttpServletRequest req, 
			HttpServletResponse resp) throws IOException, ServletException, IOException {
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		
		User employee = new User();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setUsername(userName);
		employee.setPassword(password);
		
		try {
			int result = userDao.registerEmployee(employee);
			if(result == 1)
				req.setAttribute("NOTIFICATION", "User Register Successfully!");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
        dispatcher.forward(req, resp);
	}
}
