/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.impl;

import colla.kernel.api.CollAGroup;
import java.util.ArrayList;
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
        CollAGroup group = null;
        Group instanceA = new Group("A");
        Group instanceB = new Group("A");
        int expResult = 0;
        int result = instanceA.compareTo(instanceB);
        assertEquals(expResult, result);
        result = instanceA.compareTo(group);
        expResult = -1;
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Group.
     */
    @Test
    public void testGetName() {
        Group instance = new Group("A");
        String expResult = "A";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Group.
     */
    @Test
    public void testSetName() {
        String name = "B";
        Group instance = new Group("A");
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of addMember method, of class Group.
     */
    @Test
    public void testAddMember() {
        String memberName = "member";
        Group instance = new Group("A");
        boolean expResult = true;
        //add member once = true;
        boolean result = instance.addMember(memberName);
        assertEquals(expResult, result);
        expResult = false;
        //add member twice = false
        result = instance.addMember(memberName);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeMember method, of class Group.
     */
    @Test
    public void testRemoveMember() {
        String memberName = "member";
        Group instance = new Group("A");
        boolean expResult = false;
        boolean result = instance.removeMember(memberName);
        assertEquals(expResult, result);
        expResult = true;
        result = instance.addMember(memberName);
        assertEquals(expResult, result);
        expResult = true;
        result = instance.removeMember(memberName);
        assertEquals(expResult, result);
    }

    /**
     * Test of addMemberToWaitingList method, of class Group.
     */
    @Test
    public void testAddMemberToWaitingList() {
        String memberName = "member";
        Group instance = new Group("A");
        boolean expResult = true;
        boolean result = instance.addMemberToWaitingList(memberName);
        assertEquals(expResult, result);
        expResult = false;
        result = instance.addMemberToWaitingList(memberName);
        assertEquals(expResult, result);
    }

    /**
     * Test of remMemberFromWaitingList method, of class Group.
     */
    @Test
    public void testRemMemberFromWaitingList() {
        String memberName = "member";
        Group instance = new Group("A");
        boolean expResult = true;
        boolean result = instance.addMemberToWaitingList(memberName);
        assertEquals(expResult, result);
        expResult = true;
        result = instance.remMemberFromWaitingList(memberName);
        assertEquals(expResult, result);
        expResult = false;
        result = instance.remMemberFromWaitingList(memberName);
        assertEquals(expResult, result);
    }

    /**
     * Test of addMemberToBlockedList method, of class Group.
     */
    @Test
    public void testAddMemberToBlockedList() {
        String memberName = "member";
        Group instance = new Group("A");
        boolean expResult = true;
        boolean result = instance.addMemberToBlockedList(memberName);
        assertEquals(expResult, result);
        expResult = false;
        result = instance.addMemberToBlockedList(memberName);
        assertEquals(expResult, result);
    }

    /**
     * Test of remMemberFromBlockedList method, of class Group.
     */
    @Test
    public void testRemMemberFromBlockedList() {
        String memberName = "member";
        Group instance = new Group("A");
        boolean expResult = false;
        boolean result = instance.remMemberFromBlockedList(memberName);
        assertEquals(expResult, result);
        expResult = true;
        result = instance.addMemberToBlockedList(memberName);
        assertEquals(expResult, result);
        expResult = true;
        result = instance.remMemberFromBlockedList(memberName);
        assertEquals(expResult, result);
    }

    /**
     * Test of addAdmin method, of class Group.
     */
    @Test
    public void testAddAdmin() {
        String memberName = "admin";
        Group instance = new Group("A");
        boolean expResult = true;
        boolean result = instance.addAdmin(memberName);
        assertEquals(expResult, result);
        expResult = false;
        result = instance.addAdmin(memberName);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeAdmin method, of class Group.
     */
    @Test
    public void testRemoveAdmin() {
        String memberName = "admin";
        Group instance = new Group("A");
        boolean expResult = false;
        boolean result = instance.removeAdmin(memberName);
        assertEquals(expResult, result);
        expResult = true;
        result = instance.addAdmin(memberName);
        assertEquals(expResult, result);
        expResult = true;
        result = instance.removeAdmin(memberName);
        assertEquals(expResult, result);
    }

    /**
     * Test of getMembers method, of class Group.
     */
    @Test
    public void testGetMembers() {
        Group instance = new Group("A");
        List expResult = new ArrayList();
        List result = instance.getMembers();
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of getWaitingList method, of class Group.
     */
    @Test
    public void testGetWaitingList() {
        Group instance = new Group("A");
        List expResult = new ArrayList();
        List result = instance.getWaitingList();
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of getBlockedList method, of class Group.
     */
    @Test
    public void testGetBlockedList() {
        Group instance = new Group("A");
        List expResult = new ArrayList();
        List result = instance.getBlockedList();
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of getAdminsList method, of class Group.
     */
    @Test
    public void testGetAdminsList() {
        Group instance = new Group("A");
        List expResult = new ArrayList();
        List result = instance.getAdminsList();
        assertEquals(expResult.size(), result.size());
    }
}