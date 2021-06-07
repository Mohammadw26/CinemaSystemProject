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
import javax.persistence.UniqueConstraint;

@Entity
public class CustomerServiceEmployee extends Worker {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4791474992620673727L;
	
	
//	private List<Complaint> unansweredComplaints = null;
//	private List<Complaint> answeredComplaints = null;
	
	public CustomerServiceEmployee() {}
	
	
//	public List<Complaint> getUnanswered(){
//		return this.unansweredComplaints;
//	}
//	
//	public void setUnanswered(List <Complaint> _unansweredComplaints){
//		this.unansweredComplaints = _unansweredComplaints;
//	}
//	
//	public List<Complaint> getAnswered(){
//		return this.answeredComplaints;
//	}
//	
//	public void setAnswered(List <Complaint> _answeredComplaints){
//		this.answeredComplaints = _answeredComplaints;
//	}
}