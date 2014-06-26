/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.test.functional.developer_viewer.online;

import colla.appl.developer_viewer.DevViewerLogin;
import colla.appl.developer_viewer.DeveloperViewerController;
import colla.appl.developer_viewer.exceptions.DeveloperConfigurationException;
import colla.appl.host_viewer.controller.HostViewerRegister;
import colla.appl.server.Server;
import colla.kernel.api.CollATask;
import colla.kernel.api.CollAUser;
import colla.kernel.impl.User;
import colla.kernel.util.Debugger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openide.util.Exceptions;

/**
 *
 * @author dmatos
 */
public class DeveloperViewerTaskTests {

    @Test
    public void createAndSendTaskTest() {

        String groupName = "GroupForTest";
        String testFilesDir = "../user_jars/";

        /*sign user up for the server*/
        devViewerLogin.setUser(user);
        try {
            assertFalse(devViewerLogin.signUpForServer(password));
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

        //setup a host
        hostRegister = new HostViewerRegister();
        hostRegister.register(user.getName(), password, 0);

        try {
            devViewer = devViewerLogin.logInServer(user.getName(), password);
            assertNotNull(devViewer);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

        try {
            /*wait asynchronous reponse from server              
             *while establishing connections
             */
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        }

        //creates a nes group
        devViewer.createGroup(groupName);

        try {
            //wait asynchronous reponse from server
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        }

        //user must have a group
        assertEquals(1, devViewer.getUser().getGroups().size());

        //sending a task (fibonnaci from testes.jar)
        File taskFile = new File(testFilesDir + "testes.jar");
        ArrayList<File> args = new ArrayList<File>();
        ArrayList<File> attaches = new ArrayList<File>();
        args.add(new File(testFilesDir + "testesinput.txt"));
        String classToExecute = "Fibonacci";
        String methodToExecute = "calculate";

        //send task to multicore host
        devViewer.getAvailableHostsOnServer(taskFile, attaches, args, classToExecute, methodToExecute, groupName, null, false);

        while (devViewer.getTasks().isEmpty()) {
            Debugger.debug("waiting parallel task");
            try {
                //wait until it has a result from a host (30 seconds)
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Exceptions.printStackTrace(ex);
            }
        }


        CollATask task = devViewer.getTaskResult(groupName, "[1]testes.jar");
        assertNotNull(task);
        assertEquals("O número de fibonacci posição 6 é 8", task.getResult());

        Debugger.debug("no testes o tamanho de getTask eh " + devViewer.getTasks().size());

        /* Sending task to multicore host.
         * Given that any File sent as argument for a task will
         * be write by a CollAHost, the JCLHost MUST be started
         * in the same absolut path as the CollAHost.
         */
        devViewer.getAvailableHostsOnServer(taskFile, attaches, args, classToExecute, methodToExecute, groupName, null, true);


        Debugger.debug("waiting distributed task for 30 seconds");
        try {
            //wait until it has a result from a host (30 seconds)
            Thread.sleep(30000);
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        }


        task = devViewer.getTaskResult(groupName, "[2]testes.jar");
        assertNotNull(task);
        assertEquals("O número de fibonacci posição 6 é 8", task.getResult());

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
    private DeveloperViewerController devViewer;
    private DevViewerLogin devViewerLogin;
    private CollAUser user;
    private final String password = "password";
    private HostViewerRegister hostRegister;
    private static Server collaServer;
}
