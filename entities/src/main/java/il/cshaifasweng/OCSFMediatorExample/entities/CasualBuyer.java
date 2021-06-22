package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Casual_Buyer")
public class CasualBuyer implements Serializable {

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	protected String firstName;
	protected String lastName;
	protected int customerId;
	protected long creditNum;
	protected String electronicMail;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	protected List<Purchase> purchases;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
	protected List<Complaint> complaints;
	
	public CasualBuyer() {
		complaints = new ArrayList<Complaint>();
		purchases = new ArrayList<Purchase>();
	}
	
	public CasualBuyer(String firstName, String lastName, int customerId,long creditNum, String electronicMail) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.customerId = customerId;
		this.creditNum = creditNum;
		this.electronicMail = electronicMail;
		complaints = new ArrayList<Complaint>();
		purchases = new ArrayList<Purchase>();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public List<Purchase> getTickets() {
		return purchases;
	}

	public void addTicket(Purchase purchase) {
		this.purchases.add(purchase);
	}

	public long getCreditNum() {
		return creditNum;
	}

	public void setCreditNum(long creditNum) {
		this.creditNum = creditNum;
	}

	public String getElectronicMail() {
		return electronicMail;
	}

	public void setElectronicMail(String electronicMail) {
		this.electronicMail = electronicMail;
	};
	
}
