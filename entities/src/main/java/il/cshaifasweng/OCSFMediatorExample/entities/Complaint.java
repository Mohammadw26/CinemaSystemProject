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
	//private static final long serialVersionUID = ??;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private string sentDate;
	private string answerDate;
	private string workerName;
	private string description;
	private Client client;
	//private 
	
	public Ticket() {}
	
	public Ticket(string _sentDate,string _answerDate,string _workerName,string _description,Client _client){
		this.sentDate = _sentDate;
		this.answerDate = _answerDate;
		this.workerName = _workerName;
		this.description = _description;
		this.client = _client;
	}
	
	public string getsentDate(){
		return this.sentDate;
	}
	
	public void setsentDate(int _sentDate){
		this.sentDate = _sentDate;
	}
	
	public string getanswerDate(){
		return this.answerDate;
	}
	
	public void setanswerDate(int _answerDate){
		this.answerDate = _answerDate;
	}
	
	public string getworkerName(){
		return this.workerName;
	}
	
	public void setworkerName(int _workerName){
		this.workerName = _workerName;
	}
	
	public string getDescription(){
		return this.Description;
	}
	
	public void setDescription(string _description){
		this.description = _description;
	}
	
	public Client getClient(){
		return this.client;
	}
	
	public void setClient(int _client){
		this.client = _client;
	}
	

	
	

	