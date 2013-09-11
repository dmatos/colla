package colla.kernel.messages.toClient;

import java.util.LinkedList;
import colla.kernel.impl.Host;
import colla.kernel.api.CollAHost;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>SendAvailableHostsMsgTest</code> contains tests for the class <code>{@link SendAvailableHostsMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class SendAvailableHostsMsgTest {
	/**
	 * Run the SendAvailableHostsMsg(Long) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSendAvailableHostsMsg_1()
		throws Exception {
		Long taskID = new Long(1L);

		SendAvailableHostsMsg result = new SendAvailableHostsMsg(taskID);

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getSender());
		assertEquals(new Long(1L), result.getTaskID());
	}

	/**
	 * Run the void addHost(CollAHost) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testAddHost_1()
		throws Exception {
		SendAvailableHostsMsg fixture = new SendAvailableHostsMsg(new Long(1L));
		fixture.setSender("");
		fixture.addHost(new Host());
		CollAHost host = new Host();

		fixture.addHost(host);

		// add additional test code here
	}

	/**
	 * Run the LinkedList<CollAHost> getHosts() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetHosts_1()
		throws Exception {
		SendAvailableHostsMsg fixture = new SendAvailableHostsMsg(new Long(1L));
		fixture.setSender("");
		fixture.addHost(new Host());

		LinkedList<CollAHost> result = fixture.getHosts();

		// add additional test code here
		assertNotNull(result);
		assertEquals(1, result.size());
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
		SendAvailableHostsMsg fixture = new SendAvailableHostsMsg(new Long(1L));
		fixture.setSender("");
		fixture.addHost(new Host());

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("RECEIVE_AVAILABLE_HOSTS", result.name());
		assertEquals("RECEIVE_AVAILABLE_HOSTS", result.toString());
		assertEquals(12, result.ordinal());
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
		SendAvailableHostsMsg fixture = new SendAvailableHostsMsg(new Long(1L));
		fixture.setSender("");
		fixture.addHost(new Host());

		String result = fixture.getSender();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the Long getTaskID() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetTaskID_1()
		throws Exception {
		SendAvailableHostsMsg fixture = new SendAvailableHostsMsg(new Long(1L));
		fixture.setSender("");
		fixture.addHost(new Host());

		Long result = fixture.getTaskID();

		// add additional test code here
		assertNotNull(result);
		assertEquals("1", result.toString());
		assertEquals((byte) 1, result.byteValue());
		assertEquals((short) 1, result.shortValue());
		assertEquals(1, result.intValue());
		assertEquals(1L, result.longValue());
		assertEquals(1.0f, result.floatValue(), 1.0f);
		assertEquals(1.0, result.doubleValue(), 1.0);
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
		SendAvailableHostsMsg fixture = new SendAvailableHostsMsg(new Long(1L));
		fixture.setSender("");
		fixture.addHost(new Host());
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
		new org.junit.runner.JUnitCore().run(SendAvailableHostsMsgTest.class);
	}
}