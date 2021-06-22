package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class BookingRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int[] seats;
	private String[] seatIds;
	private Screening screening;
	private int arrSize;
	private double costForEach;


	public BookingRequest() {}
	
	public BookingRequest(int[] seats, String[] seatIds, Screening screening, int size, double costForEach) {
		this.setSeatIds(seatIds);
		this.setSeats(seats);
		this.setScreening(screening);
		this.setArrSize(size);
		this.costForEach = costForEach;
	}
	
	public String[] getSeatIds() {
		return seatIds;
	}

	public void setSeatIds(String[] seatIds) {
		this.seatIds = seatIds;
	}

	public int[] getSeats() {
		return seats;
	}
	
	public void setSeats(int[] seats) {
		this.seats = seats;
	}
	
	public Screening getScreening() {
		return screening;
	}

	public void setScreening(Screening screening) {
		this.screening = screening;
	}

	public int getArrSize() {
		return arrSize;
	}

	public void setArrSize(int arrSize) {
		this.arrSize = arrSize;
	}

	public double getCost() {
		return costForEach;
	}

	public void setCost(double costForEach) {
		this.costForEach = costForEach;
	}
}
