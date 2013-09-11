package colla.kernel.messages.toServer;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>GetAvailableHostsMsgTest</code> contains tests for the class <code>{@link GetAvailableHostsMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class GetAvailableHostsMsgTest {
	/**
	 * Run the GetAvailableHostsMsg() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetAvailableHostsMsg_1()
		throws Exception {

		GetAvailableHostsMsg result = new GetAvailableHostsMsg();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getGroup());
		assertEquals(null, result.getSender());
	}

	/**
	 * Run the String getGroup() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetGroup_1()
		throws Exception {
		GetAvailableHostsMsg fixture = new GetAvailableHostsMsg();
		fixture.setGroup("");
		fixture.setSender("");

		String result = fixture.getGroup();

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
		GetAvailableHostsMsg fixture = new GetAvailableHostsMsg();
		fixture.setGroup("");
		fixture.setSender("");

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("GET_AVAILABLE_HOSTS", result.name());
		assertEquals("GET_AVAILABLE_HOSTS", result.toString());
		assertEquals(26, result.ordinal());
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
		GetAvailableHostsMsg fixture = new GetAvailableHostsMsg();
		fixture.setGroup("");
		fixture.setSender("");

		String result = fixture.getSender();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the void setGroup(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetGroup_1()
		throws Exception {
		GetAvailableHostsMsg fixture = new GetAvailableHostsMsg();
		fixture.setGroup("");
		fixture.setSender("");
		String group = "";

		fixture.setGroup(group);

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
		GetAvailableHostsMsg fixture = new GetAvailableHostsMsg();
		fixture.setGroup("");
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
		new org.junit.runner.JUnitCore().run(GetAvailableHostsMsgTest.class);
	}
}