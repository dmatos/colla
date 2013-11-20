/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.impl;

import colla.kernel.api.CollAJob;
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
public class SessionTest {
    
    public SessionTest() {
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
     * Test of addJob method, of class Session.
     */
    @Test
    public void testAddJob() {
        System.out.println("addJob");
        CollAJob job = null;
        Session instance = new Session();
        instance.addJob(job);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJobs method, of class Session.
     */
    @Test
    public void testGetJobs() {
        System.out.println("getJobs");
        Session instance = new Session();
        CollAJob[] expResult = null;
        CollAJob[] result = instance.getJobs();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSessionID method, of class Session.
     */
    @Test
    public void testSetSessionID() {
        System.out.println("setSessionID");
        long id = 0L;
        Session instance = new Session();
        instance.setSessionID(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSessionID method, of class Session.
     */
    @Test
    public void testGetSessionID() {
        System.out.println("getSessionID");
        Session instance = new Session();
        long expResult = 0L;
        long result = instance.getSessionID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSessionDateAndTime method, of class Session.
     */
    @Test
    public void testGetSessionDateAndTime() {
        System.out.println("getSessionDateAndTime");
        Session instance = new Session();
        String expResult = "";
        String result = instance.getSessionDateAndTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}