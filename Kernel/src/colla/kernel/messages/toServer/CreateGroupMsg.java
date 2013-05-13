/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toServer;

import java.io.Serializable;

import colla.kernel.api.CollAGroup;
import colla.kernel.api.CollAMessage;
import colla.kernel.enumerations.ServerOps;

/**
 *
 * @author dmatos
 */
@SuppressWarnings("rawtypes")
public class CreateGroupMsg implements Serializable, CollAMessage {

    public CreateGroupMsg() {        
        this.operation = ServerOps.CONFIRM_ADD_GROUP;        
    }

    public CreateGroupMsg(CollAGroup group) {        
        this.operation = ServerOps.CONFIRM_ADD_GROUP;
        this.group = group;
    }

    public void setGroup(CollAGroup group) {
        this.group = group;
    }
    
    public String getGroupName(){
        return this.group.getName();
    }
    
    public CollAGroup getGroup(){
        return this.group;
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
    
    private final Enum operation;
    private CollAGroup group;
    private String sender;
    private static final long serialVersionUID = 1L;
}
