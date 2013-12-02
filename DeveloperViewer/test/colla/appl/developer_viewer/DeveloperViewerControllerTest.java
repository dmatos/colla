/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.developer_viewer;

import colla.appl.developer_viewer.exceptions.DeveloperControllerInitializationException;
import colla.appl.developer_viewer.view.CollADeveloperViewerUI;
import colla.kernel.api.CollAGroup;
import colla.kernel.api.CollAHost;
import colla.kernel.api.CollATicket;
import colla.kernel.api.CollAUser;
import colla.kernel.impl.Group;
import colla.kernel.impl.Host;
import colla.kernel.impl.User;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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
public class DeveloperViewerControllerTest {

    public DeveloperViewerControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setupDeveloperController method, of class
     * DeveloperViewerController.
     */
    @Test
    public void testSetupDeveloperController() {
        String username = "username";
        CollAUser usr = new User();
        usr.setName(username);
        int serverPort = 9999;
        String serverIP = "127.0.0.1";
        HashMap<String, CollAUser> contacts = new HashMap<String, CollAUser>();
        DeveloperViewerController result = DeveloperViewerController.setupDeveloperController(usr, serverPort, serverIP, contacts);
        DeveloperViewerController expResult;
        try {
            expResult = DeveloperViewerController.getInstance();
            assertEquals(expResult, result);
            expResult.shutdown();
        } catch (DeveloperControllerInitializationException ex) {
            fail(ex.toString());
        }
    }

