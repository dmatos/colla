/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.impl;

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
public class TicketTest {
    
    public TicketTest() {
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
     * Test of setTicket method, of class Ticket.
     */
    @Test
    public void testSetTicket() {
        System.out.println("setTicket");
        Integer ticket = null;
        Ticket instance = new Ticket();
        instance.setTicket(ticket);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTicket method, of class Ticket.
     */
    @Test
    public void testGetTicket() {
        System.out.println("getTicket");
        Ticket instance = new Ticket();
        Integer expResult = null;
        Integer result = instance.getTicket();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHostPort method, of class Ticket.
     */
    @Test
    public void testSetHostPort() {
        System.out.println("setHostPort");
        Integer port = null;
        Ticket instance = new Ticket();
        instance.setHostPort(port);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHostPort method, of class Ticket.
     */
    @Test
    public void testGetHostPort() {
        System.out.println("getHostPort");
        Ticket instance = new Ticket();
        Integer expResult = null;
        Integer result = instance.getHostPort();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHostIPaddress method, of class Ticket.
     */
    @Test
    public void testSetHostIPaddress() {
        System.out.println("setHostIPaddress");
        String ipAddress = "";
        Ticket instance = new Ticket();
        instance.setHostIPaddress(ipAddress);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHostIPAddress method, of class Ticket.
     */
    @Test
    public void testGetHostIPAddress() {
        System.out.println("getHostIPAddress");
        Ticket instance = new Ticket();
        String expResult = "";
        String result = instance.getHostIPAddress();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}