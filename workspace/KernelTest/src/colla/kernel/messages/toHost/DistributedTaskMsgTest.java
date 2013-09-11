package colla.kernel.messages.toHost;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>DistributedTaskMsgTest</code> contains tests for the class <code>{@link DistributedTaskMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class DistributedTaskMsgTest {
	/**
	 * Run the DistributedTaskMsg() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testDistributedTaskMsg_1()
		throws Exception {

		DistributedTaskMsg result = new DistributedTaskMsg();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getUser());
		assertEquals(null, result.getGroup());
		assertEquals(null, result.getGroupName());
		assertEquals(null, result.getSender());
		assertEquals(null, result.getTask());
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
		DistributedTaskMsg fixture = new DistributedTaskMsg();
		fixture.addAttach(new byte[] {}, "");
		fixture.addAttach(new byte[] {}, "");

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("TASK_EXECUTE_DISTRIBUTED", result.name());
		assertEquals("TASK_EXECUTE_DISTRIBUTED", result.toString());
		assertEquals(2, result.ordinal());
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
		new org.junit.runner.JUnitCore().run(DistributedTaskMsgTest.class);
	}
}