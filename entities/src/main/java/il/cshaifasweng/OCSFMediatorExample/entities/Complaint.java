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
@Table(name = "complaints")
public class Complaint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2475537423268969676L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String sentDate;
	private String answerDate;
	private String workerName;
	private String description;
	private Client client;
	//private 
	
	public Complaint() {}
	
	public Complaint (String _sentDate,String _answerDate,String _workerName,String _description,Client _client){
		this.sentDate = _sentDate;
		this.answerDate = _answerDate;
		this.workerName = _workerName;
		this.description = _description;
		this.client = _client;
	}
	
	public String getsentDate(){
		return this.sentDate;
	}
	
	public void setsentDate(String _sentDate){
		this.sentDate = _sentDate;
	}
	
	public String getanswerDate(){
		return this.answerDate;
	}
	
	public void setanswerDate(String _answerDate){
		this.answerDate = _answerDate;
	}
	
	public String getworkerName(){
		return this.workerName;
	}
	
	public void setworkerName(String _workerName){
		this.workerName = _workerName;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String _description){
		this.description = _description;
	}
	
	public Client getClient(){
		return this.client;
	}
	
	public void setClient(Client _client){
		this.client = _client;
	}
}
	

	
	

	