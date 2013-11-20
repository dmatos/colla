/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.impl;

import colla.kernel.api.CollATicket;
import interfaces.kernel.JCL_result;
import java.io.File;
import java.util.Date;
import java.util.Map;
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
public class TaskTest {
    
    public TaskTest() {
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
     * Test of setTaskName method, of class Task.
     */
    @Test
    public void testSetTaskName() {
        System.out.println("setTaskName");
        String name = "";
        Task instance = new Task();
        instance.setTaskName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTaskName method, of class Task.
     */
    @Test
    public void testGetTaskName() {
        System.out.println("getTaskName");
        Task instance = new Task();
        String expResult = "";
        String result = instance.getTaskName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTask method, of class Task.
     */
    @Test
    public void testSetTask() throws Exception {
        System.out.println("setTask");
        File taskFile = null;
        Task instance = new Task();
        instance.setTask(taskFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTask method, of class Task.
     */
    @Test
    public void testGetTask() {
        System.out.println("getTask");
        Task instance = new Task();
        byte[] expResult = null;
        byte[] result = instance.getTask();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClassToExecute method, of class Task.
     */
    @Test
    public void testSetClassToExecute() {
        System.out.println("setClassToExecute");
        String className = "";
        Task instance = new Task();
        instance.setClassToExecute(className);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClassToExecute method, of class Task.
     */
    @Test
    public void testGetClassToExecute() {
        System.out.println("getClassToExecute");
        Task instance = new Task();
        String expResult = "";
        String result = instance.getClassToExecute();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMethodToExecute method, of class Task.
     */
    @Test
    public void testSetMethodToExecute() {
        System.out.println("setMethodToExecute");
        String methodName = "";
        Task instance = new Task();
        instance.setMethodToExecute(methodName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMethodToExecute method, of class Task.
     */
    @Test
    public void testGetMethodToExecute() {
        System.out.println("getMethodToExecute");
        Task instance = new Task();
        String expResult = "";
        String result = instance.getMethodToExecute();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addDependency method, of class Task.
     */
    @Test
    public void testAddDependency() throws Exception {
        System.out.println("addDependency");
        File file = null;
        Task instance = new Task();
        instance.addDependency(file);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDependencies method, of class Task.
     */
    @Test
    public void testGetDependencies() {
        System.out.println("getDependencies");
        Task instance = new Task();
        Map expResult = null;
        Map result = instance.getDependencies();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStarted method, of class Task.
     */
    @Test
    public void testSetStarted() {
        System.out.println("setStarted");
        Task instance = new Task();
        instance.setStarted();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isFinished method, of class Task.
     */
    @Test
    public void testIsFinished() {
        System.out.println("isFinished");
        Task instance = new Task();
        boolean expResult = false;
        boolean result = instance.isFinished();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFinished method, of class Task.
     */
    @Test
    public void testSetFinished() {
        System.out.println("setFinished");
        Task instance = new Task();
        instance.setFinished();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalTime method, of class Task.
     */
    @Test
    public void testGetTotalTime() {
        System.out.println("getTotalTime");
        Task instance = new Task();
        String expResult = "";
        String result = instance.getTotalTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInitialTime method, of class Task.
     */
    @Test
    public void testGetInitialTime() {
        System.out.println("getInitialTime");
        Task instance = new Task();
        String expResult = "";
        String result = instance.getInitialTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFinalTime method, of class Task.
     */
    @Test
    public void testGetFinalTime() {
        System.out.println("getFinalTime");
        Task instance = new Task();
        String expResult = "";
        String result = instance.getFinalTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalTimeInNanoS method, of class Task.
     */
    @Test
    public void testGetTotalTimeInNanoS() {
        System.out.println("getTotalTimeInNanoS");
        Task instance = new Task();
        Long expResult = null;
        Long result = instance.getTotalTimeInNanoS();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setResult method, of class Task.
     */
    @Test
    public void testSetResult() throws Exception {
        System.out.println("setResult");
        JCL_result jclr = null;
        Task instance = new Task();
        instance.setResult(jclr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResult method, of class Task.
     */
    @Test
    public void testGetResult() {
        System.out.println("getResult");
        Task instance = new Task();
        Object expResult = null;
        Object result = instance.getResult();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFileFromResult method, of class Task.
     */
    @Test
    public void testGetFileFromResult() throws Exception {
        System.out.println("getFileFromResult");
        Task instance = new Task();
        File expResult = null;
        File result = instance.getFileFromResult();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOwner method, of class Task.
     */
    @Test
    public void testSetOwner() {
        System.out.println("setOwner");
        String name = "";
        Task instance = new Task();
        instance.setOwner(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOwner method, of class Task.
     */
    @Test
    public void testGetOwner() {
        System.out.println("getOwner");
        Task instance = new Task();
        String expResult = "";
        String result = instance.getOwner();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGroup method, of class Task.
     */
    @Test
    public void testGetGroup() {
        System.out.println("getGroup");
        Task instance = new Task();
        String expResult = "";
        String result = instance.getGroup();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGroup method, of class Task.
     */
    @Test
    public void testSetGroup() {
        System.out.println("setGroup");
        String group = "";
        Task instance = new Task();
        instance.setGroup(group);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addArgument method, of class Task.
     */
    @Test
    public void testAddArgument() throws Exception {
        System.out.println("addArgument");
        File file = null;
        Task instance = new Task();
        instance.addArgument(file);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArguments method, of class Task.
     */
    @Test
    public void testGetArguments() throws Exception {
        System.out.println("getArguments");
        Task instance = new Task();
        Object[] expResult = null;
        Object[] result = instance.getArguments();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasTicket method, of class Task.
     */
    @Test
    public void testHasTicket() {
        System.out.println("hasTicket");
        Task instance = new Task();
        boolean expResult = false;
        boolean result = instance.hasTicket();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTicket method, of class Task.
     */
    @Test
    public void testGetTicket() {
        System.out.println("getTicket");
        Task instance = new Task();
        CollATicket expResult = null;
        CollATicket result = instance.getTicket();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTicket method, of class Task.
     */
    @Test
    public void testSetTicket() {
        System.out.println("setTicket");
        CollATicket ticket = null;
        Task instance = new Task();
        instance.setTicket(ticket);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeTicket method, of class Task.
     */
    @Test
    public void testRemoveTicket() {
        System.out.println("removeTicket");
        Task instance = new Task();
        boolean expResult = false;
        boolean result = instance.removeTicket();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTaskID method, of class Task.
     */
    @Test
    public void testSetTaskID() {
        System.out.println("setTaskID");
        Long id = null;
        Task instance = new Task();
        instance.setTaskID(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTaskID method, of class Task.
     */
    @Test
    public void testGetTaskID() {
        System.out.println("getTaskID");
        Task instance = new Task();
        Long expResult = null;
        Long result = instance.getTaskID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clean method, of class Task.
     */
    @Test
    public void testClean() {
        System.out.println("clean");
        Task instance = new Task();
        instance.clean();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSchedule method, of class Task.
     */
    @Test
    public void testGetSchedule() {
        System.out.println("getSchedule");
        Task instance = new Task();
        Date expResult = null;
        Date result = instance.getSchedule();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSchedule method, of class Task.
     */
    @Test
    public void testSetSchedule() {
        System.out.println("setSchedule");
        Date date = null;
        Task instance = new Task();
        instance.setSchedule(date);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasSchedule method, of class Task.
     */
    @Test
    public void testHasSchedule() {
        System.out.println("hasSchedule");
        Task instance = new Task();
        boolean expResult = false;
        boolean result = instance.hasSchedule();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDistributedMode method, of class Task.
     */
    @Test
    public void testSetDistributedMode() {
        System.out.println("setDistributedMode");
        Boolean isDisbributed = null;
        Task instance = new Task();
        instance.setDistributedMode(isDisbributed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDistributed method, of class Task.
     */
    @Test
    public void testIsDistributed() {
        System.out.println("isDistributed");
        Task instance = new Task();
        boolean expResult = false;
        boolean result = instance.isDistributed();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}