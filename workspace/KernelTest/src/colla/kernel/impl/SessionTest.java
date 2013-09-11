package colla.kernel.impl;

import org.junit.*;
import colla.kernel.api.CollAJob;
import static org.junit.Assert.*;

/**
 * The class <code>SessionTest</code> contains tests for the class <code>{@link Session}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:26
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class SessionTest {
	/**
	 * Run the Session() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSession_1()
		throws Exception {

		Session result = new Session();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0L, result.getSessionID());
		assertEquals("2013-09-10 18:26:25", result.getSessionDateAndTime());
	}

	/**
	 * Run the void addJob(CollAJob) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testAddJob_1()
		throws Exception {
		Session fixture = new Session();
		fixture.setSessionID(1L);
		CollAJob job = new JobChat();

		fixture.addJob(job);

		// add additional test code here
	}

	/**
	 * Run the void addJob(CollAJob) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testAddJob_2()
		throws Exception {
		Session fixture = new Session();
		fixture.setSessionID(1L);
		CollAJob job = new JobChat();

		fixture.addJob(job);

		// add additional test code here
	}

	/**
	 * Run the CollAJob[] getJobs() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetJobs_1()
		throws Exception {
		Session fixture = new Session();
		fixture.setSessionID(1L);

		CollAJob[] result = fixture.getJobs();

		// add additional test code here
		assertNotNull(result);
		assertEquals(20, result.length);
		assertEquals(null, result[0]);
		assertEquals(null, result[1]);
		assertEquals(null, result[2]);
		assertEquals(null, result[3]);
		assertEquals(null, result[4]);
		assertEquals(null, result[5]);
		assertEquals(null, result[6]);
		assertEquals(null, result[7]);
		assertEquals(null, result[8]);
		assertEquals(null, result[9]);
		assertEquals(null, result[10]);
		assertEquals(null, result[11]);
		assertEquals(null, result[12]);
		assertEquals(null, result[13]);
		assertEquals(null, result[14]);
		assertEquals(null, result[15]);
		assertEquals(null, result[16]);
		assertEquals(null, result[17]);
		assertEquals(null, result[18]);
		assertEquals(null, result[19]);
	}

	/**
	 * Run the String getSessionDateAndTime() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetSessionDateAndTime_1()
		throws Exception {
		Session fixture = new Session();
		fixture.setSessionID(1L);

		String result = fixture.getSessionDateAndTime();

		// add additional test code here
		assertEquals("2013-09-10 18:26:25", result);
	}

	/**
	 * Run the long getSessionID() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetSessionID_1()
		throws Exception {
		Session fixture = new Session();
		fixture.setSessionID(1L);

		long result = fixture.getSessionID();

		// add additional test code here
		assertEquals(1L, result);
	}

	/**
	 * Run the void setSessionID(long) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetSessionID_1()
		throws Exception {
		Session fixture = new Session();
		fixture.setSessionID(1L);
		long id = 1L;

		fixture.setSessionID(id);

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
		new org.junit.runner.JUnitCore().run(SessionTest.class);
	}
}