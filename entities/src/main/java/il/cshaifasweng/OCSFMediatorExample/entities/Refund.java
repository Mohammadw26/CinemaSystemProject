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

@Entity
@Table(name = "refunds")
public class Refund implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4121072229667524669L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String refundDate;
	private Client client;
	private double price;
	private boolean acceptedByManager;
	private CreditCard creditCard;
	private boolean byCreditCard;
	
	
	public Refund() {}
	
	public Refund(String _refundDate, Client _client, double _price, boolean _acceptedByManager, CreditCard _creditCard, boolean _byCreditCard){
		this.refundDate = _refundDate;
		this.setClient(_client);
		this.setPrice(_price);
		this.setAcceptedByManager(_acceptedByManager);
		this.setCreditCard(_creditCard);
		this.setByCreditCard(_byCreditCard);
	}

	public boolean isByCreditCard() {
		return byCreditCard;
	}

	public void setByCreditCard(boolean byCreditCard) {
		this.byCreditCard = byCreditCard;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public boolean isAcceptedByManager() {
		return acceptedByManager;
	}

	public void setAcceptedByManager(boolean acceptedByManager) {
		this.acceptedByManager = acceptedByManager;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
}