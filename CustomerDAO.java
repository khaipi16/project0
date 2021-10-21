package project0;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerDAO{
	Connection conn;
	private static final String user = "admin";
	private static final String password = "12345678";
	private static final String db_url = "jdbc:oracle:thin:@database-1.czjydg612bvc.us-east-2.rds.amazonaws.com:1521:ORCL";
	private static Scanner in = new Scanner(System.in);
	private  String firstname, lastname, username, pass, ssn;
	boolean status;

	public CustomerDAO(){
		try {
			//loading the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//
			conn = DriverManager.getConnection(db_url, user, password);
			System.out.println("Establishing connection...");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("Unable to load driver class");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException caught!");
		}
	}

	//	public void methodInsert() {
	//		String safe = "INSERT INTO CUSTOMERS VALUES (?, ?, ?, ?)"; 
	//		PreparedStatement push;    
	//
	//		try {             
	//			push = conn.prepareStatement(safe);                        
	//			push.setString(1, "KhaiDAO");             
	//			push.setString(2, "PiDAO");             
	//			push.setString(3, "1234");
	//			push.setDouble(4, 1000);
	//			int row = push.executeUpdate();                         
	//			System.out.println("Sucessfuly Insert");             
	//		}
	//		catch (SQLException e){                 
	//			e.printStackTrace();
	//		}
	//
	//	}





	public void selectDB()
	{
		String safeQuery = "Select * from CUSTOMERS where FIRST_NAME=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(safeQuery);

			ps.setString(1, "KhaiDAO");

			ResultSet rs = ps.executeQuery();

			while(rs.next())
			{
				System.out.println("First_Name: " + rs.getString(1));

				System.out.println("Last_Name: " + rs.getString(2));

				System.out.println("SSN: " + rs.getString(3));

				System.out.println("Balance: " + rs.getDouble(4));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void insertIntoRegister() { 
		System.out.println("--------------------------------------------------------------");

		System.out.println("Welcome new coming user!");
		System.out.print("Please enter your first name: ");
		this.firstname = in.nextLine();

		System.out.print("Please enter your last name: ");
		this.lastname = in.nextLine();


		System.out.print("Please enter a user you would like: ");
		this.username = in.nextLine();

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

		do {
			System.out.print("Please enter your last 4-digit SSN: ");
			this.ssn = in.nextLine();
			if(this.ssn.length() > 4 ) {
				System.out.println("4 digit limit");
			}
			else if(this.ssn.length() < 4 ) {
				System.out.println("Please enter up to 4 digits");
			}
		}while(this.ssn.length() != 4);
		System.out.println("\nThank you for registering with Dream Bank! We are processing your information. "
				+ "\nYour account is pending, and will have to be approved."
				+ "\n"
				+ "\nNote: Your account ID is the last 4 digits of your SSN");
		
		
		String safe = "INSERT INTO REGISTER VALUES (?, ?, ?, ?, ?, ?)"; 
		PreparedStatement push;   
		try {             
			push = conn.prepareStatement(safe);                        
			push.setString(1, this.firstname);             
			push.setString(2, this.lastname);             
			push.setString(3, this.username);
			push.setString(4, this.pass);             
			push.setString(5, this.ssn);             
			push.setString(6, "Pending");
			int row = push.executeUpdate();                         
			System.out.println("Sucessfuly Insert");             
		}
		catch (SQLException e){ 
			e.printStackTrace();
		}

	}




	//	public void insertIntoRegister2() { 
	//		//		firstname = in.nextLine();
	//		//		lastname = in.nextLine();
	//		//		username = in.nextLine();
	//		//		pass = in.nextLine();
	//		//		ssn = in.nextLine();
	//
	//		System.out.println("--------------------------------------------------------------");
	//
	//		System.out.println("Welcome new coming user!");
	//		System.out.print("Please enter your first name: ");
	//		this.firstname = in.nextLine();
	//
	//		System.out.print("Please enter your last name: ");
	//		this.lastname = in.nextLine();
	//
	//		System.out.print("Please enter a user you would like: ");
	//		this.username = in.nextLine();		
	//
	//		System.out.print("Please enter a password you would like: ");
	//		System.out.println("\nYour password must have: \n* At least 8 characters"
	//				+ "\n* Must not contain space");
	//		System.out.print("Password: ");
	//		this.pass = in.nextLine();
	//		if(this.pass.length() < 8) {
	//			System.out.println("Please enter up to 8 characters");
	//		}
	//		if(this.pass.contains(" ")) {
	//			System.out.println("Space is not allowed!");
	//		}
	//		//			if(!this.pass.contains()) {
	//		//				System.out.println("Please input at least 1 number!");
	//		//			}
	//
	//	}while( (this.pass.length() < 8) || this.pass.contains(" "));
	//
	//	do {
	//		System.out.print("Please enter your last 4-digit SSN: ");
	//		this.ssn = in.nextLine();
	//		if(this.ssn.length() > 4 ) {
	//			System.out.println("4 digit limit");
	//		}
	//		else if(this.ssn.length() < 4 ) {
	//			System.out.println("Please enter up to 4 digits");
	//		}
	//		else if(selectCustomerLogin) {
	//			System.out.println("That SSN has already been registerd!");	
	//
	//		}
	//
	//
	//		String safe = "INSERT INTO CUSTOMERS VALUES (?, ?, ?, ?, ?, ?)"; 
	//		PreparedStatement push;   
	//	}while(this.ssn.length() != 4 || userCustomers.containsKey(this.ssn));
	//	System.out.println("\nThank you for registering with Dream Bank! We are processing your information. "
	//			+ "\nYour account is pending, and will have to be approved."
	//			+ "\n"
	//			+ "\nNote: Your account ID is the last 4 digits of your SSN");
	//	try {             
	//		push = conn.prepareStatement(safe);                        
	//		push.setString(1, "KhaiDAO");             
	//		push.setString(2, "PiDAO");             
	//		push.setString(3, "1234");
	//		push.setDouble(4, 1000);
	//		int row = push.executeUpdate();                         
	//		System.out.println("Sucessfuly Insert");             
	//	}
	//	catch (SQLException e){                 
	//		e.printStackTrace();
	//	}
	//
	//}
	//}


	public boolean selectCustomerLogin() throws SQLException
	{
		System.out.println("Username: " );
		String user = in.nextLine();
		
//		String passW = in.nextLine();
//		System.out.println("");

		String safeQuery = "Select * from CUSTOMERLOGIN where USERNAME=?";
		PreparedStatement ps;

		boolean verify = false;

			ps = conn.prepareStatement(safeQuery);
			ps.setString(1, user);
//			ps.setString(2, passW);
			ResultSet rs = ps.executeQuery();

			if(!rs.next()) {
				System.out.println("invalid username/password");
				verify = false;
			}
			else 
				verify = true;
			
		rs.close();
		ps.close();
		return verify;
		
	}



}


