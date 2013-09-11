package colla.kernel.util;

import org.junit.*;
import colla.kernel.api.GenericResource;
import colla.kernel.messages.toServer.ACK;
import colla.kernel.api.CollAMessage;
import static org.junit.Assert.*;

/**
 * The class <code>ScheduledTaskTest</code> contains tests for the class <code>{@link ScheduledTask}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class ScheduledTaskTest {
	/**
	 * Run the ScheduledTask(CollAMessage,GenericResource) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testScheduledTask_1()
		throws Exception {
		CollAMessage message = new ACK();
		GenericResource resource = new GenericResource();

		ScheduledTask result = new ScheduledTask(message, resource);

		// add additional test code here
		assertNotNull(result);
		assertEquals(false, result.cancel());
		assertEquals(0L, result.scheduledExecutionTime());
	}

	/**
	 * Run the void run() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testRun_1()
		throws Exception {
		ScheduledTask fixture = new ScheduledTask(new ACK(), new GenericResource());

		fixture.run();

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
		new org.junit.runner.JUnitCore().run(ScheduledTaskTest.class);
	}
}