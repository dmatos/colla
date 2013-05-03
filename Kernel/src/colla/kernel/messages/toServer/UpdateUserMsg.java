/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toServer;

import java.io.Serializable;
import colla.kernel.enumerations.ServerOps;
import colla.kernel.api.CollAMessage;
import colla.kernel.api.CollAUser;

/**
 *
 * @author dmatos
 */
public class UpdateUserMsg implements Serializable, CollAMessage {

    public UpdateUserMsg(CollAUser usr) {       
        this.user = usr;
        this.operation = ServerOps.UPDATE_USER;
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
    
    private CollAUser user;
    private String sender;
    private final Enum operation;
}
