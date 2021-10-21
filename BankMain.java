package project0;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class BankMain implements Serializable{
	static final String user = "admin";
	static final String password = "12345678";
	static final String db_url = "jdbc:oracle:thin:@database-1.czjydg612bvc.us-east-2.rds.amazonaws.com:1521:ORCL";
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	int option;

	Scanner in = new Scanner(System.in);
	static ArrayList<Customer> cusMain = new ArrayList<>();
	static CustomerDAO dao = new CustomerDAO();
	public static Connection conn = getConnection();


	public static void main(String[] args) throws SQLException {
		BankMain bank = new BankMain();
		DataBase data = new DataBase();
		Customer cus = new Customer();
		Account acc = new Account();
		//dao.insertIntoRegister();
		//getConnection();
		/*
		 * ACTUAL TESTING
		 */
		//<----LOADING CUSTOMER DATAINTO BANK
//		Connection conn = getConnection();
		//dao.methodInsert();
		//dao.selectDB();
		//System.out.println("Loading Logins");
		data.loadLogin();  
		//System.out.println("Loading Accounts");
		//data.loadAcc();
		//System.out.println("Loading Customers");
		data.loadCustomers(); // <----LOADING EMPLOYEE & ADMIN DATA INTO BANK
		//System.out.println("Loading Customer Lists");

		//data.loadIsApproved();
		cusMain = data.loadCusList();

		bank.mainMenu(data, cus, acc); 	// <------------------------

		/*
		 * random testing
		 */
		//System.out.println("test");
	}

	private String userN, passW;
	public static Connection getConnection(){
		try {
			//loading the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//
			Connection conn = DriverManager.getConnection(db_url, user, password);
			return conn;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("Unable to load driver class");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException caught!");
		}
		return null;
	}
	public void mainMenu(DataBase data, Customer cus, Account acc) throws SQLException {
		do {
			System.out.println("--------------------------------------------------------------");
			System.out.println("Welcome to Dream Bank! Please select 1-5");
			System.out.println("1. Customer-Login \n2. Employee-Login \n3. Admin-Login \n4. Register \n5. Exit"); 
			System.out.println("--------------------------------------------------------------");

			int option = in.nextInt();
			in.nextLine();
			System.out.println("--------------------------------------------------------------");
			switch(option) {
			case 1: System.out.println("Welcome Customer!");
			System.out.println("--------------------------------------------------------------");
			int index = 0;
			int attempts = 2;



			do {
				System.out.print("Username: ");
				this.userN = in.nextLine();

				System.out.print("Password: ");
				this.passW = in.nextLine();
				if(data.verifyLogin(this.userN, this.passW)==true ) {
					for(int i = 0; i < cusMain.size(); i++)
						if(cusMain.get(i).getStatus() == false && userN.equals(cusMain.get(i).getUserName())) {
							int choice;
							do {
								System.out.println("Welcome Customer! What would you like to do? ");
								System.out.println("1. See Account Info");
								System.out.println("2. Withdraw");
								System.out.println("3. Deposit");
								System.out.println("4. Transfer");
								System.out.println("5. Quit");
								System.out.println();
								choice = in.nextInt();
								if(choice < 1 || choice > 5) {
									System.out.println("Invalid choice. Please select 1-5");	
								}

								switch(choice) {
								case 1:{
									data.seeAccountInfo(this.userN);
									break;
								}
								case 2:{
									data.withdrawFunds(this.userN);
									break;

								}
								case 3:{
									data.depositFunds(this.userN);
									break;
								}
								case 4:{
									data.transferFunds();
								}
								default:
								}
							}while((choice != 5));
							break;
						}
						else if(cusMain.get(i).getStatus() == true && userN.equals(cusMain.get(i).getUserName()) ) {
							System.out.println("Sorry for the inconvience, your account is still pending!");
							break;
						}
					break;
				}
				else {
					System.out.println("You have " + attempts + " attempts remaining");
					if(attempts == 0) {
						System.out.println("Too many attemtps. Logging you out. Goodbye! Thank you for using Dream Bank!");
						System.out.println("--------------------------------------------------------------");

					}
					attempts--;
				}
			}while(attempts >= 0);


			break;


			case 2: 
				System.out.println("--------------------------------------------------------------");

				int attempt = 3;
				do {
					System.out.println("Employee Login");
					System.out.print("Username: ");
					this.userN = in.nextLine();
					System.out.print("Password: ");
					this.passW = in.nextLine();
					if(data.verifyLogin(this.userN, this.passW)==true ) {
						if(data.verifyEmployee() == true) {
							int choice1;
							do {
								System.out.println("Welcome Employee, what would you like to do?");
								System.out.println("1. View Customers Info \n2. Approve/Reject Customers");
								choice1 = in.nextInt();
								switch(choice1) {
								case 1: System.out.println("The following lists are lists of customers");
								data.printCustomers(cus, cusMain);
								break;
								case 2:
									System.out.println("Select the customer you like to approve/reject");
									data.isApproved(cus, cusMain);
									break;
								}
							}while(choice1 > 3 || choice1 < 1);	
							System.out.println("TEST 2");
						}
						else {
							System.out.println("Incorrect Employee ID");
							break;
							//							attempt--;
						}
					}
					else {
						//						System.out.println("Incorrect username/password");
						attempt--;
						System.out.println("You have " + attempt + " attempts remaining");
						if(attempt==0) {
							System.out.println("Too many attempts, logging you off. Goodbye! \nThank you for using Dream Bank!");
							System.out.println("--------------------------------------------------------------");
							break;
						}
					}
				}while(attempt >= 0);

				//required here also to break the lines of printing the password and the separating lines of ------
				//				in.hasNextLine();
				//				System.out.println("--------------------------------------------------------------");
				break;
			case 3:
				int attemptA = 3;
				do {
					System.out.println("Administrator Login");
					System.out.print("Username: ");
					this.userN = in.nextLine();
					System.out.print("Password: ");
					this.passW = in.nextLine();
					int choice1;
					if(data.verifyLogin(userN, this.passW)==true) {
						if(data.verifyAdmin() == true) {
							do {
								System.out.println("Welcome Admin, what would you like to do?");
								System.out.println("1. View Customers Info \n2. Approve/Reject Customers \n3. Deactivate an Account \n4. Remove an Account");
								choice1 = in.nextInt();
								switch(choice1) {
								case 1: System.out.println("The following lists are lists of customers");
								data.printCustomers(cus, cusMain);
								break;
								case 2:
									//250715802
									System.out.println("Select the customer you like to approve/reject");
									//data.printPending(cus, cusMain);
									data.isApproved(cus, cusMain);
									break;
								case 3:
									data.reject(cus, cusMain);
								}
								break;
							}while(choice1 > 2 || choice1 < 1);	
						}
					}
					else {
						attemptA--;
						System.out.println("You have " + attemptA + " attempts remaining");
						if(attemptA==0) {
							System.out.println("Too many attempts, logging you off. Goodbye! \nThank you for using Dream Bank!");
							System.out.println("--------------------------------------------------------------");
							break;
						}
					}
				}while(attemptA >= 0);
				break;

			case 4:
				System.out.println("1. Register as customer \n2. Register as Employee \n3. Register as Admin");
				int choice = in.nextInt();
				//System.out.println("test");
				switch(choice){
				case 1:
					data.registerCustomer(cus);
					//dao.insertIntoRegister();

					//data.saveLogin();
					//					data.saveAccounts();
					break;

				case 2:
					//System.out.println("test");
					data.registerEmployee();
					//	Employee.register();
					//data.saveLogin();
					break;
				case 3:
					data.registerAdmin();
					break;
				}
				//				break;
			case 5:
				System.exit(1);
				break;
			}
			break;
		}while(option > 3 || option < 1);

	}


	
	
	

	
	
	
	
	
	
	
	
	
	
	////36232433
	
	
	
	
	
}
