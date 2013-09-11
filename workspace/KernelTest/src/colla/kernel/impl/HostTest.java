package colla.kernel.impl;

import java.util.HashMap;
import org.junit.*;
import colla.kernel.api.CollAHost;
import static org.junit.Assert.*;

/**
 * The class <code>HostTest</code> contains tests for the class <code>{@link Host}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class HostTest {
	/**
	 * Run the Host() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testHost_1()
		throws Exception {

		Host result = new Host();

		// add additional test code here
		assertNotNull(result);
		assertEquals("nameUser: null\nname: null\ncountry: null\nip: null\nport: -1\nvalidIp: false\n", result.toString());
		assertEquals(null, result.getName());
		assertEquals(-1, result.getPort());
		assertEquals(null, result.getCountry());
		assertEquals("Total Memory: 279224320\nFree Memory: 140390416\nAvaliable Processors: 4\nOS: Linux 3.8.0-30-generic\nArch: i386\nCountry: BR\n", result.getSystemProperties());
		assertEquals(null, result.getIp());
		assertEquals(false, result.hasValidIP());
		assertEquals(false, result.IsOnline());
		assertEquals(null, result.getNameUser());
		assertEquals("not finished", result.getTempoTotalConexao());
	}

	/**
	 * Run the Host(String,String,int) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testHost_2()
		throws Exception {
		String name = "";
		String ip = "";
		int port = 1;

		Host result = new Host(name, ip, port);

		// add additional test code here
		assertNotNull(result);
		assertEquals("nameUser: null\nname: \ncountry: null\nip: \nport: 1\nvalidIp: false\n", result.toString());
		assertEquals("", result.getName());
		assertEquals(1, result.getPort());
		assertEquals(null, result.getCountry());
		assertEquals("Total Memory: 279224320\nFree Memory: 140133512\nAvaliable Processors: 4\nOS: Linux 3.8.0-30-generic\nArch: i386\nCountry: BR\n", result.getSystemProperties());
		assertEquals("", result.getIp());
		assertEquals(false, result.hasValidIP());
		assertEquals(false, result.IsOnline());
		assertEquals(null, result.getNameUser());
		assertEquals("not finished", result.getTempoTotalConexao());
	}

	/**
	 * Run the boolean IsOnline() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testIsOnline_1()
		throws Exception {
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");

		boolean result = fixture.IsOnline();

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean IsOnline() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testIsOnline_2()
		throws Exception {
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");

		boolean result = fixture.IsOnline();

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the int compareTo(CollAHost) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testCompareTo_1()
		throws Exception {
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");
		CollAHost host = new Host();

		int result = fixture.compareTo(host);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at java.lang.String.compareTo(String.java:1139)
		//       at colla.kernel.impl.Host.compareTo(Host.java:70)
		assertEquals(0, result);
	}

	/**
	 * Run the int compareTo(CollAHost) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testCompareTo_2()
		throws Exception {
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");
		CollAHost host = new Host();

		int result = fixture.compareTo(host);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at java.lang.String.compareTo(String.java:1139)
		//       at colla.kernel.impl.Host.compareTo(Host.java:70)
		assertEquals(0, result);
	}

	/**
	 * Run the int compareTo(CollAHost) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testCompareTo_3()
		throws Exception {
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");
		CollAHost host = new Host();

		int result = fixture.compareTo(host);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at java.lang.String.compareTo(String.java:1139)
		//       at colla.kernel.impl.Host.compareTo(Host.java:70)
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
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");

		HashMap<String, String> result = fixture.getActivities();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
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
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");

		String result = fixture.getCountry();

		// add additional test code here
		assertEquals("", result);
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
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");

		String result = fixture.getIp();

		// add additional test code here
		assertEquals("", result);
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
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");

		String result = fixture.getName();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getNameUser() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetNameUser_1()
		throws Exception {
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");

		String result = fixture.getNameUser();

		// add additional test code here
		assertEquals("", result);
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
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");

		int result = fixture.getPort();

		// add additional test code here
		assertEquals(1, result);
	}

	/**
	 * Run the String getSystemProperties() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetSystemProperties_1()
		throws Exception {
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");

		String result = fixture.getSystemProperties();

		// add additional test code here
		assertEquals("Total Memory: 279224320\nFree Memory: 96466160\nAvaliable Processors: 4\nOS: Linux 3.8.0-30-generic\nArch: i386\nCountry: BR\n", result);
	}

	/**
	 * Run the String getTempoTotalConexao() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetTempoTotalConexao_1()
		throws Exception {
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");

		String result = fixture.getTempoTotalConexao();

		// add additional test code here
		assertEquals("not finished", result);
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
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");

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
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");

		boolean result = fixture.hasValidIP();

		// add additional test code here
		assertEquals(false, result);
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
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");

		fixture.invalidateIP();

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
	public void testRecordActivities_1()
		throws Exception {
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");
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
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");
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
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");
		HashMap<String, String> map = new HashMap();

		fixture.setActivities(map);

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
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");
		String cntry = "";

		fixture.setCountry(cntry);

		// add additional test code here
	}

	/**
	 * Run the void setInicioConexao() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetInicioConexao_1()
		throws Exception {
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");

		fixture.setInicioConexao();

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
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");
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
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");
		String name = "";

		fixture.setName(name);

		// add additional test code here
	}

	/**
	 * Run the void setNameUser(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetNameUser_1()
		throws Exception {
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");
		String nameUser = "";

		fixture.setNameUser(nameUser);

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
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");

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
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");

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
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");
		int port = 1;

		fixture.setPort(port);

		// add additional test code here
	}

	/**
	 * Run the String toString() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testToString_1()
		throws Exception {
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");

		String result = fixture.toString();

		// add additional test code here
		assertEquals("nameUser: \nname: \ncountry: \nip: \nport: 1\nvalidIp: false\n", result);
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
		Host fixture = new Host("", "", 1);
		fixture.setActivities(new HashMap());
		fixture.setNameUser("");
		fixture.setCountry("");

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
		new org.junit.runner.JUnitCore().run(HostTest.class);
	}
}