package colla.kernel.messages.toServer;

import java.util.List;
import org.junit.*;
import colla.kernel.impl.Group;
import colla.kernel.api.CollAGroup;
import static org.junit.Assert.*;

/**
 * The class <code>UpdateGroupsMsgTest</code> contains tests for the class <code>{@link UpdateGroupsMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:27
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class UpdateGroupsMsgTest {
	/**
	 * Run the UpdateGroupsMsg() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testUpdateGroupsMsg_1()
		throws Exception {

		UpdateGroupsMsg result = new UpdateGroupsMsg();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getManager());
	}

	/**
	 * Run the void addGroup(CollAGroup) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testAddGroup_1()
		throws Exception {
		UpdateGroupsMsg fixture = new UpdateGroupsMsg();
		fixture.setManager("");
		fixture.addGroup(new Group(""));
		CollAGroup group = new Group("");

		fixture.addGroup(group);

		// add additional test code here
	}

	/**
	 * Run the List<CollAGroup> getGroups() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGetGroups_1()
		throws Exception {
		UpdateGroupsMsg fixture = new UpdateGroupsMsg();
		fixture.setManager("");
		fixture.addGroup(new Group(""));

		List<CollAGroup> result = fixture.getGroups();

		// add additional test code here
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	/**
	 * Run the String getManager() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGetManager_1()
		throws Exception {
		UpdateGroupsMsg fixture = new UpdateGroupsMsg();
		fixture.setManager("");
		fixture.addGroup(new Group(""));

		String result = fixture.getManager();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the Enum getOperation() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGetOperation_1()
		throws Exception {
		UpdateGroupsMsg fixture = new UpdateGroupsMsg();
		fixture.setManager("");
		fixture.addGroup(new Group(""));

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("UPDATE_GROUPS", result.name());
		assertEquals("UPDATE_GROUPS", result.toString());
		assertEquals(22, result.ordinal());
	}

	/**
	 * Run the void setManager(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testSetManager_1()
		throws Exception {
		UpdateGroupsMsg fixture = new UpdateGroupsMsg();
		fixture.setManager("");
		fixture.addGroup(new Group(""));
		String usrName = "";

		fixture.setManager(usrName);

		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
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
	 * @generatedBy CodePro at 10/09/13 18:27
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
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(UpdateGroupsMsgTest.class);
	}
}