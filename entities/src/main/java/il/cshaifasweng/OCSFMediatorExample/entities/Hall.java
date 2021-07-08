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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Halls")
public class Hall implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8548182575097380508L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int rowsNum;
	private int colsNum;
	private int seatsNum;

	private String hallName;
	
	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "hall_id")
	private SirtyaBranch branch;
	

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hall")
	private List<Screening> screenings = new ArrayList<Screening>();
	
	public Hall() {};
	
	public Hall(int rows, int cols, int seatsNum, String hallName,SirtyaBranch branch){
		this.rowsNum = rows;
		this.colsNum = cols;
		this.seatsNum = seatsNum;
		this.branch = branch;
		branch.addHall(this);
		this.screenings = new ArrayList<Screening>();
		this.hallName = hallName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRows() {
		return rowsNum;
	}

	public void setRows(int rows) {
		this.rowsNum = rows;
	}

	public int getCols() {
		return colsNum;
	}

	public void setCols(int cols) {
		this.colsNum = cols;
	}

	public int getSeatsNum() {
		return seatsNum;
	}

	public void setSeatsNum(int seatsNum) {
		this.seatsNum = seatsNum;
	}

	public SirtyaBranch getBranch() {
		return branch;
	}

	public void setBranch(SirtyaBranch branch) {
		this.branch = branch;
	}

	public void setScreening(List<Screening> screening) {
		this.screenings = screening;
	}
	
	public void addScreening(Screening screening) {
		this.screenings.add(screening);
	}

	public int getRowsNum() {
		return rowsNum;
	}

	public void setRowsNum(int rowsNum) {
		this.rowsNum = rowsNum;
	}

	public int getColsNum() {
		return colsNum;
	}

	public void setColsNum(int colsNum) {
		this.colsNum = colsNum;
	}

	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

	public List<Screening> getScreenings() {
		return screenings;
	}

	public void setScreenings(List<Screening> screenings) {
		this.screenings = screenings;
	}
	
	
	
	/*private List<Seat> SeatsOfHall;
	private String cinema_name;
	private int numOfSeats;
	private static int seaters=0;
	private String movie_screening;
	private Boolean isOccupied;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "Hall")
	private int Hall_number;
	
	public Hall(){}
	
	public Hall(List<Seat> SeatsOfHall,String cinema_name,int numOfSeats,int seaters,String movie_screening,boolean isOccupied){
		this.SeatsOfHall = SeatsOfHall;
		this.cinema_name = cinema_name;
		this.numOfSeats = numOfSeats;
		this.seaters = seaters;
		this.movie_screening = movie_screening;
		this.setIsOccupied(isOccupied);
		
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

	public Boolean getIsOccupied() {
		return isOccupied;
	}

	public void setIsOccupied(Boolean isOccupied) {
		this.isOccupied = isOccupied;
	}*/
}
	