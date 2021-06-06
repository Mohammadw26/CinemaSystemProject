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
@Table(name = "tickets")
public class Ticket implements Serializable {
	/**
	 * 
	 */
	//private static final long serialVersionUID = ??;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private string date;
	private string movieName;
	private int hallNumber;
	private int seatNum;
	private double price;
	private Client client;
	
	public Ticket() {}
	
	public Ticket(string _date, string _movieName, int _hallNumber, int _seatNum, double _price,Client _client){
		this.date = _date;
		this.movieName = _movieName;
		this.hallNumber = _hallNumber;
		this.seatNum = _seatNum;
		this.price = _price;
		this.client = _client;
	}
	
	public string getDate(){
		return this.date;
	}
	
	public void setDate(int _date){
		this.date = _date;
	}
	
	public string getMovieName(){
		return this.movieName;
	}
	
	public void setMovieName(int _movieName){
		this.movieName = _movieName;
	}
	
	public int getHallNumber(){
		return this.hallNumber;
	}
	
	public void setHallNumber(int _hallNumber){
		this.hallNumber = _hallNumber;
	}
	
	public int getSeatNum(){
		return this.seatNum;
	}
	
	public void setSeatNum(int _seatNum){
		this.seatNum = _seatNum;
	}
	
	public double getPrice(){
		return this.price;
	}
	
	public void setPrice(int _price){
		this.price = _price;
	}
	
	public Client getClient(){
		return this.client;
	}
	
	public void setClient(int _client){
		this.client = _client;
	}
	

	
	

	