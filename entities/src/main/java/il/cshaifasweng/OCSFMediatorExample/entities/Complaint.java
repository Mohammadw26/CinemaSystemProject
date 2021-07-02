package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	private String description;
	private ZonedDateTime submissionDate;
	private String response;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "complaint_client")
	private CasualBuyer client;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "complaint_representetive")
	private CustomerServiceEmployee representetive;
		
	public Complaint() {}
	
	public Complaint(CasualBuyer client, String description) {
		this.client = client;
		this.description = description;
		this.submissionDate = ZonedDateTime.now(ZoneId.of("Asia/Jerusalem"));
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

	public  ZonedDateTime getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(ZonedDateTime submissionDate) {
		this.submissionDate = submissionDate;
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
}
	

	
	

	