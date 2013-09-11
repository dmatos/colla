package colla.kernel.messages.toServer;

import java.util.ArrayList;
import colla.kernel.api.CollAUser;
import colla.kernel.impl.Task;
import colla.kernel.api.CollATask;
import colla.kernel.impl.User;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>TransmitTaskMsgTest</code> contains tests for the class <code>{@link TransmitTaskMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class TransmitTaskMsgTest {
	/**
	 * Run the TransmitTaskMsg() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testTransmitTaskMsg_1()
		throws Exception {

		TransmitTaskMsg result = new TransmitTaskMsg();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getUser());
		assertEquals(null, result.getGroup());
		assertEquals(null, result.getSender());
		assertEquals(null, result.getTask());
		assertEquals(null, result.getReceiver());
	}

	/**
	 * Run the void addAttach(byte[],String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testAddAttach_1()
		throws Exception {
		TransmitTaskMsg fixture = new TransmitTaskMsg();
		fixture.setUser(new User());
		fixture.setSender("");
		fixture.setGroup("");
		fixture.setTask(new Task());
		fixture.setReceiver("");
		fixture.addAttach(new byte[] {}, "");
		fixture.addAttach(new byte[] {}, "");
		byte[] attachBuffer = new byte[] {};
		String attachName = "";

		fixture.addAttach(attachBuffer, attachName);

		// add additional test code here
	}

	/**
	 * Run the ArrayList<String> getAttachNames() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetAttachNames_1()
		throws Exception {
		TransmitTaskMsg fixture = new TransmitTaskMsg();
		fixture.setUser(new User());
		fixture.setSender("");
		fixture.setGroup("");
		fixture.setTask(new Task());
		fixture.setReceiver("");
		fixture.addAttach(new byte[] {}, "");
		fixture.addAttach(new byte[] {}, "");

		ArrayList<String> result = fixture.getAttachNames();

		// add additional test code here
		assertNotNull(result);
		assertEquals(2, result.size());
		assertTrue(result.contains(""));
	}

	/**
	 * Run the ArrayList<byte[]> getAttaches() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetAttaches_1()
		throws Exception {
		TransmitTaskMsg fixture = new TransmitTaskMsg();
		fixture.setUser(new User());
		fixture.setSender("");
		fixture.setGroup("");
		fixture.setTask(new Task());
		fixture.setReceiver("");
		fixture.addAttach(new byte[] {}, "");
		fixture.addAttach(new byte[] {}, "");

		ArrayList<byte[]> result = fixture.getAttaches();

		// add additional test code here
		assertNotNull(result);
		assertEquals(2, result.size());
	}

	/**
	 * Run the String getGroup() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetGroup_1()
		throws Exception {
		TransmitTaskMsg fixture = new TransmitTaskMsg();
		fixture.setUser(new User());
		fixture.setSender("");
		fixture.setGroup("");
		fixture.setTask(new Task());
		fixture.setReceiver("");
		fixture.addAttach(new byte[] {}, "");
		fixture.addAttach(new byte[] {}, "");

		String result = fixture.getGroup();

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
		TransmitTaskMsg fixture = new TransmitTaskMsg();
		fixture.setUser(new User());
		fixture.setSender("");
		fixture.setGroup("");
		fixture.setTask(new Task());
		fixture.setReceiver("");
		fixture.addAttach(new byte[] {}, "");
		fixture.addAttach(new byte[] {}, "");

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("TRANSMIT_TASK", result.name());
		assertEquals("TRANSMIT_TASK", result.toString());
		assertEquals(13, result.ordinal());
	}

	/**
	 * Run the String getReceiver() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetReceiver_1()
		throws Exception {
		TransmitTaskMsg fixture = new TransmitTaskMsg();
		fixture.setUser(new User());
		fixture.setSender("");
		fixture.setGroup("");
		fixture.setTask(new Task());
		fixture.setReceiver("");
		fixture.addAttach(new byte[] {}, "");
		fixture.addAttach(new byte[] {}, "");

		String result = fixture.getReceiver();

		// add additional test code here
		assertEquals("", result);
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
		TransmitTaskMsg fixture = new TransmitTaskMsg();
		fixture.setUser(new User());
		fixture.setSender("");
		fixture.setGroup("");
		fixture.setTask(new Task());
		fixture.setReceiver("");
		fixture.addAttach(new byte[] {}, "");
		fixture.addAttach(new byte[] {}, "");

		String result = fixture.getSender();

		// add additional test code here
		assertEquals("", result);
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
		TransmitTaskMsg fixture = new TransmitTaskMsg();
		fixture.setUser(new User());
		fixture.setSender("");
		fixture.setGroup("");
		fixture.setTask(new Task());
		fixture.setReceiver("");
		fixture.addAttach(new byte[] {}, "");
		fixture.addAttach(new byte[] {}, "");

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
	 * Run the CollAUser getUser() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetUser_1()
		throws Exception {
		TransmitTaskMsg fixture = new TransmitTaskMsg();
		fixture.setUser(new User());
		fixture.setSender("");
		fixture.setGroup("");
		fixture.setTask(new Task());
		fixture.setReceiver("");
		fixture.addAttach(new byte[] {}, "");
		fixture.addAttach(new byte[] {}, "");

		CollAUser result = fixture.getUser();

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
	 * Run the void setGroup(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetGroup_1()
		throws Exception {
		TransmitTaskMsg fixture = new TransmitTaskMsg();
		fixture.setUser(new User());
		fixture.setSender("");
		fixture.setGroup("");
		fixture.setTask(new Task());
		fixture.setReceiver("");
		fixture.addAttach(new byte[] {}, "");
		fixture.addAttach(new byte[] {}, "");
		String groupName = "";

		fixture.setGroup(groupName);

		// add additional test code here
	}

	/**
	 * Run the void setReceiver(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetReceiver_1()
		throws Exception {
		TransmitTaskMsg fixture = new TransmitTaskMsg();
		fixture.setUser(new User());
		fixture.setSender("");
		fixture.setGroup("");
		fixture.setTask(new Task());
		fixture.setReceiver("");
		fixture.addAttach(new byte[] {}, "");
		fixture.addAttach(new byte[] {}, "");
		String rcvr = "";

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
		TransmitTaskMsg fixture = new TransmitTaskMsg();
		fixture.setUser(new User());
		fixture.setSender("");
		fixture.setGroup("");
		fixture.setTask(new Task());
		fixture.setReceiver("");
		fixture.addAttach(new byte[] {}, "");
		fixture.addAttach(new byte[] {}, "");
		String sender = "";

		fixture.setSender(sender);

		// add additional test code here
	}

	/**
	 * Run the void setTask(CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetTask_1()
		throws Exception {
		TransmitTaskMsg fixture = new TransmitTaskMsg();
		fixture.setUser(new User());
		fixture.setSender("");
		fixture.setGroup("");
		fixture.setTask(new Task());
		fixture.setReceiver("");
		fixture.addAttach(new byte[] {}, "");
		fixture.addAttach(new byte[] {}, "");
		CollATask task = new Task();

		fixture.setTask(task);

		// add additional test code here
	}

	/**
	 * Run the void setUser(CollAUser) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetUser_1()
		throws Exception {
		TransmitTaskMsg fixture = new TransmitTaskMsg();
		fixture.setUser(new User());
		fixture.setSender("");
		fixture.setGroup("");
		fixture.setTask(new Task());
		fixture.setReceiver("");
		fixture.addAttach(new byte[] {}, "");
		fixture.addAttach(new byte[] {}, "");
		CollAUser usr = new User();

		fixture.setUser(usr);

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
		new org.junit.runner.JUnitCore().run(TransmitTaskMsgTest.class);
	}
}