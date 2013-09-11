package colla.kernel.impl;

import java.util.List;
import org.junit.*;
import colla.kernel.api.CollAGroup;
import static org.junit.Assert.*;

/**
 * The class <code>GroupTest</code> contains tests for the class <code>{@link Group}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class GroupTest {
	/**
	 * Run the Group(String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGroup_1()
		throws Exception {
		String groupName = "";

		Group result = new Group(groupName);

		// add additional test code here
		assertNotNull(result);
		assertEquals("", result.getName());
	}

	/**
	 * Run the boolean addAdmin(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testAddAdmin_1()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");
		String memberName = "";

		boolean result = fixture.addAdmin(memberName);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean addAdmin(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testAddAdmin_2()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");
		String memberName = "";

		boolean result = fixture.addAdmin(memberName);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean addMember(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testAddMember_1()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");
		String memberName = "";

		boolean result = fixture.addMember(memberName);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean addMember(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testAddMember_2()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");
		String memberName = "";

		boolean result = fixture.addMember(memberName);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean addMemberToBlockedList(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testAddMemberToBlockedList_1()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");
		String memberName = "";

		boolean result = fixture.addMemberToBlockedList(memberName);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean addMemberToBlockedList(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testAddMemberToBlockedList_2()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");
		String memberName = "";

		boolean result = fixture.addMemberToBlockedList(memberName);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean addMemberToWaitingList(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testAddMemberToWaitingList_1()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");
		String memberName = "";

		boolean result = fixture.addMemberToWaitingList(memberName);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean addMemberToWaitingList(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testAddMemberToWaitingList_2()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");
		String memberName = "";

		boolean result = fixture.addMemberToWaitingList(memberName);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the int compareTo(CollAGroup) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testCompareTo_1()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");
		CollAGroup group = new Group("");

		int result = fixture.compareTo(group);

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the int compareTo(CollAGroup) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testCompareTo_2()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");
		CollAGroup group = new Group("");

		int result = fixture.compareTo(group);

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the int compareTo(CollAGroup) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testCompareTo_3()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");
		CollAGroup group = new Group("");

		int result = fixture.compareTo(group);

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the List<String> getAdminsList() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetAdminsList_1()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");

		List<String> result = fixture.getAdminsList();

		// add additional test code here
		assertNotNull(result);
		assertEquals(1, result.size());
		assertTrue(result.contains(""));
	}

	/**
	 * Run the List<String> getBlockedList() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetBlockedList_1()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");

		List<String> result = fixture.getBlockedList();

		// add additional test code here
		assertNotNull(result);
		assertEquals(1, result.size());
		assertTrue(result.contains(""));
	}

	/**
	 * Run the List<String> getMembers() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetMembers_1()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");

		List<String> result = fixture.getMembers();

		// add additional test code here
		assertNotNull(result);
		assertEquals(1, result.size());
		assertTrue(result.contains(""));
	}

	/**
	 * Run the String getName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetName_1()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");

		String result = fixture.getName();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the List<String> getWaitingList() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetWaitingList_1()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");

		List<String> result = fixture.getWaitingList();

		// add additional test code here
		assertNotNull(result);
		assertEquals(1, result.size());
		assertTrue(result.contains(""));
	}

	/**
	 * Run the boolean remMemberFromBlockedList(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testRemMemberFromBlockedList_1()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");
		String memberName = "";

		boolean result = fixture.remMemberFromBlockedList(memberName);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean remMemberFromBlockedList(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testRemMemberFromBlockedList_2()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");
		String memberName = "";

		boolean result = fixture.remMemberFromBlockedList(memberName);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean remMemberFromWaitingList(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testRemMemberFromWaitingList_1()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");
		String memberName = "";

		boolean result = fixture.remMemberFromWaitingList(memberName);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean remMemberFromWaitingList(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testRemMemberFromWaitingList_2()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");
		String memberName = "";

		boolean result = fixture.remMemberFromWaitingList(memberName);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean removeAdmin(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testRemoveAdmin_1()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");
		String memberName = "";

		boolean result = fixture.removeAdmin(memberName);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean removeAdmin(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testRemoveAdmin_2()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");
		String memberName = "";

		boolean result = fixture.removeAdmin(memberName);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean removeMember(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testRemoveMember_1()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");
		String memberName = "";

		boolean result = fixture.removeMember(memberName);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean removeMember(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testRemoveMember_2()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");
		String memberName = "";

		boolean result = fixture.removeMember(memberName);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the void setName(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetName_1()
		throws Exception {
		Group fixture = new Group("");
		fixture.setName("");
		fixture.addAdmin("");
		fixture.addMemberToWaitingList("");
		fixture.addMemberToBlockedList("");
		fixture.addMember("");
		String name = "";

		fixture.setName(name);

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
		new org.junit.runner.JUnitCore().run(GroupTest.class);
	}
}