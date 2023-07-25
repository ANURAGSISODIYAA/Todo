package ecorse.com.todoapp.todo.ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecorse.com.todoapp.todo.dao.ITodoDao;
import ecorse.com.todoapp.todo.dao.impl.TodoDaoImpl;
import ecorse.com.todoapp.todo.model.Todo;

@WebServlet("/")
public class TodoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5151468520771774797L;
	private ITodoDao todoDao;

	@Override
	public void init() {
		todoDao = new TodoDaoImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(req, resp);
				break;
			case "/insert":
				insertTodo(req, resp);
				break;
			case "/delete":
				deleteTodo(req, resp);
				break;
			case "/edit":
				showEditForm(req, resp);
				break;
			case "/update":
				updateTodo(req, resp);
				break;
			case "/list":
				listTodo(req, resp);
				break;
			default:
				RequestDispatcher dispatcher = req.getRequestDispatcher("login/login.jsp");
				dispatcher.forward(req, resp);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo-form.jsp");
		dispatcher.forward(request, response);
	}

	private void insertTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

		String title = request.getParameter("title");
		String username = request.getParameter("username");
		String description = request.getParameter("description");

		/*
		 * DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd"); LocalDate
		 * targetDate = LocalDate.parse(request.getParameter("targetDate"),df);
		 */

		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		Todo newTodo = new Todo(title, username, description, LocalDate.now(), isDone);
		todoDao.insertTodo(newTodo);
		response.sendRedirect("list");
	}

	private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		todoDao.deleteTodo(id);
		response.sendRedirect("list");
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Todo existingTodo = todoDao.selectTodo(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo-form.jsp");
		request.setAttribute("todo", existingTodo);
		dispatcher.forward(request, response);
	}

	private void updateTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		long id = Integer.parseInt(request.getParameter("id"));

		String title = request.getParameter("title");
		String username = request.getParameter("username");
		String description = request.getParameter("description");
		// DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));

		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		Todo updateTodo = new Todo(id, title, username, description, targetDate, isDone);

		todoDao.updateTodo(updateTodo);

		response.sendRedirect("list");
	}

	private void listTodo(HttpServletRequest request, 
			HttpServletResponse response)
					throws SQLException, IOException, ServletException {
		List<Todo> listTodo = todoDao.selectAllTodos();
		request.setAttribute("listTodo", listTodo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo-list.jsp");
		dispatcher.forward(request, response);
	}

}
