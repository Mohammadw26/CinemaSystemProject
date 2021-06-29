package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sirtyaBranches")
public class SirtyaBranch implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5392130662038628203L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	String address;
	private double totalTicketsIncome;
	private int totalTicketsSold;
	private int totalTabTicketsSold;
	
	
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = Movie.class)
	@JoinTable(name = "branches_movies", joinColumns = @JoinColumn(name = "branch_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
	private List<Movie> movies;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "inBranch")
	private List<Screening> screenings;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "branch")
	private List<Hall> halls;
	
    private BranchManager manager;
	
	public SirtyaBranch() {}
	
	
	public SirtyaBranch(String address) {
		this.address = address;
		this.movies = new ArrayList<Movie>();
		this.screenings = new ArrayList<Screening>();
		this.halls = new ArrayList<Hall>();
		this.setTotalTicketsIncome(0.0);
		this.setTotalTicketsSold(0);
		this.setTotalTabTicketsSold(0);

	}

	public int getId() {
		return id;
	}

	public List<Hall> getHalls() {
		return halls;
	}


	public void addHall(Hall hall) {
		this.getHalls().add(hall);
	}
	
	public void setManager(BranchManager manager) {
		this.manager = manager;
	}
	
	public BranchManager getManager() {
		return manager;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public List<Screening> getScreenings() {
		return screenings;
	}

	public void setScreenings(List<Screening> screenings) {
		this.screenings = screenings;
	}


	public double getTotalTicketsIncome() {
		return totalTicketsIncome;
	}


	public void setTotalTicketsIncome(double totalTicketsIncome) {
		this.totalTicketsIncome =  totalTicketsIncome;
	}


	public int getTotalTicketsSold() {
		return totalTicketsSold;
	}


	public void setTotalTicketsSold(int totalTicketsSold) {
		this.totalTicketsSold = totalTicketsSold;
	}




	public int getTotalTabTicketsSold() {
		return totalTabTicketsSold;
	}


	public void setTotalTabTicketsSold(int totalTabTicketsSold) {
		this.totalTabTicketsSold =totalTabTicketsSold;
	}




	
}
