package colla.kernel.impl;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>JobChatTest</code> contains tests for the class <code>{@link JobChat}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class JobChatTest {
	/**
	 * Run the JobChat() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testJobChat_1()
		throws Exception {

		JobChat result = new JobChat();

		// add additional test code here
		assertNotNull(result);
		assertEquals("Chat", result.getJobName());
		assertEquals("2013-09-10 18:25:35", result.getJobDateAndTime());
	}

	/**
	 * Run the String getJobDateAndTime() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetJobDateAndTime_1()
		throws Exception {
		JobChat fixture = new JobChat();

		String result = fixture.getJobDateAndTime();

		// add additional test code here
		assertEquals("2013-09-10 18:25:35", result);
	}

	/**
	 * Run the String getJobName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetJobName_1()
		throws Exception {
		JobChat fixture = new JobChat();

		String result = fixture.getJobName();

		// add additional test code here
		assertEquals("Chat", result);
	}

	/**
	 * Run the void setJobName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSetJobName_1()
		throws Exception {
		JobChat fixture = new JobChat();

		fixture.setJobName();

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
		new org.junit.runner.JUnitCore().run(JobChatTest.class);
	}
}