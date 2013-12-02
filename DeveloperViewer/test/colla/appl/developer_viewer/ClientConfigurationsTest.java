/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.developer_viewer;

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
public class ClientConfigurationsTest {
    
    public ClientConfigurationsTest() {
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
     * Test of setPortNumber method, of class ClientConfigurations.
     */
    @Test
    public void testSetPortNumber() {
        int port = 8080;
        ClientConfigurations instance = new ClientConfigurations();
        instance.setPortNumber(port);
        int result = instance.getPortNumber();
        assertEquals(port, result);
        
    }

    /**
     * Test of getPortNumber method, of class ClientConfigurations.
     */
    @Test
    public void testGetPortNumber() {
        ClientConfigurations instance = new ClientConfigurations();
        Integer expResult =  -1;
        Integer result = instance.getPortNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of setResultDir method, of class ClientConfigurations.
     */
    @Test
    public void testSetResultDir() {
        String dir = "results/";
        ClientConfigurations instance = new ClientConfigurations();
        instance.setResultDir(dir);
        String result = instance.getResulstDir();
        assertEquals(dir, result);
    }

    /**
     * Test of getResulstDir method, of class ClientConfigurations.
     */
    @Test
    public void testGetResulstDir() {
        ClientConfigurations instance = new ClientConfigurations();
        String expResult = "results/";
        String result = instance.getResulstDir();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNonFileResultDir method, of class ClientConfigurations.
     */
    @Test
    public void testSetNonFileResultDir() {
        String dir = "results/";
        ClientConfigurations instance = new ClientConfigurations();
        instance.setNonFileResultDir(dir);
        String result = instance.getNonFileResulstDir();
        assertEquals(dir, result);
    }

    /**
     * Test of getNonFileResulstDir method, of class ClientConfigurations.
     */
    @Test
    public void testGetNonFileResulstDir() {
        ClientConfigurations instance = new ClientConfigurations();
        String expResult = "results/objects/";
        String result = instance.getNonFileResulstDir();
        assertEquals(expResult, result);
    }
}