package colla.kernel.impl;

import java.util.HashMap;
import colla.kernel.api.CollAJob;
import java.util.List;
import java.util.Set;
import colla.kernel.api.CollASession;
import org.junit.*;
import colla.kernel.api.CollAUser;
import colla.kernel.api.CollAHost;
import colla.kernel.api.CollAGroup;
import static org.junit.Assert.*;

/**
 * The class <code>UserTest</code> contains tests for the class <code>{@link User}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class UserTest {
	/**
	 * Run the User() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testUser_1()
		throws Exception {

		User result = new User();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getName());
		assertEquals(-1, result.getPort());
		assertEquals(null, result.getCountry());
		assertEquals("none@none.com", result.getEmail());
		assertEquals("not finished", result.getConnectionTotalTime());
		assertEquals(null, result.getIp());
		assertEquals(false, result.hasValidIP());
		assertEquals(false, result.isOnline());
		assertEquals(null, result.getCompany());
	}

	/**
	 * Run the User(String,String,int) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testUser_2()
		throws Exception {
		String name = "";
		String ip = "";
		int port = 1;

		User result = new User(name, ip, port);

		// add additional test code here
		assertNotNull(result);
		assertEquals("", result.getName());
		assertEquals(1, result.getPort());
		assertEquals(null, result.getCountry());
		assertEquals("none@none.com", result.getEmail());
		assertEquals("not finished", result.getConnectionTotalTime());
		assertEquals("", result.getIp());
		assertEquals(false, result.hasValidIP());
		assertEquals(false, result.isOnline());
		assertEquals(null, result.getCompany());
	}

	/**
	 * Run the void addGroup(String,CollAGroup) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testAddGroup_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");
		String groupName = "";
		CollAGroup group = new Group("");

		fixture.addGroup(groupName, group);

		// add additional test code here
	}

	/**
	 * Run the void addHost(CollAHost) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testAddHost_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");
		CollAHost host = new Host();

		fixture.addHost(host);

		// add additional test code here
	}

	/**
	 * Run the void addJob(CollAJob) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testAddJob_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");
		CollAJob job = new JobChat();

		fixture.addJob(job);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: -1
		//       at java.util.ArrayList.elementData(ArrayList.java:371)
		//       at java.util.ArrayList.get(ArrayList.java:384)
		//       at colla.kernel.impl.User.getLastSession(User.java:346)
		//       at colla.kernel.impl.User.addJob(User.java:356)
	}

	/**
	 * Run the void addSession(CollASession) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testAddSession_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");
		CollASession ses = new Session();

		fixture.addSession(ses);

		// add additional test code here
	}

	/**
	 * Run the int compareTo(CollAUser) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testCompareTo_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");
		CollAUser user = new User();

		int result = fixture.compareTo(user);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at java.lang.String.compareTo(String.java:1139)
		//       at colla.kernel.impl.User.compareTo(User.java:47)
		assertEquals(0, result);
	}

	/**
	 * Run the int compareTo(CollAUser) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testCompareTo_2()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");
		CollAUser user = new User();

		int result = fixture.compareTo(user);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at java.lang.String.compareTo(String.java:1139)
		//       at colla.kernel.impl.User.compareTo(User.java:47)
		assertEquals(0, result);
	}

	/**
	 * Run the int compareTo(CollAUser) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testCompareTo_3()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");
		CollAUser user = new User();

		int result = fixture.compareTo(user);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at java.lang.String.compareTo(String.java:1139)
		//       at colla.kernel.impl.User.compareTo(User.java:47)
		assertEquals(0, result);
	}

	/**
	 * Run the HashMap<String, String> getActivities() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetActivities_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");

		HashMap<String, String> result = fixture.getActivities();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the String getCompany() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetCompany_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");

		String result = fixture.getCompany();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getConnectionTotalTime() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetConnectionTotalTime_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");

		String result = fixture.getConnectionTotalTime();

		// add additional test code here
		assertEquals("not finished", result);
	}

	/**
	 * Run the String getCountry() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetCountry_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");

		String result = fixture.getCountry();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getEmail() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetEmail_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");

		String result = fixture.getEmail();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the HashMap<String, CollAGroup> getGroups() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetGroups_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");

		HashMap<String, CollAGroup> result = fixture.getGroups();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the CollAHost getHost(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetHost_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");
		String hostName = "";

		CollAHost result = fixture.getHost(hostName);

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the Set<CollAHost> getHosts() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetHosts_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");

		Set<CollAHost> result = fixture.getHosts();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the Set<CollAHost> getHosts() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetHosts_2()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");

		Set<CollAHost> result = fixture.getHosts();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the String getIp() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetIp_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");

		String result = fixture.getIp();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the Session getLastSession() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetLastSession_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");

		Session result = fixture.getLastSession();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: -1
		//       at java.util.ArrayList.elementData(ArrayList.java:371)
		//       at java.util.ArrayList.get(ArrayList.java:384)
		//       at colla.kernel.impl.User.getLastSession(User.java:346)
		assertNotNull(result);
	}

	/**
	 * Run the String getName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetName_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");

		String result = fixture.getName();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the List<String> getOrderedSessions() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetOrderedSessions_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");

		List<String> result = fixture.getOrderedSessions();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the int getPort() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetPort_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");

		int result = fixture.getPort();

		// add additional test code here
		assertEquals(1, result);
	}

	/**
	 * Run the HashMap<String, CollASession> getSessions() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetSessions_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");

		HashMap<String, CollASession> result = fixture.getSessions();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the boolean hasValidIP() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testHasValidIP_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");

		boolean result = fixture.hasValidIP();

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean hasValidIP() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testHasValidIP_2()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");

		boolean result = fixture.hasValidIP();

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the void initializeUser() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testInitializeUser_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");

		fixture.initializeUser();

		// add additional test code here
	}

	/**
	 * Run the void invalidateIP() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testInvalidateIP_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");

		fixture.invalidateIP();

		// add additional test code here
	}

	/**
	 * Run the boolean isOnline() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testIsOnline_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");

		boolean result = fixture.isOnline();

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean isOnline() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testIsOnline_2()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");

		boolean result = fixture.isOnline();

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the void recordActivities(String,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testRecordActivities_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");
		String date = "";
		String activity = "";

		fixture.recordActivities(date, activity);

		// add additional test code here
	}

	/**
	 * Run the void recordActivities(String,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testRecordActivities_2()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");
		String date = "";
		String activity = "";

		fixture.recordActivities(date, activity);

		// add additional test code here
	}

	/**
	 * Run the void setActivities(HashMap<String,String>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetActivities_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");
		HashMap<String, String> map = new HashMap();

		fixture.setActivities(map);

		// add additional test code here
	}

	/**
	 * Run the void setCompany(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetCompany_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");
		String company = "";

		fixture.setCompany(company);

		// add additional test code here
	}

	/**
	 * Run the void setConnectionInitialized() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetConnectionInitialized_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");

		fixture.setConnectionInitialized();

		// add additional test code here
	}

	/**
	 * Run the void setCountry(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetCountry_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");
		String cntry = "";

		fixture.setCountry(cntry);

		// add additional test code here
	}

	/**
	 * Run the void setEmail(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetEmail_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");
		String mail = "";

		fixture.setEmail(mail);

		// add additional test code here
	}

	/**
	 * Run the void setIp(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetIp_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");
		String ip = "";

		fixture.setIp(ip);

		// add additional test code here
	}

	/**
	 * Run the void setName(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetName_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");
		String name = "";

		fixture.setName(name);

		// add additional test code here
	}

	/**
	 * Run the void setOffline() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetOffline_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");

		fixture.setOffline();

		// add additional test code here
	}

	/**
	 * Run the void setOnline() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetOnline_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");

		fixture.setOnline();

		// add additional test code here
	}

	/**
	 * Run the void setPort(int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetPort_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");
		int port = 1;

		fixture.setPort(port);

		// add additional test code here
	}

	/**
	 * Run the void validateIP() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testValidateIP_1()
		throws Exception {
		User fixture = new User("", "", 1);
		fixture.setCountry("");
		fixture.setActivities(new HashMap());
		fixture.setCompany("");
		fixture.setEmail("");

		fixture.validateIP();

		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(UserTest.class);
	}
}