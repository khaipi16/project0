package project0;

import java.io.Serializable;

public class Customer implements Serializable{
	

	/**## Requirements 
	 * 1. Functionality should reflect the below user stories. 
	 * 2. Data is stored in a database. 
	 * 3. A custom stored procedure is called to perform some portion of the functionality. 
	 * 4. Data Access is performed through the use of JDBC in a data layer consisting of Data Access Objects. 
	 * 5. All input is received using the java.util.Scanner class. 
	 * 6. Log4j is implemented to log events to a file. 
	 * 7. A minimum of one (1) JUnit test is written to test some functionality. 
	 * ## User Stories * As a user, I can login. 
	 * * As a customer, I can apply for a new bank account with a starting balance. 
	 * * As a customer, I can view the balance of a specific account. 
	 * * As a customer, I can make a withdrawal or deposit to a specific account. 
	 * * As the system, I reject invalid transactions. 
	 * * Ex: * A withdrawal that would result in a negative balance. 
	 * * A deposit or withdrawal of negative money. 
	 * * As an employee, I can approve or reject an account. 
	 * * As an employee, I can view a customer's bank accounts. 
	 * * As a user, I can register for a customer account. 
	 * * As a customer, I can post a money transfer to another account. 
	 * * As a customer, I can accept a money transfer from another account. 
	 * * A an employee, I can view a log of all transactions
	 * 
	 */
	private static final long serialVersionUID = 8381448503173030056L;
	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	//	static Scanner in = new Scanner(System.in);
	private String firstName, lastName, userName, password, SSN;
	private double balance;
	boolean status;
	
	public Customer(String firstName, String lastName, String userName, String SSN, double balance) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.SSN = SSN;
		this.balance = balance;
		this.status = true;
		//System.out.println(firstName + " " + lastName + " " + social + " " + balance + " " + status);
	}
	
	
	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}



	public boolean getStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		 this.status = status;
	}


	

	public Customer() {
		this.firstName = firstName;
		// TODO Auto-generated constructor stub
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

	public String getPasswrd() {
		return password;
	}

	public void setPasswrd(String passwrd) {
		this.password = passwrd;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String SSN) {
		this.SSN = SSN;
	}


	@Override
	public String toString() {
		return "\n"+firstName + "'s Account Summary: " + "\nFirst Name: " + firstName + "\nLast Name: " + lastName +  "\nSSN: " 
	+ SSN  + "\nBalance: " + balance + ("\nApproval Status: " + (getStatus()==true ? "Pending":"Approved")) ;
		
	}
	
	
	public void registerCustomer() {
	}
	

}
