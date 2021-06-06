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
@Table(name = "generalmanagers")
public class GeneralManager extends Worker {
	/**
	 * 
	 */
	//private static final long serialVersionUID = ??;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private List<SirtyaBranch> cinemas;
	private List<Movie> movies;
	
	public GeneralManager() {}
	
	public GeneralManager (List <SirtyaBranch> _cinemas, List<Movie> _movies){
		this.cinemas = _cinemas;
		this.movies = _movies;
	}
	
	public List<SirtyaBranch> getCinemas(){
		return this.cinemas;
	}
	
	public void setCinemas(List <SirtyaBranch> _cinemas){
		this.cinemas = _cinemas;
	}
	
	public List<Movie> getMovies(){
		return this.movies;
	}
	
	public void setMovies(List <Movie> _movies){
		this.movies = _movies;
	}
}