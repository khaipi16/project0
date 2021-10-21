package project0;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

public class DataBase implements Serializable {
	/**
	/**
	 * 
	 */
	/**
	 * 
	 */
	//	private static final long serialVersionUID = 1L;
	private String user, pass;
	//transient***
	protected static Scanner in = new Scanner(System.in);
	protected String loginCustomer = "/Users/khaipi/eclipse-workspace/RevatureTraining/files/loginInfo.txt";
	protected String loginAdmin = "/Users/khaipi/eclipse-workspace/RevatureTraining/files/adminLogin.txt";
	protected HashMap<String, String> loginInfo = new HashMap<>();
	Random ran = new Random();
	boolean state;

	/*BEGINNING OF LOGIN INFORMATION (CUSTOMERS)
	 * Registering new users
	 */
	public void register() {
		//keeps repeating until unique username is created
		do {
			System.out.print("Please enter a username you would like: ");
			this.user = in.nextLine();
			if(loginInfo.containsKey(user)) {
				System.out.println("That username is taken.");
			}
		}while(loginInfo.containsKey(user));
		//		System.out.print("Please enter a password you would like: ");
		//		this.pass = in.nextLine();
		do {
			System.out.print("Please enter a password you would like: ");
			System.out.println("\nYour password must have: \n* At least 8 characters"
					+ "\n* Must not contain space");
			System.out.print("Password: ");
			this.pass = in.nextLine();
			if(this.pass.length() < 8) {
				System.out.println("Please enter up to 8 characters");
			}
			if(this.pass.contains(" ")) {
				System.out.println("Space is not allowed!");
			}
		}while( (this.pass.length() < 8) || this.pass.contains(" "));

		//if the login contains data, then switches state to true
		loginInfo.put(user, pass);

		//***CAREFUL WITH THIS SAVING METHOD
		saveLogin();
		//System.out.println(loginInfo);
	}


	/*
	 * Saving the registered method with this save method
	 */
	public void saveLogin() {
		//		register();
		try {
			//System.out.println(loginInfo);
			FileOutputStream fileOut = new FileOutputStream(loginCustomer);
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(loginInfo);
			objOut.close();
			fileOut.close();

		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException caught");
		} catch (IOException e) {
			System.out.println("IOException caught");
		}
	}

