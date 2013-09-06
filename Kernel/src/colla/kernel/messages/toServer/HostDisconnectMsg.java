/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toServer;

import java.io.Serializable;

import colla.kernel.api.CollAHost;
import colla.kernel.api.CollAMessage;
import colla.kernel.enumerations.ServerOps;

/**
 *
 * @author dmatos
 */
@SuppressWarnings("rawtypes")
public class HostDisconnectMsg implements CollAMessage, Serializable {

    public HostDisconnectMsg(CollAHost host) {
        this.host = host;
        this.operation = ServerOps.HOST_DISCONNECT;
    }

    public CollAHost getHost(){
        return this.host;
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
    
    private CollAHost host;
    private String sender;
    private final Enum operation;
    private static final long serialVersionUID = 1L;
}
