package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "screening_movie")
	private CinemaMovie movie;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "screening_id")
	private SirtyaBranch inBranch;
	
	public Screening() {}
	
	public Screening(String date, String time,CinemaMovie movie2,SirtyaBranch inBranch) {
		this.screeningDate = date;
		this.screeningTime = time;
		this.movie = movie2;
		movie2.getScreenings().add(this);
		this.inBranch = inBranch;
		inBranch.getScreenings().add(this);
		this.screeningBranch = inBranch.getAddress();
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

	
}
