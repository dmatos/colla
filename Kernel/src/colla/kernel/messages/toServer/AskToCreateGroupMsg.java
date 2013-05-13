/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toServer;

import java.io.Serializable;

import colla.kernel.api.CollAMessage;
import colla.kernel.enumerations.ServerOps;

/**
 * Create a group on the server
 * 
 * @author dmatos
 */
@SuppressWarnings("rawtypes")
public class AskToCreateGroupMsg implements Serializable, CollAMessage {

    public AskToCreateGroupMsg() {
        this.operation = ServerOps.CREATE_GROUP;
    }

    public AskToCreateGroupMsg(String groupName, String adminName) {
        this.operation = ServerOps.CREATE_GROUP;
        this.adminName = adminName;
        this.groupName = groupName;
    }

    public void setGroupName(String groupName) {
        addMessageParameter(groupName, 2);
        this.groupName = groupName;
    }

    public void setAdmin(String adminName) {
        addMessageParameter(adminName, 1);
        this.adminName = adminName;
    }

    public String getAdmin() {
        return this.adminName;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void addMessageParameter(Object obj, int index) {
        throw new UnsupportedOperationException("Not supported anymore.");
    }

    public int getParametersCount() {
        return 0;
    }

    @Override
    public Enum getOperation() {
        return this.operation;
    }

    
    public void setSender(String sender) {
        this.adminName = sender;
    }

   
    public String getSender() {
        return this.adminName;
    }
   
    private final Enum operation;
    String adminName;
    String groupName;
    private static final long serialVersionUID = 1L;
}
