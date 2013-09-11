package colla.kernel.messages.toServer;

import org.junit.*;
import colla.kernel.api.CollAUser;
import colla.kernel.impl.Task;
import colla.kernel.api.CollATask;
import colla.kernel.impl.User;
import static org.junit.Assert.*;

/**
 * The class <code>TransmitResultMsgTest</code> contains tests for the class <code>{@link TransmitResultMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class TransmitResultMsgTest {
	/**
	 * Run the TransmitResultMsg(CollAUser,String,CollATask) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testTransmitResultMsg_1()
		throws Exception {
		CollAUser client = new User();
		String taskName = "";
		CollATask task = new Task();

		TransmitResultMsg result = new TransmitResultMsg(client, taskName, task);

		// add additional test code here
		assertNotNull(result);
		assertEquals("", result.getTaskName());
		assertEquals(null, result.getGroupName());
		assertEquals(null, result.getSender());
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
		TransmitResultMsg fixture = new TransmitResultMsg(new User(), "", new Task());
		fixture.setGroupName("");
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
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetOperation_1()
		throws Exception {
		TransmitResultMsg fixture = new TransmitResultMsg(new User(), "", new Task());
		fixture.setGroupName("");
		fixture.setSender("");

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("TRANSMIT_TASK_RESULT", result.name());
		assertEquals("TRANSMIT_TASK_RESULT", result.toString());
		assertEquals(14, result.ordinal());
	}

	/**
	 * Run the CollAUser getReceiver() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetReceiver_1()
		throws Exception {
		TransmitResultMsg fixture = new TransmitResultMsg(new User(), "", new Task());
		fixture.setGroupName("");
		fixture.setSender("");

		CollAUser result = fixture.getReceiver();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getName());
		assertEquals(-1, result.getPort());
		assertEquals(null, result.getCountry());
		assertEquals("none@none.com", result.getEmail());
		assertEquals("not finished", result.getConnectionTotalTime());
		assertEquals(null, result.getIp());
		assertEquals(false, result.hasValidIP());
		assertEquals(false, result.isOnline());
		assertEquals(null, result.getCompany());
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
		TransmitResultMsg fixture = new TransmitResultMsg(new User(), "", new Task());
		fixture.setGroupName("");
		fixture.setSender("");

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
		TransmitResultMsg fixture = new TransmitResultMsg(new User(), "", new Task());
		fixture.setGroupName("");
		fixture.setSender("");

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
		TransmitResultMsg fixture = new TransmitResultMsg(new User(), "", new Task());
		fixture.setGroupName("");
		fixture.setSender("");

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
		TransmitResultMsg fixture = new TransmitResultMsg(new User(), "", new Task());
		fixture.setGroupName("");
		fixture.setSender("");
		String groupName = "";

		fixture.setGroupName(groupName);

		// add additional test code here
	}

	/**
	 * Run the void setReceiver(CollAUser) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetReceiver_1()
		throws Exception {
		TransmitResultMsg fixture = new TransmitResultMsg(new User(), "", new Task());
		fixture.setGroupName("");
		fixture.setSender("");
		CollAUser rcvr = new User();

		fixture.setReceiver(rcvr);

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
		TransmitResultMsg fixture = new TransmitResultMsg(new User(), "", new Task());
		fixture.setGroupName("");
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
		new org.junit.runner.JUnitCore().run(TransmitResultMsgTest.class);
	}
}