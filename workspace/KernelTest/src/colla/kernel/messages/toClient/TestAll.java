package colla.kernel.messages.toClient;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * The class <code>TestAll</code> builds a suite that can be used to run all
 * of the tests within its package as well as within any subpackages of its
 * package.
 *
 * @generatedBy CodePro at 10/09/13 18:28
 * @author joaovq
 * @version $Revision: 1.0 $
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	ACKTest.class,
	JoinAGroupAnswerMsgTest.class,
	SignUpAnswerMsgTest.class,
	DeveloperViewerLoginAnswerMsgTest.class,
	ChatDirectMessageMsgTest.class,
	TaskResultMsgTest.class,
	PingTest.class,
	NotifyTaskStartedTest.class,
	SendOwnedHostsMsgTest.class,
	GetGroupsAnswerMsgTest.class,
	PingClientMsgTest.class,
	SendStoredResultsMsgTest.class,
	CreateGroupAnswerMsgTest.class,
	SendAvailableHostsMsgTest.class,
})
public class TestAll {

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 10/09/13 18:28
	 */
	public static void main(String[] args) {
		JUnitCore.runClasses(new Class[] { TestAll.class });
	}
}
