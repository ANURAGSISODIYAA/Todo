package ecorse.com.todoapp.login.model;

import java.io.Serializable;

public class Login implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 874662980722670428L;

	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Login [userName=" + userName + ", password=" + password + "]";
	}
	
}
