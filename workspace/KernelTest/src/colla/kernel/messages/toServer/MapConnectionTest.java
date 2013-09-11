package colla.kernel.messages.toServer;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>MapConnectionTest</code> contains tests for the class <code>{@link MapConnection}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:27
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class MapConnectionTest {
	/**
	 * Run the MapConnection() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testMapConnection_1()
		throws Exception {

		MapConnection result = new MapConnection();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getSender());
	}

	/**
	 * Run the MapConnection(String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testMapConnection_2()
		throws Exception {
		String userName = "";

		MapConnection result = new MapConnection(userName);

		// add additional test code here
		assertNotNull(result);
		assertEquals("", result.getSender());
	}

	/**
	 * Run the Enum getOperation() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGetOperation_1()
		throws Exception {
		MapConnection fixture = new MapConnection("");

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("MAP_THE_CONNECTION", result.name());
		assertEquals("MAP_THE_CONNECTION", result.toString());
		assertEquals(18, result.ordinal());
	}

	/**
	 * Run the String getSender() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGetSender_1()
		throws Exception {
		MapConnection fixture = new MapConnection("");

		String result = fixture.getSender();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the void setSender(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testSetSender_1()
		throws Exception {
		MapConnection fixture = new MapConnection("");
		String sender = "";

		fixture.setSender(sender);

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
		new org.junit.runner.JUnitCore().run(MapConnectionTest.class);
	}
}