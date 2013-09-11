package colla.kernel.util;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>TreaterTest</code> contains tests for the class <code>{@link Treater}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:27
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class TreaterTest {
	/**
	 * Run the void treat(Exception) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testTreat_1()
		throws Exception {
		Exception e = new Exception();

		Treater.treat(e);

		// add additional test code here
	}

	/**
	 * Run the void treat(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testTreat_2()
		throws Exception {
		String s = "";

		Treater.treat(s);

		// add additional test code here
	}

	/**
	 * Run the void treat(String,Exception) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testTreat_3()
		throws Exception {
		String s = "";
		Exception e = new Exception();

		Treater.treat(s, e);

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
		new org.junit.runner.JUnitCore().run(TreaterTest.class);
	}
}