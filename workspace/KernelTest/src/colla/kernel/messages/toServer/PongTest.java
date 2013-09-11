package colla.kernel.messages.toServer;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>PongTest</code> contains tests for the class <code>{@link Pong}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class PongTest {
	/**
	 * Run the Pong() constructor test.
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testPong_1()
		throws Exception {
		Pong result = new Pong();
		assertNotNull(result);
		// add additional test code here
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
		Pong fixture = new Pong();

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("PONG", result.name());
		assertEquals("PONG", result.toString());
		assertEquals(17, result.ordinal());
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
		new org.junit.runner.JUnitCore().run(PongTest.class);
	}
}