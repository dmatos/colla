package colla.kernel.util;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>BadKeyExceptionTest</code> contains tests for the class <code>{@link BadKeyException}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class BadKeyExceptionTest {
	/**
	 * Run the BadKeyException(char) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testBadKeyException_1()
		throws Exception {
		char c = '';

		BadKeyException result = new BadKeyException(c);

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getCause());
		assertEquals("colla.kernel.util.BadKeyException: converting '' () to range 0->25", result.toString());
		assertEquals("converting '' () to range 0->25", result.getMessage());
		assertEquals("converting '' () to range 0->25", result.getLocalizedMessage());
	}

	/**
	 * Run the BadKeyException(String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testBadKeyException_2()
		throws Exception {
		String s = "";

		BadKeyException result = new BadKeyException(s);

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getCause());
		assertEquals("colla.kernel.util.BadKeyException: ", result.toString());
		assertEquals("", result.getMessage());
		assertEquals("", result.getLocalizedMessage());
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
		new org.junit.runner.JUnitCore().run(BadKeyExceptionTest.class);
	}
}