package colla.kernel.messages.toServer;

import org.junit.*;
import colla.kernel.api.CollAUser;
import colla.kernel.impl.User;
import static org.junit.Assert.*;

/**
 * The class <code>UpdateUserMsgTest</code> contains tests for the class <code>{@link UpdateUserMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:28
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class UpdateUserMsgTest {
	/**
	 * Run the UpdateUserMsg(CollAUser) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:28
	 */
	@Test
	public void testUpdateUserMsg_1()
		throws Exception {
		CollAUser usr = new User();

		UpdateUserMsg result = new UpdateUserMsg(usr);

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getSender());
	}

	/**
	 * Run the Enum getOperation() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:28
	 */
	@Test
	public void testGetOperation_1()
		throws Exception {
		UpdateUserMsg fixture = new UpdateUserMsg(new User());
		fixture.setSender("");

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("UPDATE_USER", result.name());
		assertEquals("UPDATE_USER", result.toString());
		assertEquals(8, result.ordinal());
	}

	/**
	 * Run the String getSender() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:28
	 */
	@Test
	public void testGetSender_1()
		throws Exception {
		UpdateUserMsg fixture = new UpdateUserMsg(new User());
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
	 * @generatedBy CodePro at 10/09/13 18:28
	 */
	@Test
	public void testGetUser_1()
		throws Exception {
		UpdateUserMsg fixture = new UpdateUserMsg(new User());
		fixture.setSender("");

		CollAUser result = fixture.getUser();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getName());
		assertEquals(-1, result.getPort());
		assertEquals(null, result.getCountry());
		assertEquals("none@none.com", result.getEmail());
		assertEquals(null, result.getCompany());
		assertEquals(false, result.hasValidIP());
		assertEquals(false, result.isOnline());
		assertEquals("not finished", result.getConnectionTotalTime());
		assertEquals(null, result.getIp());
	}

	/**
	 * Run the void setSender(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:28
	 */
	@Test
	public void testSetSender_1()
		throws Exception {
		UpdateUserMsg fixture = new UpdateUserMsg(new User());
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
	 * @generatedBy CodePro at 10/09/13 18:28
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
	 * @generatedBy CodePro at 10/09/13 18:28
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
	 * @generatedBy CodePro at 10/09/13 18:28
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(UpdateUserMsgTest.class);
	}
}