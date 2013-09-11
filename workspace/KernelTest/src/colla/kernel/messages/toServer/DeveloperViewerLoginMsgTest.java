package colla.kernel.messages.toServer;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>DeveloperViewerLoginMsgTest</code> contains tests for the class <code>{@link DeveloperViewerLoginMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class DeveloperViewerLoginMsgTest {
	/**
	 * Run the DeveloperViewerLoginMsg() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testDeveloperViewerLoginMsg_1()
		throws Exception {

		DeveloperViewerLoginMsg result = new DeveloperViewerLoginMsg();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getIP());
		assertEquals(null, result.getPassword());
		assertEquals(null, result.getSender());
	}

	/**
	 * Run the DeveloperViewerLoginMsg(String,String,String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testDeveloperViewerLoginMsg_2()
		throws Exception {
		String userName = "";
		String password = "";
		String ip = "";

		DeveloperViewerLoginMsg result = new DeveloperViewerLoginMsg(userName, password, ip);

		// add additional test code here
		assertNotNull(result);
		assertEquals("", result.getIP());
		assertEquals("", result.getPassword());
		assertEquals("", result.getSender());
	}

	/**
	 * Run the String getIP() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetIP_1()
		throws Exception {
		DeveloperViewerLoginMsg fixture = new DeveloperViewerLoginMsg("", "", "");
		fixture.userName = "";

		String result = fixture.getIP();

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
		DeveloperViewerLoginMsg fixture = new DeveloperViewerLoginMsg("", "", "");
		fixture.userName = "";

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("LOGIN", result.name());
		assertEquals("LOGIN", result.toString());
		assertEquals(0, result.ordinal());
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
		DeveloperViewerLoginMsg fixture = new DeveloperViewerLoginMsg("", "", "");
		fixture.userName = "";

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
		DeveloperViewerLoginMsg fixture = new DeveloperViewerLoginMsg("", "", "");
		fixture.userName = "";

		String result = fixture.getSender();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the void setIP(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetIP_1()
		throws Exception {
		DeveloperViewerLoginMsg fixture = new DeveloperViewerLoginMsg("", "", "");
		fixture.userName = "";
		String ip = "";

		fixture.setIP(ip);

		// add additional test code here
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
		DeveloperViewerLoginMsg fixture = new DeveloperViewerLoginMsg("", "", "");
		fixture.userName = "";
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
		DeveloperViewerLoginMsg fixture = new DeveloperViewerLoginMsg("", "", "");
		fixture.userName = "";
		String sender = "";

		fixture.setSender(sender);

		// add additional test code here
	}

	/**
	 * Run the void setUserName(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetUserName_1()
		throws Exception {
		DeveloperViewerLoginMsg fixture = new DeveloperViewerLoginMsg("", "", "");
		fixture.userName = "";
		String userName = "";

		fixture.setUserName(userName);

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
		new org.junit.runner.JUnitCore().run(DeveloperViewerLoginMsgTest.class);
	}
}