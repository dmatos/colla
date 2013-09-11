package colla.appl.host_viewer.controller;

import java.util.HashMap;
import colla.kernel.impl.Task;
import org.junit.*;
import interfaces.kernel.JCL_result;
import colla.kernel.api.GenericResource;
import colla.kernel.api.CollAUser;
import colla.kernel.messages.toHost.TaskMessage;
import colla.kernel.api.CollATask;
import colla.kernel.api.CollAMessage;
import colla.kernel.impl.User;
import static org.junit.Assert.*;

/**
 * The class <code>DistributedTaskExecutorTest</code> contains tests for the class <code>{@link DistributedTaskExecutor}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:40
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class DistributedTaskExecutorTest {
	/**
	 * Run the JCL_result executeDistributedTask(CollAMessage,CollAConsumer) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testExecuteDistributedTask_1()
		throws Exception {
		DistributedTaskExecutor fixture = new DistributedTaskExecutor();
		TaskMessage collAMsg = new TaskMessage();
		collAMsg.setUser(new User());
		collAMsg.setGroup(new HashMap());
		collAMsg.setTask(new Task());
		collAMsg.setGroupName("");
		CollAConsumer consumer = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));

		JCL_result result = fixture.executeDistributedTask(collAMsg, consumer);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertNotNull(result);
	}

	/**
	 * Run the JCL_result executeDistributedTask(CollAMessage,CollAConsumer) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testExecuteDistributedTask_2()
		throws Exception {
		DistributedTaskExecutor fixture = new DistributedTaskExecutor();
		TaskMessage collAMsg = new TaskMessage();
		collAMsg.setUser(new User());
		collAMsg.setGroup(new HashMap());
		collAMsg.setTask(new Task());
		collAMsg.setGroupName("");
		CollAConsumer consumer = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));

		JCL_result result = fixture.executeDistributedTask(collAMsg, consumer);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertNotNull(result);
	}

	/**
	 * Run the JCL_result executeDistributedTask(CollAMessage,CollAConsumer) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testExecuteDistributedTask_3()
		throws Exception {
		DistributedTaskExecutor fixture = new DistributedTaskExecutor();
		TaskMessage collAMsg = new TaskMessage();
		collAMsg.setUser(new User());
		collAMsg.setGroup(new HashMap());
		collAMsg.setTask(new Task());
		collAMsg.setGroupName("");
		CollAConsumer consumer = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));

		JCL_result result = fixture.executeDistributedTask(collAMsg, consumer);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertNotNull(result);
	}

	/**
	 * Run the JCL_result executeDistributedTask(CollAMessage,CollAConsumer) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testExecuteDistributedTask_4()
		throws Exception {
		DistributedTaskExecutor fixture = new DistributedTaskExecutor();
		TaskMessage collAMsg = new TaskMessage();
		collAMsg.setUser(new User());
		collAMsg.setGroup(new HashMap());
		collAMsg.setTask(new Task());
		collAMsg.setGroupName("");
		CollAConsumer consumer = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));

		JCL_result result = fixture.executeDistributedTask(collAMsg, consumer);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertNotNull(result);
	}

	/**
	 * Run the JCL_result executeDistributedTask(CollAMessage,CollAConsumer) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testExecuteDistributedTask_5()
		throws Exception {
		DistributedTaskExecutor fixture = new DistributedTaskExecutor();
		TaskMessage collAMsg = new TaskMessage();
		collAMsg.setUser(new User());
		collAMsg.setGroup(new HashMap());
		collAMsg.setTask(new Task());
		collAMsg.setGroupName("");
		CollAConsumer consumer = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));

		JCL_result result = fixture.executeDistributedTask(collAMsg, consumer);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertNotNull(result);
	}

	/**
	 * Run the JCL_result executeDistributedTask(CollAMessage,CollAConsumer) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testExecuteDistributedTask_6()
		throws Exception {
		DistributedTaskExecutor fixture = new DistributedTaskExecutor();
		TaskMessage collAMsg = new TaskMessage();
		collAMsg.setUser(new User());
		collAMsg.setGroup(new HashMap());
		collAMsg.setTask(new Task());
		collAMsg.setGroupName("");
		CollAConsumer consumer = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));

		JCL_result result = fixture.executeDistributedTask(collAMsg, consumer);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertNotNull(result);
	}

	/**
	 * Run the JCL_result executeDistributedTask(CollAMessage,CollAConsumer) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testExecuteDistributedTask_7()
		throws Exception {
		DistributedTaskExecutor fixture = new DistributedTaskExecutor();
		TaskMessage collAMsg = new TaskMessage();
		collAMsg.setUser(new User());
		collAMsg.setGroup(new HashMap());
		collAMsg.setTask(new Task());
		collAMsg.setGroupName("");
		CollAConsumer consumer = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));

		JCL_result result = fixture.executeDistributedTask(collAMsg, consumer);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertNotNull(result);
	}

	/**
	 * Run the JCL_result executeDistributedTask(CollAMessage,CollAConsumer) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testExecuteDistributedTask_8()
		throws Exception {
		DistributedTaskExecutor fixture = new DistributedTaskExecutor();
		TaskMessage collAMsg = new TaskMessage();
		collAMsg.setUser(new User());
		collAMsg.setGroup(new HashMap());
		collAMsg.setTask(new Task());
		collAMsg.setGroupName("");
		CollAConsumer consumer = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));

		JCL_result result = fixture.executeDistributedTask(collAMsg, consumer);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertNotNull(result);
	}

	/**
	 * Run the JCL_result executeDistributedTask(CollAMessage,CollAConsumer) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testExecuteDistributedTask_9()
		throws Exception {
		DistributedTaskExecutor fixture = new DistributedTaskExecutor();
		TaskMessage collAMsg = new TaskMessage();
		collAMsg.setUser(new User());
		collAMsg.setGroup(new HashMap());
		collAMsg.setTask(new Task());
		collAMsg.setGroupName("");
		CollAConsumer consumer = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));

		JCL_result result = fixture.executeDistributedTask(collAMsg, consumer);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertNotNull(result);
	}

	/**
	 * Run the JCL_result executeDistributedTask(CollAMessage,CollAConsumer) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:40
	 */
	@Test
	public void testExecuteDistributedTask_10()
		throws Exception {
		DistributedTaskExecutor fixture = new DistributedTaskExecutor();
		TaskMessage collAMsg = new TaskMessage();
		collAMsg.setUser(new User());
		collAMsg.setGroup(new HashMap());
		collAMsg.setTask(new Task());
		collAMsg.setGroupName("");
		CollAConsumer consumer = new CollAConsumer(new GenericResource(), new HostViewerMicroServer("", 1));

		JCL_result result = fixture.executeDistributedTask(collAMsg, consumer);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.init(HostViewerMicroServer.java:48)
		//       at colla.appl.host_viewer.controller.HostViewerMicroServer.<init>(HostViewerMicroServer.java:44)
		assertNotNull(result);
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
		new org.junit.runner.JUnitCore().run(DistributedTaskExecutorTest.class);
	}
}