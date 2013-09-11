package colla.kernel.messages.toServer;

import org.junit.*;
import colla.kernel.api.CollAUser;
import colla.kernel.impl.User;
import static org.junit.Assert.*;

/**
 * The class <code>DeveloperViewerDisconnectMsgTest</code> contains tests for the class <code>{@link DeveloperViewerDisconnectMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class DeveloperViewerDisconnectMsgTest {
	/**
	 * Run the DeveloperViewerDisconnectMsg(CollAUser) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testDeveloperViewerDisconnectMsg_1()
		throws Exception {
		CollAUser user = new User();

		DeveloperViewerDisconnectMsg result = new DeveloperViewerDisconnectMsg(user);

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getSender());
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
		DeveloperViewerDisconnectMsg fixture = new DeveloperViewerDisconnectMsg(new User());
		fixture.setSender("");

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("DISCONNECT", result.name());
		assertEquals("DISCONNECT", result.toString());
		assertEquals(10, result.ordinal());
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
		DeveloperViewerDisconnectMsg fixture = new DeveloperViewerDisconnectMsg(new User());
		fixture.setSender("");

		String result = fixture.getSender();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the CollAUser getUser() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetUser_1()
		throws Exception {
		DeveloperViewerDisconnectMsg fixture = new DeveloperViewerDisconnectMsg(new User());
		fixture.setSender("");

		CollAUser result = fixture.getUser();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getName());
		assertEquals(-1, result.getPort());
		assertEquals(null, result.getCountry());
		assertEquals("none@none.com", result.getEmail());
		assertEquals("not finished", result.getConnectionTotalTime());
		assertEquals(null, result.getIp());
		assertEquals(false, result.hasValidIP());
		assertEquals(false, result.isOnline());
		assertEquals(null, result.getCompany());
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
		DeveloperViewerDisconnectMsg fixture = new DeveloperViewerDisconnectMsg(new User());
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
		new org.junit.runner.JUnitCore().run(DeveloperViewerDisconnectMsgTest.class);
	}
}