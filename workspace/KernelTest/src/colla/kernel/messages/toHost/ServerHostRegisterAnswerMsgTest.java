package colla.kernel.messages.toHost;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>ServerHostRegisterAnswerMsgTest</code> contains tests for the class <code>{@link ServerHostRegisterAnswerMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class ServerHostRegisterAnswerMsgTest {
	/**
	 * Run the ServerHostRegisterAnswerMsg() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testServerHostRegisterAnswerMsg_1()
		throws Exception {

		ServerHostRegisterAnswerMsg result = new ServerHostRegisterAnswerMsg();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getHostName());
		assertEquals(null, result.getOperation());
		assertEquals(null, result.getSender());
	}

	/**
	 * Run the ServerHostRegisterAnswerMsg(String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testServerHostRegisterAnswerMsg_2()
		throws Exception {
		String hostName = "";

		ServerHostRegisterAnswerMsg result = new ServerHostRegisterAnswerMsg(hostName);

		// add additional test code here
		assertNotNull(result);
		assertEquals("", result.getHostName());
		assertEquals(null, result.getOperation());
		assertEquals(null, result.getSender());
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
		ServerHostRegisterAnswerMsg fixture = new ServerHostRegisterAnswerMsg("");
		fixture.setSender("");

		String result = fixture.getHostName();

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
		ServerHostRegisterAnswerMsg fixture = new ServerHostRegisterAnswerMsg("");
		fixture.setSender("");

		Enum result = fixture.getOperation();

		// add additional test code here
		assertEquals(null, result);
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
		ServerHostRegisterAnswerMsg fixture = new ServerHostRegisterAnswerMsg("");
		fixture.setSender("");

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
		ServerHostRegisterAnswerMsg fixture = new ServerHostRegisterAnswerMsg("");
		fixture.setSender("");
		String hostName = "";

		fixture.setHostName(hostName);

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
		ServerHostRegisterAnswerMsg fixture = new ServerHostRegisterAnswerMsg("");
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
		new org.junit.runner.JUnitCore().run(ServerHostRegisterAnswerMsgTest.class);
	}
}