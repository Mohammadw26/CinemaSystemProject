package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;


@Entity
@Inheritance

public class Worker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4521913046405040845L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	protected String workerId;
	protected String workerName;
	protected String workerPassword;
	protected String wokerUsername;
	protected String workerEmail;
	
	public Worker() {}
	
	public Worker (String iD, String name, String password, String username, String email){
		this.workerId = iD;
		this.workerName = name;
		this.workerPassword = password;
		this.wokerUsername = username;
		this.workerEmail = email;
	}
	
	public String getWorkerID(){
		return this.workerId;
	}
	
	public void setWorkerID(String _ID){
		this.workerId = _ID;
	}
	
	public String getWorkerName(){
		return this.workerName;
	}
	
	public void setWorkerName(String _name){
		this.workerName = _name;
	}
	
	public String getWorkerPassword(){
		return this.workerPassword;
	}
	
	public void setWorkerPassword(String _pass){
		this.workerPassword = _pass;
	}

	
	public String getWorkerEmail() {
		return workerEmail;
	}

	public void setWorkerEmail(String workerEmail) {
		this.workerEmail = workerEmail;
	}

	public String getWokerUsername() {
		return wokerUsername;
	}

	public void setWokerUsername(String wokerUsername) {
		this.wokerUsername = wokerUsername;
	}
	
}

	
	

	