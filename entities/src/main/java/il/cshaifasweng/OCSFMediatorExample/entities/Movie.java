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
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
@Inheritance
public class Movie implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6631717836650221159L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	protected String movieTitle;
	protected String movieTitleHeb;
	protected String movieProducer;
	protected String starringActors;
	protected String movieDescription;
//	private double ticketCost;
	
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "movie")
//	private List<Screening> screenings;
	
	@ManyToMany(mappedBy = "movies",
	cascade = {CascadeType.PERSIST, CascadeType.MERGE},	targetEntity = SirtyaBranch.class)
	protected List<SirtyaBranch> sirtyaBranch;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER,targetEntity = Image.class)
	private Image image;
	
	public Movie() {}
	
	public Movie (String title,String titleHeb, String producer, String actors, String description,Image image) {
		this.movieTitle = title;
		this.movieTitleHeb = titleHeb;
		this.movieProducer = producer;
		this.starringActors = actors;
		this.movieDescription = description;
//		this.ticketCost = cost;
		this.setImage(image);
//		this.screenings = new ArrayList<Screening>();
		this.sirtyaBranch = new ArrayList<SirtyaBranch>();
	}

	public int getId() {
		return id;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getMovieTitleHeb() {
		return movieTitleHeb;
	}
	
	public void setMovieTitleHeb(String movieTitle) {
		this.movieTitleHeb = movieTitle;
	}

	public String getMovieProducer() {
		return movieProducer;
	}

	public void setMovieProducer(String movieProducer) {
		this.movieProducer = movieProducer;
	}

	public String getStarringActors() {
		return starringActors;
	}

	public void setStarringActors(String starringActors) {
		this.starringActors = starringActors;
	}

	public String getMovieDescription() {
		return movieDescription;
	}

	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}

//	public double getTicketCost() {
//		return ticketCost;
//	}
//
//	public void setTicketCost(double ticketCost) {
//		this.ticketCost = ticketCost;
//	}
//
//	public List<Screening> getScreenings() {
//		return screenings;
//	}

	/*public void addScreening(Screening newScreening) {
		for (Screening screening : screenings) {
			this.getScreenings().add(screening);
		}
	}*/
	
//	public void setScreenings(List<Screening> screenings) {
//		this.screenings = screenings;
//	}
	
	public List<SirtyaBranch> getSirtyaBranch() {
		return sirtyaBranch;
	}

	public void setSirtyaBranch(List<SirtyaBranch> sirtyaBranch) {
		this.sirtyaBranch = sirtyaBranch;
	}

	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
		image.setMovie(this);
	}
	
}
