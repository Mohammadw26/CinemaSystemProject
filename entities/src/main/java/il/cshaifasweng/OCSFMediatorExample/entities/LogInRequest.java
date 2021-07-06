package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class LogInRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private boolean isConnected;
	
	public LogInRequest() {}
	
	public LogInRequest(String user, String pass) {
		this.username = user;
		this.password = pass;
		setConnected(false);

	}
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}
	
}
