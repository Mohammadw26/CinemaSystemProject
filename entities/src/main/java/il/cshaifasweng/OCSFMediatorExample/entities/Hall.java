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
@Table(name = "Halls")
public class Hall implements Serializable {
	/**
	 * 
	 */
	//private static final long serialVersionUID = ??;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private List<Seat> SeatsOfHall;
	private String cinema_name;
	private int numOfSeats;
	private static int seaters=0;
	private String movie_screening;
	private Boolean isOccupied;


	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "Hall")
	private int Hall_number;
	
	@ManyToMany(mappedBy = "Hall",
	cascade = {CascadeType.PERSIST, CascadeType.MERGE},	targetEntity = SirtyaBranch.class)
	
	public Hall(){}
	
	public Hall(List<Seat> SeatsOfHall,String cinema_name,int numOfSeats,int seaters,String movie_screening,boolean isOccupied){
		this.SeatsOfHall = SeatsOfHall;
		this.cinema_name = cinema_name;
		this.numOfSeats = numOfSeats;
		this.seaters = seaters;
		this.movie_screening = movie_screening;
		this.isOccupied = isOccupied;
		
	}
	
	public void setHallNum(int num)
	{ 
		this.Hall_number = num;
		
	}
	
	public int getHallNum()
	{ 
		return this.Hall_number;
	}
			
	public void setCinemaName(String name)
	{
		this.cinema_name = name;
	}	
	
	public String getCinemaName()
	{
		return this.cinema_name; 
	}
	
	public void setNumSeats(int Number)
	{
		this.numOfSeats=Number; 
	}
	
	public int getNumSeats()
	{
		return this.numOfSeats;   
	}
	
	public void setMovieScreening(String movieScr)
	{
		this.movie_screening = movieScr;  
	}
	
	public String getMovieScreening()
	{
		return this.movie_screening; 
	}
	
	public void addPerson()
	{ 
		if (seaters==numOfSeats)
		
		
		seaters++;
	}

	void removePerson()
	{
		if (seaters<=0) //
		
		seaters--;
	
	}
}
	