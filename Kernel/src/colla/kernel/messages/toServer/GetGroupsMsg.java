/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toServer;

import java.io.Serializable;
import colla.kernel.enumerations.ServerOps;
import colla.kernel.api.CollAMessage;

/**
 *
 * @author dmatos
 */
@SuppressWarnings("rawtypes")
public class GetGroupsMsg implements Serializable, CollAMessage{
    
    public GetGroupsMsg(){
        this.operation = ServerOps.GET_GROUPS;
    }
    
    public GetGroupsMsg(String sender){
        this.sender = sender;
        this.operation = ServerOps.GET_GROUPS;
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
    private static final long serialVersionUID = 1L;
    
}
