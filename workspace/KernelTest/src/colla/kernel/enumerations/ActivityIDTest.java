package colla.kernel.enumerations;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>ActivityIDTest</code> contains tests for the class <code>{@link ActivityID}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:24
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class ActivityIDTest {
	/**
	 * Run the ActivityID(String,String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:24
	 */

	/**
	 * Run the String getActivity() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:24
	 */
	@Test
	public void testGetActivity_1()
		throws Exception {
		ActivityID fixture = ActivityID.CREATE_GROUP;

		String result = fixture.getActivity();

		// add additional test code here
		assertEquals("creation of group: ", result);
	}

	/**
	 * Run the int getID() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:24
	 */
	@Test
	public void testGetID_1()
		throws Exception {
		ActivityID fixture = ActivityID.CREATE_GROUP;

		int result = fixture.getID();

		// add additional test code here
		assertEquals(6, result);
	}

	/**
	 * Run the int getID() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:24
	 */
	@Test
	public void testGetID_2()
		throws Exception {
		ActivityID fixture = ActivityID.CREATE_GROUP;

		int result = fixture.getID();

		// add additional test code here
		assertEquals(6, result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 10/09/13 18:24
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
	 * @generatedBy CodePro at 10/09/13 18:24
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
	 * @generatedBy CodePro at 10/09/13 18:24
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ActivityIDTest.class);
	}
}