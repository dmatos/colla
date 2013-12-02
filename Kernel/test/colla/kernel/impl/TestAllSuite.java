/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.impl;

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
@Suite.SuiteClasses({colla.kernel.impl.JobChatTest.class, colla.kernel.impl.TaskTest.class, colla.kernel.impl.HostTest.class, colla.kernel.impl.TicketTest.class, colla.kernel.impl.UserTest.class, colla.kernel.impl.GroupTest.class, colla.kernel.impl.SessionTest.class})
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