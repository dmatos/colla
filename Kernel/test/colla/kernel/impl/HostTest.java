/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.impl;

import colla.kernel.api.CollAHost;
import java.util.HashMap;
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
public class HostTest {
    
    public HostTest() {
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
     * Test of compareTo method, of class Host.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        CollAHost host = null;
        Host instance = new Host();
        int expResult = 0;
        int result = instance.compareTo(host);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSystemProperties method, of class Host.
     */
    @Test
    public void testGetSystemProperties() {
        System.out.println("getSystemProperties");
        Host instance = new Host();
        String expResult = "";
        String result = instance.getSystemProperties();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateIP method, of class Host.
     */
    @Test
    public void testValidateIP() {
        System.out.println("validateIP");
        Host instance = new Host();
        instance.validateIP();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of invalidateIP method, of class Host.
     */
    @Test
    public void testInvalidateIP() {
        System.out.println("invalidateIP");
        Host instance = new Host();
        instance.invalidateIP();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasValidIP method, of class Host.
     */
    @Test
    public void testHasValidIP() {
        System.out.println("hasValidIP");
        Host instance = new Host();
        boolean expResult = false;
        boolean result = instance.hasValidIP();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Host.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Host instance = new Host();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNameUser method, of class Host.
     */
    @Test
    public void testSetNameUser() {
        System.out.println("setNameUser");
        String nameUser = "";
        Host instance = new Host();
        instance.setNameUser(nameUser);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIp method, of class Host.
     */
    @Test
    public void testSetIp() {
        System.out.println("setIp");
        String ip = "";
        Host instance = new Host();
        instance.setIp(ip);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPort method, of class Host.
     */
    @Test
    public void testSetPort() {
        System.out.println("setPort");
        int port = 0;
        Host instance = new Host();
        instance.setPort(port);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOnline method, of class Host.
     */
    @Test
    public void testSetOnline() {
        System.out.println("setOnline");
        Host instance = new Host();
        instance.setOnline();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOffline method, of class Host.
     */
    @Test
    public void testSetOffline() {
        System.out.println("setOffline");
        Host instance = new Host();
        instance.setOffline();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of IsOnline method, of class Host.
     */
    @Test
    public void testIsOnline() {
        System.out.println("IsOnline");
        Host instance = new Host();
        boolean expResult = false;
        boolean result = instance.IsOnline();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Host.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Host instance = new Host();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNameUser method, of class Host.
     */
    @Test
    public void testGetNameUser() {
        System.out.println("getNameUser");
        Host instance = new Host();
        String expResult = "";
        String result = instance.getNameUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIp method, of class Host.
     */
    @Test
    public void testGetIp() {
        System.out.println("getIp");
        Host instance = new Host();
        String expResult = "";
        String result = instance.getIp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPort method, of class Host.
     */
    @Test
    public void testGetPort() {
        System.out.println("getPort");
        Host instance = new Host();
        int expResult = 0;
        int result = instance.getPort();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInicioConexao method, of class Host.
     */
    @Test
    public void testSetInicioConexao() {
        System.out.println("setInicioConexao");
        Host instance = new Host();
        instance.setInicioConexao();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTempoTotalConexao method, of class Host.
     */
    @Test
    public void testGetTempoTotalConexao() {
        System.out.println("getTempoTotalConexao");
        Host instance = new Host();
        String expResult = "";
        String result = instance.getTempoTotalConexao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of recordActivities method, of class Host.
     */
    @Test
    public void testRecordActivities() {
        System.out.println("recordActivities");
        String date = "";
        String activity = "";
        Host instance = new Host();
        instance.recordActivities(date, activity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setActivities method, of class Host.
     */
    @Test
    public void testSetActivities() {
        System.out.println("setActivities");
        HashMap<String, String> map = null;
        Host instance = new Host();
        instance.setActivities(map);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActivities method, of class Host.
     */
    @Test
    public void testGetActivities() {
        System.out.println("getActivities");
        Host instance = new Host();
        HashMap expResult = null;
        HashMap result = instance.getActivities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCountry method, of class Host.
     */
    @Test
    public void testSetCountry() {
        System.out.println("setCountry");
        String cntry = "";
        Host instance = new Host();
        instance.setCountry(cntry);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCountry method, of class Host.
     */
    @Test
    public void testGetCountry() {
        System.out.println("getCountry");
        Host instance = new Host();
        String expResult = "";
        String result = instance.getCountry();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Host.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Host instance = new Host();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}