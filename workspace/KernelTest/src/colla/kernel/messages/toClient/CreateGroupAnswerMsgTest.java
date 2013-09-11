package colla.kernel.messages.toClient;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>CreateGroupAnswerMsgTest</code> contains tests for the class <code>{@link CreateGroupAnswerMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:27
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class CreateGroupAnswerMsgTest {
	/**
	 * Run the CreateGroupAnswerMsg(String,Boolean) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testCreateGroupAnswerMsg_1()
		throws Exception {
		String groupName = "";
		Boolean confirmation = new Boolean(true);

		CreateGroupAnswerMsg result = new CreateGroupAnswerMsg(groupName, confirmation);

		// add additional test code here
		assertNotNull(result);
		assertEquals("", result.getGroupName());
		assertEquals(true, result.getConfirmation());
		assertEquals(null, result.getSender());
	}

	/**
	 * Run the boolean getConfirmation() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGetConfirmation_1()
		throws Exception {
		CreateGroupAnswerMsg fixture = new CreateGroupAnswerMsg("", new Boolean(true));
		fixture.setSender("");

		boolean result = fixture.getConfirmation();

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean getConfirmation() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGetConfirmation_2()
		throws Exception {
		CreateGroupAnswerMsg fixture = new CreateGroupAnswerMsg("", new Boolean(true));
		fixture.setSender("");

		boolean result = fixture.getConfirmation();

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the String getGroupName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGetGroupName_1()
		throws Exception {
		CreateGroupAnswerMsg fixture = new CreateGroupAnswerMsg("", new Boolean(true));
		fixture.setSender("");

		String result = fixture.getGroupName();

		// add additional test code here
		assertEquals("", result);
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
		CreateGroupAnswerMsg fixture = new CreateGroupAnswerMsg("", new Boolean(true));
		fixture.setSender("");

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("GROUP_CREATION_CONFIRMATION", result.name());
		assertEquals("GROUP_CREATION_CONFIRMATION", result.toString());
		assertEquals(9, result.ordinal());
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
		CreateGroupAnswerMsg fixture = new CreateGroupAnswerMsg("", new Boolean(true));
		fixture.setSender("");

		String result = fixture.getSender();

		// add additional test code here
		assertEquals("", result);
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
		CreateGroupAnswerMsg fixture = new CreateGroupAnswerMsg("", new Boolean(true));
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
		new org.junit.runner.JUnitCore().run(CreateGroupAnswerMsgTest.class);
	}
}