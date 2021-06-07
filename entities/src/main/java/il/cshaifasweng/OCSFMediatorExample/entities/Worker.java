package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.InheritanceType;

@Entity
@Inheritance

public class Worker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4521913046405040845L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String workerId;
	private String workerName;
	private String workerPassword;
	private String wokerUsername;
	private String workerEmail;
	
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

	
	

	