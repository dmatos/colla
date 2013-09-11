package colla.kernel.messages.toClient;

import java.util.TreeSet;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>GetGroupsAnswerMsgTest</code> contains tests for the class <code>{@link GetGroupsAnswerMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:28
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class GetGroupsAnswerMsgTest {
	/**
	 * Run the GetGroupsAnswerMsg() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:28
	 */
	@Test
	public void testGetGroupsAnswerMsg_1()
		throws Exception {

		GetGroupsAnswerMsg result = new GetGroupsAnswerMsg();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getSender());
		assertEquals(null, result.getGroupsSet());
	}

	/**
	 * Run the TreeSet<String> getGroupsSet() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:28
	 */
	@Test
	public void testGetGroupsSet_1()
		throws Exception {
		GetGroupsAnswerMsg fixture = new GetGroupsAnswerMsg();
		fixture.setSender("");
		fixture.setGroupsSet(new TreeSet());

		TreeSet<String> result = fixture.getGroupsSet();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
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
		GetGroupsAnswerMsg fixture = new GetGroupsAnswerMsg();
		fixture.setSender("");
		fixture.setGroupsSet(new TreeSet());

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("LIST_GROUPS", result.name());
		assertEquals("LIST_GROUPS", result.toString());
		assertEquals(7, result.ordinal());
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
		GetGroupsAnswerMsg fixture = new GetGroupsAnswerMsg();
		fixture.setSender("");
		fixture.setGroupsSet(new TreeSet());

		String result = fixture.getSender();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the void setGroupsSet(TreeSet<String>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:28
	 */
	@Test
	public void testSetGroupsSet_1()
		throws Exception {
		GetGroupsAnswerMsg fixture = new GetGroupsAnswerMsg();
		fixture.setSender("");
		fixture.setGroupsSet(new TreeSet());
		TreeSet<String> groups = new TreeSet();

		fixture.setGroupsSet(groups);

		// add additional test code here
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
		GetGroupsAnswerMsg fixture = new GetGroupsAnswerMsg();
		fixture.setSender("");
		fixture.setGroupsSet(new TreeSet());
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
		new org.junit.runner.JUnitCore().run(GetGroupsAnswerMsgTest.class);
	}
}