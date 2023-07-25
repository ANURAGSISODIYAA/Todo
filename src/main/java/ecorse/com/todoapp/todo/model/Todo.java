package ecorse.com.todoapp.todo.model;

import java.time.LocalDate;
import java.util.Objects;

public class Todo {

	private Long id;
    private String title;
    private String username;
    private String description;
    private LocalDate targetDate;
    private boolean status;
    
	public Todo() {
		super();
	}
	
	public Todo(Long id, String title, String username, String description, LocalDate targetDate, boolean status) {
		super();
		this.id = id;
		this.title = title;
		this.username = username;
		this.description = description;
		this.targetDate = targetDate;
		this.status = status;
	}

	public Todo(String title, String username, String description, LocalDate targetDate, boolean status) {
		super();
		this.title = title;
		this.username = username;
		this.description = description;
		this.targetDate = targetDate;
		this.status = status;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Todo [id=" + id + ", title=" + title + ", username=" + username + ", description=" + description
				+ ", targetDate=" + targetDate + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, status, targetDate, title, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id) && status == other.status
				&& Objects.equals(targetDate, other.targetDate) && Objects.equals(title, other.title)
				&& Objects.equals(username, other.username);
	}
}
