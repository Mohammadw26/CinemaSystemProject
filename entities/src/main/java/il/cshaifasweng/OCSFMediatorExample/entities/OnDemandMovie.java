package il.cshaifasweng.OCSFMediatorExample.entities;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class OnDemandMovie extends Movie {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double costPer24h;
	private String streamingLink;
	private int ticketsSold;
	private double movieIncome;
	private ZonedDateTime dateTimeStart;
	private ZonedDateTime dateTimeFinish;
	
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
	private List<Rent> rents;
	
	public OnDemandMovie() {}
	
	public OnDemandMovie (int id) {
		this.id = id;
	}
	
	public OnDemandMovie (String title,String titleHeb, String producer, String actors, String description, double cost, Image image, ZonedDateTime dateTimeStart, ZonedDateTime dateTimeFinish) {
		this.movieTitle = title;
		this.movieTitleHeb = titleHeb;
		this.movieProducer = producer;
		this.starringActors = actors;
		this.movieDescription = description;
		this.costPer24h = cost;
		this.setImage(image);
		this.setDateTimeStart(dateTimeStart);
		this.setDateTimeFinish(dateTimeFinish);
	}

	public double getCost() {
		return costPer24h;
	}

	public void setCost(double costPer24h) {
		this.costPer24h = costPer24h;
	}

	public String getStreamingLink() {
		return streamingLink;
	}

	public void setStreamingLink(String streamingLink) {
		this.streamingLink = streamingLink;
	}

	public int getTicketsSold() {
		return ticketsSold;
	}

	public void setTicketsSold(int ticketsSold) {
		this.ticketsSold = ticketsSold;
	}

	public double getMovieIncome() {
		return movieIncome;
	}

	public void setMovieIncome(double movieIncome) {
		this.movieIncome = movieIncome;
	}
	
	
	public double calcMovieIncome() {
		return this.getCost() * this.getTicketsSold();
	}

	public ZonedDateTime getDateTimeStart() {
		return dateTimeStart;
	}

	public void setDateTimeStart(ZonedDateTime dateTimeStart2) {
		this.dateTimeStart = dateTimeStart2;
	}

	public ZonedDateTime getDateTimeFinish() {
		return dateTimeFinish;
	}

	public void setDateTimeFinish(ZonedDateTime dateTimeFinish) {
		this.dateTimeFinish = dateTimeFinish;
	}
	
	
	
}
