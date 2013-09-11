package colla.kernel.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import org.junit.*;
import interfaces.kernel.JCL_result;
import static org.junit.Assert.*;
import commom.JCL_resultImpl;
import colla.kernel.api.CollATicket;

/**
 * The class <code>TaskTest</code> contains tests for the class <code>{@link Task}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:26
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class TaskTest {
	/**
	 * Run the Task() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testTask_1()
		throws Exception {

		Task result = new Task();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getOwner());
		assertEquals("", result.getMethodToExecute());
		assertEquals(null, result.getGroup());
		assertEquals("", result.getTaskName());
		assertEquals(false, result.hasTicket());
		assertEquals(new Long(0L), result.getTaskID());
		assertEquals(null, result.getSchedule());
		assertEquals(false, result.hasSchedule());
		assertEquals(false, result.removeTicket());
		assertEquals("not finished", result.getTotalTime());
		assertEquals(null, result.getInitialTime());
		assertEquals("not finished yet", result.getFinalTime());
		assertEquals(new Long(0L), result.getTotalTimeInNanoS());
		assertEquals("waiting result", result.getResult());
		assertEquals(null, result.getArguments());
		assertEquals(null, result.getTask());
		assertEquals(null, result.getFileFromResult());
		assertEquals(null, result.getTicket());
		assertEquals("", result.getClassToExecute());
		assertEquals(false, result.isFinished());
	}

	/**
	 * Run the void addArgument(File) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testAddArgument_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		File file = new File("");

		fixture.addArgument(file);

		// add additional test code here
	}

	/**
	 * Run the void addArgument(File) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testAddArgument_2()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		File file = new File("");

		fixture.addArgument(file);

		// add additional test code here
	}

	/**
	 * Run the void addArgument(File) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testAddArgument_3()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		File file = new File("");

		fixture.addArgument(file);

		// add additional test code here
	}

	/**
	 * Run the void addArgument(File) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testAddArgument_4()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		File file = new File("");

		fixture.addArgument(file);

		// add additional test code here
	}

	/**
	 * Run the void addArgument(File) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testAddArgument_5()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		File file = new File("");

		fixture.addArgument(file);

		// add additional test code here
	}

	/**
	 * Run the void addDependency(File) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testAddDependency_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		File file = new File("");

		fixture.addDependency(file);

		// add additional test code here
	}

	/**
	 * Run the void addDependency(File) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testAddDependency_2()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		File file = new File("");

		fixture.addDependency(file);

		// add additional test code here
	}

	/**
	 * Run the void addDependency(File) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testAddDependency_3()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		File file = new File("");

		fixture.addDependency(file);

		// add additional test code here
	}

	/**
	 * Run the void addDependency(File) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testAddDependency_4()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		File file = new File("");

		fixture.addDependency(file);

		// add additional test code here
	}

	/**
	 * Run the void addDependency(File) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testAddDependency_5()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		File file = new File("");

		fixture.addDependency(file);

		// add additional test code here
	}

	/**
	 * Run the void clean() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testClean_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		fixture.clean();

		// add additional test code here
	}

	/**
	 * Run the void clean() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testClean_2()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		fixture.clean();

		// add additional test code here
	}

	/**
	 * Run the void clean() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testClean_3()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		fixture.clean();

		// add additional test code here
	}

	/**
	 * Run the void clean() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testClean_4()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		fixture.clean();

		// add additional test code here
	}

	/**
	 * Run the void clean() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testClean_5()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		fixture.clean();

		// add additional test code here
	}

	/**
	 * Run the void clean() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testClean_6()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		fixture.clean();

		// add additional test code here
	}

	/**
	 * Run the void clean() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testClean_7()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		fixture.clean();

		// add additional test code here
	}

	/**
	 * Run the void clean() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testClean_8()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		fixture.clean();

		// add additional test code here
	}

	/**
	 * Run the Object[] getArguments() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetArguments_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		Object[] result = fixture.getArguments();

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the Object[] getArguments() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetArguments_2()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		Object[] result = fixture.getArguments();

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the Object[] getArguments() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetArguments_3()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		Object[] result = fixture.getArguments();

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the Object[] getArguments() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetArguments_4()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		Object[] result = fixture.getArguments();

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the Object[] getArguments() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetArguments_5()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		Object[] result = fixture.getArguments();

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the Object[] getArguments() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetArguments_6()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		Object[] result = fixture.getArguments();

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the Object[] getArguments() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetArguments_7()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		Object[] result = fixture.getArguments();

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the Object[] getArguments() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetArguments_8()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		Object[] result = fixture.getArguments();

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the String getClassToExecute() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetClassToExecute_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		String result = fixture.getClassToExecute();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the Map<String, byte[]> getDependencies() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetDependencies_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		Map<String, byte[]> result = fixture.getDependencies();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the File getFileFromResult() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetFileFromResult_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		File result = fixture.getFileFromResult();

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the File getFileFromResult() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetFileFromResult_2()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		File result = fixture.getFileFromResult();

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the File getFileFromResult() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetFileFromResult_3()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		File result = fixture.getFileFromResult();

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the File getFileFromResult() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetFileFromResult_4()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		File result = fixture.getFileFromResult();

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the File getFileFromResult() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetFileFromResult_5()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		File result = fixture.getFileFromResult();

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the File getFileFromResult() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetFileFromResult_6()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		File result = fixture.getFileFromResult();

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the File getFileFromResult() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetFileFromResult_7()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		File result = fixture.getFileFromResult();

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the String getFinalTime() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetFinalTime_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		String result = fixture.getFinalTime();

		// add additional test code here
		assertEquals("not finished yet", result);
	}

	/**
	 * Run the String getGroup() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetGroup_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		String result = fixture.getGroup();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getInitialTime() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetInitialTime_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		String result = fixture.getInitialTime();

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the String getMethodToExecute() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetMethodToExecute_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		String result = fixture.getMethodToExecute();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getOwner() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetOwner_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		String result = fixture.getOwner();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the Object getResult() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetResult_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		Object result = fixture.getResult();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Date getSchedule() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetSchedule_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		Date result = fixture.getSchedule();

		// add additional test code here
		assertNotNull(result);
		assertEquals(DateFormat.getInstance().format(new Date(1378848382969L)), DateFormat.getInstance().format(result));
		assertEquals(1378848382969L, result.getTime());
	}

	/**
	 * Run the byte[] getTask() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetTask_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		byte[] result = fixture.getTask();

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the Long getTaskID() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetTaskID_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		Long result = fixture.getTaskID();

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
	 * Run the String getTaskName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetTaskName_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		String result = fixture.getTaskName();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the CollATicket getTicket() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetTicket_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		CollATicket result = fixture.getTicket();

		// add additional test code here
		assertNotNull(result);
		assertEquals("", result.getHostIPAddress());
		assertEquals(new Integer(-1), result.getHostPort());
		assertEquals(new Integer(-1), result.getTicket());
	}

	/**
	 * Run the String getTotalTime() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetTotalTime_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		String result = fixture.getTotalTime();

		// add additional test code here
		assertEquals("not finished", result);
	}

	/**
	 * Run the Long getTotalTimeInNanoS() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetTotalTimeInNanoS_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

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
	 * Run the boolean hasSchedule() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testHasSchedule_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule((Date) null);
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		boolean result = fixture.hasSchedule();

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean hasSchedule() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testHasSchedule_2()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		boolean result = fixture.hasSchedule();

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean hasTicket() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testHasTicket_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		boolean result = fixture.hasTicket();

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean isDistributed() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testIsDistributed_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		boolean result = fixture.isDistributed();

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean isFinished() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testIsFinished_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		boolean result = fixture.isFinished();

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean removeTicket() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testRemoveTicket_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		boolean result = fixture.removeTicket();

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean removeTicket() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testRemoveTicket_2()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		boolean result = fixture.removeTicket();

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the void setClassToExecute(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetClassToExecute_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		String className = "";

		fixture.setClassToExecute(className);

		// add additional test code here
	}

	/**
	 * Run the void setDistributedMode(Boolean) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetDistributedMode_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		Boolean isDisbributed = new Boolean(true);

		fixture.setDistributedMode(isDisbributed);

		// add additional test code here
	}

	/**
	 * Run the void setFinished() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetFinished_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		fixture.setFinished();

		// add additional test code here
	}

	/**
	 * Run the void setFinished() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetFinished_2()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		fixture.setFinished();

		// add additional test code here
	}

	/**
	 * Run the void setGroup(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetGroup_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		String group = "";

		fixture.setGroup(group);

		// add additional test code here
	}

	/**
	 * Run the void setMethodToExecute(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetMethodToExecute_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		String methodName = "";

		fixture.setMethodToExecute(methodName);

		// add additional test code here
	}

	/**
	 * Run the void setOwner(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetOwner_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		String name = "";

		fixture.setOwner(name);

		// add additional test code here
	}

	/**
	 * Run the void setResult(JCL_result) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetResult_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		JCL_result jclr = new JCL_resultImpl();

		fixture.setResult(jclr);

		// add additional test code here
	}

	/**
	 * Run the void setResult(JCL_result) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetResult_2()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		JCL_result jclr = new JCL_resultImpl();

		fixture.setResult(jclr);

		// add additional test code here
	}

	/**
	 * Run the void setResult(JCL_result) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetResult_3()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		JCL_result jclr = new JCL_resultImpl();

		fixture.setResult(jclr);

		// add additional test code here
	}

	/**
	 * Run the void setResult(JCL_result) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetResult_4()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		JCL_result jclr = new JCL_resultImpl();

		fixture.setResult(jclr);

		// add additional test code here
	}

	/**
	 * Run the void setResult(JCL_result) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetResult_5()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		JCL_result jclr = new JCL_resultImpl();

		fixture.setResult(jclr);

		// add additional test code here
	}

	/**
	 * Run the void setResult(JCL_result) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetResult_6()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		JCL_result jclr = new JCL_resultImpl();

		fixture.setResult(jclr);

		// add additional test code here
	}

	/**
	 * Run the void setResult(JCL_result) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetResult_7()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		JCL_result jclr = new JCL_resultImpl();

		fixture.setResult(jclr);

		// add additional test code here
	}

	/**
	 * Run the void setResult(JCL_result) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetResult_8()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		JCL_result jclr = null;

		fixture.setResult(jclr);

		// add additional test code here
	}

	/**
	 * Run the void setSchedule(Date) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetSchedule_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		Date date = new Date();

		fixture.setSchedule(date);

		// add additional test code here
	}

	/**
	 * Run the void setStarted() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetStarted_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		fixture.setStarted();

		// add additional test code here
	}

	/**
	 * Run the void setStarted() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetStarted_2()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());

		fixture.setStarted();

		// add additional test code here
	}

	/**
	 * Run the void setTask(File) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testSetTask_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		File taskFile = new File("");

		fixture.setTask(taskFile);

		// add additional test code here
	}

	/**
	 * Run the void setTask(File) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testSetTask_2()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		File taskFile = new File("");

		fixture.setTask(taskFile);

		// add additional test code here
	}

	/**
	 * Run the void setTask(File) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testSetTask_3()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		File taskFile = new File("");

		fixture.setTask(taskFile);

		// add additional test code here
	}

	/**
	 * Run the void setTask(File) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testSetTask_4()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		File taskFile = new File("");

		fixture.setTask(taskFile);

		// add additional test code here
	}

	/**
	 * Run the void setTask(File) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testSetTask_5()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		File taskFile = new File("");

		fixture.setTask(taskFile);

		// add additional test code here
	}

	/**
	 * Run the void setTaskID(Long) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetTaskID_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		Long id = new Long(1L);

		fixture.setTaskID(id);

		// add additional test code here
	}

	/**
	 * Run the void setTaskName(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetTaskName_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		String name = "";

		fixture.setTaskName(name);

		// add additional test code here
	}

	/**
	 * Run the void setTicket(CollATicket) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSetTicket_1()
		throws Exception {
		Task fixture = new Task();
		fixture.setClassToExecute("");
		fixture.setMethodToExecute("");
		fixture.setTaskName("");
		fixture.setDistributedMode(new Boolean(true));
		fixture.setTaskID(new Long(1L));
		fixture.setGroup("");
		fixture.setSchedule(new Date());
		fixture.setOwner("");
		fixture.setTicket(new Ticket());
		CollATicket ticket = new Ticket();

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
		new org.junit.runner.JUnitCore().run(TaskTest.class);
	}
}