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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "workers")
public class Worker implements Serializable {
	/**
	 * 
	 */
	//private static final long serialVersionUID = ??;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int workerId;
	private string workerName;
	private string workerPassword;
	
	public Worker() {}
	
	public Worker (int iD,string name, string password){
		this.workerId = iD;
		this.workerName = name;
		this.workerPassword = password;
	}
	
	public int getWorkerID(){
		return this.workerId;
	}
	
	public void setWorkerID(int _ID){
		this.workerId = _ID;
	}
	
	public int getWorkerName(){
		return this.workerName;
	}
	
	public void setWorkerName(int _name){
		this.workerName = _name;
	}
	
	public int getWorkerPassword(){
		return this.workerPassword;
	}
	
	public void setWorkerPassword(int _pass){
		this.workerPassword = _pass;
	}
	

	
	

	