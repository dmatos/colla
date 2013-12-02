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
import java.util.ArrayList;
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
        CollAUser user = new User();
        User instance = new User();                
        int expResult = 0;
        int result = instance.compareTo(user);
        assertEquals(expResult, result);       
    }  

    /**
     * Test of getHosts method, of class User.
     */
    @Test
    public void testGetHosts() {
        User instance = new User();
        Set expResult = new TreeSet();
        Set result = instance.getHosts();
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of addHost method, of class User.
     */
    @Test
    public void testAddHost() {
        CollAHost host = new Host();
        User instance = new User();
        instance.addHost(host);
        int expResult = 1;
        Set hosts = instance.getHosts();
        int result = hosts.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHost method, of class User.
     */
    @Test
    public void testGetHost() {
        String hostName = "host";
        User instance = new User();
        CollAHost expResult = null;
        CollAHost result = instance.getHost(hostName);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateIP method, of class User.
     */
    @Test
    public void testValidateIP() {
        User instance = new User();
        instance.validateIP();
        assertTrue(instance.hasValidIP());
    }

    /**
     * Test of invalidateIP method, of class User.
     */
    @Test
    public void testInvalidateIP() {
        User instance = new User();
        instance.invalidateIP();
        assertFalse(instance.hasValidIP());
    }

    /**
     * Test of hasValidIP method, of class User.
     */
    @Test
    public void testHasValidIP() {
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.hasValidIP();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class User.
     */
    @Test
    public void testSetName() {
        String name = "username";
        User instance = new User();
        instance.setName(name);
        String result = instance.getName();
        assertEquals(name, result);
    }

    /**
     * Test of setIp method, of class User.
     */
    @Test
    public void testSetIp() {
        String ip = "127.0.0.1";
        User instance = new User();
        instance.setIp(ip);
        String result = instance.getIp();
        assertEquals(ip, result);
    }

    /**
     * Test of setPort method, of class User.
     */
    @Test
    public void testSetPort() {        
        int port = 8080;
        User instance = new User();
        instance.setPort(port);
        int result = instance.getPort();
        assertEquals(port, result);
    }

    /**
     * Test of setOnline method, of class User.
     */
    @Test
    public void testSetOnline() {
        User instance = new User();
        instance.setOnline();
        assertTrue(instance.isOnline());
    }

    /**
     * Test of setOffline method, of class User.
     */
    @Test
    public void testSetOffline() {
        User instance = new User();
        instance.setOffline();
        assertFalse(instance.isOnline());
    }

    /**
     * Test of isOnline method, of class User.
     */
    @Test
    public void testIsOnline() {
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.isOnline();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class User.
     */
    @Test
    public void testGetName() {
        User instance = new User();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIp method, of class User.
     */
    @Test
    public void testGetIp() {
        User instance = new User();
        String expResult = "127.0.0.1";
        String result = instance.getIp();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPort method, of class User.
     */
    @Test
    public void testGetPort() {
        User instance = new User();
        int expResult = 0;
        int result = instance.getPort();
        assertEquals(expResult, result);
    }

    /**
     * Test of setConnectionInitialized method, of class User.
     */
    @Test
    public void testSetConnectionInitialized() {
        User instance = new User();
        instance.setConnectionInitialized();
        assertNotNull(instance.getConnectionTotalTime());
    }

    /**
     * Test of getConnectionTotalTime method, of class User.
     */
    @Test
    public void testGetConnectionTotalTime() {
        User instance = new User();
        String expResult = "not finished";
        String result = instance.getConnectionTotalTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of addGroup method, of class User.
     */
    @Test
    public void testAddGroup() {
        String groupName = "";
        CollAGroup group = new Group("group");
        User instance = new User();
        instance.addGroup(groupName, group);
        HashMap groups = instance.getGroups();
        int result = groups.size();
        int expResult = 1;
        assertEquals(expResult, result);
    }

    /**
     * Test of getGroups method, of class User.
     */
    @Test
    public void testGetGroups() {
        User instance = new User();
        HashMap expResult = new HashMap();
        HashMap result = instance.getGroups();
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of recordActivities method, of class User.
     */
    @Test
    public void testRecordActivities() {
        String date = "";
        String activity = "";
        User instance = new User();
        instance.recordActivities(date, activity);
        HashMap activities = instance.getActivities();
        int result = activities.size();
        int expResult = 1;
        assertEquals(expResult, result);
    }

    /**
     * Test of setActivities method, of class User.
     */
    @Test
    public void testSetActivities() {
        HashMap<String, String> map = new HashMap<String, String>();
        User instance = new User();
        instance.setActivities(map);     
        HashMap result = instance.getActivities();
        assertEquals(map.size(), result.size());
    }

    /**
     * Test of getActivities method, of class User.
     */
    @Test
    public void testGetActivities() {
        User instance = new User();
        HashMap expResult = new HashMap();
        HashMap result = instance.getActivities();
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of setCountry method, of class User.
     */
    @Test
    public void testSetCountry() {
        String cntry = "country";
        User instance = new User();
        instance.setCountry(cntry);
        String result = instance.getCountry();
        assertEquals(cntry, result);
    }

    /**
     * Test of getCountry method, of class User.
     */
    @Test
    public void testGetCountry() {
        User instance = new User();
        String expResult = "";
        String result = instance.getCountry();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class User.
     */
    @Test
    public void testSetEmail() {
        String mail = "email";
        User instance = new User();
        instance.setEmail(mail);
        String result = instance.getEmail();
        assertEquals(mail, result);
    }

    /**
     * Test of getEmail method, of class User.
     */
    @Test
    public void testGetEmail() {
        User instance = new User();
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of addSession method, of class User.
     */
    @Test
    public void testAddSession() {
        CollASession ses = new Session();
        User instance = new User();
        instance.addSession(ses);
        HashMap sessions = instance.getSessions();
        int result = sessions.size();
        int expResult = 1;
        assertEquals(expResult, result);
    }

    /**
     * Test of getSessions method, of class User.
     */
    @Test
    public void testGetSessions() {
        User instance = new User();
        HashMap expResult = new HashMap();
        HashMap result = instance.getSessions();
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of getLastSession method, of class User.
     */
    @Test
    public void testGetLastSession() {
        User instance = new User();
        Session result = instance.getLastSession();
        assertNotNull(result);
    }

    /**
     * Test of addJob method, of class User.
     */
    @Test
    public void testAddJob() {
        CollAJob job = new JobChat();
        User instance = new User();
        instance.addJob(job);
    }

    /**
     * Test of getOrderedSessions method, of class User.
     */
    @Test
    public void testGetOrderedSessions() {
        User instance = new User();
        List expResult = new ArrayList();
        List result = instance.getOrderedSessions();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCompany method, of class User.
     */
    @Test
    public void testSetCompany() {
        String company = "";
        User instance = new User();
        instance.setCompany(company);
        String result = instance.getCompany();
        assertEquals(company, result);
    }

    /**
     * Test of getCompany method, of class User.
     */
    @Test
    public void testGetCompany() {
        User instance = new User();
        String expResult = "";
        String result = instance.getCompany();
        assertEquals(expResult, result);
    }
}