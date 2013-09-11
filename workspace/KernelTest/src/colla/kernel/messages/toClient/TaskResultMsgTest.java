package colla.kernel.messages.toClient;

import org.junit.*;
import colla.kernel.impl.Task;
import colla.kernel.api.CollATask;
import static org.junit.Assert.*;

/**
 * The class <code>TaskResultMsgTest</code> contains tests for the class <code>{@link TaskResultMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class TaskResultMsgTest {
	/**
	 * Run the TaskResultMsg(String,String,CollATask) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testTaskResultMsg_1()
		throws Exception {
		String sender = "";
		String taskName = "";
		CollATask task = new Task();

		TaskResultMsg result = new TaskResultMsg(sender, taskName, task);

		// add additional test code here
		assertNotNull(result);
		assertEquals("", result.getTaskName());
		assertEquals(null, result.getGroupName());
		assertEquals("", result.getSender());
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
		TaskResultMsg fixture = new TaskResultMsg("", "", new Task());
		fixture.setGroupName("");

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
		TaskResultMsg fixture = new TaskResultMsg("", "", new Task());
		fixture.setGroupName("");

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("RECEIVE_TASK_RESULT", result.name());
		assertEquals("RECEIVE_TASK_RESULT", result.toString());
		assertEquals(13, result.ordinal());
	}

	/**
	 * Run the CollATask getResult() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetResult_1()
		throws Exception {
		TaskResultMsg fixture = new TaskResultMsg("", "", new Task());
		fixture.setGroupName("");

		CollATask result = fixture.getResult();

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
	 * Run the String getSender() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetSender_1()
		throws Exception {
		TaskResultMsg fixture = new TaskResultMsg("", "", new Task());
		fixture.setGroupName("");

		String result = fixture.getSender();

		// add additional test code here
		assertEquals("", result);
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
		TaskResultMsg fixture = new TaskResultMsg("", "", new Task());
		fixture.setGroupName("");

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
		TaskResultMsg fixture = new TaskResultMsg("", "", new Task());
		fixture.setGroupName("");
		String groupName = "";

		fixture.setGroupName(groupName);

		// add additional test code here
	}

	/**
	 * Run the void setResult(CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetResult_1()
		throws Exception {
		TaskResultMsg fixture = new TaskResultMsg("", "", new Task());
		fixture.setGroupName("");
		CollATask task = new Task();

		fixture.setResult(task);

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
		TaskResultMsg fixture = new TaskResultMsg("", "", new Task());
		fixture.setGroupName("");
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
		new org.junit.runner.JUnitCore().run(TaskResultMsgTest.class);
	}
}