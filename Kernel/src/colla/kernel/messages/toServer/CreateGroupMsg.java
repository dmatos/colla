/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toServer;

import colla.kernel.api.CollAGroup;
import java.io.Serializable;
import java.util.HashMap;
import colla.kernel.enumerations.ServerOps;
import colla.kernel.api.CollAMessage;
import java.util.Set;

/**
 *
 * @author dmatos
 */
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
}
