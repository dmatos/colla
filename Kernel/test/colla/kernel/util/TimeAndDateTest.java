/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.util;

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
public class TimeAndDateTest {
    
    public TimeAndDateTest() {
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
     * Test of startTimer method, of class TimeAndDate.
     */
    @Test
    public void testStartTimer() {        
        TimeAndDate instance = new TimeAndDate();
        instance.startTimer();
        instance.stopTimer();
        assertNotNull(instance.getTotalTime());
    }

    /**
     * Test of getSimpleDate method, of class TimeAndDate.
     */
    @Test
    public void testGetSimpleDate() {
        TimeAndDate instance = new TimeAndDate();
        String result = instance.getSimpleDate();
        assertNotNull(result);
    }

    /**
     * Test of getHour method, of class TimeAndDate.
     */
    @Test
    public void testGetHour() {
        TimeAndDate instance = new TimeAndDate();
        String result = instance.getHour();
        assertNotNull(result);
    }

    /**
     * Test of getStartDate method, of class TimeAndDate.
     */
    @Test
    public void testGetStartDate() {
        TimeAndDate instance = new TimeAndDate();
        String result = instance.getStartDate();
        assertNotNull(result);
    }

    /**
     * Test of getEndDate method, of class TimeAndDate.
     */
    @Test
    public void testGetEndDate() {
        TimeAndDate instance = new TimeAndDate();
        String result = instance.getEndDate();
        assertNotNull(result);
    }

    /**
     * Test of stopTimer method, of class TimeAndDate.
     */
    @Test
    public void testStopTimer() {
        TimeAndDate instance = new TimeAndDate();
        instance.startTimer();
        instance.stopTimer();
        assertNotNull(instance.getTotalTime());
    }

    /**
     * Test of getTotalTime method, of class TimeAndDate.
     */
    @Test
    public void testGetTotalTime() {
        TimeAndDate instance = new TimeAndDate();
        String expResult = "not finished";
        String result = instance.getTotalTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTotalTimeInNanoS method, of class TimeAndDate.
     */
    @Test
    public void testGetTotalTimeInNanoS() {
        TimeAndDate instance = new TimeAndDate();
        Long expResult = 0L;
        Long result = instance.getTotalTimeInNanoS();
        assertEquals(expResult, result);
    }
}