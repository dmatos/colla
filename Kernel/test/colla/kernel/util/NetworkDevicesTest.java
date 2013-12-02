/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.util;

import java.util.List;
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
public class NetworkDevicesTest {
    
    public NetworkDevicesTest() {
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
     * Test of getIPs method, of class NetworkDevices.
     */
    @Test
    public void testGetIPs() throws Exception {        
        NetworkDevices instance = new NetworkDevices();        
        List result = instance.getIPs();
        assertNotNull(result);
    }
}