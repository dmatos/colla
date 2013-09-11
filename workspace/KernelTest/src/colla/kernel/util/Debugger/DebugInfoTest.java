package colla.kernel.util.Debugger;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>DebugInfoTest</code> contains tests for the class <code>{@link DebugInfo}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:27
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class DebugInfoTest {
	/**
	 * Run the void clear() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testClear_1()
		throws Exception {
		DebugInfo fixture = new DebugInfo();
		fixture.setException(new Exception());
		fixture.setInfo("");
		fixture.setDebuggedName("");

		fixture.clear();

		// add additional test code here
	}

	/**
	 * Run the String getDebuggedName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGetDebuggedName_1()
		throws Exception {
		DebugInfo fixture = new DebugInfo();
		fixture.setException(new Exception());
		fixture.setInfo("");
		fixture.setDebuggedName("");

		String result = fixture.getDebuggedName();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the Exception getException() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGetException_1()
		throws Exception {
		DebugInfo fixture = new DebugInfo();
		fixture.setException(new Exception());
		fixture.setInfo("");
		fixture.setDebuggedName("");

		Exception result = fixture.getException();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getCause());
		assertEquals("java.lang.Exception", result.toString());
		assertEquals(null, result.getMessage());
		assertEquals(null, result.getLocalizedMessage());
	}

	/**
	 * Run the String getInfo() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGetInfo_1()
		throws Exception {
		DebugInfo fixture = new DebugInfo();
		fixture.setException(new Exception());
		fixture.setInfo("");
		fixture.setDebuggedName("");

		String result = fixture.getInfo();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the void setDebuggedName(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testSetDebuggedName_1()
		throws Exception {
		DebugInfo fixture = new DebugInfo();
		fixture.setException(new Exception());
		fixture.setInfo("");
		fixture.setDebuggedName("");
		String debuggedName = "";

		fixture.setDebuggedName(debuggedName);

		// add additional test code here
	}

	/**
	 * Run the void setException(Exception) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testSetException_1()
		throws Exception {
		DebugInfo fixture = new DebugInfo();
		fixture.setException(new Exception());
		fixture.setInfo("");
		fixture.setDebuggedName("");
		Exception exception = new Exception();

		fixture.setException(exception);

		// add additional test code here
	}

	/**
	 * Run the void setInfo(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testSetInfo_1()
		throws Exception {
		DebugInfo fixture = new DebugInfo();
		fixture.setException(new Exception());
		fixture.setInfo("");
		fixture.setDebuggedName("");
		String info = "";

		fixture.setInfo(info);

		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
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
	 * @generatedBy CodePro at 10/09/13 18:27
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
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(DebugInfoTest.class);
	}
}