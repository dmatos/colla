package colla.appl.server.GUI;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>BackGroundTest</code> contains tests for the class <code>{@link BackGround}</code>.
 *
 * @generatedBy CodePro at 10/09/13 19:43
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class BackGroundTest {
	/**
	 * Run the BackGround(String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:43
	 */
	@Test
	public void testBackGround_1()
		throws Exception {
		String path = "";

		BackGround result = new BackGround(path);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NoSuchMethodException: colla.appl.server.GUI.BackGround.<init>(java.lang.String)
		//       at java.lang.Class.getConstructor0(Class.java:2800)
		//       at java.lang.Class.getDeclaredConstructor(Class.java:2043)
		//       at com.instantiations.eclipse.analysis.expression.model.InstanceCreationExpression.findConstructor(InstanceCreationExpression.java:572)
		//       at com.instantiations.eclipse.analysis.expression.model.InstanceCreationExpression.execute(InstanceCreationExpression.java:452)
		//       at com.instantiations.assist.eclipse.junit.execution.core.ExecutionRequest.execute(ExecutionRequest.java:286)
		//       at com.instantiations.assist.eclipse.junit.execution.communication.LocalExecutionClient$1.run(LocalExecutionClient.java:158)
		//       at java.lang.Thread.run(Thread.java:724)
		assertNotNull(result);
	}

	/**
	 * Run the String getPath() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:43
	 */
	@Test
	public void testGetPath_1()
		throws Exception {
		BackGround fixture = BackGround.DENSE_GREEN;

		String result = fixture.getPath();

		// add additional test code here
		assertEquals("/colla/appl/developer_viewer/GUI/images/host_fundo.jpg", result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 10/09/13 19:43
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
	 * @generatedBy CodePro at 10/09/13 19:43
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
	 * @generatedBy CodePro at 10/09/13 19:43
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BackGroundTest.class);
	}
}