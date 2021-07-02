package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Restrictions")
public class TavSagoal implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int y;
	private Boolean effective;
	private String fromDate;
	private String toDate;
	
	public TavSagoal() {}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Boolean isEffective() {
		return effective;
	}

	public void setEffective(Boolean effective) {
		this.effective = effective;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
}
