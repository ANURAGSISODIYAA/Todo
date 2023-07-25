package ecorse.com.todoapp.login.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ecorse.com.todoapp.login.dao.LoginDao;
import ecorse.com.todoapp.login.model.Login;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7057811529449976401L;
	private LoginDao loginDao;
	
	@Override
	public void init() throws ServletException {
		loginDao = new LoginDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("login.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		authenticate(req, resp);
	}
	
	private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        Login loginBean = new Login();
        loginBean.setUserName(userName);
        loginBean.setPassword(password);

        try {
            if (loginDao.validate(loginBean)) {
                response.sendRedirect("list");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", userName);
                response.sendRedirect("login.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
