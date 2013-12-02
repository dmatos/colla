/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.impl;

import colla.kernel.api.CollATicket;
import interfaces.kernel.JCL_result;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String name = "task";
        Task instance = new Task();
        instance.setTaskName(name);
        String result = instance.getTaskName();
        assertEquals(name, result);
    }

    /**
     * Test of getTaskName method, of class Task.
     */
    @Test
    public void testGetTaskName() {
        Task instance = new Task();
        String expResult = "";
        String result = instance.getTaskName();
        assertEquals(expResult, result);
        String name = "task";
        instance.setTaskName(name);
        result = instance.getTaskName();
        assertEquals(name, result);
    }

    /**
     * Test of setTask method, of class Task.
     */
    @Test(expected = java.lang.Exception.class)
    public void testSetTask() throws Exception {
        File taskFile = new File("file");
        Task instance = new Task();
        instance.setTask(taskFile);
    }

    /**
     * Test of getTask method, of class Task.
     */
    @Test
    public void testGetTask() {
        Task instance = new Task();
        byte[] expResult = null;
        byte[] result = instance.getTask();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of setClassToExecute method, of class Task.
     */
    @Test
    public void testSetClassToExecute() {
        String className = "notAClass";
        Task instance = new Task();
        instance.setClassToExecute(className);
        String result = instance.getClassToExecute();
        assertEquals(className, result);
    }

    /**
     * Test of getClassToExecute method, of class Task.
     */
    @Test
    public void testGetClassToExecute() {
        Task instance = new Task();
        String expResult = "";
        String result = instance.getClassToExecute();
        assertEquals(expResult, result);        
    }

    /**
     * Test of setMethodToExecute method, of class Task.
     */
    @Test
    public void testSetMethodToExecute() {
        String methodName = "method";
        Task instance = new Task();
        instance.setMethodToExecute(methodName);
        String result = instance.getMethodToExecute();
        assertEquals(methodName, result);
    }

    /**
     * Test of getMethodToExecute method, of class Task.
     */
    @Test
    public void testGetMethodToExecute() {
        Task instance = new Task();
        String expResult = "";
        String result = instance.getMethodToExecute();
        assertEquals(expResult, result);
    }

    /**
     * Test of addDependency method, of class Task.
     */
    @Test
    public void testAddDependency(){
        File file = new File("nonExistentFile");
        Task instance = new Task();
        try {
            instance.addDependency(file);
        } catch (Exception ex) {
            //do nothing
        }
        int expResult = 0;
        Map<String, byte[]> dependencies = instance.getDependencies();
        int result = dependencies.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDependencies method, of class Task.
     */
    @Test
    public void testGetDependencies() {
        Task instance = new Task();
        Map expResult = new HashMap();
        Map result = instance.getDependencies();
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of setStarted method, of class Task.
     */
    @Test
    public void testSetStarted() {
        Task instance = new Task();
        instance.setStarted();
    }

    /**
     * Test of isFinished method, of class Task.
     */
    @Test
    public void testIsFinished() {
        Task instance = new Task();
        boolean expResult = false;
        boolean result = instance.isFinished();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFinished method, of class Task.
     */
    @Test
    public void testSetFinished() {
        System.out.println("setFinished");
        Task instance = new Task();
        instance.setFinished();
        boolean expResult = true;
        boolean result = instance.isFinished();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTotalTime method, of class Task.
     */
    @Test
    public void testGetTotalTime() {
        Task instance = new Task();
        String expResult = "not finished";
        String result = instance.getTotalTime();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getInitialTime method, of class Task.
     */
    @Test
    public void testGetInitialTime() {
        Task instance = new Task();
        String expResult = null;
        String result = instance.getInitialTime();
        assertEquals(expResult, result);        
    }

    /**
     * Test of getFinalTime method, of class Task.
     */
    @Test
    public void testGetFinalTime() {
        Task instance = new Task();
        String expResult = "not finished yet";
        String result = instance.getFinalTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTotalTimeInNanoS method, of class Task.
     */
    @Test
    public void testGetTotalTimeInNanoS() {
        Task instance = new Task();
        Long expResult = 0L;
        Long result = instance.getTotalTimeInNanoS();
        assertEquals(expResult, result);
    }

    /**
     * Test of setResult method, of class Task.
     */
    @Test
    public void testSetResult(){
        JCL_result jclr = null;
        Task instance = new Task();
        String expResult = "could not execute task";
        try {
            instance.setResult(jclr);
        } catch (FileNotFoundException ex) {
            //do nothing
        } catch (IOException ex) {
            //do nothing
        }
        assertEquals(expResult, instance.getResult());
    }

    /**
     * Test of getResult method, of class Task.
     */
    @Test
    public void testGetResult() {
        Task instance = new Task();
        Object expResult = "waiting result";
        String result = (String) instance.getResult();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFileFromResult method, of class Task.
     */
    @Test
    public void testGetFileFromResult() throws Exception {
        Task instance = new Task();
        File expResult = null;
        File result = instance.getFileFromResult();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOwner method, of class Task.
     */
    @Test
    public void testSetOwner() {
        String name = "owner";
        Task instance = new Task();
        instance.setOwner(name);
        String result = instance.getOwner();
        assertEquals(name, result);
    }

    /**
     * Test of getOwner method, of class Task.
     */
    @Test
    public void testGetOwner() {
        Task instance = new Task();
        String expResult = "";
        String result = instance.getOwner();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGroup method, of class Task.
     */
    @Test
    public void testGetGroup() {
        Task instance = new Task();
        String expResult = "";
        String result = instance.getGroup();
        assertEquals(expResult, result);
    }

    /**
     * Test of setGroup method, of class Task.
     */
    @Test
    public void testSetGroup() {
        System.out.println("setGroup");
        String group = "group";
        Task instance = new Task();
        instance.setGroup(group);
        String result = instance.getGroup();
        assertEquals(group, result);        
    }

    /**
     * Test of addArgument method, of class Task.
     */
    @Test(expected = java.io.FileNotFoundException.class)
    public void testAddArgument() throws Exception {
        File file = new File("nonExistentFile");
        Task instance = new Task();
        instance.addArgument(file);
    }

    /**
     * Test of getArguments method, of class Task.
     */
    @Test
    public void testGetArguments() throws Exception {
        Task instance = new Task();
        Object[] expResult = null;
        Object[] result = instance.getArguments();
        assertArrayEquals(expResult, result);
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
    }

    /**
     * Test of setTicket method, of class Task.
     */
    @Test
    public void testSetTicket() {
        System.out.println("setTicket");
        CollATicket ticket = new Ticket();
        Task instance = new Task();
        instance.setTicket(ticket);
        CollATicket result = instance.getTicket();
        assertEquals(ticket, result);
    }

    /**
     * Test of removeTicket method, of class Task.
     */
    @Test
    public void testRemoveTicket() {
        Task instance = new Task();
        boolean expResult = false;
        boolean result = instance.removeTicket();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTaskID method, of class Task.
     */
    @Test
    public void testSetTaskID() {
        Long id = 0L;
        Task instance = new Task();
        instance.setTaskID(id);
        Long result = instance.getTaskID();
        assertEquals(id, result);
    }

    /**
     * Test of getTaskID method, of class Task.
     */
    @Test
    public void testGetTaskID() {
        Task instance = new Task();
        Long expResult = 0L;
        Long result = instance.getTaskID();
        assertEquals(expResult, result);
    }

    /**
     * Test of clean method, of class Task.
     */
    @Test
    public void testClean() {
        Task instance = new Task();
        instance.clean();
    }

    /**
     * Test of getSchedule method, of class Task.
     */
    @Test
    public void testGetSchedule() {
        Task instance = new Task();
        Date expResult = null;
        Date result = instance.getSchedule();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSchedule method, of class Task.
     */
    @Test
    public void testSetSchedule() {
        Date date = new Date();
        Task instance = new Task();
        instance.setSchedule(date);
        Date result = instance.getSchedule();
        assertEquals(date, result);
    }

    /**
     * Test of hasSchedule method, of class Task.
     */
    @Test
    public void testHasSchedule() {
        Task instance = new Task();
        boolean expResult = false;
        boolean result = instance.hasSchedule();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDistributedMode method, of class Task.
     */
    @Test
    public void testSetDistributedMode() {        
        Task instance = new Task();
        instance.setDistributedMode(false);
        assertFalse(instance.isDistributed());
        instance.setDistributedMode(true);
        assertTrue(instance.isDistributed());
        
    }

    /**
     * Test of isDistributed method, of class Task.
     */
    @Test
    public void testIsDistributed() {
        Task instance = new Task();
        boolean expResult = false;
        boolean result = instance.isDistributed();
        assertEquals(expResult, result);
    }
}