    /**
     * Test of getInstance method, of class DeveloperViewerController.
     */
    @Test
    public void testGetInstance() {
        DeveloperViewerController result;
        CollAUser usr = new User();
        try {
            DeveloperViewerController devViewer = DeveloperViewerController.setupDeveloperController(usr, 9999, "127.0.0.1", new HashMap());
            result = DeveloperViewerController.getInstance();
            assertEquals(devViewer, result);
            devViewer.shutdown();
        } catch (DeveloperControllerInitializationException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Test of refresh_a_group method, of class DeveloperViewerController.
     */
    @Test
    public void testRefresh_a_group() {
        CollAUser usr = new User();
        usr.setName("username");
        String groupName = "group";
        Set<String> group = new TreeSet<String>();
        DeveloperViewerController instance = DeveloperViewerController.setupDeveloperController(usr, 9999, "127.0.0.1", new HashMap());
        instance.refresh_a_group(groupName, group);
        instance.shutdown();
    }

    /**
     * Test of displayInfo method, of class DeveloperViewerController.
     */
    @Test
    public void testDisplayInfo() {
        String info = "display test";
        CollAUser usr = new User();
        DeveloperViewerController instance = DeveloperViewerController.setupDeveloperController(usr, 9999, "127.0.0.1", new HashMap());
        instance.displayInfo(info);
        instance.shutdown();
    }

    /**
     * Test of setUser method, of class DeveloperViewerController.
     */
    @Test
    public void testSetUser() {
        CollAUser usr = new User();        
        DeveloperViewerController instance = DeveloperViewerController.setupDeveloperController(usr, 9999, "127.0.0.1", new HashMap());
        usr.setName("username");
        instance.setUser(usr);
        CollAUser result = instance.getUser();
        assertEquals(usr, result);
        instance.shutdown();
    }

    /**
     * Test of getUser method, of class DeveloperViewerController.
     */
    @Test
    public void testGetUser() {
        CollAUser usr = new User();
        DeveloperViewerController instance = DeveloperViewerController.setupDeveloperController(usr, 9999, "127.0.0.1", new HashMap());
        CollAUser expResult = usr;
        CollAUser result = instance.getUser();
        assertEquals(expResult, result);
        instance.shutdown();
    }

    /**
     * Test of shutdown method, of class DeveloperViewerController.
     */
    @Test
    public void testShutdown() {
        CollAUser usr = new User();
        DeveloperViewerController instance = DeveloperViewerController.setupDeveloperController(usr, 9999, "127.0.0.1", new HashMap());
        instance.shutdown();
    }

    /**
     * Test of getContactByGroup method, of class DeveloperViewerController.
     */
    @Test
    public void testGetContactByGroup() {
        String contact = "";
        String groupName = "";
        CollAUser usr = new User();
        DeveloperViewerController instance = DeveloperViewerController.setupDeveloperController(usr, 9999, "127.0.0.1", new HashMap());
        CollAUser expResult = null;
        CollAUser result = instance.getContactByGroup(contact, groupName);
        assertEquals(expResult, result);
        instance.shutdown();
    }

    /**
     * Test of showChatMessage method, of class DeveloperViewerController.
     */
    @Test
    public void testShowChatMessage() {
        String sender = "";
        String message = "";
        CollAUser usr = new User();
        DeveloperViewerController instance = DeveloperViewerController.setupDeveloperController(usr, 9999, "127.0.0.1", new HashMap());
        instance.showChatMessage(sender, message);
        instance.shutdown();
    }
    
    /**
     * Test of addGroup method, of class DeveloperViewerController.
     */
    @Test
    public void testAddGroup() {
        String groupName = "group";
        CollAGroup group = new Group(groupName);
        HashMap<String, CollAUser> usersMap = new HashMap();
        CollAUser usr = new User();
        DeveloperViewerController instance = DeveloperViewerController.setupDeveloperController(usr, 9999, "127.0.0.1", new HashMap());
        instance.addGroup(groupName, group, usersMap);
        usr = instance.getUser();
        CollAGroup temp = usr.getGroups().get(groupName);
        assertNotNull(temp);
        instance.shutdown();
    }

    /**
     * Test of addHost method, of class DeveloperViewerController.
     */
    @Test
    public void testAddHost() {
        CollAHost host = new Host();
        host.setName("host");
        CollAUser usr = new User();
        DeveloperViewerController instance = DeveloperViewerController.setupDeveloperController(usr, 9999, "127.0.0.1", new HashMap());
        instance.addHost(host);
        usr  = instance.getUser();
        CollAHost result = usr.getHost("host");
        assertEquals(host, result);
        instance.shutdown();
        
    }

    /**
     * Test of addContact method, of class DeveloperViewerController.
     */
    @Test
    public void testAddContact() {
        CollAUser contact = new User();
        contact.setName("contact");
        CollAUser usr = new User();
        DeveloperViewerController instance = DeveloperViewerController.setupDeveloperController(usr, 9999, "127.0.0.1", new HashMap());
        instance.addContact(contact);
        CollAUser result = instance.getContact("contact");
        assertEquals(contact, result);
        instance.shutdown();
    }

    /**
     * Test of getContact method, of class DeveloperViewerController.
     */
    @Test
    public void testGetContact() {
        CollAUser usr = new User();
        DeveloperViewerController instance = DeveloperViewerController.setupDeveloperController(usr, 9999, "127.0.0.1", new HashMap());
        CollAUser expResult = null;
        CollAUser result = instance.getContact("nonExistentContact");
        assertEquals(expResult, result);
        instance.shutdown();
    }

    /**
     * Test of getServerIPAddress method, of class DeveloperViewerController.
     */
    @Test
    public void testGetServerIPAddress() {
        CollAUser usr = new User();
        DeveloperViewerController instance = DeveloperViewerController.setupDeveloperController(usr, 9999, "127.0.0.1", new HashMap());
        String expResult = "127.0.0.1";
        String result = instance.getServerIPAddress();
        assertEquals(expResult, result);
        instance.shutdown();
    }

    /**
     * Test of getServerPortNumber method, of class DeveloperViewerController.
     */
    @Test
    public void testGetServerPortNumber() {
        CollAUser usr = new User();
        DeveloperViewerController instance = DeveloperViewerController.setupDeveloperController(usr, 9999, "127.0.0.1", new HashMap());
        int expResult = 9999;
        int result = instance.getServerPortNumber();
        assertEquals(expResult, result);
        instance.shutdown();        
    }
}