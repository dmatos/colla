package colla.kernel.messages.toClient;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>ChatDirectMessageMsgTest</code> contains tests for the class <code>{@link ChatDirectMessageMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class ChatDirectMessageMsgTest {
	/**
	 * Run the ChatDirectMessageMsg(String,String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testChatDirectMessageMsg_1()
		throws Exception {
		String sender = "";
		String message = "";

		ChatDirectMessageMsg result = new ChatDirectMessageMsg(sender, message);

		// add additional test code here
		assertNotNull(result);
		assertEquals("", result.getMessage());
		assertEquals("", result.getSender());
	}

	/**
	 * Run the String getMessage() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetMessage_1()
		throws Exception {
		ChatDirectMessageMsg fixture = new ChatDirectMessageMsg("", "");

		String result = fixture.getMessage();

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
		ChatDirectMessageMsg fixture = new ChatDirectMessageMsg("", "");

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("MESSAGE", result.name());
		assertEquals("MESSAGE", result.toString());
		assertEquals(0, result.ordinal());
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
		ChatDirectMessageMsg fixture = new ChatDirectMessageMsg("", "");

		String result = fixture.getSender();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the void setMessage(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetMessage_1()
		throws Exception {
		ChatDirectMessageMsg fixture = new ChatDirectMessageMsg("", "");
		String msg = "";

		fixture.setMessage(msg);

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
		ChatDirectMessageMsg fixture = new ChatDirectMessageMsg("", "");
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
		new org.junit.runner.JUnitCore().run(ChatDirectMessageMsgTest.class);
	}
}