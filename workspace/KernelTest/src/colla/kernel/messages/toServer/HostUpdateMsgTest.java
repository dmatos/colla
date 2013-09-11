package colla.kernel.messages.toServer;

import org.junit.*;
import colla.kernel.impl.Host;
import colla.kernel.api.CollAHost;
import static org.junit.Assert.*;

/**
 * The class <code>HostUpdateMsgTest</code> contains tests for the class <code>{@link HostUpdateMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class HostUpdateMsgTest {
	/**
	 * Run the HostUpdateMsg(CollAHost) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testHostUpdateMsg_1()
		throws Exception {
		CollAHost host = new Host();

		HostUpdateMsg result = new HostUpdateMsg(host);

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getSender());
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
		HostUpdateMsg fixture = new HostUpdateMsg(new Host());
		fixture.setSender("");

		CollAHost result = fixture.getHost();

		// add additional test code here
		assertNotNull(result);
		assertEquals("nameUser: null\nname: null\ncountry: null\nip: null\nport: -1\nvalidIp: false\n", result.toString());
		assertEquals(null, result.getName());
		assertEquals(-1, result.getPort());
		assertEquals(null, result.getCountry());
		assertEquals("Total Memory: 279224320\nFree Memory: 137467352\nAvaliable Processors: 4\nOS: Linux 3.8.0-30-generic\nArch: i386\nCountry: BR\n", result.getSystemProperties());
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
		HostUpdateMsg fixture = new HostUpdateMsg(new Host());
		fixture.setSender("");

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("UPDATE_HOST", result.name());
		assertEquals("UPDATE_HOST", result.toString());
		assertEquals(9, result.ordinal());
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
		HostUpdateMsg fixture = new HostUpdateMsg(new Host());
		fixture.setSender("");

		String result = fixture.getSender();

		// add additional test code here
		assertEquals("", result);
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
		HostUpdateMsg fixture = new HostUpdateMsg(new Host());
		fixture.setSender("");
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
		new org.junit.runner.JUnitCore().run(HostUpdateMsgTest.class);
	}
}