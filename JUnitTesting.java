package project0;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class JUnitTesting {



	@Test
	public static void main(String[] args) {
		DataBase JUnitTest = new DataBase();
		assertTrue(JUnitTest.verifyLogin("mscott", "password") == false);
		assertTrue(JUnitTest.verifyEmployee() == true);
		assertTrue(JUnitTest.verifyAdmin("36232433") == false);


		
		

	}
}
