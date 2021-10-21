package project0;

import java.io.Serializable;
import bankPractice.BankMain;
import bankPractice.User;

public class Account implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -676356461856085262L;
	private String firstName, lastName, userName;
//	private static final long serialVersionUID = 1L;
	private double balance;
	/**
	 * 
	 */
	
	public Account(String firstName, String lastName, String userName, double balance) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.balance = balance;
	}
	
	public Account() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Account [firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", balance="
				+ balance + "]";
	}

	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

	
}
