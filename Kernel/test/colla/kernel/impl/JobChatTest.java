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
        JobChat instance = new JobChat();
        instance.setJobName();
    }

    /**
     * Test of getJobName method, of class JobChat.
     */
    @Test
    public void testGetJobName() {
        JobChat instance = new JobChat();
        String expResult = "Chat";
        String result = instance.getJobName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJobDateAndTime method, of class JobChat.
     */
    @Test
    public void testGetJobDateAndTime() {
        JobChat instance = new JobChat();
        String result = instance.getJobDateAndTime();
        assertNotNull(result);
    }
}