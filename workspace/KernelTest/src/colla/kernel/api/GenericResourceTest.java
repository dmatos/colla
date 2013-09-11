package colla.kernel.api;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>GenericResourceTest</code> contains tests for the class <code>{@link GenericResource}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:27
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class GenericResourceTest {
	/**
	 * Run the GenericResource() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGenericResource_1()
		throws Exception {

		GenericResource result = new GenericResource();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the int getNumOfRegisters() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGetNumOfRegisters_1()
		throws Exception {
		GenericResource fixture = new GenericResource();

		int result = fixture.getNumOfRegisters();

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the Object getRegister() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGetRegister_1()
		throws Exception {
		GenericResource fixture = new GenericResource();

		Object result = fixture.getRegister();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object getRegister() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGetRegister_2()
		throws Exception {
		GenericResource fixture = new GenericResource();

		Object result = fixture.getRegister();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object getRegister() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test(expected = java.lang.Exception.class)
	public void testGetRegister_3()
		throws Exception {
		GenericResource fixture = new GenericResource();

		Object result = fixture.getRegister();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the boolean isFinished() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testIsFinished_1()
		throws Exception {
		GenericResource fixture = new GenericResource();

		boolean result = fixture.isFinished();

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean isFinished() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testIsFinished_2()
		throws Exception {
		GenericResource fixture = new GenericResource();

		boolean result = fixture.isFinished();

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the void putRegister(S) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testPutRegister_1()
		throws Exception {
		GenericResource fixture = new GenericResource();

		fixture.putRegister(null);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at java.util.concurrent.ConcurrentLinkedQueue.checkNotNull(ConcurrentLinkedQueue.java:807)
		//       at java.util.concurrent.ConcurrentLinkedQueue.offer(ConcurrentLinkedQueue.java:326)
		//       at colla.kernel.api.GenericResource.putRegister(GenericResource.java:17)
	}

	/**
	 * Run the void setFinished() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testSetFinished_1()
		throws Exception {
		GenericResource fixture = new GenericResource();

		fixture.setFinished();

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
		new org.junit.runner.JUnitCore().run(GenericResourceTest.class);
	}
}