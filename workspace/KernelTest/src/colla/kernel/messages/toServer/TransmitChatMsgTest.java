package colla.kernel.messages.toServer;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>TransmitChatMsgTest</code> contains tests for the class <code>{@link TransmitChatMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:26
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class TransmitChatMsgTest {
	/**
	 * Run the TransmitChatMsg() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testTransmitChatMsg_1()
		throws Exception {

		TransmitChatMsg result = new TransmitChatMsg();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getMessage());
		assertEquals(null, result.getSender());
		assertEquals(null, result.getReceiver());
	}

	/**
	 * Run the TransmitChatMsg(String,String,String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testTransmitChatMsg_2()
		throws Exception {
		String sndr = "";
		String rcvr = "";
		String msg = "";

		TransmitChatMsg result = new TransmitChatMsg(sndr, rcvr, msg);

		// add additional test code here
		assertNotNull(result);
		assertEquals("", result.getMessage());
		assertEquals("", result.getSender());
		assertEquals("", result.getReceiver());
	}

	/**
	 * Run the String getMessage() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetMessage_1()
		throws Exception {
		TransmitChatMsg fixture = new TransmitChatMsg("", "", "");

		String result = fixture.getMessage();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the Enum getOperation() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetOperation_1()
		throws Exception {
		TransmitChatMsg fixture = new TransmitChatMsg("", "", "");

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("TRANSMIT_MESSAGE", result.name());
		assertEquals("TRANSMIT_MESSAGE", result.toString());
		assertEquals(12, result.ordinal());
	}

	/**
	 * Run the String getReceiver() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetReceiver_1()
		throws Exception {
		TransmitChatMsg fixture = new TransmitChatMsg("", "", "");

		String result = fixture.getReceiver();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getSender() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetSender_1()
		throws Exception {
		TransmitChatMsg fixture = new TransmitChatMsg("", "", "");

		String result = fixture.getSender();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the void setMessage(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetMessage_1()
		throws Exception {
		TransmitChatMsg fixture = new TransmitChatMsg("", "", "");
		String msg = "";

		fixture.setMessage(msg);

		// add additional test code here
	}

	/**
	 * Run the void setReceiver(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetReceiver_1()
		throws Exception {
		TransmitChatMsg fixture = new TransmitChatMsg("", "", "");
		String receiver = "";

		fixture.setReceiver(receiver);

		// add additional test code here
	}

	/**
	 * Run the void setSender(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetSender_1()
		throws Exception {
		TransmitChatMsg fixture = new TransmitChatMsg("", "", "");
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
	 * @generatedBy CodePro at 10/09/13 18:26
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
	 * @generatedBy CodePro at 10/09/13 18:26
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
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(TransmitChatMsgTest.class);
	}
}