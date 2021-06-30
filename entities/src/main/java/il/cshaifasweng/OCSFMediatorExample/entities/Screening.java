package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "screenings")
public class Screening implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8260879257214770872L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String screeningDate;
	private String screeningTime;
	private String screeningBranch;
	private String screeningHall;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "screening_movie")
	private CinemaMovie movie;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "screening_hall")
	private Hall hall;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "screening_id")
	private SirtyaBranch inBranch;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "screening")
	private List<Ticket> tickets;
	
	private boolean[] seatsArray;
	private int availableSeats;
	private int soldSeats;
	
    public int getSoldSeats() {	
            return soldSeats;
    }	
    
    public void setSoldSeats(int soldSeats) {
            this.soldSeats = soldSeats;
    }
	
	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public Screening() {}
	
	public Screening(String date, String time,CinemaMovie movie2,SirtyaBranch inBranch) {
		this.screeningDate = date;
		this.screeningTime = time;
		this.movie = movie2;
		this.inBranch = inBranch;
		inBranch.getScreenings().add(this);
		this.screeningBranch = inBranch.getAddress();
		movie2.getScreenings().add(this);
		this.tickets = new ArrayList<Ticket>();
		//movie2.addScreening(this);
	}
	
	
	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
		this.setScreeningHall(hall.getHallName());
		availableSeats = hall.getSeatsNum();
		this.seatsArray = new boolean[availableSeats];
		for (int i = 0; i <availableSeats ; i++ ) {
			seatsArray[i]=false;
		}
		 
	}
	
	public void setTakenSeatAt(int i) {
		if (i<hall.getSeatsNum()) {
			seatsArray[i]=true;
		}
	}
	
	
	public void setAvailableSeatAt(int i) {
		if (i<hall.getSeatsNum()) {
			seatsArray[i]=false;
		}
	}
	
	public boolean getSeatStatus(int i) {
		return seatsArray[i];
	}

	public int getId() {
		return id;
	}
	public String getScreeningDate() {
		return screeningDate;
	}
	public void setScreeningDate(String screeningDate) {
		this.screeningDate = screeningDate;
	}
	public String getScreeningTime() {
		return screeningTime;
	}
	public void setScreeningTime(String screeningTime) {
		this.screeningTime = screeningTime;
	}
	public CinemaMovie getMovie() {
		return movie;
	}
	public void setMovie(CinemaMovie movie) {
		this.movie = movie;
		movie.getScreenings().add(this);
	}
	public SirtyaBranch getBranch() {
		return inBranch;
	}
	public void setBranch(SirtyaBranch inBranch) {
		this.inBranch = inBranch;
		inBranch.getScreenings().add(this);
	}

	public String getScreeningBranch() {
		return screeningBranch;
	}

	public void setScreeningBranch(String screeningBranch) {
		this.screeningBranch = screeningBranch;
	}
	
	public Boolean isEalierThan(Screening another) {
		int i,j;
		String temp = this.getScreeningDate();
		String temp2 = another.getScreeningDate();
		i = Integer.parseInt(temp.substring(6, 9));
		j = Integer.parseInt(temp2.substring(6, 9));
		if (i < j)
			return true;
		else if (i > j)
			return false;
		i = Integer.parseInt(temp.substring(3, 4));
		j = Integer.parseInt(temp2.substring(3, 4));
		if (i < j)
			return true;
		else if (i > j)
			return false;
		i = Integer.parseInt(temp.substring(0, 1));
		j = Integer.parseInt(temp2.substring(0, 1));
		if (i < j)
			return true;
		else if (i > j)
			return false;
		temp = this.getScreeningTime();
		temp2 = another.getScreeningTime();
		i = Integer.parseInt(temp.substring(0, 1));
		j = Integer.parseInt(temp2.substring(0, 1));
		if (i < j)
			return true;
		else if (i > j)
			return false;
		i = Integer.parseInt(temp.substring(3, 4));
		j = Integer.parseInt(temp2.substring(3, 4));
		if (i < j)
			return true;
		else if (i > j)
			return false;
		return true;
	}

	public String getScreeningHall() {
		return screeningHall;
	}

	public void setScreeningHall(String screeningHall) {
		this.screeningHall = screeningHall;
	}
	
}
