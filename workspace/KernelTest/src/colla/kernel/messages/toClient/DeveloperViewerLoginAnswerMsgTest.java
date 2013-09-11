package colla.kernel.messages.toClient;

import java.util.HashMap;
import org.junit.*;
import colla.kernel.api.CollAUser;
import colla.kernel.impl.User;
import static org.junit.Assert.*;

/**
 * The class <code>DeveloperViewerLoginAnswerMsgTest</code> contains tests for the class <code>{@link DeveloperViewerLoginAnswerMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:27
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class DeveloperViewerLoginAnswerMsgTest {
	/**
	 * Run the DeveloperViewerLoginAnswerMsg() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testDeveloperViewerLoginAnswerMsg_1()
		throws Exception {

		DeveloperViewerLoginAnswerMsg result = new DeveloperViewerLoginAnswerMsg();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getOperation());
		assertEquals(null, result.getUser());
		assertEquals(null, result.getSender());
		assertEquals(null, result.getNameConfirmation());
		assertEquals(null, result.getPassConfirmation());
	}

	/**
	 * Run the void confirmPassword(Boolean) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testConfirmPassword_1()
		throws Exception {
		DeveloperViewerLoginAnswerMsg fixture = new DeveloperViewerLoginAnswerMsg();
		fixture.confirmUserName(new Boolean(true));
		fixture.setSender("");
		fixture.setUser(new User());
		fixture.setContacts(new HashMap());
		fixture.confirmPassword(new Boolean(true));
		Boolean confirmPass = new Boolean(true);

		fixture.confirmPassword(confirmPass);

		// add additional test code here
	}

	/**
	 * Run the void confirmUserName(Boolean) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testConfirmUserName_1()
		throws Exception {
		DeveloperViewerLoginAnswerMsg fixture = new DeveloperViewerLoginAnswerMsg();
		fixture.confirmUserName(new Boolean(true));
		fixture.setSender("");
		fixture.setUser(new User());
		fixture.setContacts(new HashMap());
		fixture.confirmPassword(new Boolean(true));
		Boolean confirmName = new Boolean(true);

		fixture.confirmUserName(confirmName);

		// add additional test code here
	}

	/**
	 * Run the HashMap<String, CollAUser> getContacts() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGetContacts_1()
		throws Exception {
		DeveloperViewerLoginAnswerMsg fixture = new DeveloperViewerLoginAnswerMsg();
		fixture.confirmUserName(new Boolean(true));
		fixture.setSender("");
		fixture.setUser(new User());
		fixture.setContacts(new HashMap());
		fixture.confirmPassword(new Boolean(true));

		HashMap<String, CollAUser> result = fixture.getContacts();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the Boolean getNameConfirmation() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGetNameConfirmation_1()
		throws Exception {
		DeveloperViewerLoginAnswerMsg fixture = new DeveloperViewerLoginAnswerMsg();
		fixture.confirmUserName(new Boolean(true));
		fixture.setSender("");
		fixture.setUser(new User());
		fixture.setContacts(new HashMap());
		fixture.confirmPassword(new Boolean(true));

		Boolean result = fixture.getNameConfirmation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("true", result.toString());
		assertEquals(true, result.booleanValue());
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
		DeveloperViewerLoginAnswerMsg fixture = new DeveloperViewerLoginAnswerMsg();
		fixture.confirmUserName(new Boolean(true));
		fixture.setSender("");
		fixture.setUser(new User());
		fixture.setContacts(new HashMap());
		fixture.confirmPassword(new Boolean(true));

		Enum result = fixture.getOperation();

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the Boolean getPassConfirmation() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGetPassConfirmation_1()
		throws Exception {
		DeveloperViewerLoginAnswerMsg fixture = new DeveloperViewerLoginAnswerMsg();
		fixture.confirmUserName(new Boolean(true));
		fixture.setSender("");
		fixture.setUser(new User());
		fixture.setContacts(new HashMap());
		fixture.confirmPassword(new Boolean(true));

		Boolean result = fixture.getPassConfirmation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("true", result.toString());
		assertEquals(true, result.booleanValue());
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
		DeveloperViewerLoginAnswerMsg fixture = new DeveloperViewerLoginAnswerMsg();
		fixture.confirmUserName(new Boolean(true));
		fixture.setSender("");
		fixture.setUser(new User());
		fixture.setContacts(new HashMap());
		fixture.confirmPassword(new Boolean(true));

		String result = fixture.getSender();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the CollAUser getUser() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGetUser_1()
		throws Exception {
		DeveloperViewerLoginAnswerMsg fixture = new DeveloperViewerLoginAnswerMsg();
		fixture.confirmUserName(new Boolean(true));
		fixture.setSender("");
		fixture.setUser(new User());
		fixture.setContacts(new HashMap());
		fixture.confirmPassword(new Boolean(true));

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
	 * Run the void setContacts(HashMap<String,CollAUser>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testSetContacts_1()
		throws Exception {
		DeveloperViewerLoginAnswerMsg fixture = new DeveloperViewerLoginAnswerMsg();
		fixture.confirmUserName(new Boolean(true));
		fixture.setSender("");
		fixture.setUser(new User());
		fixture.setContacts(new HashMap());
		fixture.confirmPassword(new Boolean(true));
		HashMap<String, CollAUser> contacts = new HashMap();

		fixture.setContacts(contacts);

		// add additional test code here
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
		DeveloperViewerLoginAnswerMsg fixture = new DeveloperViewerLoginAnswerMsg();
		fixture.confirmUserName(new Boolean(true));
		fixture.setSender("");
		fixture.setUser(new User());
		fixture.setContacts(new HashMap());
		fixture.confirmPassword(new Boolean(true));
		String sender = "";

		fixture.setSender(sender);

		// add additional test code here
	}

	/**
	 * Run the void setUser(CollAUser) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testSetUser_1()
		throws Exception {
		DeveloperViewerLoginAnswerMsg fixture = new DeveloperViewerLoginAnswerMsg();
		fixture.confirmUserName(new Boolean(true));
		fixture.setSender("");
		fixture.setUser(new User());
		fixture.setContacts(new HashMap());
		fixture.confirmPassword(new Boolean(true));
		CollAUser usr = new User();

		fixture.setUser(usr);

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
		new org.junit.runner.JUnitCore().run(DeveloperViewerLoginAnswerMsgTest.class);
	}
}