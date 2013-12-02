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
public class SystemPropertiesTest {
    
    public SystemPropertiesTest() {
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
     * Test of getSystemProperties method, of class SystemProperties.
     */
    @Test
    public void testGetSystemProperties() {
        SystemProperties instance = new SystemProperties();        
        String result = instance.getSystemProperties();
        assertNotNull(result);
    }
}