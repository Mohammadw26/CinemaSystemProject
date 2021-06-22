package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;

@Entity
public class CinemaMember extends CasualBuyer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -364482658878786871L;
	private String username;
	private String password;
	int ticketsCredit;
	
	
	public CinemaMember () {}
	
	public CinemaMember (String firstName, String lastName, int customerId,int creditNum, String electronicMail
			, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.customerId = customerId;
		this.creditNum = creditNum;
		this.electronicMail = electronicMail;
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTicketsCredit() {
		return ticketsCredit;
	}

	public void setTicketsCredit(int ticketsCredit) {
		this.ticketsCredit = ticketsCredit;
	}
	
	
}
