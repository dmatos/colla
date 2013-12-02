/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.developer_viewer;

import colla.appl.developer_viewer.exceptions.DeveloperConfigurationException;
import colla.kernel.api.CollAUser;
import colla.kernel.impl.User;
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
public class DevViewerLoginTest {

    public DevViewerLoginTest() {
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
     * Test of getServerIPaddress method, of class DevViewerLogin.
     */
    @Test
    public void testGetServerIPaddress() {
        DevViewerLogin instance = null;
        String result = "";
        try {
            instance = new DevViewerLogin();
            result = instance.getServerIPaddress();
        } catch (DeveloperConfigurationException ex) {
            fail(ex.toString());
        }
        String expResult = "127.0.0.1";
        assertEquals(expResult, result);
    }

    /**
     * Test of getServerPort method, of class DevViewerLogin.
     */
    @Test
    public void testGetServerPort() {
        DevViewerLogin instance = null;
        Integer result = 0;
        try {
            instance = new DevViewerLogin();
            result = instance.getServerPort();
        } catch (DeveloperConfigurationException ex) {
            fail(ex.toString());
        }
        Integer expResult = 9999;
        assertEquals(expResult, result);
    }

    /**
     * Test of getUser method, of class DevViewerLogin.
     */
    @Test
    public void testGetUser() {
        String username = "username";
        CollAUser user = new User();
        user.setName(username);
        DevViewerLogin instance = null;        
        CollAUser result = null;
        try {
            instance = new DevViewerLogin();
            instance.setUser(user);
            result = instance.getUser();
        } catch (DeveloperConfigurationException ex) {
            fail(ex.toString());
        }

        assertEquals(user, result);
    }

    /**
     * Test of displayMessage method, of class DevViewerLogin.
     */
    @Test
    public void testDisplayMessage() {
        System.out.println("displayMessage");
        String message = "";
        DevViewerLogin instance = null;
        try {
            instance = new DevViewerLogin();
            instance.displayMessage(message);
        } catch (DeveloperConfigurationException ex) {
            fail(ex.toString());
        }
    }

    /**
     * Test of setUser method, of class DevViewerLogin.
     */
    @Test
    public void testSetUser() {
        CollAUser user = new User();
        user.setName("username");
        DevViewerLogin instance = null;
        CollAUser result = null;
        try {
            instance = new DevViewerLogin();
            instance.setUser(user);
            result = instance.getUser();
            assertEquals(user, result);
        } catch (DeveloperConfigurationException ex) {
            fail(ex.toString());
        }
    }
}