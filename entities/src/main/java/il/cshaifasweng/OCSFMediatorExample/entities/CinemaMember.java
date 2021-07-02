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
	
	
	public CinemaMember() {}
	public CinemaMember (String firstName, String lastName, int customerId, long creditNum, String electronicMail
			, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.customerId = customerId;
		this.creditNum = creditNum;
		this.electronicMail = electronicMail;
		this.username = username;
		this.password = password;
	}

	public CinemaMember(String username2, String password2, String firstName, String lastName, int id, int card,
			String mail, int ticketsCredit2) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.customerId = id;
		this.creditNum = card;
		this.electronicMail = mail;
		this.username = username2;
		this.password = password2;
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