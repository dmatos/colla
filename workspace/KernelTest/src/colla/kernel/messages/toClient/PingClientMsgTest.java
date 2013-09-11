package colla.kernel.messages.toClient;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>PingClientMsgTest</code> contains tests for the class <code>{@link PingClientMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:27
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class PingClientMsgTest {
	/**
	 * Run the PingClientMsg() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testPingClientMsg_1()
		throws Exception {

		PingClientMsg result = new PingClientMsg();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getSender());
	}

	/**
	 * Run the Enum getOperation() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGetOperation_1()
		throws Exception {
		PingClientMsg fixture = new PingClientMsg();
		fixture.setSender("");

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("PING", result.name());
		assertEquals("PING", result.toString());
		assertEquals(2, result.ordinal());
	}

	/**
	 * Run the String getSender() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGetSender_1()
		throws Exception {
		PingClientMsg fixture = new PingClientMsg();
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
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testSetSender_1()
		throws Exception {
		PingClientMsg fixture = new PingClientMsg();
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
	 * @generatedBy CodePro at 10/09/13 18:27
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
	 * @generatedBy CodePro at 10/09/13 18:27
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
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PingClientMsgTest.class);
	}
}