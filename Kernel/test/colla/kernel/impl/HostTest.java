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
        CollAHost host = new Host("host", "127.0.0.1", 8080);
        Host instance = new Host("host", "127.0.0.1", 8080);
        int expResult = 0;
        int result = instance.compareTo(host);
        assertEquals(expResult, result);
    }

    /**
     * Test of getSystemProperties method, of class Host.
     */
    @Test
    public void testGetSystemProperties() {
        Host instance = new Host();
        String result = instance.getSystemProperties();
        assertNotNull(result);
    }

    /**
     * Test of validateIP method, of class Host.
     */
    @Test
    public void testValidateIP() {
        Host instance = new Host();
        instance.validateIP();
        boolean result = instance.hasValidIP();
        boolean expResult = true;
        assertEquals(expResult, result);
    }

    /**
     * Test of invalidateIP method, of class Host.
     */
    @Test
    public void testInvalidateIP() {
        Host instance = new Host();
        instance.invalidateIP();
        boolean result = instance.hasValidIP();
        boolean expResult = false;
        assertEquals(expResult, result);
    }

    /**
     * Test of hasValidIP method, of class Host.
     */
    @Test
    public void testHasValidIP() {
        Host instance = new Host();
        boolean expResult = false;
        boolean result = instance.hasValidIP();
        assertEquals(expResult, result);       
    }

    /**
     * Test of setName method, of class Host.
     */
    @Test
    public void testSetName() {
        String name = "host";
        Host instance = new Host();
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of setNameUser method, of class Host.
     */
    @Test
    public void testSetNameUser() {       
        String nameUser = "owner";
        Host instance = new Host();
        instance.setNameUser(nameUser);
        assertEquals(nameUser, instance.getNameUser());
    }

    /**
     * Test of setIp method, of class Host.
     */
    @Test
    public void testSetIp() {       
        String ip = "122.0.0.1";
        Host instance = new Host();
        instance.setIp(ip);
        assertEquals(ip, instance.getIp());
    }

    /**
     * Test of setPort method, of class Host.
     */
    @Test
    public void testSetPort() {        
        int port = 8080;
        Host instance = new Host();
        instance.setPort(port);
        assertEquals(port, instance.getPort());
    }

    /**
     * Test of setOnline method, of class Host.
     */
    @Test
    public void testSetOnline() {        
        Host instance = new Host();
        instance.setOnline();
        boolean expResult = true;
        boolean result = instance.IsOnline();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOffline method, of class Host.
     */
    @Test
    public void testSetOffline() {
         Host instance = new Host();
        instance.setOffline();
        boolean expResult = false;
        boolean result = instance.IsOnline();
        assertEquals(expResult, result);
    }

    /**
     * Test of IsOnline method, of class Host.
     */
    @Test
    public void testIsOnline() {
        Host instance = new Host();
        boolean expResult = false;
        boolean result = instance.IsOnline();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Host.
     */
    @Test
    public void testGetName() {
        Host instance = new Host();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNameUser method, of class Host.
     */
    @Test
    public void testGetNameUser() {
        Host instance = new Host();
        String expResult = "";
        String result = instance.getNameUser();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIp method, of class Host.
     */
    @Test
    public void testGetIp() {
        Host instance = new Host();
        String expResult = "";
        String result = instance.getIp();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPort method, of class Host.
     */
    @Test
    public void testGetPort() {
        Host instance = new Host();
        int expResult = -1;
        int result = instance.getPort();
        assertEquals(expResult, result);
    }

    /**
     * Test of setInicioConexao method, of class Host.
     */
    @Test
    public void testSetInicioConexao() {
        Host instance = new Host();
        instance.setInicioConexao();
        String result = instance.getTempoTotalConexao();
        assertNotNull(result);
    }

    /**
     * Test of getTempoTotalConexao method, of class Host.
     */
    @Test
    public void testGetTempoTotalConexao() {
        Host instance = new Host();
        instance.setInicioConexao();
        String result = instance.getTempoTotalConexao();
        assertNotNull(result);
    }

    /**
     * Test of recordActivities method, of class Host.
     */
    @Test
    public void testRecordActivities() {
        String date = "";
        String activity = "";
        Host instance = new Host();
        instance.recordActivities(date, activity);
    }

    /**
     * Test of setActivities method, of class Host.
     */
    @Test
    public void testSetActivities() {
        HashMap<String, String> map = null;
        Host instance = new Host();
        instance.setActivities(map);
    }

    /**
     * Test of getActivities method, of class Host.
     */
    @Test
    public void testGetActivities() {
        Host instance = new Host();
        HashMap expResult = new HashMap();
        HashMap result = instance.getActivities();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCountry method, of class Host.
     */
    @Test
    public void testSetCountry() {
        System.out.println("setCountry");
        String cntry = "test";
        Host instance = new Host();
        instance.setCountry(cntry);
        String result = instance.getCountry();
        assertEquals(cntry, result);
    }

    /**
     * Test of getCountry method, of class Host.
     */
    @Test
    public void testGetCountry() {
        Host instance = new Host();
        String expResult = "";
        String result = instance.getCountry();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Host.
     */
    @Test
    public void testToString() {
        Host instance = new Host();
        String result = instance.toString();
        assertNotNull(result);
    }
}