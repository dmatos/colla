/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.server;

import colla.kernel.api.CollAGroup;
import colla.kernel.api.CollAHost;
import colla.kernel.api.CollAUser;
import colla.kernel.exceptions.server.NonExistentUser;
import colla.kernel.exceptions.server.ServerInitializationException;
import colla.kernel.exceptions.server.UserAlreadyExists;
import colla.kernel.impl.Group;
import colla.kernel.impl.Host;
import colla.kernel.impl.User;
import colla.kernel.messages.toClient.TaskResultMsg;
import colla.kernel.util.TimeAndDate;
import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dmatos
 */
public class ServerTest {

    private static Server instance = null;

    public ServerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            instance = Server.setupServer(9999, 15000);
        } catch (IOException ex) {
            Logger.getLogger(ServerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    @After
    public void tearDown() {
        instance.shutdown();
        deleteDir(new File("data/")); 
    }

    /**
     * Test of getInstance method, of class Server.
     */
    @Test
    public void testGetInstance() {
        Server expResult = instance;
        Server result = null;
        try {
            result = Server.getInstance();
        } catch (ServerInitializationException ex) {
            Logger.getLogger(ServerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of addUser method, of class Server.
     */
    @Test
    public void testAddUser() {
        String username = "username";
        CollAUser usr = new User();
        usr.setName(username);
        try {
            instance.addUser(usr);
        } catch (UserAlreadyExists ex) {
            Logger.getLogger(ServerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        CollAUser result = null;
        try {
            result = instance.getUser(username);
        } catch (NonExistentUser ex) {
            Logger.getLogger(ServerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(usr, result);
    }

    /**
     * Test of updateUser method, of class Server.
     */
    @Test
    public void testUpdateUser() {
        String name = "username";
        String ip = "127.0.0.1";
        CollAUser user = new User();
        user.setName(name);
        user.setIp(ip);
        try {
            instance.addUser(user);
        } catch (UserAlreadyExists ex) {
            Logger.getLogger(ServerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        CollAUser result = null;
        try {
            result = instance.getUser(name);
        } catch (NonExistentUser ex) {
            Logger.getLogger(ServerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(result, user);
        String newIP = "127.0.0.2";
        user.setIp(newIP);
        try {
            instance.updateUser(user);
        } catch (NonExistentUser ex) {
            Logger.getLogger(ServerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            result = instance.getUser(name);
        } catch (NonExistentUser ex) {
            Logger.getLogger(ServerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        String resultIP = result.getIp();
        assertEquals(newIP, resultIP);


    }

    /**
     * Test of updateHost method, of class Server.
     */
    @Test
    public void testUpdateHost() {
        CollAHost host = new Host("host", "127.0.0.1", 8080);
        CollAUser user = new User("user", "127.0.0.1", 8081);
        host.setNameUser("user");
        user.addHost(host);
        try {
            instance.addUser(user);
        } catch (UserAlreadyExists ex) {
            fail("fail to add user");
        }
        host.setIp("127.0.0.2");
        try {
            instance.updateHost(host);
        } catch (NonExistentUser ex) {
            fail("fail to update host");
        }
        try {
            user = instance.getUser("user");
        } catch (NonExistentUser ex) {
            fail("fail to get user");
        }
        host = user.getHost("host");
        String expResult = "127.0.0.2";
        assertEquals(expResult, host.getIp());
    }

    /**
     * Test of getPortNumber method, of class Server.
     */
    @Test
    public void testGetPortNumber() {
        int expResult = 9999;
        int result = instance.getPortNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUser method, of class Server.
     */
    @Test
    public void testGetUser() {
        String userName = "username";
        CollAUser user = new User("username", "127.0.0.1", 8080);
        CollAUser result = null;
        try {
            instance.addUser(user);
        } catch (UserAlreadyExists ex) {
            fail("fail to add user");
        }
        try {
            result = instance.getUser(userName);
        } catch (NonExistentUser ex) {
            fail("fail to get user");
        }
        assertEquals(user, result);
    }

    /**
     * Test of getUserPassword method, of class Server.
     */
    @Test
    public void testGetUserPassword() {
        String userName = "username";
        String expResult = null;
        String result = instance.getUserPassword(userName);
        assertEquals(expResult, result);
    }

    /**
     * Test of setUserPassword method, of class Server.
     */
    @Test
    public void testSetUserPassword() {
        String userName = "username";
        String password = "password";
        instance.setUserPassword(userName, password);
        String result = instance.getUserPassword(userName);
        assertEquals(password, result);
    }

    /**
     * Test of updateGroup method, of class Server.
     */
    @Test
    public void testUpdateGroup() {
        String groupName = "group";
        CollAGroup group = new Group(groupName);
        instance.updateGroup(groupName, group);
        CollAGroup result = instance.getGroup(groupName);
        assertEquals(group, result);
    }

    /**
     * Test of getGroup method, of class Server.
     */
    @Test
    public void testGetGroup() {
        String groupName = "";
        CollAGroup expResult = null;
        CollAGroup result = instance.getGroup(groupName);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllGroups method, of class Server.
     */
    @Test
    public void testGetAllGroups() {
        Set expResult = new TreeSet();
        Set result = instance.getAllGroups();
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of getUsersSet method, of class Server.
     */
    @Test
    public void testGetUsersSet() {
        Set expResult = new TreeSet();
        Set result = instance.getUsersSet();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTimeoutValue method, of class Server.
     */
    @Test
    public void testGetTimeoutValue() {
        int expResult = 15000;
        int result = instance.getTimeoutValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of generateSessionID method, of class Server.
     */
    @Test
    public void testGenerateSessionID() {        
        long expResult = 1L;
        long result = instance.generateSessionID();
        assertEquals(expResult, result);
    }

    /**
     * Test of disconnectAllClients method, of class Server.
     */
    @Test
    public void testDisconnectAllClients() {        
        instance.disconnectAllClients();
    }

    /**
     * Test of generateTaskID method, of class Server.
     */
    @Test
    public void testGenerateTaskID() {
        Long expResult = 1L;
        Long result = instance.generateTaskID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMappedConnections method, of class Server.
     */
    @Test
    public void testGetMappedConnections() {
        Map expResult = new HashMap();
        Map result = instance.getMappedConnections();
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of mapConnection method, of class Server.
     */
    @Test
    public void testMapConnection() {
        Socket socket = new Socket(); 
        String identifier = "username";
        instance.mapConnection(identifier, socket);
        Socket result = instance.getAMapedConnection(identifier);
        assertEquals(socket, result);
    }

    /**
     * Test of getTimeAndDateInstance method, of class Server.
     */
    @Test
    public void testGetTimeAndDateInstance() {        
        TimeAndDate result = instance.getTimeAndDateInstance();
        assertNotNull(result);
    }

    /**
     * Test of getAMapedConnection method, of class Server.
     */
    @Test
    public void testGetAMapedConnection() {
        String identifier = "";
        Socket expResult = null;
        Socket result = instance.getAMapedConnection(identifier);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeAMappedConnection method, of class Server.
     */
    @Test
    public void testRemoveAMappedConnection() {
        String identifier = "";
        instance.removeAMappedConnection(identifier);
    }

    /**
     * Test of getMapedResults method, of class Server.
     */
    @Test
    public void testGetMapedResults() {
        String identifier = "";
        ArrayList expResult = null;
        ArrayList result = instance.getMapedResults(identifier);
        assertEquals(expResult, result);
    }   

    /**
     * Test of updateResultsMap method, of class Server.
     */
    @Test
    public void testUpdateResultsMap() {
        String identifier = "";
        ArrayList<TaskResultMsg> resultsArray = null;        
        instance.updateResultsMap(identifier, resultsArray);
    }

    /**
     * Test of removeMapedResults method, of class Server.
     */
    @Test
    public void testRemoveMapedResults() {
        String identifier = "";
        instance.removeMapedResults(identifier);
    }

    /**
     * Test of displayMessage method, of class Server.
     */
    @Test
    public void testDisplayMessage() {
        String message = "it's a test message.";
        instance.displayMessage(message);
    }
}