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
public class CreditCard implements Serializable {
	/**
	 * 
	 */
	//private static final long serialVersionUID = ??;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cvv;
	private string cardNum;
	private string cardOwner;
	private string validDate;
	private bool isValid;
	
	public CreditCard() {}
	
	public CreditCard (int _cvv, string _cardNum, string _validDate, bool _isValid){
		this.cvv = _cvv;
		this.cardOwner = _cardOwner;
		this.validDate = _validDate;
		this.isValid = _isValid;
	}
	
	public int getCvv(){
		return this.cvv;
	}
	
	public void setCvv(int _cvv){
		this.cvv = _cvv;
	}
	
	public int getCardOwner(){
		return this.cardOwner;
	}
	
	public void setCardOwner(int _cardOwner){
		this.cardOwner = _cardOwner;
	}
	
	public int getValidDate(){
		return this.validDate;
	}
	
	public void setValidDate(int _validDate){
		this.validDate = _validDate;
	}
	
	public int getIsValid(){
		return this.isValid;
	}
	
	public void setIsValid(int _isValid){
		this.isValid = _isValid;
	}
	

	
	

	