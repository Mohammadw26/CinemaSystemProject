package il.cshaifasweng.OCSFMediatorExample.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;


@Entity
public class CustomerServiceEmployee extends Worker {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4791474992620673727L;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "representetive")
	protected List<Complaint> newComplaints;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "representetive")
	protected List<Complaint> ClosedComplaints;
	
	
	public CustomerServiceEmployee() {}
	
	public CustomerServiceEmployee(String iD, String name, String password, String username, String email) {
		this.workerId = iD;
		this.workerName = name;
		this.workerPassword = password;
		this.wokerUsername = username;
		this.workerEmail = email;
		this.newComplaints = new ArrayList<Complaint>();
		this.ClosedComplaints = new ArrayList<Complaint>();
	}
	
}