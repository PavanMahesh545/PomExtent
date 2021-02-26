package com.htc.ems.dao;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.*;

public class UserDAOImplTest {
	UserDAOImpl udao=null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//Dependancy Loading
		//one time it will execute before current class loading
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@BeforeMethod(alwaysRun=true)
	public void setUp() throws Exception {
		System.out.println("Setting up for test");
		udao=new UserDAOImpl();
	}

	@AfterMethod(alwaysRun=true)
	public void tearDown() throws Exception {
		System.out.println("Cleaning up object");
		udao=null;
	}

	@Test(groups= {"valid"},dataProvider = "userIds",priority=3)//stream data from mentioned data provider
    @Parameters({"validuserid"})//its passes from xml file 
	public void testGetUser_validUserId_userObject(@Optional("2")Integer userId) {
		System.out.println(udao);
		System.out.println("Test Get  User valid Input value is"+userId);
		//assertNotNull(udao.getUser(Integer.parseInt(empid)));
		assertNotNull(udao.getUser(userId));
	}
	@Test(expectedExceptions=NullPointerException.class,priority=3,groups= {"invalid","negative"})
	public void testGetUser_InvalidUserId_ExceptionThrown() {
		System.out.println(udao);
		System.out.println("Test Get  User Invalid valid Input");
		udao.getUser(10).getEmail();
		/*
		 * User usr=udao.getUser(10);
		 * usr.getEmail();
		 */
		
	}
	
	@Test
	@Parameters({"vuid","vemail"})
	public void testUpdateEmail_validUserIdandEmail_True(Integer uid,String email)
	{
		assertTrue(udao.updateemail(uid, email));
		assertEquals(udao.updateemail(uid, email),true);
	}
	/*@Test(expected=NullPointerException.class)``
	public void testStringObject()
	{
		String str=new String("Hi");
		System.out.println(str);
		str=null;
		System.out.println(str);
	}*/
	@Test(groups= {"valid"},priority=2,enabled = false)
	void displayMessage()
	{
		System.out.println("Check valid groups");
		assertTrue(true);
	}
	@DataProvider(name="userIds")
	public Object[] getEmployeeId()
	{
		return new Object[] {
				1,5,10

		};
	}
}
