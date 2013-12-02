/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.host_viewer.controller;

import colla.kernel.api.CollAHost;
import colla.kernel.api.CollATask;
import colla.kernel.impl.Host;
import colla.kernel.messages.toHost.TaskMessage;
import interfaces.kernel.JCL_result;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;

/**
 *
 * @author dmatos
 */
public class HostViewerControllerTest {

    public HostViewerControllerTest() {
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
     * Test of setup method, of class HostViewerController.
     */
    @Test
    public void testSetup() throws Exception {
        HostViewerController result = HostViewerController.setup();
        assertNotNull(result);
        result.shutdown();
    }

    /**
     * Test of getInstance method, of class HostViewerController.
     */
    @Test
    public void testGetInstance() throws Exception {
        HostViewerController expResult = HostViewerController.setup();
        HostViewerController result = HostViewerController.getInstance();
        assertEquals(expResult, result);
        result.shutdown();
    }

    /**
     * Test of updateHost method, of class HostViewerController.
     */
    @Test
    public void testUpdateHost() {
        CollAHost host = new Host();
        host.setName("host");
        CollAHost result = null;
        HostViewerController instance = null;
        try {
            instance = HostViewerController.setup();
            instance.updateHost(host);
            result = instance.getHost();
        } catch (Exception ex) {
            fail(ex.toString());
        }
        assertEquals(host, result);
        instance.shutdown();

    }

    /**
     * Test of displayStatus method, of class HostViewerController.
     */
    @Test
    public void testDisplayStatus() {
        HostViewerController instance;
        try {
            instance = HostViewerController.setup();
            instance.displayStatus("display status test");
            instance.shutdown();
        } catch (Exception ex) {
            fail(ex.toString());
        }

    }

    /**
     * Test of deleteDir method, of class HostViewerController.
     */
    @Test
    public void testDeleteDir() {
        File dir = new File("deleteTest/");
        dir.mkdir();
        HostViewerController instance = null;
        try {
            instance = HostViewerController.setup();
        } catch (Exception ex) {
            fail(ex.toString());
        }
        boolean expResult = true;
        boolean result = instance.deleteDir(dir);
        assertEquals(expResult, result);
        instance.shutdown();
    }

    /**
     * Test of shutdown method, of class HostViewerController.
     */
    @Test
    public void testShutdown() {
        HostViewerController instance = null;
        try {
            instance = HostViewerController.setup();
        } catch (Exception ex) {
            fail(ex.toString());
        }
        instance.shutdown();
        assertNull(instance.getHost());
    }    

    /**
     * Test of getHost method, of class HostViewerController.
     */
    @Test
    public void testGetHost() {
       HostViewerController instance = null;
        try {
            instance = HostViewerController.setup();
        } catch (Exception ex) {
            fail(ex.toString());
        }
        CollAHost expResult = null;
        CollAHost result = instance.getHost();
        assertEquals(expResult, result);
        instance.shutdown();
    }

    /**
     * Test of getServerIPaddress method, of class HostViewerController.
     */
    @Test
    public void testGetServerIPaddress() {
        System.out.println("getServerIPaddress");
        HostViewerController instance = null;
        try {
            instance = HostViewerController.setup();
        } catch (Exception ex) {
            fail(ex.toString());
        }
        String expResult = "127.0.0.1";
        String result = instance.getServerIPaddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of getServerPortNumber method, of class HostViewerController.
     */
    @Test
    public void testGetServerPortNumber() {
        HostViewerController instance = null;
        try {
            instance = HostViewerController.setup();
        } catch (Exception ex) {
            fail(ex.toString());
        }
        int expResult = 9999;
        int result = instance.getServerPortNumber();
        assertEquals(expResult, result);
    }
}