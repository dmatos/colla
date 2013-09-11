package colla.appl.server.CLI;

import java.util.Observable;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>ComandLineInterfaceTest</code> contains tests for the class <code>{@link ComandLineInterface}</code>.
 *
 * @generatedBy CodePro at 10/09/13 19:23
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class ComandLineInterfaceTest {
	/**
	 * Run the void update(Observable,Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:23
	 */
	@Test(expected = java.lang.UnsupportedOperationException.class)
	public void testUpdate_1()
		throws Exception {
		ComandLineInterface fixture = new ComandLineInterface();
		Observable subject = new Observable();
		Object interest = new Object();

		fixture.update(subject, interest);

		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 10/09/13 19:23
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
	 * @generatedBy CodePro at 10/09/13 19:23
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
	 * @generatedBy CodePro at 10/09/13 19:23
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ComandLineInterfaceTest.class);
	}
}