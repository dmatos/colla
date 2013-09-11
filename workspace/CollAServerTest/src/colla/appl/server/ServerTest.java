package colla.appl.server;

import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import colla.kernel.impl.User;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import colla.kernel.impl.Group;
import colla.kernel.api.CollAHost;
import colla.kernel.util.TimeAndDate;
import java.util.Observer;
import java.util.Set;
import colla.appl.server.CLI.ComandLineInterface;
import java.util.concurrent.ConcurrentHashMap;
import colla.kernel.messages.toClient.TaskResultMsg;
import colla.kernel.impl.Host;
import colla.kernel.api.CollAGroup;
import colla.kernel.api.CollAUser;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>ServerTest</code> contains tests for the class <code>{@link Server}</code>.
 *
 * @generatedBy CodePro at 10/09/13 19:40
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class ServerTest {
	/**
	 * Run the Server(int,int) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testServer_1()
		throws Exception {
		int portN = 1;
		int timeOut = 1;

		Server result = new Server(portN, timeOut);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Server(int,int) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testServer_2()
		throws Exception {
		int portN = 1;
		int timeOut = 1;

		Server result = new Server(portN, timeOut);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Server(int,int) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testServer_3()
		throws Exception {
		int portN = 1;
		int timeOut = 1;

		Server result = new Server(portN, timeOut);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Server(int,int) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testServer_4()
		throws Exception {
		int portN = 1;
		int timeOut = 1;

		Server result = new Server(portN, timeOut);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Server(int,int) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testServer_5()
		throws Exception {
		int portN = 1;
		int timeOut = 1;

		Server result = new Server(portN, timeOut);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Server(int,int) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testServer_6()
		throws Exception {
		int portN = 1;
		int timeOut = 1;

		Server result = new Server(portN, timeOut);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Server(int,int) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test(expected = java.awt.HeadlessException.class)
	public void testServer_7()
		throws Exception {
		int portN = 1;
		int timeOut = 1;

		Server result = new Server(portN, timeOut);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the void disconnectAllClients() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testDisconnectAllClients_1()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.disconnectAllClients();

		// add additional test code here
	}

	/**
	 * Run the void disconnectAllClients() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testDisconnectAllClients_2()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = new Long(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.disconnectAllClients();

		// add additional test code here
	}

	/**
	 * Run the void disconnectAllClients() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testDisconnectAllClients_3()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = new Long(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.disconnectAllClients();

		// add additional test code here
	}

	/**
	 * Run the void disconnectAllClients() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testDisconnectAllClients_4()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = new Long(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.disconnectAllClients();

		// add additional test code here
	}

	/**
	 * Run the void disconnectAllClients() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testDisconnectAllClients_5()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = new Long(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.disconnectAllClients();

		// add additional test code here
	}

	/**
	 * Run the void disconnectAllClients() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testDisconnectAllClients_6()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = new Long(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.disconnectAllClients();

		// add additional test code here
	}

	/**
	 * Run the void disconnectAllClients() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testDisconnectAllClients_7()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = new Long(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.disconnectAllClients();

		// add additional test code here
	}

	/**
	 * Run the void disconnectAllClients() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testDisconnectAllClients_8()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = new Long(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.disconnectAllClients();

		// add additional test code here
	}

	/**
	 * Run the void disconnectAllClients() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testDisconnectAllClients_9()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = new Long(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.disconnectAllClients();

		// add additional test code here
	}

	/**
	 * Run the void displayMessage(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testDisplayMessage_1()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		String message = "";

		fixture.displayMessage(message);

		// add additional test code here
	}

	/**
	 * Run the long generateSessionID() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testGenerateSessionID_1()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		long result = fixture.generateSessionID();

		// add additional test code here
		assertEquals(0L, result);
	}

	/**
	 * Run the Long generateTaskID() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testGenerateTaskID_1()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		Long result = fixture.generateTaskID();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Set<String> getAllGroups() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testGetAllGroups_1()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		Set<String> result = fixture.getAllGroups();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the CollAGroup getGroup(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testGetGroup_1()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		String groupName = "";

		CollAGroup result = fixture.getGroup(groupName);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Map<String, Socket> getMappedConnections() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testGetMappedConnections_1()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		Map<String, Socket> result = fixture.getMappedConnections();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the int getPortNumber() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testGetPortNumber_1()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		int result = fixture.getPortNumber();

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the int getTimeoutValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testGetTimeoutValue_1()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		int result = fixture.getTimeoutValue();

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the CollAUser getUser(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testGetUser_1()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		String userName = "";

		CollAUser result = fixture.getUser(userName);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the String getUserPassword(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testGetUserPassword_1()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		String userName = "";

		String result = fixture.getUserPassword(userName);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Set<String> getUsersSet() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testGetUsersSet_1()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		Set<String> result = fixture.getUsersSet();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Set<String> getUsersSet() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testGetUsersSet_2()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = new Long(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = new Long(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		Set<String> result = fixture.getUsersSet();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the void run() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testRun_1()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = new Long(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = new Long(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.run();

		// add additional test code here
	}

	/**
	 * Run the void run() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testRun_2()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = new Long(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = new Long(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.run();

		// add additional test code here
	}

	/**
	 * Run the void run() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testRun_3()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.run();

		// add additional test code here
	}

	/**
	 * Run the void setUserPassword(String,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testSetUserPassword_1()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		String userName = "";
		String password = "";

		fixture.setUserPassword(userName, password);

		// add additional test code here
	}

	/**
	 * Run the void setUserPassword(String,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testSetUserPassword_2()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		String userName = "";
		String password = "";

		fixture.setUserPassword(userName, password);

		// add additional test code here
	}

	/**
	 * Run the void storeAllServerData() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testStoreAllServerData_1()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.storeAllServerData();

		// add additional test code here
	}

	/**
	 * Run the void storeAllServerData() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testStoreAllServerData_2()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.storeAllServerData();

		// add additional test code here
	}

	/**
	 * Run the void storeAllServerData() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testStoreAllServerData_3()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.storeAllServerData();

		// add additional test code here
	}

	/**
	 * Run the void storeAllServerData() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testStoreAllServerData_4()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.storeAllServerData();

		// add additional test code here
	}

	/**
	 * Run the void storeAllServerData() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test(expected = java.io.FileNotFoundException.class)
	public void testStoreAllServerData_5()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.storeAllServerData();

		// add additional test code here
	}

	/**
	 * Run the void storeAllServerData() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test(expected = java.io.IOException.class)
	public void testStoreAllServerData_6()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.storeAllServerData();

		// add additional test code here
	}

	/**
	 * Run the void storeAllServerData() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test(expected = java.io.IOException.class)
	public void testStoreAllServerData_7()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.storeAllServerData();

		// add additional test code here
	}

	/**
	 * Run the void storeAllServerData() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test(expected = java.io.IOException.class)
	public void testStoreAllServerData_8()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.storeAllServerData();

		// add additional test code here
	}

	/**
	 * Run the void storeAllServerData() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test(expected = java.io.IOException.class)
	public void testStoreAllServerData_9()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.storeAllServerData();

		// add additional test code here
	}

	/**
	 * Run the void storeAllServerData() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test(expected = java.io.IOException.class)
	public void testStoreAllServerData_10()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.storeAllServerData();

		// add additional test code here
	}

	/**
	 * Run the void storeAllServerData() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test(expected = java.io.IOException.class)
	public void testStoreAllServerData_11()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.storeAllServerData();

		// add additional test code here
	}

	/**
	 * Run the void storeAllServerData() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test(expected = java.io.IOException.class)
	public void testStoreAllServerData_12()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.storeAllServerData();

		// add additional test code here
	}

	/**
	 * Run the void storeAllServerData() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test(expected = java.io.IOException.class)
	public void testStoreAllServerData_13()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.storeAllServerData();

		// add additional test code here
	}

	/**
	 * Run the void storeAllServerData() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test(expected = java.io.IOException.class)
	public void testStoreAllServerData_14()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.storeAllServerData();

		// add additional test code here
	}

	/**
	 * Run the void storeAllServerData() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test(expected = java.io.IOException.class)
	public void testStoreAllServerData_15()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.storeAllServerData();

		// add additional test code here
	}

	/**
	 * Run the void storeAllServerData() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test(expected = java.io.IOException.class)
	public void testStoreAllServerData_16()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();

		fixture.storeAllServerData();

		// add additional test code here
	}

	/**
	 * Run the void updateGroup(String,CollAGroup) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testUpdateGroup_1()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		String groupName = "";
		CollAGroup group = new Group("");

		fixture.updateGroup(groupName, group);

		// add additional test code here
	}

	/**
	 * Run the void updateGroup(String,CollAGroup) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testUpdateGroup_2()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		String groupName = "";
		CollAGroup group = new Group("");

		fixture.updateGroup(groupName, group);

		// add additional test code here
	}

	/**
	 * Run the void updateHost(CollAHost) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testUpdateHost_1()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		CollAHost host = new Host();

		fixture.updateHost(host);

		// add additional test code here
	}

	/**
	 * Run the void updateHost(CollAHost) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testUpdateHost_2()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		CollAHost host = new Host();

		fixture.updateHost(host);

		// add additional test code here
	}

	/**
	 * Run the void updateHost(CollAHost) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testUpdateHost_3()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		CollAHost host = new Host();

		fixture.updateHost(host);

		// add additional test code here
	}

	/**
	 * Run the void updateHost(CollAHost) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testUpdateHost_4()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		CollAHost host = new Host();

		fixture.updateHost(host);

		// add additional test code here
	}

	/**
	 * Run the void updateHost(CollAHost) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testUpdateHost_5()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		CollAHost host = new Host();

		fixture.updateHost(host);

		// add additional test code here
	}

	/**
	 * Run the void updateHost(CollAHost) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testUpdateHost_6()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		CollAHost host = new Host();

		fixture.updateHost(host);

		// add additional test code here
	}

	/**
	 * Run the void updateHost(CollAHost) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testUpdateHost_7()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		CollAHost host = new Host();

		fixture.updateHost(host);

		// add additional test code here
	}

	/**
	 * Run the void updateHost(CollAHost) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testUpdateHost_8()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		CollAHost host = new Host();

		fixture.updateHost(host);

		// add additional test code here
	}

	/**
	 * Run the void updateHost(CollAHost) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testUpdateHost_9()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		CollAHost host = new Host();

		fixture.updateHost(host);

		// add additional test code here
	}

	/**
	 * Run the void updateHost(CollAHost) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testUpdateHost_10()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		CollAHost host = new Host();

		fixture.updateHost(host);

		// add additional test code here
	}

	/**
	 * Run the void updateHost(CollAHost) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testUpdateHost_11()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		CollAHost host = new Host();

		fixture.updateHost(host);

		// add additional test code here
	}

	/**
	 * Run the void updateHost(CollAHost) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testUpdateHost_12()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		CollAHost host = new Host();

		fixture.updateHost(host);

		// add additional test code here
	}

	/**
	 * Run the void updateHost(CollAHost) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testUpdateHost_13()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		CollAHost host = new Host();

		fixture.updateHost(host);

		// add additional test code here
	}

	/**
	 * Run the void updateHost(CollAHost) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testUpdateHost_14()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		CollAHost host = new Host();

		fixture.updateHost(host);

		// add additional test code here
	}

	/**
	 * Run the void updateHost(CollAHost) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testUpdateHost_15()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		CollAHost host = new Host();

		fixture.updateHost(host);

		// add additional test code here
	}

	/**
	 * Run the void updateHost(CollAHost) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testUpdateHost_16()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		CollAHost host = new Host();

		fixture.updateHost(host);

		// add additional test code here
	}

	/**
	 * Run the void updateUser(CollAUser) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testUpdateUser_1()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		CollAUser usr = new User();

		fixture.updateUser(usr);

		// add additional test code here
	}

	/**
	 * Run the void updateUser(CollAUser) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testUpdateUser_2()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		CollAUser usr = new User();

		fixture.updateUser(usr);

		// add additional test code here
	}

	/**
	 * Run the void updateUser(CollAUser) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testUpdateUser_3()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		CollAUser usr = new User();

		fixture.updateUser(usr);

		// add additional test code here
	}

	/**
	 * Run the void updateUser(CollAUser) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	@Test
	public void testUpdateUser_4()
		throws Exception {
		Server fixture = new Server(1, 1);
		fixture.currentHost = 1;
		fixture.dateAndTime = new TimeAndDate();
		fixture.sessions = Long.valueOf(1L);
		fixture.serverSocket = new ServerSocket();
		fixture.addObserver(new ComandLineInterface());
		fixture.connectionsMap = new ConcurrentHashMap();
		fixture.taskIDs = Long.valueOf(1L);
		fixture.resultsMap = new ConcurrentHashMap();
		CollAUser usr = new User();

		fixture.updateUser(usr);

		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 10/09/13 19:40
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
	 * @generatedBy CodePro at 10/09/13 19:40
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
	 * @generatedBy CodePro at 10/09/13 19:40
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ServerTest.class);
	}
}