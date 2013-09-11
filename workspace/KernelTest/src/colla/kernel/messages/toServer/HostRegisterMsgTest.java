package colla.kernel.messages.toServer;

import org.junit.*;
import colla.kernel.impl.Host;
import colla.kernel.api.CollAHost;
import static org.junit.Assert.*;

/**
 * The class <code>HostRegisterMsgTest</code> contains tests for the class <code>{@link HostRegisterMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class HostRegisterMsgTest {
	/**
	 * Run the HostRegisterMsg() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testHostRegisterMsg_1()
		throws Exception {

		HostRegisterMsg result = new HostRegisterMsg();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getHost());
		assertEquals(null, result.getUserPass());
		assertEquals(null, result.getSender());
		assertEquals(null, result.getUserName());
	}

	/**
	 * Run the CollAHost getHost() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetHost_1()
		throws Exception {
		HostRegisterMsg fixture = new HostRegisterMsg();
		fixture.setSender("");
		fixture.setHost(new Host());
		fixture.setUserPass("");

		CollAHost result = fixture.getHost();

		// add additional test code here
		assertNotNull(result);
		assertEquals("nameUser: null\nname: null\ncountry: null\nip: null\nport: -1\nvalidIp: false\n", result.toString());
		assertEquals(null, result.getName());
		assertEquals(-1, result.getPort());
		assertEquals(null, result.getCountry());
		assertEquals("Total Memory: 279224320\nFree Memory: 135465968\nAvaliable Processors: 4\nOS: Linux 3.8.0-30-generic\nArch: i386\nCountry: BR\n", result.getSystemProperties());
		assertEquals(null, result.getIp());
		assertEquals(false, result.hasValidIP());
		assertEquals(false, result.IsOnline());
		assertEquals(null, result.getNameUser());
		assertEquals("not finished", result.getTempoTotalConexao());
	}

	/**
	 * Run the Enum getOperation() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetOperation_1()
		throws Exception {
		HostRegisterMsg fixture = new HostRegisterMsg();
		fixture.setSender("");
		fixture.setHost(new Host());
		fixture.setUserPass("");

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("HOST_REGISTER", result.name());
		assertEquals("HOST_REGISTER", result.toString());
		assertEquals(24, result.ordinal());
	}

	/**
	 * Run the String getSender() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetSender_1()
		throws Exception {
		HostRegisterMsg fixture = new HostRegisterMsg();
		fixture.setSender("");
		fixture.setHost(new Host());
		fixture.setUserPass("");

		String result = fixture.getSender();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getUserName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetUserName_1()
		throws Exception {
		HostRegisterMsg fixture = new HostRegisterMsg();
		fixture.setSender("");
		fixture.setHost(new Host());
		fixture.setUserPass("");

		String result = fixture.getUserName();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getUserPass() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetUserPass_1()
		throws Exception {
		HostRegisterMsg fixture = new HostRegisterMsg();
		fixture.setSender("");
		fixture.setHost(new Host());
		fixture.setUserPass("");

		String result = fixture.getUserPass();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the void setHost(CollAHost) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetHost_1()
		throws Exception {
		HostRegisterMsg fixture = new HostRegisterMsg();
		fixture.setSender("");
		fixture.setHost(new Host());
		fixture.setUserPass("");
		CollAHost host = new Host();

		fixture.setHost(host);

		// add additional test code here
	}

	/**
	 * Run the void setSender(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetSender_1()
		throws Exception {
		HostRegisterMsg fixture = new HostRegisterMsg();
		fixture.setSender("");
		fixture.setHost(new Host());
		fixture.setUserPass("");
		String sender = "";

		fixture.setSender(sender);

		// add additional test code here
	}

	/**
	 * Run the void setUserName(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetUserName_1()
		throws Exception {
		HostRegisterMsg fixture = new HostRegisterMsg();
		fixture.setSender("");
		fixture.setHost(new Host());
		fixture.setUserPass("");
		String userName = "";

		fixture.setUserName(userName);

		// add additional test code here
	}

	/**
	 * Run the void setUserPass(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetUserPass_1()
		throws Exception {
		HostRegisterMsg fixture = new HostRegisterMsg();
		fixture.setSender("");
		fixture.setHost(new Host());
		fixture.setUserPass("");
		String userPass = "";

		fixture.setUserPass(userPass);

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
		new org.junit.runner.JUnitCore().run(HostRegisterMsgTest.class);
	}
}