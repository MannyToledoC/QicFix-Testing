package TestCases;

import entity.DatastoreFacade;
import entity.Client;
import entity.User;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.mockito.MockitoAnnotations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;


import java.util.ArrayList;
import java.util.List;

public class DatastoreFacadeTest {

	@Mock
	Client clientMock;
	
	private DatastoreFacade datastoreFacade;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		datastoreFacade = new DatastoreFacade(); 
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {

		datastoreFacade = null;
	}
	
	

	/**
	 * ID: QicFix-DatastoreFacade-007-validateUser-001
	 * Purpose: Test if method validates user info 
	 * Preconditions: ?, 
	 * Input: Email: 123@gmail.com and Password: password123
	 * Expected Output: true
	 */
	@Test
	public void testvalidateUser_1() {
		String email = "123@gmail.com";
		String password = "password123";
		boolean result = datastoreFacade.validateUser(email, password);
		assertTrue(result);
	}
	
	/**
	 * ID: QicFix-DatastoreFacade-007-validateUser-002
	 * Purpose: Test if method validates user info 
	 * Preconditions: ?, 
	 * Input: Email: 123@gmail.com and password
	 * Expected Output: false
	 */
	@Test
	public void testvalidateUser_2() {
		String email = "123@gmail.com";
		String password = "password";
		boolean result = datastoreFacade.validateUser(email, password);
		assertFalse(result);
	}
		
	/**
	 * ID: QicFix-DatastoreFacade-007-validateUser-003
	 * Purpose: Test if method validates user info 
	 * Preconditions: ?, 
	 * Input: Email: 123@gmail.com and password
	 * Expected Output: true
	 */
	@Test
	public void testvalidateUser_3() {
		String email = "JohnDoe@gmail.com";
		String password = "12345678";
		boolean result = datastoreFacade.validateUser(email, password);
		assertTrue(result);
	}
	
	/**
	 * ID: QicFix-DatastoreFacade-014-selectUserByEmail-001
	 * Purpose: Test if a user is found from email
	 * Preconditions: ?, 
	 * Input: Email: 123@gmail.com
	 * Expected Output: A user with that email should be returned
	 */
	@Test
	public void testselectUserByEmail_1() {
		String email = "123@gmail.com";
		List<User> list = new ArrayList<User>();
		
		
		list = datastoreFacade.selectUserByEmail(email);
		assertTrue(list.get(0).getEmail().equals(email));	
	}
	
	/**
	 * ID: QicFix-DatastoreFacade-014-selectUserByEmail-002
	 * Purpose: Test if a user is found from email
	 * Preconditions: ?, 
	 * Input: Email: notfound@gmail.com
	 * Expected Output: No user should be returned
	 */
	@Test
	public void testselectUserByEmail_2() {
		String email = "notfound@gmail.com";
		List<User> list = new ArrayList<User>();
		
		
		list = datastoreFacade.selectUserByEmail(email);
		assertTrue(list.isEmpty());		
	}
	
	/**
	 * ID: QicFix-DatastoreFacade-014-selectUserByEmail-003
	 * Purpose: Test if a user is found from email
	 * Preconditions: ?, 
	 * Input: Email: JohnDoe@gmail.com
	 * Expected Output: No user should be returned
	 */
	@Test
	public void testselectUserByEmail_3() {
		String email = "JohnDoe@gmail.com";
		List<User> list = new ArrayList<User>();
		
		
		list = datastoreFacade.selectUserByEmail(email);
		assertTrue(list.get(0).getEmail().equals(email));	
	}
	
	/**
	 * ID: QicFix-DatastoreFacade-015-selectClientByEmail-001
	 * Purpose: Test if a client is found from email
	 * Preconditions: ?, 
	 * Input: Email: 123@gmail.com
	 * Expected Output: No user should be returned
	 */
	@Test
	public void testselectClientByEmail_1() {
		String email = "123@gmail.com";
		List<Client> list = new ArrayList<Client>();
		
		
		list = datastoreFacade.selectClientByEmail(email);
		assertTrue(list.get(0).getEmail().equals(email));	
	}
	
	/**
	 * ID: QicFix-DatastoreFacade-015-selectClientByEmail-002
	 * Purpose: Test if a client is found from email
	 * Preconditions: ?, 
	 * Input: Email: notin@gmail.com
	 * Expected Output: No user should be returned
	 */
	@Test
	public void testselectClientByEmail_2() {
		String email = "notin@gmail.com";
		List<Client> list = new ArrayList<Client>();
		
		
		list = datastoreFacade.selectClientByEmail(email);
		assertTrue(list.isEmpty());		
	}
	
	/**
	 * ID: QicFix-DatastoreFacade-015-selectClientByEmail-003
	 * Purpose: Test if a client is found from email
	 * Preconditions: ?, 
	 * Input: Email: 123@gmail.com
	 * Expected Output: No user should be returned
	 */
	@Test
	public void testselectClientByEmail_3() {
		String email = "JohnDoe@gmail.com";
		List<Client> list = new ArrayList<Client>();
		
		
		list = datastoreFacade.selectClientByEmail(email);
		assertTrue(list.get(0).getEmail().equals(email));	
	}
	
	/**
	 * ID: QicFix-DatastoreFacade-022-block-001
	 * Purpose: Test if method calls user.block()
	 * Preconditions: mock of User.class, 
	 * Input: datastoreFacade.block(userMock)
	 * Expected Output: n/a
	 */
	@Test
	public void testblock_1() {
		User userMock = mock(User.class);
		when(userMock.block()).thenReturn(true);
		datastoreFacade.block(userMock);
		verify(userMock).block();
	}
	
	/**
	 * ID: QicFix-DatastoreFacade-022-block-002
	 * Purpose: Test if method calls user.block()
	 * Preconditions: mock of User.class, 
	 * Input: datastoreFacade.block(userMock2)
	 * Expected Output: n/a
	 */
	@Test
	public void testblock_2() {
		User userMock2 = mock(User.class);
		when(userMock2.block()).thenReturn(false);
		datastoreFacade.block(userMock2);
		verify(userMock2).block();
	}
	
	/**
	 * ID: QicFix-DatastoreFacade-022-block-003
	 * Purpose: Test if method calls user.block()
	 * Preconditions: mock of User.class, 
	 * Input: datastoreFacade.block(userMock3)
	 * Expected Output: n/a
	 */
	@Test
	public void testblock_3() {
		User userMock2 = mock(User.class);	
		when(userMock2.block()).thenReturn(true);
		datastoreFacade.block(userMock2);
		verify(userMock2).block();
	}
	
	// Helper methods
	
	// Compares two User objects
	public boolean compareUsers(User u1, User u2){
		
		return(u1.getEmail().equals(u2.getEmail()) &&
			    u1.getPassword().equals(u2.getPassword()) &&
				u1.getFname().equals(u2.getFname()) &&
				u1.getLname().equals(u2.getLname()) &&
				u1.getUserTypeId() == u2.getUserTypeId() &&
				u1.getStreetAddress().equals(u2.getStreetAddress()) &&
				u1.getCity().equals(u2.getCity()) &&
				u1.getState().equals(u2.getState()) &&
				u1.getZipcode().equals(u2.getZipcode()) &&
				u1.getDob().equals(u2.getDob()) &&
				u1.getPhone().equals(u2.getPhone())
		);
		
	}

}

