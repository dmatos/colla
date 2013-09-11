package colla.kernel.impl;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>TicketTest</code> contains tests for the class <code>{@link Ticket}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:27
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class TicketTest {
	/**
	 * Run the Ticket() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testTicket_1()
		throws Exception {

		Ticket result = new Ticket();

		// add additional test code here
		assertNotNull(result);
		assertEquals("", result.getHostIPAddress());
		assertEquals(new Integer(-1), result.getHostPort());
		assertEquals(new Integer(-1), result.getTicket());
	}

	/**
	 * Run the String getHostIPAddress() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGetHostIPAddress_1()
		throws Exception {
		Ticket fixture = new Ticket();
		fixture.setHostIPaddress("");
		fixture.setHostPort(new Integer(1));
		fixture.setTicket(new Integer(1));

		String result = fixture.getHostIPAddress();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the Integer getHostPort() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGetHostPort_1()
		throws Exception {
		Ticket fixture = new Ticket();
		fixture.setHostIPaddress("");
		fixture.setHostPort(new Integer(1));
		fixture.setTicket(new Integer(1));

		Integer result = fixture.getHostPort();

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
	 * Run the Integer getTicket() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testGetTicket_1()
		throws Exception {
		Ticket fixture = new Ticket();
		fixture.setHostIPaddress("");
		fixture.setHostPort(new Integer(1));
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
	 * Run the void setHostIPaddress(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testSetHostIPaddress_1()
		throws Exception {
		Ticket fixture = new Ticket();
		fixture.setHostIPaddress("");
		fixture.setHostPort(new Integer(1));
		fixture.setTicket(new Integer(1));
		String ipAddress = "";

		fixture.setHostIPaddress(ipAddress);

		// add additional test code here
	}

	/**
	 * Run the void setHostPort(Integer) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testSetHostPort_1()
		throws Exception {
		Ticket fixture = new Ticket();
		fixture.setHostIPaddress("");
		fixture.setHostPort(new Integer(1));
		fixture.setTicket(new Integer(1));
		Integer port = new Integer(1);

		fixture.setHostPort(port);

		// add additional test code here
	}

	/**
	 * Run the void setTicket(Integer) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:27
	 */
	@Test
	public void testSetTicket_1()
		throws Exception {
		Ticket fixture = new Ticket();
		fixture.setHostIPaddress("");
		fixture.setHostPort(new Integer(1));
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
		new org.junit.runner.JUnitCore().run(TicketTest.class);
	}
}