	/*
	 * Logging/loading the entered data of users
	 */
	@SuppressWarnings("unchecked")
	public void loadLogin() {
		//	      loginInfo = null;
		try {
			FileInputStream fileIn = new FileInputStream(loginCustomer);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			loginInfo = (HashMap<String, String>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			System.out.println("IOException caught");
		} catch (ClassNotFoundException c) {
			System.out.println("FileNotFoundException caught");
		}
	}

	/*
	 * Verifying users with key-value username-password
	 */
	public boolean verifyLogin(String user, String pass) {
		if(loginInfo.containsKey(user) && loginInfo.containsValue(pass)
				) {
			return true;
		}
		else {
			System.out.println("Incorrect username/password");
			return false;
		}
	}
	
	


	//**EMPLOYEE/ADMIN VERIFICATION
	/*
	 * working on this ----> later
	 */
	protected File loginEmployee = new File("/Users/khaipi/eclipse-workspace/RevatureTraining/files/employeeLogin");
	protected ArrayList<Admin> verify = new ArrayList<>();

	//FileOutputStream fileOut = new FileOutputStream(loginEmployee)

	public void generateEmpID() {
		String empID = String.valueOf(ran.nextInt(100000));
		System.out.println("Welcome to Dream Bank, thank you for choosing to work with us! "
				+ "\nPrinted below is your Employee ID for next time you login "
				+ "\nEmployee ID: " + empID);
		loginInfo.put(empID, null);
		saveLogin();
	}


	@SuppressWarnings("unlikely-arg-type")
	public boolean verifyEmployee() {
		System.out.println("Employee ID: ");
		String id = in.nextLine();
		if(loginInfo.containsKey(id)) {
			System.out.println("Login sucessful! ");
			return true;
		}
		else {
			return false;
		}	
	}


	public void registerEmployee() {
		register();
		generateEmpID();

	}
	/*
	 * END OF LOGIN INFORMATION
	 */




	/*
	 * START OF CUSTOMER DATA
	 */

	Connection conn;
	private static final String userA = "admin";
	private static final String password = "12345678";
	private static final String db_url = "jdbc:oracle:thin:@database-1.czjydg612bvc.us-east-2.rds.amazonaws.com:1521:ORCL";

	public DataBase() {
		registerDAO();
	}

	public void registerDAO(){
		try {
			//loading the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//
			conn = DriverManager.getConnection(db_url, userA, password);
			System.out.println("System is online");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("Unable to load driver class");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException caught!");
		}
	}

	protected String fileCustomers = "/Users/khaipi/eclipse-workspace/RevatureTraining/files/customerdata.txt";
	protected HashMap<String, Customer> userCustomers = new HashMap<>();
	//public boolean state; 
	private String first, last, userN, passW, status, ssn;

	//	ArrayList<Double> balance = new ArrayList<>();
	//Writing the customer data into userCustomers
	public void registerCustomer(Customer cus) {
		System.out.println("--------------------------------------------------------------");

		System.out.println("Welcome new coming user!");
		System.out.print("Please enter your first name: ");
		this.first = in.nextLine();

		System.out.print("Please enter your last name: ");
		this.last = in.nextLine();

		do {
			System.out.print("Please enter a user you would like: ");
			this.user = in.nextLine();
			if(loginInfo.containsKey(user)) {
				System.out.println("That username is taken.");
			}
		}while(loginInfo.containsKey(user));

		do {
			System.out.print("Please enter a password you would like: ");
			System.out.println("\nYour password must have: \n* At least 8 characters"
					+ "\n* Must not contain space");
			System.out.print("Password: ");
			this.pass = in.nextLine();
			if(this.pass.length() < 8) {
				System.out.println("Please enter up to 8 characters");
			}
			if(this.pass.contains(" ")) {
				System.out.println("Space is not allowed!");
			}
			//			if(!this.pass.contains()) {
			//				System.out.println("Please input at least 1 number!");
			//			}

		}while( (this.pass.length() < 8) || this.pass.contains(" "));

		do {
			System.out.print("Please enter your last 4-digit SSN: ");
			this.ssn = in.nextLine();
			if(this.ssn.length() > 4 ) {
				System.out.println("4 digit limit");
			}
			else if(this.ssn.length() < 4 ) {
				System.out.println("Please enter up to 4 digits");
			}
			else if(userCustomers.containsKey(this.ssn)) {
				System.out.println("That SSN has already been registerd!");	

			}
		}while(this.ssn.length() != 4 || userCustomers.containsKey(this.ssn));
		System.out.println("\nThank you for registering with Dream Bank! We are processing your information. "
				+ "\nYour account is pending, and will have to be approved."
				+ "\n"
				+ "\nNote: Your account ID is the last 4 digits of your SSN");
		userCustomers.put(this.ssn, new Customer(this.first, this.last, this.user, this.ssn, 0 ));
		accHash.put(this.ssn, new Account(this.first, this.last, this.user, this.balance));
		statusCus.add(new Customer(this.first, this.last, this.user , this.ssn, 0));
		loginInfo.put(user, pass);
		if(this.ssn.length() == 4) {
			state = true;
			saveCustomers();
			saveLogin();
			//saveRecords();
			saveCusList(statusCus);


			String safe = "INSERT INTO REGISTER VALUES (?, ?, ?, ?, ?, ?)"; 
			PreparedStatement push;   
			try {             
				push = conn.prepareStatement(safe);                        
				push.setString(1, this.first);             
				push.setString(2, this.last);             
				push.setString(3, this.user);
				push.setString(4, this.pass);             
				push.setString(5, this.ssn);             
				push.setString(6, "Pending");
				int row = push.executeUpdate();                         
				System.out.println("Data Uploaded Online");             
			}
			catch (SQLException e){ 
				e.printStackTrace();
			}

		}	
		System.out.println("--------------------------------------------------------------");

	}


	public void saveCustomers() {
		try {
			FileOutputStream fileOut = new FileOutputStream(fileCustomers);
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(userCustomers);
			objOut.close();
			fileOut.close();

		} catch (FileNotFoundException e) {
			System.out.println("saveCustomers: FileNotFoundException");
		} catch (IOException e) {
			System.out.println("saveCustomers: IOException");
		}
	}


	//method to read the customer data from the save file of userCustomers
	@SuppressWarnings("unchecked")
	public void loadCustomers() {
		try {
			FileInputStream fileIn = new FileInputStream(fileCustomers);
			ObjectInputStream objIn = new ObjectInputStream(fileIn);
			userCustomers = (HashMap<String, Customer>)objIn.readObject();
			//			for(Customer c: userCustomers.values()) {
			//				statusCus.add(c);
			//			}
			//			saveCusList();
			objIn.close();
			fileIn.close();
		} catch (IOException i) {
			System.out.println("error 1: IOException there is no account with that username/password in our data base!");

		}catch (ClassNotFoundException e) {
			System.out.println("error 2: ClassNotFoundException " + e.getMessage());
		}
		//System.out.println(userCustomers);	
	}
	/*
	 * END OF CUSTOMER DATA
	 */


	/*
	 * ArrayList of Customers
	 */
	ArrayList<Customer> statusCus = new ArrayList<>();
	protected String cusList = "/Users/khaipi/eclipse-workspace/RevatureTraining/files/customerList.txt";
	public void saveCusList(ArrayList<Customer> listCus) {
		try {
			FileOutputStream fileOut = new FileOutputStream(cusList);
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(listCus);
			objOut.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			System.out.println("saveCustomers: FileNotFoundException");
		} catch (IOException e) {
			System.out.println("saveCustomers: IOException");
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Customer> loadCusList() {
		try {
			FileInputStream fileIn = new FileInputStream(cusList);
			ObjectInputStream objIn = new ObjectInputStream(fileIn);
			statusCus = (ArrayList<Customer>)objIn.readObject();
			objIn.close();
			fileIn.close();
		} catch (IOException i) {
			System.out.println("error 1: IOException there is no account with that username/password in our data base!");

		}catch (ClassNotFoundException e) {
			System.out.println("error 2: ClassNotFoundException " + e.getMessage());
		}
		//System.out.println("Lists of Customers: " + statusCus);	
		return statusCus;
	}


	/*
	 * START OF TRANSACTION DATA
	 */
	//	ArrayList<Transactions> trans = new ArrayList<>();


	/*
	 * START OF ADMIN DATA
	 */

	/*
	 * 
	 */
	HashMap<String, String> isApproved = new HashMap<>();
	protected String isApprovedFile = "/Users/khaipi/eclipse-workspace/RevatureTraining/files/isApproved.txt";
	public void saveIsApproved() {
		try {
			FileOutputStream fileOut = new FileOutputStream(isApprovedFile);
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(isApproved);
			objOut.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			System.out.println("saveCustomers: FileNotFoundException");
		} catch (IOException e) {
			System.out.println("saveCustomers: IOException");
		}
	}

	@SuppressWarnings("unchecked")
	public void loadIsApproved() {
		try {
			FileInputStream fileIn = new FileInputStream(isApprovedFile);
			ObjectInputStream objIn = new ObjectInputStream(fileIn);
			isApproved = (HashMap<String, String>)objIn.readObject();
			objIn.close();
			fileIn.close();
		} catch (IOException i) {
			System.out.println("error 1: IOException there is no account with that username/password in our data base!");

		}catch (ClassNotFoundException e) {
			System.out.println("error 2: ClassNotFoundException " + e.getMessage());
		}
		//System.out.println("Lists of Approved Customers: " + isApproved);	
	}


	//Approving/Denying new coming customers
	@SuppressWarnings("unlikely-arg-type")
	public void printCustomers(Customer cus, ArrayList<Customer> listCusomer) {
		for(int i = 0; i < listCusomer.size(); i++){
			System.out.println("\nFirst Name: " + listCusomer.get(i).getFirstName() + "\nLast Name: " + listCusomer.get(i).getLastName() 
					+ "\nUsername: " + listCusomer.get(i).getUserName()
					+ "\nSSN: " + listCusomer.get(i).getSSN() + "\nBalance: " + listCusomer.get(i).getBalance()  
					+"\nStatus: Pending " + listCusomer.get(i).getStatus());
			System.out.println("--------------------------------------------------------------");

		}
	}
	public void printPending(Customer cus, ArrayList<Customer> listCusomer) {
		//		boolean isPending = false;
		for(int i = 0; i < listCusomer.size(); i++){
			if(listCusomer.get(i).getStatus() == true) {
				System.out.println("\nFirst Name: " + listCusomer.get(i).getFirstName() + "\nLast Name: " + listCusomer.get(i).getLastName() 
						+ "\nUsername: " + listCusomer.get(i).getUserName() 
						+ "\nSSN: " + listCusomer.get(i).getSSN() + "\nBalance: " + listCusomer.get(i).getBalance()  
						+ "\nStatus: Pending " + listCusomer.get(i).getStatus());
				System.out.println("--------------------------------------------------------------");
				//				return true;
			}
		}
		//		return isPending;
	}

	public void isApproved(Customer cus, ArrayList<Customer> listCusomer) {
		int attempts = 2;

		printPending(cus, listCusomer);
		do {	
			System.out.print("Enter Account ID to approve: ");
			String ID = in.nextLine();
			for(int i = 0; i < listCusomer.size(); i++) {
				//				System.out.println("VALUE OF INDEX: " + listCusomer.get(i).getSSN());
				if(ID.equals((listCusomer.get(i).getSSN())) ) {
					listCusomer.get(i).setStatus(false);
					saveCusList(listCusomer);
					System.out.println( "Customer " + listCusomer.get(i).getFirstName() + " has been approved! Login again to continue");
					return;
					//						break;
				}
			}
			System.out.println("Incorrect Account ID. You have " + attempts + " attempts remaining");
			attempts--;
			if(attempts== -1) {
				System.out.println("Too many attempts, logging you off. Goodbye! \nThank you for using Dream Bank!");
				System.out.println("--------------------------------------------------------------");
				break;
			}
		}while(attempts >= 0);
	}
	//		else {
	//			System.out.println("Oops! There are no pending customers. GoodBye!");
	//		}

	public void reject(Customer cus, ArrayList<Customer> listCusomer) {
		int attempts = 2;
		boolean isID = false;
		for(int i = 0; i < listCusomer.size(); i++){
			if(listCusomer.get(i).getStatus() == false) {
				System.out.println("\nFirst Name: " + listCusomer.get(i).getFirstName() + "\nLast Name: " + listCusomer.get(i).getLastName() 
						+ "\nUsername: " + listCusomer.get(i).getUserName() 
						+ "\nSSN: " + listCusomer.get(i).getSSN() + "\nBalance: " + listCusomer.get(i).getBalance()  
						+ "\nStatus: Pending " + listCusomer.get(i).getStatus());
				System.out.println("--------------------------------------------------------------");
			}			
		}
		do {	
			System.out.print("Enter Account ID to Deactivate: ");
			String ID = in.nextLine();
			for(int i = 0; i < listCusomer.size(); i++) {
				//				System.out.println("VALUE OF INDEX: " + listCusomer.get(i).getSSN());
				if(ID.equals((listCusomer.get(i).getSSN())) ) {
					listCusomer.get(i).setStatus(true);
					saveCusList(listCusomer);
					System.out.println( "Customer " + listCusomer.get(i).getFirstName() + " account has been deactivated! Login again to continue");
					isID = true;
					return;
				}
			}
			if(isID == false) {
				System.out.println("Incorrect Account ID. You have " + attempts + " attempts remaining");
				attempts--;
			}
		}while(attempts >= 0);

	}



	//	public static void main(String[] args) {
	//		DataBase test = new DataBase();
	//		test.add(1, 2);
	//		System.out.println(test);
	//	}


	//	ArrayList<String> stuff = new ArrayList<>();
	//Login credentials
	public void generateAdID() {
		String empID = String.valueOf(ran.nextInt(100000));
		System.out.println("Welcome to Dream Bank, thank you for choosing to work with us! "
				+ "\nPrinted below is your Employee ID for next time you login "
				+ "\nEmployee ID: " + empID);
		loginInfo.put(empID, null);
		saveLogin();
	}


	@SuppressWarnings("unlikely-arg-type")
	public void generateAdminID() {
		String AdID = null;
		for(int i = 0; i<=9; i++) {
			AdID = String.valueOf(ran.nextInt(1000000000));
		}
		System.out.println("Welcome to Dream Bank, thank you for choosing to work with us! "
				+ "\nPrinted below is your Employee ID for next time you login "
				+ "\nAdmin ID: " + AdID);
		loginInfo.put(AdID, null);
		saveLogin();
	}

	public void registerAdmin() {
		register();
		generateAdminID();

	}
	//	String adminID = "1234567890";
	public boolean verifyAdmin() {
		System.out.print("Admin ID: ");
		String id = in.nextLine();
		if(loginInfo.containsKey(id)) {
			System.out.println("Login sucessful! ");
			return true;
		}
		else {
			return false;
		}	
	}
	
	/*
	 * For JUnit Testing
	 */
	public boolean verifyAdmin(String id) {
		System.out.print("Admin ID: ");
		id = in.nextLine();
		if(loginInfo.containsKey(id)) {
			System.out.println("Login sucessful! ");
			return true;
		}
		else {
			return false;
		}	
	}

	//	ArrayList<String> validID = new ArrayList<>();
	//	public void validateEmployee() {
	//		System.out.print
	//
	//	}

	/*
	 * END OF ADMIN DATA
	 */




	/*
	 * START OF ACCOUNTS DATA
	 */
	public void seeAccountInfo(String userN) {
		boolean isID = false;
		int attempts = 2;
		int index = 0;
		do {
			System.out.println("--------------------------------------------------------------");
			System.out.print("Please enter your account ID: ");
			String ID = in.nextLine();
			for(int i = 0; i < statusCus.size(); i++) {
				if(ID.equals(statusCus.get(i).getSSN()) && userN.equals(statusCus.get(i).getUserName()) ) {
					index = i;
					isID = true;
					break;
				}
			}
			if(isID == false) {
				System.out.println("Incorrect Account ID, you have " + attempts + " attempts remaining");
				attempts--;
				if(attempts == -1) {
					System.out.println("Too many attemtps. Logging you out. Goodbye! Thank you for using Dream Bank!");
					System.out.println("--------------------------------------------------------------");
					return;
				}
			}
			else
				break;

		}while(attempts >= 0);
		//if(isID == true) {
		System.out.println("Welcome " + statusCus.get(index).getFirstName() 
				+ "\nFirst Name: " + statusCus.get(index).getFirstName() + 
				"\nLast Name: " + statusCus.get(index).getLastName() + 
				"\nUsername: " + statusCus.get(index).getUserName() +
				"\nYour current balance is: $" + statusCus.get(index).getBalance());
		System.out.println("--------------------------------------------------------------");
		//}
	}

	public void withdrawFunds(String userN) {
		boolean isID = false;
		int attempts = 2;
		int index = 0;
		do {
			System.out.println("--------------------------------------------------------------");
			System.out.print("Please enter your account ID: ");
			String ID = in.nextLine();
			for(int i = 0; i < statusCus.size(); i++) {
				if(ID.equals(statusCus.get(i).getSSN()) && userN.equals(statusCus.get(i).getUserName()) ) {
					index = i;
					isID = true;
					break;
				}
			}
			if(isID == false) {
				System.out.println("Incorrect Account ID, you have " + attempts + " attempts remaining");
				attempts--;
				if(attempts == -1) {
					System.out.println("Too many attemtps. Logging you out. Goodbye! Thank you for using Dream Bank!");
					System.out.println("--------------------------------------------------------------");
					return;
				}
			}
			else
				break;
		}while(attempts >= 0);
		double withdrawLimit;
		if(isID == true) {
			System.out.println("Welcome " + statusCus.get(index).getFirstName() + "\nYour current balance is: $" + statusCus.get(index).getBalance());
			double withdraw;
			do {
				withdrawLimit = 10000;
				System.out.print("Enter Amount to Withdraw: $");
				withdraw = in.nextDouble();

				if(withdraw > statusCus.get(index).getBalance() ){
					System.out.println("You cannot withdraw more than your balance");
					break;
				}
				else if(withdraw > withdrawLimit) {
					System.out.println("You cannot withdraw more than the withdraw limit of $10,000");
					break;
				}
				else if(withdraw == withdrawLimit) {
					System.out.println("You have reached your withdraw limit for today");
					break;
				}
				else {
					withdrawLimit -= withdraw;
					statusCus.get(index).setBalance(statusCus.get(index).getBalance() - withdraw);
					System.out.println();
					saveCusList(statusCus);
					System.out.print("$" + withdraw + " withdrawn. Thank you for using Dream Bank!");
					System.out.println("--------------------------------------------------------------");
					break;
				}
			}while(withdrawLimit < withdraw || withdraw > statusCus.get(index).getBalance());

		}

	}

	public void depositFunds(String userN) {
		boolean isID = false;
		int attempts = 2;
		int index = 0;
		do {
			System.out.println("--------------------------------------------------------------");
			System.out.print("Please enter your account ID: ");
			String ID = in.nextLine();
			for(int i = 0; i < statusCus.size(); i++) {
				if(ID.equals(statusCus.get(i).getSSN()) && userN.equals(statusCus.get(i).getUserName())) {
					index = i;
					isID = true;
					break;
				}
			}
			if(isID == false) {
				System.out.println("Incorrect Account ID, you have " + attempts + " attempts remaining");
				attempts--;
				if(attempts == -1) {
					System.out.println("Too many attemtps. Logging you out. Goodbye! Thank you for using Dream Bank!");
					System.out.println("--------------------------------------------------------------");
					return;
				}
			}
			else
				break;


		}while(attempts >= 0);
		if(isID == true) {
			System.out.println("Welcome " + statusCus.get(index).getFirstName() + "\nYour current balance is: $" + statusCus.get(index).getBalance());
			System.out.print("Enter Amount to Deposit: $");
			double deposit = in.nextDouble();
			if(deposit <0) {
				System.out.println("Cannot deposit negative amount!");
			}
			else {
				statusCus.get(index).setBalance(statusCus.get(index).getBalance() + deposit);
				saveCusList(statusCus);
				System.out.println("$" + deposit + " deposited. Thank you for using Dream Bank!");
				System.out.println("--------------------------------------------------------------");
			}
		}

	}

	/*
	 * Logging/loading the entered data of users
	 */
	@SuppressWarnings("unchecked")
	protected String recordTrans = "/Users/khaipi/eclipse-workspace/RevatureTraining/files/accountsData.txt";
	public void loadAcc() {
		try {
			FileInputStream fileIn = new FileInputStream(recordTrans);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			this.accTrans = (HashMap<String, Account>)in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			System.out.println("IOException caught");
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			System.out.println("FileNotFoundException caught");
		}
		System.out.println(accTrans);
	}

	public void saveRecords() {
		try {
			FileOutputStream fileOut = new FileOutputStream(recordTrans);
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
			//			System.out.println("DEBUGGING: " + this.accTrans);
			objOut.writeObject(this.accTrans);
			objOut.close();
			fileOut.close();

		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException caught");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException caught");
			e.printStackTrace();
		}
	}


	protected String fileAccounts = "/Users/khaipi/eclipse-workspace/RevatureTraining/files/accountsData.txt";
	protected HashMap<String, Account> accHash = new HashMap<String, Account>();
	protected HashMap<String, Account> accTrans = new HashMap<String, Account>();
	protected Double balance = 0.0; 
	protected Double withdraw;

	public void newAccount(Account acc) {
		System.out.print("Please enter your first name: ");
		this.first = in.nextLine();

		System.out.print("Please enter your last name: ");
		this.last = in.nextLine();

		do {
			System.out.print("Please enter your last 4-digit SSN: ");
			this.ssn = in.nextLine();
			if(this.ssn.length() > 4 ) {
				System.out.println("4 digit limit");
			}
			else if(this.ssn.length() < 4 ) {
				System.out.println("Please enter up to 4 digits");
			}
			else if(userCustomers.containsKey(this.ssn)) {
				System.out.println("That SSN has already been registerd!");	
			}
		}while(this.ssn.length() != 4 || userCustomers.containsKey(this.ssn));

		accHash.put(this.ssn, new Account(acc.getFirstName(), acc.getLastName(), this.ssn, acc.getBalance()));
		accHash.put(this.ssn, new Account(acc.getFirstName(), acc.getLastName(), acc.getUserName(), acc.getBalance()));
		System.out.println("Status: " + accHash);
		if(this.ssn.length() == 4) {
			state = true;
			//			saveAccounts();
		}
	}

	public String transferFunds() {
		// TODO Auto-generated method stub
		return null;
	}






	/*
	 * JUnit TESTING
	 */
	public void testGenerateAdID() {



	}



	/*
	 * Logging/loading the entered data of accounts
	 */
	//	@SuppressWarnings("unchecked")
	//	public void loadAcc() {
	//		//	      loginInfo = null;
	//		try {
	//			FileInputStream fileIn = new FileInputStream(accountsData);
	//			ObjectInputStream in = new ObjectInputStream(fileIn);
	//			transactions = (ArrayList<String>) in.readObject();
	//			in.close();
	//			fileIn.close();
	//		} catch (IOException i) {
	//			System.out.println("IOException caught");
	//		} catch (ClassNotFoundException c) {
	//			System.out.println("FileNotFoundException caught");
	//		}
	//		//System.out.println("Deserialized Employee...");
	//		System.out.println("Name: " + transactions);
	//	}



}
