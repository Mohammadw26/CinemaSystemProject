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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "movie")
	private List<Screening> screenings;

	CinemaMovie() {
	}
	
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

}
