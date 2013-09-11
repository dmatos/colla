package colla.kernel.messages.toServer;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>HostLoginMsgTest</code> contains tests for the class <code>{@link HostLoginMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class HostLoginMsgTest {
	/**
	 * Run the HostLoginMsg() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testHostLoginMsg_1()
		throws Exception {

		HostLoginMsg result = new HostLoginMsg();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getHostName());
		assertEquals(null, result.getIPAddress());
		assertEquals(null, result.getSender());
	}

	/**
	 * Run the HostLoginMsg(String,String,String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testHostLoginMsg_2()
		throws Exception {
		String sender = "";
		String hostName = "";
		String IPAddress = "";

		HostLoginMsg result = new HostLoginMsg(sender, hostName, IPAddress);

		// add additional test code here
		assertNotNull(result);
		assertEquals("", result.getHostName());
		assertEquals("", result.getIPAddress());
		assertEquals("", result.getSender());
	}

	/**
	 * Run the String getHostName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetHostName_1()
		throws Exception {
		HostLoginMsg fixture = new HostLoginMsg("", "", "");

		String result = fixture.getHostName();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getIPAddress() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetIPAddress_1()
		throws Exception {
		HostLoginMsg fixture = new HostLoginMsg("", "", "");

		String result = fixture.getIPAddress();

		// add additional test code here
		assertEquals("", result);
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
		HostLoginMsg fixture = new HostLoginMsg("", "", "");

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("HOST_LOGIN", result.name());
		assertEquals("HOST_LOGIN", result.toString());
		assertEquals(23, result.ordinal());
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
		HostLoginMsg fixture = new HostLoginMsg("", "", "");

		String result = fixture.getSender();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the void setHostName(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetHostName_1()
		throws Exception {
		HostLoginMsg fixture = new HostLoginMsg("", "", "");
		String hostName = "";

		fixture.setHostName(hostName);

		// add additional test code here
	}

	/**
	 * Run the void setIPAddress(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetIPAddress_1()
		throws Exception {
		HostLoginMsg fixture = new HostLoginMsg("", "", "");
		String ip = "";

		fixture.setIPAddress(ip);

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
		HostLoginMsg fixture = new HostLoginMsg("", "", "");
		String sender = "";

		fixture.setSender(sender);

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
		new org.junit.runner.JUnitCore().run(HostLoginMsgTest.class);
	}
}