package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "purchases")
@Inheritance
public class Purchase implements Serializable {

	/**
	 *
	 */
	protected static final long serialVersionUID = 1137111242278557975L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "purchase_customer")
	protected CasualBuyer customer;

	protected double cost;
	protected String transactionTime;
	protected long creditCardNum;
	protected String purchaseType;
	protected String details;
	protected String status;

	public Purchase () {}

	public Purchase (CasualBuyer customer, double cost, String transactionTime, long creditCardNum) {
		this.customer = customer;
		this.cost= cost;
		this.transactionTime = transactionTime;
		this.creditCardNum = creditCardNum;
	}

	public CasualBuyer getCustomer() {
		return customer;
	}

	public void setCustomer(CasualBuyer customer) {
		this.customer = customer;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getCreditCardNum() {
		return creditCardNum;
	}

	public void setCreditCardNum(long creditCardNum) {
		this.creditCardNum = creditCardNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
