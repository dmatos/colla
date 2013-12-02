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
        CollAJob job = new JobChat();
        Session instance = new Session();
        instance.addJob(job);
        CollAJob[] jobs = instance.getJobs();
        int result = 0;
        for(CollAJob temp : jobs)            
            if(temp != null)
                result++;        
        int expResult = 1;
        assertEquals(expResult, result);
    }

    /**
     * Test of getJobs method, of class Session.
     */
    @Test
    public void testGetJobs() {
       CollAJob job = new JobChat();
        Session instance = new Session();
        CollAJob[] jobs = instance.getJobs();
        int result = 0;
        for(CollAJob temp : jobs)
            if(temp != null)
                result++;        
        int expResult = 0;
        assertEquals(expResult, result);
        instance.addJob(job);
        jobs = instance.getJobs();
        result = 0;
        for(CollAJob temp : jobs)
            if(temp != null)
                result++;        
        expResult = 1;
        assertEquals(expResult, result);
    }

    /**
     * Test of setSessionID method, of class Session.
     */
    @Test
    public void testSetSessionID() {
        long id = 0L;
        Session instance = new Session();
        instance.setSessionID(id);
        long result = instance.getSessionID();
        assertEquals(id, result);
    }

    /**
     * Test of getSessionID method, of class Session.
     */
    @Test
    public void testGetSessionID() {
        long id = 0L;
        Session instance = new Session();
        instance.setSessionID(id);
        long result = instance.getSessionID();
        assertEquals(id, result);
    }

    /**
     * Test of getSessionDateAndTime method, of class Session.
     */
    @Test
    public void testGetSessionDateAndTime() {
        System.out.println("getSessionDateAndTime");
        Session instance = new Session();
        String result = instance.getSessionDateAndTime();
        assertNotNull(result);
    }
}