package colla.kernel.messages.toServer;

import org.junit.*;
import colla.kernel.impl.Task;
import colla.kernel.api.CollATask;
import static org.junit.Assert.*;

/**
 * The class <code>TransmitStartNotificationTest</code> contains tests for the class <code>{@link TransmitStartNotification}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class TransmitStartNotificationTest {
	/**
	 * Run the TransmitStartNotification(CollATask) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testTransmitStartNotification_1()
		throws Exception {
		CollATask task = new Task();

		TransmitStartNotification result = new TransmitStartNotification(task);

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getTaskName());
		assertEquals(null, result.getGroupName());
	}

	/**
	 * Run the String getGroupName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetGroupName_1()
		throws Exception {
		TransmitStartNotification fixture = new TransmitStartNotification(new Task());
		fixture.setGroupName("");
		fixture.setTaskName("");

		String result = fixture.getGroupName();

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
		TransmitStartNotification fixture = new TransmitStartNotification(new Task());
		fixture.setGroupName("");
		fixture.setTaskName("");

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("TRANSMIT_START_NOTIFICATION", result.name());
		assertEquals("TRANSMIT_START_NOTIFICATION", result.toString());
		assertEquals(15, result.ordinal());
	}

	/**
	 * Run the CollATask getTask() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetTask_1()
		throws Exception {
		TransmitStartNotification fixture = new TransmitStartNotification(new Task());
		fixture.setGroupName("");
		fixture.setTaskName("");

		CollATask result = fixture.getTask();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getOwner());
		assertEquals("", result.getMethodToExecute());
		assertEquals("", result.getTaskName());
		assertEquals("", result.getClassToExecute());
		assertEquals("not finished", result.getTotalTime());
		assertEquals(null, result.getInitialTime());
		assertEquals("not finished yet", result.getFinalTime());
		assertEquals(new Long(0L), result.getTotalTimeInNanoS());
		assertEquals(null, result.getFileFromResult());
		assertEquals(false, result.hasTicket());
		assertEquals(null, result.getTicket());
		assertEquals(false, result.removeTicket());
		assertEquals(new Long(0L), result.getTaskID());
		assertEquals(null, result.getSchedule());
		assertEquals(false, result.hasSchedule());
		assertEquals("waiting result", result.getResult());
		assertEquals(null, result.getArguments());
		assertEquals(null, result.getTask());
		assertEquals(false, result.isFinished());
	}

	/**
	 * Run the String getTaskName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetTaskName_1()
		throws Exception {
		TransmitStartNotification fixture = new TransmitStartNotification(new Task());
		fixture.setGroupName("");
		fixture.setTaskName("");

		String result = fixture.getTaskName();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the void setGroupName(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetGroupName_1()
		throws Exception {
		TransmitStartNotification fixture = new TransmitStartNotification(new Task());
		fixture.setGroupName("");
		fixture.setTaskName("");
		String name = "";

		fixture.setGroupName(name);

		// add additional test code here
	}

	/**
	 * Run the void setTaskName(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetTaskName_1()
		throws Exception {
		TransmitStartNotification fixture = new TransmitStartNotification(new Task());
		fixture.setGroupName("");
		fixture.setTaskName("");
		String name = "";

		fixture.setTaskName(name);

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
		new org.junit.runner.JUnitCore().run(TransmitStartNotificationTest.class);
	}
}