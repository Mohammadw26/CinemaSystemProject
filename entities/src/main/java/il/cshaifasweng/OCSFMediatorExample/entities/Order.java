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
@Table(name = "orders")
public class Order implements Serializable {
	/**
	 * 
	 */
	//private static final long serialVersionUID = ??;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private List <Ticket> ticketList;
	private int totalPrice;
	private String orderDate;
	private Client client;
	//private 
	
	public Order() {}
	
	public Order(List <Ticket> _ticketList,int _totalPrice,String _orderDate,Client _client){
		this.ticketList = _ticketList;
		this.totalPrice = _totalPrice;
		this.orderDate = _orderDate;
		this.client = _client;
	}
	
	public List<Ticket> getTicketList(){
		return this.ticketList;
	}
	
	public void setTicketList(List<Ticket> _ticketList){
		this.ticketList = _ticketList;
	}
	
	
	public String getOrderDate(){
		return this.orderDate;
	}
	
	public void setOrderDate(String _orderDate){
		this.orderDate = _orderDate;
	}
	
	public int getTotalPrice(){
		return this.totalPrice;
	}
	
	public void setTotalPrice(int _totalPrice){
		this.totalPrice = _totalPrice;
	}
	
	public Client getClient(){
		return this.client;
	}
	
	public void setClient(Client _client){
		this.client = _client;
	}
}
	

	
	

	