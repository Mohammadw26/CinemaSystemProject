package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -780147193396884787L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ticket_screening")
	private Screening screening;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ticket_customer")
	private CasualBuyer customer;
	
	private String seat;
	
	public Ticket() {}
	
	public Ticket(Screening screening, CasualBuyer user, String seatID) {
	this.screening = screening;
	this.customer = user;
	this.seat = seatID;
	this.customer.addTicket(this);
	}
	
	public Screening getScreening() {
		return screening;
	}
	public void setScreening(Screening screening) {
		this.screening = screening;
	}
	public CasualBuyer getCustomer() {
		return customer;
	}
	public void setCustomer(CasualBuyer customer) {
		this.customer = customer;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	
	
}
	

	
	

	