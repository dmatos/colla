/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toServer;

import java.io.Serializable;

import colla.kernel.api.CollAMessage;
import colla.kernel.api.CollAUser;
import colla.kernel.enumerations.ServerOps;

/**
 *
 * @author dmatos
 */
@SuppressWarnings("rawtypes")
public class DeveloperViewerDisconnectMsg implements Serializable, CollAMessage {
    
    public DeveloperViewerDisconnectMsg(CollAUser user){
        this.operation = ServerOps.DISCONNECT;
        this.user = user;
        this.sender = user.getName();
    }       
      
    public CollAUser getUser(){
        return this.user;
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
    private String sender;
    private CollAUser user;
    private static final long serialVersionUID = 1L;
}
