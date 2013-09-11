package colla.kernel.messages.toHost;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>DownloadResultMsgTest</code> contains tests for the class <code>{@link DownloadResultMsg}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:26
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class DownloadResultMsgTest {
	/**
	 * Run the DownloadResultMsg() constructor test.
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testDownloadResultMsg_1()
		throws Exception {
		DownloadResultMsg result = new DownloadResultMsg();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the Enum getOperation() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetOperation_1()
		throws Exception {
		DownloadResultMsg fixture = new DownloadResultMsg();
		fixture.setTicket(new Integer(1));

		Enum result = fixture.getOperation();

		// add additional test code here
		assertNotNull(result);
		assertEquals("DOWNLOAD_RESULT", result.name());
		assertEquals("DOWNLOAD_RESULT", result.toString());
		assertEquals(4, result.ordinal());
	}

	/**
	 * Run the Integer getTicket() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetTicket_1()
		throws Exception {
		DownloadResultMsg fixture = new DownloadResultMsg();
		fixture.setTicket(new Integer(1));

		Integer result = fixture.getTicket();

		// add additional test code here
		assertNotNull(result);
		assertEquals("1", result.toString());
		assertEquals((byte) 1, result.byteValue());
		assertEquals((short) 1, result.shortValue());
		assertEquals(1, result.intValue());
		assertEquals(1L, result.longValue());
		assertEquals(1.0f, result.floatValue(), 1.0f);
		assertEquals(1.0, result.doubleValue(), 1.0);
	}

	/**
	 * Run the void setTicket(Integer) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetTicket_1()
		throws Exception {
		DownloadResultMsg fixture = new DownloadResultMsg();
		fixture.setTicket(new Integer(1));
		Integer ticket = new Integer(1);

		fixture.setTicket(ticket);

		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
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
	 * @generatedBy CodePro at 10/09/13 18:26
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
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(DownloadResultMsgTest.class);
	}
}