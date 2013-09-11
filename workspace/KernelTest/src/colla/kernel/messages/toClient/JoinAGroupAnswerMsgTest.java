package colla.kernel.messages.toClient;

import java.util.HashMap;
import org.junit.*;
import colla.kernel.impl.Group;
import colla.kernel.api.CollAUser;
import colla.kernel.api.CollAGroup;
import static org.junit.Assert.*;

/**
 * The class <code>JoinAGroupAnswerMsgTest</code> contains tests for the class <code>{@link JoinAGroupAnswerMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:26
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class JoinAGroupAnswerMsgTest {
	/**
	 * Run the JoinAGroupAnswerMsg(CollAGroup) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testJoinAGroupAnswerMsg_1()
		throws Exception {
		CollAGroup group = new Group("");

		JoinAGroupAnswerMsg result = new JoinAGroupAnswerMsg(group);

		// add additional test code here
		assertNotNull(result);
		assertEquals("", result.getGroupName());
		assertEquals(null, result.getUsersMap());
		assertEquals(null, result.getSender());
	}

	/**
	 * Run the CollAGroup getGroup() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetGroup_1()
		throws Exception {
		JoinAGroupAnswerMsg fixture = new JoinAGroupAnswerMsg(new Group(""));
		fixture.setSender("");
		fixture.setUsersMap(new HashMap());

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
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetGroupName_1()
		throws Exception {
		JoinAGroupAnswerMsg fixture = new JoinAGroupAnswerMsg(new Group(""));
		fixture.setSender("");
		fixture.setUsersMap(new HashMap());

		String result = fixture.getGroupName();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the Enum getOperation() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetOperation_1()
		throws Exception {
		JoinAGroupAnswerMsg fixture = new JoinAGroupAnswerMsg(new Group(""));
		fixture.setSender("");
		fixture.setUsersMap(new HashMap());

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("RECEIVE_A_GROUP", result.name());
		assertEquals("RECEIVE_A_GROUP", result.toString());
		assertEquals(6, result.ordinal());
	}

	/**
	 * Run the String getSender() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetSender_1()
		throws Exception {
		JoinAGroupAnswerMsg fixture = new JoinAGroupAnswerMsg(new Group(""));
		fixture.setSender("");
		fixture.setUsersMap(new HashMap());

		String result = fixture.getSender();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the HashMap<String, CollAUser> getUsersMap() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetUsersMap_1()
		throws Exception {
		JoinAGroupAnswerMsg fixture = new JoinAGroupAnswerMsg(new Group(""));
		fixture.setSender("");
		fixture.setUsersMap(new HashMap());

		HashMap<String, CollAUser> result = fixture.getUsersMap();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the void setGroup(CollAGroup) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetGroup_1()
		throws Exception {
		JoinAGroupAnswerMsg fixture = new JoinAGroupAnswerMsg(new Group(""));
		fixture.setSender("");
		fixture.setUsersMap(new HashMap());
		CollAGroup group = new Group("");

		fixture.setGroup(group);

		// add additional test code here
	}

	/**
	 * Run the void setGroupName(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetGroupName_1()
		throws Exception {
		JoinAGroupAnswerMsg fixture = new JoinAGroupAnswerMsg(new Group(""));
		fixture.setSender("");
		fixture.setUsersMap(new HashMap());
		String groupName = "";

		fixture.setGroupName(groupName);

		// add additional test code here
	}

	/**
	 * Run the void setSender(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetSender_1()
		throws Exception {
		JoinAGroupAnswerMsg fixture = new JoinAGroupAnswerMsg(new Group(""));
		fixture.setSender("");
		fixture.setUsersMap(new HashMap());
		String sender = "";

		fixture.setSender(sender);

		// add additional test code here
	}

	/**
	 * Run the void setUsersMap(HashMap<String,CollAUser>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetUsersMap_1()
		throws Exception {
		JoinAGroupAnswerMsg fixture = new JoinAGroupAnswerMsg(new Group(""));
		fixture.setSender("");
		fixture.setUsersMap(new HashMap());
		HashMap<String, CollAUser> usersMap = new HashMap();

		fixture.setUsersMap(usersMap);

		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
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
	 * @generatedBy CodePro at 10/09/13 18:26
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
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(JoinAGroupAnswerMsgTest.class);
	}
}