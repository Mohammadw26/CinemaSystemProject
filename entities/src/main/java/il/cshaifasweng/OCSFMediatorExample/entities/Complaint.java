package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private int id;
	private String email;
	private String description;
	private String submissionDate;
	private String responseDate;
	private String response;
	private String status;
	private double refundValue;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "complaint_client")
	private CasualBuyer client;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "complaint_representetive")
	private CustomerServiceEmployee representetive;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "complaint_branch")
	private SirtyaBranch branch;
		
	public Complaint() {}
	
	public Complaint(CasualBuyer client, String description, String email, SirtyaBranch branch) {
		this.client = client;
		client.getComplaints().add(this);
		this.description = description;
		this.submissionDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy ',' HH:mm"));
		this.email = email;
		status = "Pending";
		this.branch = branch;
		if (branch!=null)
			branch.getComplaints().add(this);
	}
	
	public void addResponse(String response, int workerId) {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public CustomerServiceEmployee getRepresentetive() {
		return representetive;
	}
	
	public void setRepresentetive( CustomerServiceEmployee representetive) {
		this.representetive = representetive;
	}
	
	public void setClient(CasualBuyer client) {
		this.client = client;
	}
	
	public CasualBuyer getClient() {
		return client;
	}

	public  String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public  String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	

	public String getCustomerName() {
		return (this.client.getFirstName() + " " + this.client.getLastName());
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(String responseDate) {
		this.responseDate = responseDate;
	}

	public SirtyaBranch getBranch() {
		return branch;
	}

	public void setBranch(SirtyaBranch branch) {
		this.branch = branch;
	}

	public double getRefundValue() {
		return refundValue;
	}

	public void setRefundValue(double refundValue) {
		this.refundValue = refundValue;
	}
	
	
	
}
	

	
	

	