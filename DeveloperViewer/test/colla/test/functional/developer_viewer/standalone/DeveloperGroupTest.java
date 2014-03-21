/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.test.functional.developer_viewer.standalone;

import colla.appl.developer_viewer.DevViewerLogin;
import colla.appl.developer_viewer.DeveloperViewerController;
import colla.appl.developer_viewer.exceptions.DeveloperConfigurationException;
import colla.appl.server.Server;
import colla.kernel.api.CollAGroup;
import colla.kernel.api.CollAUser;
import colla.kernel.impl.User;
import colla.kernel.util.Debugger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openide.util.Exceptions;

/**
 *
 * @author dmatos
 */
public class DeveloperGroupTest {

    @Test
    public void testJoinGroup() {
        /*CREATING USER A*/
        String groupName = "GroupForTest";
        /*sign user up for the server*/
        devViewerLogin.setUser(userA);
        try {
            assertFalse(devViewerLogin.signUpForServer(password));
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

        try {
            devViewer = devViewerLogin.logInServer(userA.getName(), password);
            assertNotNull(devViewer);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

        try {
            /*wait asynchronous reponse from server              
             *while establishing connections
             */
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        }

        //creates a nes group
        devViewer.createGroup(groupName);

        try {
            //wait asynchronous reponse from server
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        }

        //user must have a group
        assertEquals(1, devViewer.getUser().getGroups().size());

        devViewer.shutdown();

        /*CREATING USER B*/
        /*sign user up for the server*/
        devViewerLogin.setUser(userB);
        try {
            assertFalse(devViewerLogin.signUpForServer(password));
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

        try {
            devViewer = devViewerLogin.logInServer(userB.getName(), password);
            assertNotNull(devViewer);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

        try {    /*wait asynchronous reponse from server */
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        }

        devViewer.joinGroup(groupName);

        try {    //wait asynchronous reponse from server
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        }

        devViewer.shutdown();

        /*sign user up for the server*/
        devViewerLogin.setUser(userA);

        try {
            devViewer = devViewerLogin.logInServer(userA.getName(), password);
            assertNotNull(devViewer);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

        //userA must accept userB
        try {
            //wait asynchronous reponse from server
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        }

        userA = devViewer.getUser();

        HashMap<String, CollAGroup> groups = userA.getGroups();

        CollAGroup group = groups.get(groupName);

        List<String> waitingMembers = group.getWaitingList();

        assertEquals(1, group.getAdminsList().size());
        assertTrue(group.getAdminsList().contains(userA.getName()));
        assertEquals(1, group.getMembers().size());
        assertEquals(1, waitingMembers.size());

        HashMap<String, List<String>> accepted = new HashMap<String, List<String>>();
        HashMap<String, List<String>> refused = new HashMap<String, List<String>>();

        //accept any member from the waiting list
        List<String> tempWaiting = new ArrayList<String>();
        tempWaiting.addAll(waitingMembers);
        accepted.put(groupName, tempWaiting);

        devViewer.manageGroups(accepted, refused);

        try {
            //wait asynchronous reponse from server
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        }

        userA = devViewer.getUser();

        groups = userA.getGroups();

        group = groups.get(groupName);

        assertEquals(2, group.getMembers().size());

        devViewer.shutdown();

        /*sign user up for the server*/
        devViewerLogin.setUser(userB);

        try {
            devViewer = devViewerLogin.logInServer(userB.getName(), password);
            assertNotNull(devViewer);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

        try {
            //wait asynchronous reponse from server
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        }

        //userB must have a group
        assertEquals(1, devViewer.getUser().getGroups().size());

        devViewer.shutdown();
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
        Debugger.setDebugger(true);
        try {
            /* at this point all server data should have been wiped out.*/
            deleteDir(new File("data/"));
            collaServer = Server.setupServer(40000, true);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        Thread thr = new Thread(collaServer);
        thr.start();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws DeveloperConfigurationException {
        devViewerLogin = new DevViewerLogin();

        /*a new user to register to the CollA server*/
        userA = new User();
        userA.setName("testerA");
        userA.setCountry("test");
        userA.setEmail("mail@mail.com");
        userA.setCompany("UFOP");

        /*a new user to register to the CollA server*/
        userB = new User();
        userB.setName("testerB");
        userB.setCountry("test");
        userB.setEmail("mail@mail.com");
        userB.setCompany("UFOP");
    }

    @After
    public void tearDown() {
        devViewerLogin = null;
        userA = null;
        userB = null;
    }
    private DeveloperViewerController devViewer;
    private DevViewerLogin devViewerLogin;
    private CollAUser userA;
    private CollAUser userB;
    private final String password = "password";
    private static Server collaServer;
}
