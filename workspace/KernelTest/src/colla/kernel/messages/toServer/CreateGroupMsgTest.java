package colla.kernel.messages.toServer;

import org.junit.*;
import colla.kernel.impl.Group;
import colla.kernel.api.CollAGroup;
import static org.junit.Assert.*;

/**
 * The class <code>CreateGroupMsgTest</code> contains tests for the class <code>{@link CreateGroupMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class CreateGroupMsgTest {
	/**
	 * Run the CreateGroupMsg() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testCreateGroupMsg_1()
		throws Exception {

		CreateGroupMsg result = new CreateGroupMsg();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getGroup());
		assertEquals(null, result.getSender());
	}

	/**
	 * Run the CreateGroupMsg(CollAGroup) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testCreateGroupMsg_2()
		throws Exception {
		CollAGroup group = new Group("");

		CreateGroupMsg result = new CreateGroupMsg(group);

		// add additional test code here
		assertNotNull(result);
		assertEquals("", result.getGroupName());
		assertEquals(null, result.getSender());
	}

	/**
	 * Run the CollAGroup getGroup() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetGroup_1()
		throws Exception {
		CreateGroupMsg fixture = new CreateGroupMsg(new Group(""));
		fixture.setSender("");

		CollAGroup result = fixture.getGroup();

		// add additional test code here
		assertNotNull(result);
		assertEquals("", result.getName());
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
		CreateGroupMsg fixture = new CreateGroupMsg(new Group(""));
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
		CreateGroupMsg fixture = new CreateGroupMsg(new Group(""));
		fixture.setSender("");

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("CONFIRM_ADD_GROUP", result.name());
		assertEquals("CONFIRM_ADD_GROUP", result.toString());
		assertEquals(21, result.ordinal());
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
		CreateGroupMsg fixture = new CreateGroupMsg(new Group(""));
		fixture.setSender("");

		String result = fixture.getSender();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the void setGroup(CollAGroup) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetGroup_1()
		throws Exception {
		CreateGroupMsg fixture = new CreateGroupMsg(new Group(""));
		fixture.setSender("");
		CollAGroup group = new Group("");

		fixture.setGroup(group);

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
		CreateGroupMsg fixture = new CreateGroupMsg(new Group(""));
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
		new org.junit.runner.JUnitCore().run(CreateGroupMsgTest.class);
	}
}