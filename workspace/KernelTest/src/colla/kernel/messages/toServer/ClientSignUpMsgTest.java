package colla.kernel.messages.toServer;

import org.junit.*;
import colla.kernel.api.CollAUser;
import colla.kernel.impl.User;
import static org.junit.Assert.*;

/**
 * The class <code>ClientSignUpMsgTest</code> contains tests for the class <code>{@link ClientSignUpMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class ClientSignUpMsgTest {
	/**
	 * Run the ClientSignUpMsg() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testClientSignUpMsg_1()
		throws Exception {

		ClientSignUpMsg result = new ClientSignUpMsg();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getPassword());
		assertEquals(null, result.getUser());
		assertEquals(null, result.getSender());
	}

	/**
	 * Run the ClientSignUpMsg(CollAUser,String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testClientSignUpMsg_2()
		throws Exception {
		CollAUser user = new User();
		String password = "";

		ClientSignUpMsg result = new ClientSignUpMsg(user, password);

		// add additional test code here
		assertNotNull(result);
		assertEquals("", result.getPassword());
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
		ClientSignUpMsg fixture = new ClientSignUpMsg(new User(), "");
		fixture.setSender("");

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("SIGN_UP", result.name());
		assertEquals("SIGN_UP", result.toString());
		assertEquals(1, result.ordinal());
	}

	/**
	 * Run the String getPassword() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetPassword_1()
		throws Exception {
		ClientSignUpMsg fixture = new ClientSignUpMsg(new User(), "");
		fixture.setSender("");

		String result = fixture.getPassword();

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
		ClientSignUpMsg fixture = new ClientSignUpMsg(new User(), "");
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
		ClientSignUpMsg fixture = new ClientSignUpMsg(new User(), "");
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
	 * Run the void setPassword(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetPassword_1()
		throws Exception {
		ClientSignUpMsg fixture = new ClientSignUpMsg(new User(), "");
		fixture.setSender("");
		String password = "";

		fixture.setPassword(password);

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
		ClientSignUpMsg fixture = new ClientSignUpMsg(new User(), "");
		fixture.setSender("");
		String sender = "";

		fixture.setSender(sender);

		// add additional test code here
	}

	/**
	 * Run the void setUser(CollAUser) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetUser_1()
		throws Exception {
		ClientSignUpMsg fixture = new ClientSignUpMsg(new User(), "");
		fixture.setSender("");
		CollAUser user = new User();

		fixture.setUser(user);

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
		new org.junit.runner.JUnitCore().run(ClientSignUpMsgTest.class);
	}
}