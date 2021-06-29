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
@Table(name = "tabs")
public class Tab implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 762012672556720919L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Client client;
	private int origimalNumOfTickets;
	private int numOfTicketsLeft;
	private String datePurchased;
	private int price;
	
	public Tab() {}
	
	public Tab (Client _client, int _origimalNumOfTickets , int _numOfTicketsLeft, String _datePurchased , int _price){
		this.client = _client;
		this.origimalNumOfTickets = _origimalNumOfTickets;
		this.numOfTicketsLeft = _numOfTicketsLeft;
		this.datePurchased = _datePurchased;
		this.price = _price;
	}
	
	public Client getClient(){
		return this.client;
	}
	
	public void setDate(Client _client){
		this.client = _client;
	}
	
	public int getOrigimalNumOfTickets(){
		return this.origimalNumOfTickets;
	}
	
	public void setOrigimalNumOfTickets(int _origimalNumOfTickets){
		this.origimalNumOfTickets = _origimalNumOfTickets;
	}
	
	public int getNumOfTicketsLeft(){
		return this.numOfTicketsLeft;
	}
	
	public void setNumOfTicketsLeft(int _numOfTicketsLeft){
		this.numOfTicketsLeft = _numOfTicketsLeft;
	}
	
	public String getDatePurchased(){
		return this.datePurchased;
	}
	
	public void setDatePurchased(String _datePurchased){
		this.datePurchased = _datePurchased;
	}
	
	public int getPrice(){
		return this.price;
	}
	
	public void setPrice(int _price){
		this.price = _price;
	}
}
	
