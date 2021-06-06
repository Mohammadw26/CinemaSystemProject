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
@Table(name = "clients")
public class Client implements Serializable {
	/**
	 * 
	 */
	//private static final long serialVersionUID = ??;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ClientId;
	private string ClientName;
	private int ClientAge;
	private list<order> orders;
	private int NumOfOrders;
	private list<complaint> Complaints;
	private int NumOfComplaints;
	private bool Hastab;
	private Tab ClientTab;
	private double WalletFunds;
	private CreditCard ClientCard;

	
	public Client() {}
	
	public Client(int _ClientId, string _ClientName, int _ClientAge, list<order> _orders , int _NumOfOrders , list<complaint> _Complaints , int _NumOfComplaints, bool _Hastab, Tab _ClientTab, double _WalletFunds , CreditCard _ClientCard){
		this.ClientId =  _ClientId;
		this.ClientName = _ClientName;
		this.ClientAge =  _ClientAge;
		this.orders = _orders;
		this.NumOfOrders = _NumOfOrders;
		this.Complaints = _Complaints;
		this.NumOfComplaints= _NumOfComplaints;
		this.Hastab= _Hastab;
		this.ClientTab=	 _ClientTab;
		this.WalletFunds= _WalletFunds;
		this.ClientCard= _ClientCard;
	}
	
	public int getClientId(){
		return this.ClientId;
	}
	
	public void setClientId(int _ClientId){
		this.ClientId = _ClientId;
	}
	
	public string getClientName(){
		return this.ClientName;
	}
	
	public void setClientName(string _ClientName){
		this.ClientName = _ClientName;
	}
	
	public int getClientAge(){
		return this.ClientAge;
	}
	
	public void setClientAge(int _ClientAge){
		this.ClientAge = _ClientAge;
	}
	
	public list<order> getOrders(){
		return this.orders;
	}
	
	public void setOrders(list<order> _orders){
		this.orders = _orders;
	}
	
	public int getNumOfOrders(){
		return this.NumOfOrders;
	}
	
	public void setNumOfOrders(int _NumOfOrders){
		this.NumOfOrders = _NumOfOrders;
	}
	
	public list<complaint> getComplaints(){
		return this.Complaints;
	}
	
	public void setComplaints(list<complaint> _Complaints){
		this.Complaints = _Complaints;
	}
	public int getNumOfComplaints(){
		return this.NumOfComplaints;
	}
	
	public void setNumOfComplaints(int _NumOfComplaints){
		this.NumOfComplaints = _NumOfComplaints;
	}
	public bool getHastab(){
		return this.Hastab;
	}
	
	public void setHastab(bool _Hastab){
		this.Hastab = _Hastab;
	}
	public Tab getClientTab(){
		return this.ClientTab;
	}
	
	public void setClientTab(Tab _ClientTab){
		this.ClientTab = _ClientTab;
	}
	public double getWalletFunds(){
		return this.WalletFunds;
	}
	
	public void setWalletFunds(double _WalletFunds){
		this.WalletFunds = _WalletFunds;
	}
	public CreditCard getClientCard(){
		return this.ClientCard;
	}
	
	public void setClientCard(CreditCard _ClientCard){
		this.ClientCard = _ClientCard;
	}