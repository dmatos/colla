package colla.appl.host_viewer.controller;

import java.io.File;
import java.util.HashMap;
import colla.kernel.impl.Task;
import org.junit.*;
import interfaces.kernel.JCL_result;
import colla.kernel.api.GenericResource;
import colla.kernel.api.CollAUser;
import colla.kernel.api.CollATask;
import colla.kernel.messages.toServer.ACK;
import colla.kernel.api.CollAMessage;
import colla.kernel.impl.User;
import static org.junit.Assert.*;

/**
 * The class <code>CollAConsumerTest</code> contains tests for the class <code>{@link CollAConsumer}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:40
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class CollAConsumerTest {
	/**
	 * Run the CollAConsumer(GenericResource<S>,HostViewerMicroServer) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testCollAConsumer_1()
		throws Exception {
		GenericResource<CollAMessage> re = new GenericResource();
		HostViewerMicroServer hostMicroServer = new HostViewerMicroServer("", 1);

		CollAConsumer result = new CollAConsumer(re, hostMicroServer);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertNotNull(result);
	}

	/**
	 * Run the boolean deleteDir(File) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testDeleteDir_1()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		File dir = new File("");

		boolean result = fixture.deleteDir(dir);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertTrue(result);
	}

	/**
	 * Run the boolean deleteDir(File) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testDeleteDir_2()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		File dir = new File("");

		boolean result = fixture.deleteDir(dir);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertTrue(result);
	}

	/**
	 * Run the boolean deleteDir(File) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testDeleteDir_3()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		File dir = new File("");

		boolean result = fixture.deleteDir(dir);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertTrue(result);
	}

	/**
	 * Run the boolean deleteDir(File) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testDeleteDir_4()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		File dir = new File("");

		boolean result = fixture.deleteDir(dir);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertTrue(result);
	}

	/**
	 * Run the void doSomething(S) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testDoSomething_1()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));

		fixture.doSomething(new ACK());

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void doSomething(S) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testDoSomething_2()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));

		fixture.doSomething(new ACK());

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void doSomething(S) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testDoSomething_3()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));

		fixture.doSomething(new ACK());

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void doSomething(S) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testDoSomething_4()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));

		fixture.doSomething(new ACK());

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void doSomething(S) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testDoSomething_5()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));

		fixture.doSomething(new ACK());

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void doSomething(S) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testDoSomething_6()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));

		fixture.doSomething(new ACK());

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void doSomething(S) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testDoSomething_7()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));

		fixture.doSomething(new ACK());

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void doSomething(S) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testDoSomething_8()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));

		fixture.doSomething(new ACK());

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void doSomething(S) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testDoSomething_9()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));

		fixture.doSomething(new ACK());

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void doSomething(S) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testDoSomething_10()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));

		fixture.doSomething(new ACK());

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void doSomething(S) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testDoSomething_11()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));

		fixture.doSomething(new ACK());

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the JCL_result executeTask(CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testExecuteTask_1()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		CollATask cTask = new Task();

		JCL_result result = fixture.executeTask(cTask);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertNotNull(result);
	}

	/**
	 * Run the JCL_result executeTask(CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testExecuteTask_2()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		CollATask cTask = new Task();

		JCL_result result = fixture.executeTask(cTask);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertNotNull(result);
	}

	/**
	 * Run the JCL_result executeTask(CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testExecuteTask_3()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		CollATask cTask = new Task();

		JCL_result result = fixture.executeTask(cTask);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertNotNull(result);
	}

	/**
	 * Run the JCL_result executeTask(CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testExecuteTask_4()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		CollATask cTask = new Task();

		JCL_result result = fixture.executeTask(cTask);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertNotNull(result);
	}

	/**
	 * Run the JCL_result executeTask(CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testExecuteTask_5()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		CollATask cTask = new Task();

		JCL_result result = fixture.executeTask(cTask);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertNotNull(result);
	}

	/**
	 * Run the JCL_result executeTask(CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testExecuteTask_6()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		CollATask cTask = new Task();

		JCL_result result = fixture.executeTask(cTask);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertNotNull(result);
	}

	/**
	 * Run the JCL_result executeTask(CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testExecuteTask_7()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		CollATask cTask = new Task();

		JCL_result result = fixture.executeTask(cTask);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertNotNull(result);
	}

	/**
	 * Run the JCL_result executeTask(CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testExecuteTask_8()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		CollATask cTask = new Task();

		JCL_result result = fixture.executeTask(cTask);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertNotNull(result);
	}

	/**
	 * Run the JCL_result executeTask(CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testExecuteTask_9()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		CollATask cTask = new Task();

		JCL_result result = fixture.executeTask(cTask);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertNotNull(result);
	}

	/**
	 * Run the JCL_result executeTask(CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testExecuteTask_10()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		CollATask cTask = new Task();

		JCL_result result = fixture.executeTask(cTask);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertNotNull(result);
	}

	/**
	 * Run the void sendResultBack(String,HashMap<String,CollAUser>,CollATask,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBack_1()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		HashMap<String, CollAUser> group = new HashMap();
		CollATask task = new Task();
		String taskName = "";

		fixture.sendResultBack(groupName, group, task, taskName);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBack(String,HashMap<String,CollAUser>,CollATask,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBack_2()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		HashMap<String, CollAUser> group = new HashMap();
		CollATask task = new Task();
		String taskName = "";

		fixture.sendResultBack(groupName, group, task, taskName);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBack(String,HashMap<String,CollAUser>,CollATask,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBack_3()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		HashMap<String, CollAUser> group = new HashMap();
		CollATask task = new Task();
		String taskName = "";

		fixture.sendResultBack(groupName, group, task, taskName);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToInvalidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToInvalidIPClient_1()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToInvalidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToInvalidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToInvalidIPClient_2()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToInvalidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToInvalidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToInvalidIPClient_3()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToInvalidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToInvalidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToInvalidIPClient_4()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToInvalidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToInvalidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToInvalidIPClient_5()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToInvalidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToInvalidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToInvalidIPClient_6()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToInvalidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToInvalidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToInvalidIPClient_7()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToInvalidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToInvalidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToInvalidIPClient_8()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToInvalidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToInvalidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToInvalidIPClient_9()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToInvalidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToInvalidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToInvalidIPClient_10()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToInvalidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToInvalidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToInvalidIPClient_11()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToInvalidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToInvalidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToInvalidIPClient_12()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToInvalidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToValidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToValidIPClient_1()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToValidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToValidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToValidIPClient_2()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToValidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToValidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToValidIPClient_3()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToValidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToValidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToValidIPClient_4()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToValidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToValidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToValidIPClient_5()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToValidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToValidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToValidIPClient_6()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToValidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToValidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToValidIPClient_7()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToValidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToValidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToValidIPClient_8()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToValidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToValidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToValidIPClient_9()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToValidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToValidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToValidIPClient_10()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToValidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToValidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToValidIPClient_11()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToValidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToValidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToValidIPClient_12()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToValidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToValidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToValidIPClient_13()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToValidIPClient(groupName, client, task);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
	}

	/**
	 * Run the void sendResultBackToValidIPClient(String,CollAUser,CollATask) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testSendResultBackToValidIPClient_14()
		throws Exception {
		CollAConsumer fixture = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));
		String groupName = "";
		CollAUser client = new User();
		CollATask task = new Task();

		fixture.sendResultBackToValidIPClient(groupName, client, task);

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
	 * @generatedBy CodePro at 10/09/13 18:40
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
	 * @generatedBy CodePro at 10/09/13 18:40
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
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(CollAConsumerTest.class);
	}
}