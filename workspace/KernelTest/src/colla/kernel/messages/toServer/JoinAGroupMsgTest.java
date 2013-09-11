package colla.kernel.messages.toServer;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>JoinAGroupMsgTest</code> contains tests for the class <code>{@link JoinAGroupMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class JoinAGroupMsgTest {
	/**
	 * Run the JoinAGroupMsg() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testJoinAGroupMsg_1()
		throws Exception {

		JoinAGroupMsg result = new JoinAGroupMsg();

		// add additional test code here
		assertNotNull(result);
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
		JoinAGroupMsg fixture = new JoinAGroupMsg();
		fixture.setSender("");
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
		JoinAGroupMsg fixture = new JoinAGroupMsg();
		fixture.setSender("");
		fixture.setGroupName("");

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("JOIN_A_GROUP", result.name());
		assertEquals("JOIN_A_GROUP", result.toString());
		assertEquals(20, result.ordinal());
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
		JoinAGroupMsg fixture = new JoinAGroupMsg();
		fixture.setSender("");
		fixture.setGroupName("");

		String result = fixture.getSender();

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
		JoinAGroupMsg fixture = new JoinAGroupMsg();
		fixture.setSender("");
		fixture.setGroupName("");
		String groupName = "";

		fixture.setGroupName(groupName);

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
		JoinAGroupMsg fixture = new JoinAGroupMsg();
		fixture.setSender("");
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
		new org.junit.runner.JUnitCore().run(JoinAGroupMsgTest.class);
	}
}