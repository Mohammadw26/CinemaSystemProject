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
@Table(name = "creditcards")
public class Seat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6346221067374316039L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seatNumber;
	private Hall hall;
	private Client client;
	private SirtyaBranch branch;
	private int row;
	private int column;
	private boolean vip;
	
	public Seat() {}
	
	public Seat (int _seatNumber, Hall _hall, Client _client, SirtyaBranch _branch, int _row, int _column, boolean _vip){
		this.seatNumber = _seatNumber;
		this.setClient(_client);
		this.setBranch(_branch);
		this.setRow(_row);
		this.setColumn(_column);
		this.setVip(_vip);
		
	}
	
	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public SirtyaBranch getBranch() {
		return branch;
	}

	public void setBranch(SirtyaBranch branch) {
		this.branch = branch;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}
	
}
	

	
	

	