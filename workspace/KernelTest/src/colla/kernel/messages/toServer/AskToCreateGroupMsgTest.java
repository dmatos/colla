package colla.kernel.messages.toServer;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>AskToCreateGroupMsgTest</code> contains tests for the class <code>{@link AskToCreateGroupMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class AskToCreateGroupMsgTest {
	/**
	 * Run the AskToCreateGroupMsg() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testAskToCreateGroupMsg_1()
		throws Exception {

		AskToCreateGroupMsg result = new AskToCreateGroupMsg();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getGroupName());
		assertEquals(0, result.getParametersCount());
		assertEquals(null, result.getAdmin());
		assertEquals(null, result.getSender());
	}

	/**
	 * Run the AskToCreateGroupMsg(String,String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testAskToCreateGroupMsg_2()
		throws Exception {
		String groupName = "";
		String adminName = "";

		AskToCreateGroupMsg result = new AskToCreateGroupMsg(groupName, adminName);

		// add additional test code here
		assertNotNull(result);
		assertEquals("", result.getGroupName());
		assertEquals(0, result.getParametersCount());
		assertEquals("", result.getAdmin());
		assertEquals("", result.getSender());
	}

	/**
	 * Run the void addMessageParameter(Object,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test(expected = java.lang.UnsupportedOperationException.class)
	public void testAddMessageParameter_1()
		throws Exception {
		AskToCreateGroupMsg fixture = new AskToCreateGroupMsg("", "");
		Object obj = new Object();
		int index = 1;

		fixture.addMessageParameter(obj, index);

		// add additional test code here
	}

	/**
	 * Run the String getAdmin() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetAdmin_1()
		throws Exception {
		AskToCreateGroupMsg fixture = new AskToCreateGroupMsg("", "");

		String result = fixture.getAdmin();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getGroupName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetGroupName_1()
		throws Exception {
		AskToCreateGroupMsg fixture = new AskToCreateGroupMsg("", "");

		String result = fixture.getGroupName();

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
		AskToCreateGroupMsg fixture = new AskToCreateGroupMsg("", "");

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("CREATE_GROUP", result.name());
		assertEquals("CREATE_GROUP", result.toString());
		assertEquals(5, result.ordinal());
	}

	/**
	 * Run the int getParametersCount() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetParametersCount_1()
		throws Exception {
		AskToCreateGroupMsg fixture = new AskToCreateGroupMsg("", "");

		int result = fixture.getParametersCount();

		// add additional test code here
		assertEquals(0, result);
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
		AskToCreateGroupMsg fixture = new AskToCreateGroupMsg("", "");

		String result = fixture.getSender();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the void setAdmin(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetAdmin_1()
		throws Exception {
		AskToCreateGroupMsg fixture = new AskToCreateGroupMsg("", "");
		String adminName = "";

		fixture.setAdmin(adminName);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedOperationException: Not supported anymore.
		//       at colla.kernel.messages.toServer.AskToCreateGroupMsg.addMessageParameter(AskToCreateGroupMsg.java:49)
		//       at colla.kernel.messages.toServer.AskToCreateGroupMsg.setAdmin(AskToCreateGroupMsg.java:36)
	}

	/**
	 * Run the void setGroupName(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetGroupName_1()
		throws Exception {
		AskToCreateGroupMsg fixture = new AskToCreateGroupMsg("", "");
		String groupName = "";

		fixture.setGroupName(groupName);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedOperationException: Not supported anymore.
		//       at colla.kernel.messages.toServer.AskToCreateGroupMsg.addMessageParameter(AskToCreateGroupMsg.java:49)
		//       at colla.kernel.messages.toServer.AskToCreateGroupMsg.setGroupName(AskToCreateGroupMsg.java:31)
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
		AskToCreateGroupMsg fixture = new AskToCreateGroupMsg("", "");
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
		new org.junit.runner.JUnitCore().run(AskToCreateGroupMsgTest.class);
	}
}