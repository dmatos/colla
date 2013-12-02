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
public class TreaterTest {
    
    public TreaterTest() {
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
     * Test of treat method, of class Treater.
     */
    @Test
    public void testTreat_Exception() {
        Exception e = null;
        Treater.treat(e);
    }

    /**
     * Test of treat method, of class Treater.
     */
    @Test
    public void testTreat_String() {
        String s = "";
        Treater.treat(s);
    }

    /**
     * Test of treat method, of class Treater.
     */
    @Test
    public void testTreat_String_Exception() {
        String s = "";
        Exception e = null;
        Treater.treat(s, e);
    }
}