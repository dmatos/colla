package colla.kernel.messages.toHost;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>ServerHostLoginAnswerTest</code> contains tests for the class <code>{@link ServerHostLoginAnswer}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class ServerHostLoginAnswerTest {
	/**
	 * Run the ServerHostLoginAnswer(Boolean,Boolean,String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testServerHostLoginAnswer_1()
		throws Exception {
		Boolean isConnected = new Boolean(true);
		Boolean isValidIP = new Boolean(true);
		String realIP = "";

		ServerHostLoginAnswer result = new ServerHostLoginAnswer(isConnected, isValidIP, realIP);

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getOperation());
		assertEquals(Boolean.TRUE, result.isValidIP());
		assertEquals("", result.getRealIP());
		assertEquals(null, result.getSender());
		assertEquals(Boolean.TRUE, result.isConnected());
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
		ServerHostLoginAnswer fixture = new ServerHostLoginAnswer(new Boolean(true), new Boolean(true), "");
		fixture.setSender("");

		Enum result = fixture.getOperation();

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the String getRealIP() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetRealIP_1()
		throws Exception {
		ServerHostLoginAnswer fixture = new ServerHostLoginAnswer(new Boolean(true), new Boolean(true), "");
		fixture.setSender("");

		String result = fixture.getRealIP();

		// add additional test code here
		assertEquals("", result);
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
		ServerHostLoginAnswer fixture = new ServerHostLoginAnswer(new Boolean(true), new Boolean(true), "");
		fixture.setSender("");

		String result = fixture.getSender();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the Boolean isConnected() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testIsConnected_1()
		throws Exception {
		ServerHostLoginAnswer fixture = new ServerHostLoginAnswer(new Boolean(true), new Boolean(true), "");
		fixture.setSender("");

		Boolean result = fixture.isConnected();

		// add additional test code here
		assertNotNull(result);
		assertEquals("true", result.toString());
		assertEquals(true, result.booleanValue());
	}

	/**
	 * Run the Boolean isValidIP() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testIsValidIP_1()
		throws Exception {
		ServerHostLoginAnswer fixture = new ServerHostLoginAnswer(new Boolean(true), new Boolean(true), "");
		fixture.setSender("");

		Boolean result = fixture.isValidIP();

		// add additional test code here
		assertNotNull(result);
		assertEquals("true", result.toString());
		assertEquals(true, result.booleanValue());
	}

	/**
	 * Run the void setIsConnected(Boolean) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetIsConnected_1()
		throws Exception {
		ServerHostLoginAnswer fixture = new ServerHostLoginAnswer(new Boolean(true), new Boolean(true), "");
		fixture.setSender("");
		Boolean isConnected = new Boolean(true);

		fixture.setIsConnected(isConnected);

		// add additional test code here
	}

	/**
	 * Run the void setIsValidIp(Boolean) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetIsValidIp_1()
		throws Exception {
		ServerHostLoginAnswer fixture = new ServerHostLoginAnswer(new Boolean(true), new Boolean(true), "");
		fixture.setSender("");
		Boolean isValidIP = new Boolean(true);

		fixture.setIsValidIp(isValidIP);

		// add additional test code here
	}

	/**
	 * Run the void setRealIP(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetRealIP_1()
		throws Exception {
		ServerHostLoginAnswer fixture = new ServerHostLoginAnswer(new Boolean(true), new Boolean(true), "");
		fixture.setSender("");
		String realIP = "";

		fixture.setRealIP(realIP);

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
		ServerHostLoginAnswer fixture = new ServerHostLoginAnswer(new Boolean(true), new Boolean(true), "");
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
		new org.junit.runner.JUnitCore().run(ServerHostLoginAnswerTest.class);
	}
}