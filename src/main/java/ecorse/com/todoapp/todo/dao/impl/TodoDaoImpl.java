package ecorse.com.todoapp.todo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ecorse.com.todoapp.todo.dao.ITodoDao;
import ecorse.com.todoapp.todo.model.Todo;
import ecorse.com.todoapp.util.JDBCUtils;

public class TodoDaoImpl implements ITodoDao {

	private static final String INSERT_TODOS_SQL = "INSERT INTO todos"
			+ "  (title, username, description, target_date,  is_done) VALUES " + " (?, ?, ?, ?, ?);";

	private static final String SELECT_TODO_BY_ID = "select todo_id,title,username,description,target_date,is_done from todos where todo_id =?";
	private static final String SELECT_ALL_TODOS = "select * from todos";
	private static final String DELETE_TODO_BY_ID = "delete from todos where todo_id = ?;";
	private static final String UPDATE_TODO = "update todos set title = ?, username= ?, description =?, target_date =?, is_done = ? where todo_id = ?;";

	@Override
	public void insertTodo(Todo todo) throws SQLException {
		System.out.println(INSERT_TODOS_SQL);
		try {
			Connection connection = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TODOS_SQL);
			preparedStatement.setString(1, todo.getTitle());
			preparedStatement.setString(2, todo.getUsername());
			preparedStatement.setString(3, todo.getDescription());
			preparedStatement.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate()));
			preparedStatement.setBoolean(5, todo.isStatus());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean updateTodo(Todo todo) throws SQLException {
		boolean rowUpdated=false;
        try { 
        	Connection connection = JDBCUtils.getConnection(); 
        	PreparedStatement statement = connection.prepareStatement(UPDATE_TODO);
            statement.setString(1, todo.getTitle());
            statement.setString(2, todo.getUsername());
            statement.setString(3, todo.getDescription());
            statement.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate()));
            statement.setBoolean(5, todo.isStatus());
            statement.setLong(6, todo.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return rowUpdated;
	}

	@Override
	public boolean deleteTodo(int id) throws SQLException {
		boolean rowDeleted=false;
        try {
        	Connection connection = JDBCUtils.getConnection(); 
       		PreparedStatement statement = connection.prepareStatement(DELETE_TODO_BY_ID);
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return rowDeleted;
	}

	@Override
	public Todo selectTodo(long todoId) {
		Todo todo = null;
		try {
			// Step 1: Establishing a Connection
			Connection connection = JDBCUtils.getConnection();

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TODO_BY_ID);
			preparedStatement.setLong(1, todoId);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				long id = rs.getLong("todo_id");
				String title = rs.getString("title");
				String username = rs.getString("username");
				String description = rs.getString("description");
				LocalDate targetDate = rs.getDate("target_date").toLocalDate();
				boolean isDone = rs.getBoolean("is_done");
				todo = new Todo(id, title, username, description, targetDate, isDone);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return todo;
	}

	@Override
	public List<Todo> selectAllTodos() {
        List < Todo > todos = new ArrayList<Todo>();

        try {
        	// Step 1: Establishing a Connection
        	Connection connection = JDBCUtils.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TODOS);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                long id = rs.getLong("todo_id");
                String title = rs.getString("title");
                String username = rs.getString("username");
                String description = rs.getString("description");
                LocalDate targetDate = rs.getDate("target_date").toLocalDate();
                boolean isDone = rs.getBoolean("is_done");
                todos.add(new Todo(id, title, username, description, targetDate, isDone));
            }
        } catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return todos;
	}

}
