/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author dmatos
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({colla.kernel.util.TreaterTest.class, colla.kernel.util.TimeAndDateTest.class, colla.kernel.util.NetworkDevicesTest.class, colla.kernel.util.SystemPropertiesTest.class})
public class TestAllSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}