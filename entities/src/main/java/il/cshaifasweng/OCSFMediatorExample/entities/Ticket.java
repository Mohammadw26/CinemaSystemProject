package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tickets")
public class Ticket extends Purchase {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -780147193396884787L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ticket_screening")
	private Screening screening;
	private String seat;
	private boolean refunded;
	
	public Ticket() {}
	
	public Ticket(Screening screening, CasualBuyer user, String seatID, double cost, long cardNum, String transactionTime) {
	this.screening = screening;
	this.customer = user;
	this.seat = seatID;
	this.customer.addTicket(this);
	this.cost = cost;
	this.transactionTime = transactionTime;
	this.creditCardNum = cardNum;
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

	public boolean isRefunded() {
		return refunded;
	}

	public void setRefunded(boolean refunded) {
		this.refunded = refunded;
	}

}
	

	
	

	