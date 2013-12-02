/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.server;

import colla.kernel.messages.toHost.ListOnlineHostsMsg;
import java.net.Socket;
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
public class ServerWorkerTest {
    
    public ServerWorkerTest() {
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
     * Test of listOnlineHosts method, of class ServerWorker.
     */
    @Test
    public void testListOnlineHosts() {
        System.out.println("listOnlineHosts");
        ServerWorker instance = new ServerWorker();
        ListOnlineHostsMsg result = instance.listOnlineHosts();
        assertNotNull(result);
    }
}