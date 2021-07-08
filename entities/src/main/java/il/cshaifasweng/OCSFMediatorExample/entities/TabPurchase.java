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
	
	public static int tabNum = 0 ;
	public static double tabTotalIncome = 0 ;
	private double cost;

	public TabPurchase() {
		this.cost = 600;
	}
	
	public TabPurchase(long cridNum, CinemaMember member, String transactionTime) {
		this.cost = 600;
		this.creditCardNum = cridNum;
		this.customer = member;
		this.transactionTime = transactionTime;
		this.purchaseType = "20 Tickets Credit/Tab";
		this.details = "-";
		member.addPurchase(this);
		this.status = "Success";
	}

	public static int getTabNum() {
		return tabNum;
	}
	
	public double getCost() {
		return this.cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}

	public static void setTabNum(int tabNum) {
		TabPurchase.tabNum = tabNum;
	}

	
}
