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
@Table(name = "customerserviceemployee")
public class CustomerServiceEmployee extends Worker {
	/**
	 * 
	 */
	//private static final long serialVersionUID = ??;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private list<Complaint> unansweredComplaints;
	private list<Complaint> answeredComplaints;
	
	public CustomerServiceEmployee() {}
	
	public CustomerServiceEmployee (list <Complaint> _unansweredComplaints, list<Complaint> _answeredComplaints){
		this.unansweredComplaints = _unansweredComplaints;
		this.answeredComplaints = _answeredComplaints;
	}
	
	public int getUnanswered(){
		return this.unansweredComplaints;
	}
	
	public void setUnanswered(list <Complaint> _unansweredComplaints){
		this.unansweredComplaints = _unansweredComplaints;
	}
	
	public int getAnswered(){
		return this.movies;
	}
	
	public void setAnswered(list <Complaint> answeredComplaints){
		this.answeredComplaints = _answeredComplaints;
	}