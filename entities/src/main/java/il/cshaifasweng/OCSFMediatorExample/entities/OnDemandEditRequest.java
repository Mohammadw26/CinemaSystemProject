package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class OnDemandEditRequest implements Serializable {

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
	private OnDemandMovie movie;
	private int movieid;
	
	
	public OnDemandEditRequest() {}
	
	public double getCostPer24h() {
		return costPer24h;
	}
	public void setCostPer24h(double costPer24h) {
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
	public ZonedDateTime getDateTimeStart() {
		return dateTimeStart;
	}
	public void setDateTimeStart(ZonedDateTime dateTimeStart) {
		this.dateTimeStart = dateTimeStart;
	}
	public ZonedDateTime getDateTimeFinish() {
		return dateTimeFinish;
	}
	public void setDateTimeFinish(ZonedDateTime dateTimeFinish) {
		this.dateTimeFinish = dateTimeFinish;
	}
	public OnDemandMovie getMovie() {
		return movie;
	}
	public void setMovie(OnDemandMovie movie) {
		this.movie = movie;
	}
	public int getMovieid() {
		return movieid;
	}
	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}

}
