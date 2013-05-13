/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toServer;

import colla.kernel.api.CollAMessage;
import colla.kernel.enumerations.ServerOps;
import java.io.Serializable;

/**
 *
 * @author dmatos
 */
@SuppressWarnings("rawtypes")
public class JoinAGroupMsg implements Serializable, CollAMessage {

    public JoinAGroupMsg() {       
        this.operation = ServerOps.JOIN_A_GROUP;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    public String getGroupName(){
        return this.groupName;
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
    
    private String groupName;
    private String sender;
    private final Enum operation;
    private static final long serialVersionUID = 1L;
}
