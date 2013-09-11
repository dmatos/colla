package colla.kernel.messages.toHost;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>CancelTaskTest</code> contains tests for the class <code>{@link CancelTask}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class CancelTaskTest {
	/**
	 * Run the CancelTask(Long) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testCancelTask_1()
		throws Exception {
		Long taskID = new Long(1L);

		CancelTask result = new CancelTask(taskID);

		// add additional test code here
		assertNotNull(result);
		assertEquals(1L, result.getTaskID());
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
		CancelTask fixture = new CancelTask(new Long(1L));

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("TASK_CANCEL", result.name());
		assertEquals("TASK_CANCEL", result.toString());
		assertEquals(3, result.ordinal());
	}

	/**
	 * Run the long getTaskID() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetTaskID_1()
		throws Exception {
		CancelTask fixture = new CancelTask(new Long(1L));

		long result = fixture.getTaskID();

		// add additional test code here
		assertEquals(1L, result);
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
		new org.junit.runner.JUnitCore().run(CancelTaskTest.class);
	}
}