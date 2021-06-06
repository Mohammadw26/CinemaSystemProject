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
	private list<seat> SeatsOfHall;
	private string cinema_name;
	private int numOfSeats;
	private static int seaters=0;
	private string movie_screening;
	private bool isOccupied;


	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "Hall")
	private int Hall_number;
	
	@ManyToMany(mappedBy = "Hall",
	cascade = {CascadeType.PERSIST, CascadeType.MERGE},	targetEntity = SirtyaBranch.class)
	
	public Hall(){}
	
	public Hall(list<seat> SeatsOfHall,string cinema_name,int numOfSeats,int seaters,string movie_screening,bool isOccupied){
		this.SeatsOfHall = SeatsOfHall;
		this.cinema_name = cinema_name;
		this.numOfSeats = numOfSeats;
		this.seaters = seaters;
		this.movie_screening = movie_screening;
		this.isOccupied = isOccupied;
		
	}
	
	Public void setHallNum(int num)
	{ 
		this.Hall_number = num;
		
	}
	
	Public int getHallNum()
	{ 
		return this.Hall_num;
	}
			
	Public void setCinemaName(string name)
	{
		this.cinema_name = name;
	}	
	
	Public string getCinemaName()
	{
		return this.cinema_name; 
	}
	
	Public void setNumSeats(int Number)
	{
		this.numofseats=Number; 
	}
	
	Public int getNumSeats()
	{
		return this.numofseats;   
	}
	
	Public void setMovieScreening(string movieScr)
	{
		this.movie_screening = movieScr;  
	}
	
	Public string getMovieScreening()
	{
		return this.movie_screening; 
	}
	
	Public void addPerson()
	{ 
		if (seaters==numOfSeats)
		{
			throw exception();
		} //exception which returns a message "error"
		
		seaters++;
	}
	
	Public void removePerson()
	{
		if (seaters<=0) {throw exception(); } //
		
		seaters--;
	
	}
	