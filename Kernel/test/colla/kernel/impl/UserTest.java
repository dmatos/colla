/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.impl;

import colla.kernel.api.CollAGroup;
import colla.kernel.api.CollAHost;
import colla.kernel.api.CollAJob;
import colla.kernel.api.CollASession;
import colla.kernel.api.CollAUser;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
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
public class UserTest {
    
    public UserTest() {
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
     * Test of compareTo method, of class User.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        CollAUser user = null;
        User instance = new User();
        int expResult = 0;
        int result = instance.compareTo(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initializeUser method, of class User.
     */
    @Test
    public void testInitializeUser() {
        System.out.println("initializeUser");
        User instance = new User();
        instance.initializeUser();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHosts method, of class User.
     */
    @Test
    public void testGetHosts() {
        System.out.println("getHosts");
        User instance = new User();
        Set expResult = null;
        Set result = instance.getHosts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addHost method, of class User.
     */
    @Test
    public void testAddHost() {
        System.out.println("addHost");
        CollAHost host = null;
        User instance = new User();
        instance.addHost(host);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHost method, of class User.
     */
    @Test
    public void testGetHost() {
        System.out.println("getHost");
        String hostName = "";
        User instance = new User();
        CollAHost expResult = null;
        CollAHost result = instance.getHost(hostName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateIP method, of class User.
     */
    @Test
    public void testValidateIP() {
        System.out.println("validateIP");
        User instance = new User();
        instance.validateIP();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of invalidateIP method, of class User.
     */
    @Test
    public void testInvalidateIP() {
        System.out.println("invalidateIP");
        User instance = new User();
        instance.invalidateIP();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasValidIP method, of class User.
     */
    @Test
    public void testHasValidIP() {
        System.out.println("hasValidIP");
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.hasValidIP();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class User.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        User instance = new User();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIp method, of class User.
     */
    @Test
    public void testSetIp() {
        System.out.println("setIp");
        String ip = "";
        User instance = new User();
        instance.setIp(ip);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPort method, of class User.
     */
    @Test
    public void testSetPort() {
        System.out.println("setPort");
        int port = 0;
        User instance = new User();
        instance.setPort(port);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOnline method, of class User.
     */
    @Test
    public void testSetOnline() {
        System.out.println("setOnline");
        User instance = new User();
        instance.setOnline();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOffline method, of class User.
     */
    @Test
    public void testSetOffline() {
        System.out.println("setOffline");
        User instance = new User();
        instance.setOffline();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isOnline method, of class User.
     */
    @Test
    public void testIsOnline() {
        System.out.println("isOnline");
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.isOnline();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class User.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        User instance = new User();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIp method, of class User.
     */
    @Test
    public void testGetIp() {
        System.out.println("getIp");
        User instance = new User();
        String expResult = "";
        String result = instance.getIp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPort method, of class User.
     */
    @Test
    public void testGetPort() {
        System.out.println("getPort");
        User instance = new User();
        int expResult = 0;
        int result = instance.getPort();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setConnectionInitialized method, of class User.
     */
    @Test
    public void testSetConnectionInitialized() {
        System.out.println("setConnectionInitialized");
        User instance = new User();
        instance.setConnectionInitialized();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConnectionTotalTime method, of class User.
     */
    @Test
    public void testGetConnectionTotalTime() {
        System.out.println("getConnectionTotalTime");
        User instance = new User();
        String expResult = "";
        String result = instance.getConnectionTotalTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addGroup method, of class User.
     */
    @Test
    public void testAddGroup() {
        System.out.println("addGroup");
        String groupName = "";
        CollAGroup group = null;
        User instance = new User();
        instance.addGroup(groupName, group);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGroups method, of class User.
     */
    @Test
    public void testGetGroups() {
        System.out.println("getGroups");
        User instance = new User();
        HashMap expResult = null;
        HashMap result = instance.getGroups();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of recordActivities method, of class User.
     */
    @Test
    public void testRecordActivities() {
        System.out.println("recordActivities");
        String date = "";
        String activity = "";
        User instance = new User();
        instance.recordActivities(date, activity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setActivities method, of class User.
     */
    @Test
    public void testSetActivities() {
        System.out.println("setActivities");
        HashMap<String, String> map = null;
        User instance = new User();
        instance.setActivities(map);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActivities method, of class User.
     */
    @Test
    public void testGetActivities() {
        System.out.println("getActivities");
        User instance = new User();
        HashMap expResult = null;
        HashMap result = instance.getActivities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCountry method, of class User.
     */
    @Test
    public void testSetCountry() {
        System.out.println("setCountry");
        String cntry = "";
        User instance = new User();
        instance.setCountry(cntry);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCountry method, of class User.
     */
    @Test
    public void testGetCountry() {
        System.out.println("getCountry");
        User instance = new User();
        String expResult = "";
        String result = instance.getCountry();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmail method, of class User.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String mail = "";
        User instance = new User();
        instance.setEmail(mail);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class User.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        User instance = new User();
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSession method, of class User.
     */
    @Test
    public void testAddSession() {
        System.out.println("addSession");
        CollASession ses = null;
        User instance = new User();
        instance.addSession(ses);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSessions method, of class User.
     */
    @Test
    public void testGetSessions() {
        System.out.println("getSessions");
        User instance = new User();
        HashMap expResult = null;
        HashMap result = instance.getSessions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastSession method, of class User.
     */
    @Test
    public void testGetLastSession() {
        System.out.println("getLastSession");
        User instance = new User();
        Session expResult = null;
        Session result = instance.getLastSession();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addJob method, of class User.
     */
    @Test
    public void testAddJob() {
        System.out.println("addJob");
        CollAJob job = null;
        User instance = new User();
        instance.addJob(job);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrderedSessions method, of class User.
     */
    @Test
    public void testGetOrderedSessions() {
        System.out.println("getOrderedSessions");
        User instance = new User();
        List expResult = null;
        List result = instance.getOrderedSessions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCompany method, of class User.
     */
    @Test
    public void testSetCompany() {
        System.out.println("setCompany");
        String company = "";
        User instance = new User();
        instance.setCompany(company);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompany method, of class User.
     */
    @Test
    public void testGetCompany() {
        System.out.println("getCompany");
        User instance = new User();
        String expResult = "";
        String result = instance.getCompany();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}