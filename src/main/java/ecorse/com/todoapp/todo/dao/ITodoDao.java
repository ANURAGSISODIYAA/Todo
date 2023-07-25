package ecorse.com.todoapp.todo.dao;

import java.sql.SQLException;
import java.util.List;

import ecorse.com.todoapp.todo.model.Todo;

public interface ITodoDao {

	public void insertTodo(Todo todo) throws SQLException;

	public boolean updateTodo(Todo todo) throws SQLException;

	public boolean deleteTodo(int id) throws SQLException;

	public Todo selectTodo(long todoId);

	public List<Todo> selectAllTodos();
}
