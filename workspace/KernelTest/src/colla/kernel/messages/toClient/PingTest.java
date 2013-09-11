package colla.kernel.messages.toClient;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>PingTest</code> contains tests for the class <code>{@link Ping}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class PingTest {
	/**
	 * Run the Ping(long) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testPing_1()
		throws Exception {
		long id = 1L;

		Ping result = new Ping(id);

		// add additional test code here
		assertNotNull(result);
		assertEquals(1L, result.getID());
	}

	/**
	 * Run the long getID() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetID_1()
		throws Exception {
		Ping fixture = new Ping(1L);

		long result = fixture.getID();

		// add additional test code here
		assertEquals(1L, result);
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
		Ping fixture = new Ping(1L);

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("PING", result.name());
		assertEquals("PING", result.toString());
		assertEquals(2, result.ordinal());
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
		new org.junit.runner.JUnitCore().run(PingTest.class);
	}
}