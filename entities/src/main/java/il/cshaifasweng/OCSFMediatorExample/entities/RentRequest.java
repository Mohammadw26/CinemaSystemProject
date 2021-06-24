package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class RentRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String email;
	private int customerID;
	private long cardNum;
	private String username;
	private String password;
	private boolean newCustomerFlag;
	private boolean signupFlag;
	private OnDemandMovie movie;

	public RentRequest() {}
	
	public RentRequest(String firstName, String lastName, String email, int customerID, long cardNum, OnDemandMovie movie) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.customerID = customerID;
		this.cardNum = cardNum;
		this.movie = movie;
	}
	
	public RentRequest(String firstName, String lastName, String email, int customerID, long cardNum, String username, String password, OnDemandMovie movie) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.customerID = customerID;
		this.cardNum = cardNum;
		this.username = username;
		this.password = password;
		this.movie = movie;
	}

	public OnDemandMovie getMovie() {
		return movie;
	}

	public void setMovie(OnDemandMovie movie) {
		this.movie = movie;
	}

	public boolean isNewCustomerFlag() {
		return newCustomerFlag;
	}

	public void setNewCustomerFlag(boolean newCustomerFlag) {
		this.newCustomerFlag = newCustomerFlag;
	}

	public boolean isSignupFlag() {
		return signupFlag;
	}
	
	public void setSignupFlag(boolean signupFlag) {
		this.signupFlag = signupFlag;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public int getCustomerID() {
		return customerID;
	}

	public long getCardNum() {
		return cardNum;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public void setCardNum(long cardNum) {
		this.cardNum = cardNum;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
