/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.test.functional.developer_viewer.online;

import colla.appl.developer_viewer.DevViewerLogin;
import colla.appl.developer_viewer.DeveloperViewerController;
import colla.appl.developer_viewer.exceptions.DeveloperConfigurationException;
import colla.appl.server.Server;
import colla.kernel.api.CollAUser;
import colla.kernel.impl.User;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openide.util.Exceptions;

/**
 * The CollAServer must have no data before test and the server_conf
 * must be configured.
 * 
 * @author dmatos
 */
public class DevViewerLoginTest {

    /**
     * Test for users register and login to the CollA server.
     */
    @Test
    public void testRegisterAndLogin() {
        /*sign user up for the server*/
        devViewerLogin.setUser(user);
        try {
            assertFalse(devViewerLogin.signUpForServer(password));
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

        {
            try {
                /*try to sign the same user up for the server again*/
                assertTrue(devViewerLogin.signUpForServer(password));
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
        }

        {
            /*try login with a wrong password*/
            DeveloperViewerController devViewer;
            try {
                devViewer = devViewerLogin.logInServer(user.getName(), "wrong" + password);
                assertNull(devViewer);
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }

        }

        {
            /*try login with an username not registered*/
            DeveloperViewerController devViewer;
            try {
                devViewer = devViewerLogin.logInServer("notregisteredname", password);
                assertNull(devViewer);
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        
        //@todo testar join group

        /*try login with correct password*/
        DeveloperViewerController devViewer;
        try {
            devViewer = devViewerLogin.logInServer(user.getName(), password);
            assertNotNull(devViewer);
            /*logout*/
            devViewer.shutdown();
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

    }
    
     private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();        
    }

    @BeforeClass
    public static void setUpClass() {        
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws DeveloperConfigurationException {
        devViewerLogin = new DevViewerLogin();

        /*a new user to register to the CollA server*/
        user = new User();
        user.setName("tester");
        user.setCountry("test");
        user.setEmail("mail@mail.com");
        user.setCompany("UFOP");
    }

    @After
    public void tearDown() {
        devViewerLogin = null;
        user = null;
    }
    private DevViewerLogin devViewerLogin;
    private CollAUser user;
    private final String password = "password";
    private static Server collaServer;
}