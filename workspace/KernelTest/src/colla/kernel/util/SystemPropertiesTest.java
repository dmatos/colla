package colla.kernel.util;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>SystemPropertiesTest</code> contains tests for the class <code>{@link SystemProperties}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class SystemPropertiesTest {
	/**
	 * Run the SystemProperties() constructor test.
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testSystemProperties_1()
		throws Exception {
		SystemProperties result = new SystemProperties();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the String getSystemProperties() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetSystemProperties_1()
		throws Exception {
		SystemProperties fixture = new SystemProperties();

		String result = fixture.getSystemProperties();

		// add additional test code here
		assertEquals("Total Memory: 279224320\nFree Memory: 126904608\nAvaliable Processors: 4\nOS: Linux 3.8.0-30-generic\nArch: i386\nCountry: BR\n", result);
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
		new org.junit.runner.JUnitCore().run(SystemPropertiesTest.class);
	}
}