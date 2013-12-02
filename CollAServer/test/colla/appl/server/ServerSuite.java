/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.server;

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
@Suite.SuiteClasses({colla.appl.server.ServerTest.class,  colla.appl.server.ServerWorkerTest.class})
public class ServerSuite {

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