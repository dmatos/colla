package colla.kernel.messages.toClient;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>SignUpAnswerMsgTest</code> contains tests for the class <code>{@link SignUpAnswerMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class SignUpAnswerMsgTest {
	/**
	 * Run the SignUpAnswerMsg() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSignUpAnswerMsg_1()
		throws Exception {

		SignUpAnswerMsg result = new SignUpAnswerMsg();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getOperation());
		assertEquals(null, result.getSender());
		assertEquals(null, result.getConfirmation());
	}

	/**
	 * Run the void confirmUserName(Boolean) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testConfirmUserName_1()
		throws Exception {
		SignUpAnswerMsg fixture = new SignUpAnswerMsg();
		fixture.confirmUserName(new Boolean(true));
		fixture.setSender("");
		Boolean confirmName = new Boolean(true);

		fixture.confirmUserName(confirmName);

		// add additional test code here
	}

	/**
	 * Run the Boolean getConfirmation() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetConfirmation_1()
		throws Exception {
		SignUpAnswerMsg fixture = new SignUpAnswerMsg();
		fixture.confirmUserName(new Boolean(true));
		fixture.setSender("");

		Boolean result = fixture.getConfirmation();

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
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetOperation_1()
		throws Exception {
		SignUpAnswerMsg fixture = new SignUpAnswerMsg();
		fixture.confirmUserName(new Boolean(true));
		fixture.setSender("");

		Enum result = fixture.getOperation();

		// add additional test code here
		assertEquals(null, result);
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
		SignUpAnswerMsg fixture = new SignUpAnswerMsg();
		fixture.confirmUserName(new Boolean(true));
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
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetSender_1()
		throws Exception {
		SignUpAnswerMsg fixture = new SignUpAnswerMsg();
		fixture.confirmUserName(new Boolean(true));
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
		new org.junit.runner.JUnitCore().run(SignUpAnswerMsgTest.class);
	}
}