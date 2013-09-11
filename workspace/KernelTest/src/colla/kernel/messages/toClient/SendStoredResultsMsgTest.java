package colla.kernel.messages.toClient;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>SendStoredResultsMsgTest</code> contains tests for the class <code>{@link SendStoredResultsMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:27
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class SendStoredResultsMsgTest {
	/**
	 * Run the SendStoredResultsMsg(ArrayList<TaskResultMsg>) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testSendStoredResultsMsg_1()
		throws Exception {
		ArrayList<TaskResultMsg> results = new ArrayList();

		SendStoredResultsMsg result = new SendStoredResultsMsg(results);

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getSender());
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
		SendStoredResultsMsg fixture = new SendStoredResultsMsg(new ArrayList());
		fixture.setSender("");

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("RECEIVE_STORED_RESULTS", result.name());
		assertEquals("RECEIVE_STORED_RESULTS", result.toString());
		assertEquals(15, result.ordinal());
	}

	/**
	 * Run the ArrayList<TaskResultMsg> getResults() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGetResults_1()
		throws Exception {
		SendStoredResultsMsg fixture = new SendStoredResultsMsg(new ArrayList());
		fixture.setSender("");

		ArrayList<TaskResultMsg> result = fixture.getResults();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
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
		SendStoredResultsMsg fixture = new SendStoredResultsMsg(new ArrayList());
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
		SendStoredResultsMsg fixture = new SendStoredResultsMsg(new ArrayList());
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
		new org.junit.runner.JUnitCore().run(SendStoredResultsMsgTest.class);
	}
}