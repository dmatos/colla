package implementations.util;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>CoresAutodetectTest</code> contains tests for the class <code>{@link CoresAutodetect}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:41
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class CoresAutodetectTest {
	/**
	 * Run the int detect() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:41
	 */
	@Test
	public void testDetect_1()
		throws Exception {

		int result = CoresAutodetect.detect();

		// add additional test code here
		assertEquals(4, result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 10/09/13 18:41
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
	 * @generatedBy CodePro at 10/09/13 18:41
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
	 * @generatedBy CodePro at 10/09/13 18:41
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(CoresAutodetectTest.class);
	}
}