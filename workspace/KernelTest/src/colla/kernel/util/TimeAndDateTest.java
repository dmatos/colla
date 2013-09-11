package colla.kernel.util;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>TimeAndDateTest</code> contains tests for the class <code>{@link TimeAndDate}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:25
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class TimeAndDateTest {
	/**
	 * Run the TimeAndDate() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testTimeAndDate_1()
		throws Exception {

		TimeAndDate result = new TimeAndDate();

		// add additional test code here
		assertNotNull(result);
		assertEquals("18:25:23", result.getHour());
		assertEquals("Tue Sep 10 18:25:23 BRT 2013", result.getStartDate());
		assertEquals("Tue Sep 10 18:25:23 BRT 2013", result.getEndDate());
		assertEquals("not finished", result.getTotalTime());
		assertEquals(new Long(0L), result.getTotalTimeInNanoS());
		assertEquals("2013-09-10", result.getSimpleDate());
	}

	/**
	 * Run the String getEndDate() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetEndDate_1()
		throws Exception {
		TimeAndDate fixture = new TimeAndDate();

		String result = fixture.getEndDate();

		// add additional test code here
		assertEquals("Tue Sep 10 18:25:23 BRT 2013", result);
	}

	/**
	 * Run the String getHour() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetHour_1()
		throws Exception {
		TimeAndDate fixture = new TimeAndDate();

		String result = fixture.getHour();

		// add additional test code here
		assertEquals("18:25:23", result);
	}

	/**
	 * Run the String getSimpleDate() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetSimpleDate_1()
		throws Exception {
		TimeAndDate fixture = new TimeAndDate();

		String result = fixture.getSimpleDate();

		// add additional test code here
		assertEquals("2013-09-10", result);
	}

	/**
	 * Run the String getStartDate() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetStartDate_1()
		throws Exception {
		TimeAndDate fixture = new TimeAndDate();

		String result = fixture.getStartDate();

		// add additional test code here
		assertEquals("Tue Sep 10 18:25:23 BRT 2013", result);
	}

	/**
	 * Run the String getTotalTime() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetTotalTime_1()
		throws Exception {
		TimeAndDate fixture = new TimeAndDate();

		String result = fixture.getTotalTime();

		// add additional test code here
		assertEquals("not finished", result);
	}

	/**
	 * Run the String getTotalTime() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetTotalTime_2()
		throws Exception {
		TimeAndDate fixture = new TimeAndDate();

		String result = fixture.getTotalTime();

		// add additional test code here
		assertEquals("not finished", result);
	}

	/**
	 * Run the Long getTotalTimeInNanoS() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testGetTotalTimeInNanoS_1()
		throws Exception {
		TimeAndDate fixture = new TimeAndDate();

		Long result = fixture.getTotalTimeInNanoS();

		// add additional test code here
		assertNotNull(result);
		assertEquals("0", result.toString());
		assertEquals((byte) 0, result.byteValue());
		assertEquals((short) 0, result.shortValue());
		assertEquals(0, result.intValue());
		assertEquals(0L, result.longValue());
		assertEquals(0.0f, result.floatValue(), 1.0f);
		assertEquals(0.0, result.doubleValue(), 1.0);
	}

	/**
	 * Run the void startTimer() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testStartTimer_1()
		throws Exception {
		TimeAndDate fixture = new TimeAndDate();

		fixture.startTimer();

		// add additional test code here
	}

	/**
	 * Run the void stopTimer() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testStopTimer_1()
		throws Exception {
		TimeAndDate fixture = new TimeAndDate();

		fixture.stopTimer();

		// add additional test code here
	}

	/**
	 * Run the void stopTimer() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testStopTimer_2()
		throws Exception {
		TimeAndDate fixture = new TimeAndDate();

		fixture.stopTimer();

		// add additional test code here
	}

	/**
	 * Run the void stopTimer() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testStopTimer_3()
		throws Exception {
		TimeAndDate fixture = new TimeAndDate();

		fixture.stopTimer();

		// add additional test code here
	}

	/**
	 * Run the void stopTimer() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testStopTimer_4()
		throws Exception {
		TimeAndDate fixture = new TimeAndDate();

		fixture.stopTimer();

		// add additional test code here
	}

	/**
	 * Run the void stopTimer() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testStopTimer_5()
		throws Exception {
		TimeAndDate fixture = new TimeAndDate();

		fixture.stopTimer();

		// add additional test code here
	}

	/**
	 * Run the void stopTimer() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testStopTimer_6()
		throws Exception {
		TimeAndDate fixture = new TimeAndDate();

		fixture.stopTimer();

		// add additional test code here
	}

	/**
	 * Run the void stopTimer() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testStopTimer_7()
		throws Exception {
		TimeAndDate fixture = new TimeAndDate();

		fixture.stopTimer();

		// add additional test code here
	}

	/**
	 * Run the void stopTimer() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testStopTimer_8()
		throws Exception {
		TimeAndDate fixture = new TimeAndDate();

		fixture.stopTimer();

		// add additional test code here
	}

	/**
	 * Run the void stopTimer() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:25
	 */
	@Test
	public void testStopTimer_9()
		throws Exception {
		TimeAndDate fixture = new TimeAndDate();

		fixture.stopTimer();

		// add additional test code here
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
		new org.junit.runner.JUnitCore().run(TimeAndDateTest.class);
	}
}