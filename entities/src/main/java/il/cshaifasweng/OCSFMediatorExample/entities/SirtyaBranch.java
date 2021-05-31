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
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = Movie.class)
	@JoinTable(name = "branches_movies", joinColumns = @JoinColumn(name = "branch_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
	private List<Movie> movies;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "inBranch")
	private List<Screening> screenings;
	
	public SirtyaBranch() {}
	
	
	public SirtyaBranch(String address) {
		this.address = address;
		this.movies = new ArrayList<Movie>();
		this.screenings = new ArrayList<Screening>();
	}

	public int getId() {
		return id;
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
}
