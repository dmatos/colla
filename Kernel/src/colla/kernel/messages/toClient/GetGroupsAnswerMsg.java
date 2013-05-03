/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toClient;

import java.io.Serializable;
import java.util.TreeSet;
import colla.kernel.enumerations.ClientOps;
import colla.kernel.api.CollAMessage;

/**
 * Send a list containing the names of all groups to client
 * @author dmatos
 */
public class GetGroupsAnswerMsg implements Serializable, CollAMessage{
    
    public GetGroupsAnswerMsg(){
        this.operation = ClientOps.LIST_GROUPS;
    }
    
    public void setGroupsSet(TreeSet<String> groups){
        this.groups = groups;
    }
    
    public TreeSet<String> getGroupsSet(){
        return this.groups;
    }
    
    @Override
    public Enum getOperation() {
        return this.operation;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return this.sender;
    }

    private String sender;
    private final Enum operation;
    private TreeSet<String> groups;
}
