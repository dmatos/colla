package colla.appl.host_viewer.controller;

import org.junit.*;
import colla.kernel.api.GenericResource;
import colla.kernel.impl.Task;
import colla.kernel.api.CollATask;
import colla.kernel.messages.toHost.TaskMessage;
import colla.kernel.api.CollAMessage;
import colla.kernel.api.GenericConsumer;
import static org.junit.Assert.*;

/**
 * The class <code>HostViewerMicroServerTest</code> contains tests for the class <code>{@link HostViewerMicroServer}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:41
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class HostViewerMicroServerTest {
	/**
	 * Run the HostViewerMicroServer(String,int) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:41
	 */
	@Test
	public void testHostViewerMicroServer_1()
		throws Exception {
		String serverIPaddress = "";
		int serverPortNumber = 1;

		HostViewerMicroServer result = new HostViewerMicroServer(serverIPaddress, serverPortNumber);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertNotNull(result);
	}

	/**
	 * Run the boolean cancelTask(long) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:41
	 */
	@Test
	public void testCancelTask_1()
		throws Exception {
		HostViewerMicroServer fixture = new HostViewerMicroServer("", 1);
		fixture.serverR = new GenericResource();
		fixture.initialTime = 1L;
		fixture.port = 1;
		fixture.serverThreads = new GenericConsumer[] {};
		fixture.isStopped = true;
		long taskID = 1L;

		boolean result = fixture.cancelTask(taskID);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertTrue(result);
	}

	/**
	 * Run the boolean cancelTask(long) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:41
	 */
	@Test
	public void testCancelTask_2()
		throws Exception {
		HostViewerMicroServer fixture = new HostViewerMicroServer("", 1);
		fixture.serverR = new GenericResource();
		fixture.initialTime = 1L;
		fixture.port = 1;
		fixture.serverThreads = new GenericConsumer[] {};
		fixture.isStopped = true;
		long taskID = 1L;

		boolean result = fixture.cancelTask(taskID);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertTrue(result);
	}

	/**
	 * Run the boolean cancelTask(long) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:41
	 */
	@Test
	public void testCancelTask_3()
		throws Exception {
		HostViewerMicroServer fixture = new HostViewerMicroServer("", 1);
		fixture.serverR = new GenericResource();
		fixture.initialTime = 1L;
		fixture.port = 1;
		fixture.serverThreads = new GenericConsumer[] {};
		fixture.isStopped = true;
		long taskID = 1L;

		boolean result = fixture.cancelTask(taskID);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertTrue(result);
	}

	/**
	 * Run the String getServerIPaddress() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:41
	 */
	@Test
	public void testGetServerIPaddress_1()
		throws Exception {
		HostViewerMicroServer fixture = new HostViewerMicroServer("", 1);
		fixture.serverR = new GenericResource();
		fixture.initialTime = 1L;
		fixture.port = 1;
		fixture.serverThreads = new GenericConsumer[] {};
		fixture.isStopped = true;

		String result = fixture.getServerIPaddress();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertNotNull(result);
	}

	/**
	 * Run the int getServerPortNumber() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:41
	 */
	@Test
	public void testGetServerPortNumber_1()
		throws Exception {
		HostViewerMicroServer fixture = new HostViewerMicroServer("", 1);
		fixture.serverR = new GenericResource();
		fixture.initialTime = 1L;
		fixture.port = 1;
		fixture.serverThreads = new GenericConsumer[] {};
		fixture.isStopped = true;

		int result = fixture.getServerPortNumber();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertEquals(0, result);
	}

	/**
	 * Run the void run() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:41
	 */
	@Test
	public void testRun_1()
		throws Exception {
		HostViewerMicroServer fixture = new HostViewerMicroServer("", 1);
		fixture.serverR = new GenericResource();
		fixture.initialTime = 1L;
		fixture.port = 1;
		fixture.serverThreads = new GenericConsumer[] {};
		fixture.isStopped = true;

		fixture.run();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void scheduleTask(TaskMessage) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:41
	 */
	@Test
	public void testScheduleTask_1()
		throws Exception {
		HostViewerMicroServer fixture = new HostViewerMicroServer("", 1);
		fixture.serverR = new GenericResource();
		fixture.initialTime = 1L;
		fixture.port = 1;
		fixture.serverThreads = new GenericConsumer[] {};
		fixture.isStopped = true;
		TaskMessage collAMessage = new TaskMessage();
		collAMessage.setTask(new Task());

		fixture.scheduleTask(collAMessage);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void shutdown() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:41
	 */
	@Test
	public void testShutdown_1()
		throws Exception {
		HostViewerMicroServer fixture = new HostViewerMicroServer("", 1);
		fixture.serverR = new GenericResource();
		fixture.initialTime = 1L;
		fixture.port = 1;
		fixture.serverThreads = new GenericConsumer[] {};
		fixture.isStopped = true;

		fixture.shutdown();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void shutdown() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:41
	 */
	@Test
	public void testShutdown_2()
		throws Exception {
		HostViewerMicroServer fixture = new HostViewerMicroServer("", 1);
		fixture.serverR = new GenericResource();
		fixture.initialTime = 1L;
		fixture.port = 1;
		fixture.serverThreads = new GenericConsumer[] {};
		fixture.isStopped = true;

		fixture.shutdown();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void shutdown() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:41
	 */
	@Test
	public void testShutdown_3()
		throws Exception {
		HostViewerMicroServer fixture = new HostViewerMicroServer("", 1);
		fixture.serverR = new GenericResource();
		fixture.initialTime = 1L;
		fixture.port = 1;
		fixture.serverThreads = new GenericConsumer[] {};
		fixture.isStopped = true;

		fixture.shutdown();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void shutdown() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:41
	 */
	@Test
	public void testShutdown_4()
		throws Exception {
		HostViewerMicroServer fixture = new HostViewerMicroServer("", 1);
		fixture.serverR = new GenericResource();
		fixture.initialTime = 1L;
		fixture.port = 1;
		fixture.serverThreads = new GenericConsumer[] {};
		fixture.isStopped = true;

		fixture.shutdown();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void shutdown() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:41
	 */
	@Test
	public void testShutdown_5()
		throws Exception {
		HostViewerMicroServer fixture = new HostViewerMicroServer("", 1);
		fixture.serverR = new GenericResource();
		fixture.initialTime = 1L;
		fixture.port = 1;
		fixture.serverThreads = new GenericConsumer[] {};
		fixture.isStopped = true;

		fixture.shutdown();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void shutdown() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:41
	 */
	@Test
	public void testShutdown_6()
		throws Exception {
		HostViewerMicroServer fixture = new HostViewerMicroServer("", 1);
		fixture.serverR = new GenericResource();
		fixture.initialTime = 1L;
		fixture.port = 1;
		fixture.serverThreads = new GenericConsumer[] {};
		fixture.isStopped = true;

		fixture.shutdown();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void shutdown() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:41
	 */
	@Test
	public void testShutdown_7()
		throws Exception {
		HostViewerMicroServer fixture = new HostViewerMicroServer("", 1);
		fixture.serverR = new GenericResource();
		fixture.initialTime = 1L;
		fixture.port = 1;
		fixture.serverThreads = new GenericConsumer[] {};
		fixture.isStopped = true;

		fixture.shutdown();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void shutdown() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:41
	 */
	@Test
	public void testShutdown_8()
		throws Exception {
		HostViewerMicroServer fixture = new HostViewerMicroServer("", 1);
		fixture.serverR = new GenericResource();
		fixture.initialTime = 1L;
		fixture.port = 1;
		fixture.serverThreads = new GenericConsumer[] {};
		fixture.isStopped = true;

		fixture.shutdown();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void shutdown() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:41
	 */
	@Test
	public void testShutdown_9()
		throws Exception {
		HostViewerMicroServer fixture = new HostViewerMicroServer("", 1);
		fixture.serverR = new GenericResource();
		fixture.initialTime = 1L;
		fixture.port = 1;
		fixture.serverThreads = new GenericConsumer[] {};
		fixture.isStopped = true;

		fixture.shutdown();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void shutdown() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:41
	 */
	@Test
	public void testShutdown_10()
		throws Exception {
		HostViewerMicroServer fixture = new HostViewerMicroServer("", 1);
		fixture.serverR = new GenericResource();
		fixture.initialTime = 1L;
		fixture.port = 1;
		fixture.serverThreads = new GenericConsumer[] {};
		fixture.isStopped = true;

		fixture.shutdown();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void shutdown() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:41
	 */
	@Test
	public void testShutdown_11()
		throws Exception {
		HostViewerMicroServer fixture = new HostViewerMicroServer("", 1);
		fixture.serverR = new GenericResource();
		fixture.initialTime = 1L;
		fixture.port = 1;
		fixture.serverThreads = new GenericConsumer[] {};
		fixture.isStopped = true;

		fixture.shutdown();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 10/09/13 18:41
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
	 * @generatedBy CodePro at 10/09/13 18:41
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
	 * @generatedBy CodePro at 10/09/13 18:41
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(HostViewerMicroServerTest.class);
	}
}