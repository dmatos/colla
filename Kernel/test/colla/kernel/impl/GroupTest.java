/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.impl;

import colla.kernel.api.CollAGroup;
import java.util.List;
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
public class GroupTest {
    
    public GroupTest() {
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
     * Test of compareTo method, of class Group.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        CollAGroup group = null;
        Group instance = null;
        int expResult = 0;
        int result = instance.compareTo(group);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Group.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Group instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Group.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Group instance = null;
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMember method, of class Group.
     */
    @Test
    public void testAddMember() {
        System.out.println("addMember");
        String memberName = "";
        Group instance = null;
        boolean expResult = false;
        boolean result = instance.addMember(memberName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeMember method, of class Group.
     */
    @Test
    public void testRemoveMember() {
        System.out.println("removeMember");
        String memberName = "";
        Group instance = null;
        boolean expResult = false;
        boolean result = instance.removeMember(memberName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMemberToWaitingList method, of class Group.
     */
    @Test
    public void testAddMemberToWaitingList() {
        System.out.println("addMemberToWaitingList");
        String memberName = "";
        Group instance = null;
        boolean expResult = false;
        boolean result = instance.addMemberToWaitingList(memberName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remMemberFromWaitingList method, of class Group.
     */
    @Test
    public void testRemMemberFromWaitingList() {
        System.out.println("remMemberFromWaitingList");
        String memberName = "";
        Group instance = null;
        boolean expResult = false;
        boolean result = instance.remMemberFromWaitingList(memberName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMemberToBlockedList method, of class Group.
     */
    @Test
    public void testAddMemberToBlockedList() {
        System.out.println("addMemberToBlockedList");
        String memberName = "";
        Group instance = null;
        boolean expResult = false;
        boolean result = instance.addMemberToBlockedList(memberName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remMemberFromBlockedList method, of class Group.
     */
    @Test
    public void testRemMemberFromBlockedList() {
        System.out.println("remMemberFromBlockedList");
        String memberName = "";
        Group instance = null;
        boolean expResult = false;
        boolean result = instance.remMemberFromBlockedList(memberName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAdmin method, of class Group.
     */
    @Test
    public void testAddAdmin() {
        System.out.println("addAdmin");
        String memberName = "";
        Group instance = null;
        boolean expResult = false;
        boolean result = instance.addAdmin(memberName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAdmin method, of class Group.
     */
    @Test
    public void testRemoveAdmin() {
        System.out.println("removeAdmin");
        String memberName = "";
        Group instance = null;
        boolean expResult = false;
        boolean result = instance.removeAdmin(memberName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMembers method, of class Group.
     */
    @Test
    public void testGetMembers() {
        System.out.println("getMembers");
        Group instance = null;
        List expResult = null;
        List result = instance.getMembers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWaitingList method, of class Group.
     */
    @Test
    public void testGetWaitingList() {
        System.out.println("getWaitingList");
        Group instance = null;
        List expResult = null;
        List result = instance.getWaitingList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBlockedList method, of class Group.
     */
    @Test
    public void testGetBlockedList() {
        System.out.println("getBlockedList");
        Group instance = null;
        List expResult = null;
        List result = instance.getBlockedList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAdminsList method, of class Group.
     */
    @Test
    public void testGetAdminsList() {
        System.out.println("getAdminsList");
        Group instance = null;
        List expResult = null;
        List result = instance.getAdminsList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}