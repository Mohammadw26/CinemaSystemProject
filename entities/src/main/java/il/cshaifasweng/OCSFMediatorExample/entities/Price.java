package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "prices_requests")
public class Price implements Serializable {
	private static final long serialVersionUID = 6631717836650221159L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	private int movieID;
	private String workerID;
	private String movieName;
	private String workerName;
	private String requestTime;
	private String requestDate;
	private int priceId;
	private double oldPrice;
	private double newPrice;
	
	public Price(){}
	
	public Price(String movieName, String workerName, double oldPrice, double newPrice, String requestTime, String requestDate ) {
		this.movieName = movieName;
		this.workerName = workerName;
		this.oldPrice = oldPrice;
		this.newPrice = newPrice;
		this.requestDate = requestDate;
		this.requestTime = requestTime;
	}
	
	
	public int getID() {
		return id;
	}
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public double getOldPrice() {
		return oldPrice;
	}
	public void setOldPrice(double oldPrice) {
		this.oldPrice = oldPrice;
	}
	public double getNewPrice() {
		return newPrice;
	}
	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public String getWorkerID() {
		return workerID;
	}
	public void setWorkerID(String string) {
		this.workerID = string;
	}

	public int getPriceId() {
		return priceId;
	}

	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}


}
