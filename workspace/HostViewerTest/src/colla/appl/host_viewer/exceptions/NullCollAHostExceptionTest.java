package colla.appl.host_viewer.exceptions;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>NullCollAHostExceptionTest</code> contains tests for the class <code>{@link NullCollAHostException}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:40
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class NullCollAHostExceptionTest {
	/**
	 * Run the NullCollAHostException() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testNullCollAHostException_1()
		throws Exception {

		NullCollAHostException result = new NullCollAHostException();

		// add additional test code here
		assertNotNull(result);
		assertEquals("A CollAHost must be set first.", result.getMessage());
		assertEquals(null, result.getCause());
		assertEquals("colla.appl.host_viewer.exceptions.NullCollAHostException: A CollAHost must be set first.", result.toString());
		assertEquals("A CollAHost must be set first.", result.getLocalizedMessage());
	}

	/**
	 * Run the String getMessage() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testGetMessage_1()
		throws Exception {
		NullCollAHostException fixture = new NullCollAHostException();
		fixture.addSuppressed(new Throwable());

		String result = fixture.getMessage();

		// add additional test code here
		assertEquals("A CollAHost must be set first.", result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
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
	 * @generatedBy CodePro at 10/09/13 18:40
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
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(NullCollAHostExceptionTest.class);
	}
}