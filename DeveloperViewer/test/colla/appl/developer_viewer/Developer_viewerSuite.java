/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.developer_viewer;

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
@Suite.SuiteClasses({colla.appl.developer_viewer.DevViewerLoginTest.class,
    colla.appl.developer_viewer.DeveloperViewerControllerTest.class,
    colla.appl.developer_viewer.ClientConfigurationsTest.class})
public class Developer_viewerSuite {

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