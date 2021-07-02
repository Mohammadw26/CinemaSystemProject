package il.cshaifasweng.OCSFMediatorExample.entities;


import java.io.Serializable;

public class FullOrderRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String email;
	private int customerID;
	private long cardNum;
	private BookingRequest request;
	private String username;
	private String password;
	private boolean newCustomerFlag;
	private boolean signupFlag;
	private boolean buyPack;
	private int usePack;
	private String check;
	private String transactionTime;
	
	

	public String getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	public FullOrderRequest() {
		this.buyPack = false;
		usePack = 0;
	}
	
	public FullOrderRequest(String firstName, String lastName, String email, int customerID, long cardNum) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.customerID = customerID;
		this.cardNum = cardNum;
		this.buyPack = false;
		this.usePack = 0;
	}
	
	public FullOrderRequest(String firstName, String lastName, String email, int customerID, long cardNum, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.customerID = customerID;
		this.cardNum = cardNum;
		this.username = username;
		this.password = password;
		this.buyPack = false;
		this.usePack  = 0;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public boolean isBuyPack() {
		return buyPack;
	}

	public void setBuyPack(boolean buyPack) {
		this.buyPack = buyPack;
	}

	public int getUsePack() {
		return usePack;
	}

	public void setUsePack(int usePack) {
		this.usePack = usePack;
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

	public BookingRequest getRequest() {
		return request;
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

	public void setRequest(BookingRequest request) {
		this.request = request;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
