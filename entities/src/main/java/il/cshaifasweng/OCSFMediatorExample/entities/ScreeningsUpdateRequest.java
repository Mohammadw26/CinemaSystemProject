package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class ScreeningsUpdateRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int scrnID;
	private String date;
	private String time;
	private int branchID;
	private CinemaMovie movie;
	private int movieID;
	public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	private SirtyaBranch branch;
	
    public SirtyaBranch getBranch() {
		return branch;
	}

	public void setBranch(SirtyaBranch branch) {
		this.branch = branch;
	}

	public CinemaMovie getMovie() {
		return movie;
	}

	public void setMovie(CinemaMovie movie) {
		this.movie = movie;
	}
	
	public ScreeningsUpdateRequest(){
		this.scrnID = -1;
		this.date="";
		this.time="";
		this.branchID = -1;
		this.movie = null;
	};
	
	public ScreeningsUpdateRequest(int scrnID){
		this.scrnID = scrnID;
		this.date="";
		this.time="";
		this.branchID = -1;
		this.movie = null;
	};
	
	
	public int getScrnID() {
		return scrnID;
	}
	public void setScrnID(int scrnID) {
		this.scrnID = scrnID;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getBranchID() {
		return branchID;
	}
	public void setBranchID(int branchID) {
		this.branchID = branchID;
	}
}
