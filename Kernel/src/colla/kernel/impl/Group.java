/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.impl;

import colla.kernel.api.CollAGroup;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bruno
 */
public class Group implements Serializable, Comparable<CollAGroup>, CollAGroup{

    private static final long serialVersionUID = 1L;
    private String name;
    private List<String> members;
    private List<String> membersAdmin;
    private List<String> membersWaiting;
    private List<String> membersBlocked;
    

    @Override
    public int compareTo( CollAGroup group ){
        if( this.name.equals( group.getName() ) ){
            return 0;
        }else if( this.getName().compareTo( group.getName() ) > 0 ){
            return 1;
        }else{
            return -1;
        }
    }
    
    public Group(String groupName){
        this.setName( groupName );
        this.members = new ArrayList<String>();
        this.membersAdmin = new ArrayList<String>();
        this.membersBlocked = new ArrayList<String>();
        this.membersWaiting = new ArrayList<String>();
    }
    
    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public void setName( String name ){
        this.name = name;
    }

    @Override
    public boolean addMember( String memberName ){
        if( !this.members.contains( memberName ) ){
            this.members.add( memberName );
            return true;
        }
        else 
            return false;
    }

    @Override
    public boolean removeMember( String memberName ){
        if( this.members.contains( memberName ) ){
            this.members.remove( memberName );
            return true;
        }
        else 
            return false;
    }
    
    @Override
    public boolean addMemberToWaitingList(String memberName){
        if( !this.membersWaiting.contains( memberName ) ){
            this.membersWaiting.add( memberName );
            return true;
        }
        else 
            return false;
    }
    
    @Override
    public boolean remMemberFromWaitingList(String memberName){
        if( this.membersWaiting.contains( memberName ) ){
            this.membersWaiting.remove( memberName );
            return true;
        }
        else 
            return false;
    }
    @Override
    public boolean addMemberToBlockedList(String memberName){
        if( !this.membersBlocked.contains( memberName ) ){
            this.membersBlocked.add( memberName );
            return true;
        }
        else 
            return false;
    }
    
    @Override
    public boolean remMemberFromBlockedList(String memberName){
        if( this.membersBlocked.contains( memberName ) ){
            this.membersBlocked.remove( memberName );
            return true;
        }
        else 
            return false;
    }

    @Override
    public boolean addAdmin( String memberName ){
        if( !this.membersAdmin.contains( memberName ) ){
            this.membersAdmin.add( memberName );
            return true;
        }
        else 
            return false;
    }

    @Override
    public boolean removeAdmin( String memberName ){
        if( this.membersAdmin.contains( memberName ) ){
            this.membersBlocked.remove( memberName );
            return true;
        }
        else 
            return false;
    }

    @Override
    public List<String> getMembers(){
        return this.members;
    }

    @Override
    public List<String> getWaitingList() {
        return this.membersWaiting;
    }

    @Override
    public List<String> getBlockedList() {
        return this.membersBlocked;
    }

    @Override
    public List<String> getAdminsList() {
        return this.membersAdmin;
    }
}
