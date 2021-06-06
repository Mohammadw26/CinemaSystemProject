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
	private String cardNum;
	private String cardOwner;
	private String validDate;
	private Boolean isValid;
	
	public CreditCard() {}
	
	public CreditCard (int _cvv, String _cardNum,String _cardOwner, String _validDate, Boolean _isValid){
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
	
	public String getCardOwner(){
		return this.cardOwner;
	}
	
	public void setCardOwner(String _cardOwner){
		this.cardOwner = _cardOwner;
	}
	
	public String getValidDate(){
		return this.validDate;
	}
	
	public void setValidDate(String _validDate){
		this.validDate = _validDate;
	}
	
	public Boolean getIsValid(){
		return this.isValid;
	}
	
	public void setIsValid(Boolean _isValid){
		this.isValid = _isValid;
	}
}
	

	
	

	