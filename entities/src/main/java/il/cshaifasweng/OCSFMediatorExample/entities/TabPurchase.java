package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ticketTabs")
public class TabPurchase extends Purchase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TabPurchase() {
		this.cost = 600;
	}
	
	public TabPurchase(long cridNum, CinemaMember member, String transactionTime) {
		this.cost = 600;
		this.creditCardNum = cridNum;
		this.customer = member;
		this.transactionTime = transactionTime;
	}
}
