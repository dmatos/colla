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
        int ticket = 123;
        Ticket instance = new Ticket();
        instance.setTicket(ticket);
        int result = instance.getTicket();
        assertEquals(ticket, result);
    }

    /**
     * Test of getTicket method, of class Ticket.
     */
    @Test
    public void testGetTicket() {
        Ticket instance = new Ticket();
        Integer expResult = -1;
        Integer result = instance.getTicket();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHostPort method, of class Ticket.
     */
    @Test
    public void testSetHostPort() {
        int port = 8080;
        Ticket instance = new Ticket();
        instance.setHostPort(port);
        int result = instance.getHostPort();
        assertEquals(port, result);
    }

    /**
     * Test of getHostPort method, of class Ticket.
     */
    @Test
    public void testGetHostPort() {
        Ticket instance = new Ticket();
        Integer expResult = -1;
        Integer result = instance.getHostPort();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHostIPaddress method, of class Ticket.
     */
    @Test
    public void testSetHostIPaddress() {
        String ipAddress = "127.0.0.1";
        Ticket instance = new Ticket();
        instance.setHostIPaddress(ipAddress);
        String result = instance.getHostIPAddress();
        assertEquals(ipAddress, result);
    }

    /**
     * Test of getHostIPAddress method, of class Ticket.
     */
    @Test
    public void testGetHostIPAddress() {
        Ticket instance = new Ticket();
        String expResult = "";
        String result = instance.getHostIPAddress();
        assertEquals(expResult, result);
    }
}