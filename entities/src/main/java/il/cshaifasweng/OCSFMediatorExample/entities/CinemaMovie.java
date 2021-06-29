package il.cshaifasweng.OCSFMediatorExample.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class CinemaMovie extends Movie {

	/**
	 * 
	 */
	private static final long serialVersionUID = -364482658878786871L;
	double ticketCost;
	private int ticketsSold;
	private double movieIncome;
	
	

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "movie")
	private List<Screening> screenings;

	public CinemaMovie() {}
	
	public CinemaMovie (String title,String titleHeb, String producer, String actors, String description, double cost, Image image) {
		this.movieTitle = title;
		this.movieTitleHeb = titleHeb;
		this.movieProducer = producer;
		this.starringActors = actors;
		this.movieDescription = description;
		this.ticketCost = cost;
		this.setImage(image);
		this.screenings = new ArrayList<Screening>();
		this.sirtyaBranch = new ArrayList<SirtyaBranch>();
		setTicketsSold(0);
		setMovieIncome(0.0);
	}
	
	public CinemaMovie (int id,String title,String titleHeb, String producer, String actors, String description, double cost, Image image) {
		this.id = id;
		this.movieTitle = title;
		this.movieTitleHeb = titleHeb;
		this.movieProducer = producer;
		this.starringActors = actors;
		this.movieDescription = description;
		this.ticketCost = cost;
		this.setImage(image);
		this.screenings = new ArrayList<Screening>();
		this.sirtyaBranch = new ArrayList<SirtyaBranch>();
		setTicketsSold(0);
		setMovieIncome(0.0);
	}
	


	public double getTicketCost() {
		return ticketCost;
	}

	public void setTicketCost(double ticketCost) {
		this.ticketCost = ticketCost;
	}
	
	

	public List<Screening> getScreenings() {
		return screenings;
	}

	public void setScreenings(List<Screening> screenings) {
		this.screenings = screenings;
	}
	
	public void addScreening (Screening newScreening) {
		int counter = 0;
		for (Screening screening : screenings) {
			if (newScreening.isEalierThan(screening)) {
				this.screenings.add(counter, newScreening);
			}
		}
	}

	public int getTicketsSold() {
		return ticketsSold;
	}

	public void setTicketsSold(int ticketsSold) {
		this.ticketsSold = ticketsSold;
	}

	public void calcMovieIncome() {
		setMovieIncome(this.getTicketCost() * this.getTicketsSold());
	}
	
	public double getMovieIncome() {
		return movieIncome;
	}

	public void setMovieIncome(double movieIncome) {
		this.movieIncome =  movieIncome;
	}

}
