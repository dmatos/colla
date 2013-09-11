package colla.kernel.messages.toServer;

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
	GetHostMsgTest.class,
	HostDisconnectMsgTest.class,
	TransmitStartNotificationTest.class,
	ACKTest.class,
	UpdateUserMsgTest.class,
	TransmitResultMsgTest.class,
	RetrieveStoredResultsMsgTest.class,
	DeveloperViewerLoginMsgTest.class,
	AskToCreateGroupMsgTest.class,
	PongTest.class,
	GetAvailableHostsMsgTest.class,
	TransmitTaskMsgTest.class,
	TransmitChatMsgTest.class,
	HostLoginMsgTest.class,
	UpdateGroupsMsgTest.class,
	GetGroupsMsgTest.class,
	MapConnectionTest.class,
	JoinAGroupMsgTest.class,
	HostUpdateMsgTest.class,
	HostRegisterMsgTest.class,
	CreateGroupMsgTest.class,
	ClientSignUpMsgTest.class,
	DeveloperViewerDisconnectMsgTest.class,
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
