package il.cshaifasweng.OCSFMediatorExample.entities;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rents")
public class Rent extends Purchase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String expiringLink;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rent_movie")
	private OnDemandMovie movie;
	
	public Rent() {}
	
	public Rent(CasualBuyer client, double cost, OnDemandMovie movie, String expiringLink, long cardNum, String transactionTime) {
		this.customer = client;
		this.customer.addTicket(this);
		this.setCost(cost);
		this.expiringLink = expiringLink;
		this.movie = movie;
		this.creditCardNum = cardNum;
		this.transactionTime = transactionTime;
	}
	
	public String getExpiringLink() {
		return expiringLink;
	}

	public void setExpiringLink(String expiringLink) {
		this.expiringLink = expiringLink;
	}

	public OnDemandMovie getMovie() {
		return movie;
	}

	public void setMovie(OnDemandMovie movie) {
		this.movie = movie;
	}	
	
	
}
