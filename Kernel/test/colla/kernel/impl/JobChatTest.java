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
public class JobChatTest {
    
    public JobChatTest() {
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
     * Test of setJobName method, of class JobChat.
     */
    @Test
    public void testSetJobName() {
        System.out.println("setJobName");
        JobChat instance = new JobChat();
        instance.setJobName();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJobName method, of class JobChat.
     */
    @Test
    public void testGetJobName() {
        System.out.println("getJobName");
        JobChat instance = new JobChat();
        String expResult = "";
        String result = instance.getJobName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJobDateAndTime method, of class JobChat.
     */
    @Test
    public void testGetJobDateAndTime() {
        System.out.println("getJobDateAndTime");
        JobChat instance = new JobChat();
        String expResult = "";
        String result = instance.getJobDateAndTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}