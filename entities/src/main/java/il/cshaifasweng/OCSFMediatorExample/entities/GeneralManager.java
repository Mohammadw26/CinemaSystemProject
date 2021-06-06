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
	private list<SirtyaBranch> cinemas;
	private list<Movie> movies;
	
	public GeneralManager() {}
	
	public GeneralManager (list <Cinema> _cinemas, list<Movie> _movies){
		this.cinemas = _cinemas;
		this.movies = _movies;
	}
	
	public int getCinemas(){
		return this.cinemas;
	}
	
	public void setCinemas(list <Cinema> _cinemas){
		this.cinemas = _cinemas;
	}
	
	public int getMovies(){
		return this.movies;
	}
	
	public void setMovies(list <Movie> _movies){
		this.movies = _movies;
	}