package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class BookingRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int[] seats;
	private int screeningID;
	private int arrSize;


	public BookingRequest() {}
	
	public BookingRequest(int[] seats, int id, int size) {
		this.setSeats(seats);
		this.setScreeningID(id);
		this.setArrSize(size);
	}
	
	public int[] getSeats() {
		return seats;
	}
	
	public void setSeats(int[] seats) {
		this.seats = seats;
	}
	
	public int getScreeningID() {
		return screeningID;
	}
	
	public void setScreeningID(int screeningID) {
		this.screeningID = screeningID;
	}
	
	public int getArrSize() {
		return arrSize;
	}

	public void setArrSize(int arrSize) {
		this.arrSize = arrSize;
	}
}
