/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toClient;

import java.io.Serializable;
import colla.kernel.enumerations.ClientOps;
import colla.kernel.api.CollAMessage;

/**
 *Ping a client
 * @author dmatos
 */
@SuppressWarnings("rawtypes")
public class PingClientMsg implements Serializable, CollAMessage{
    
    public PingClientMsg(){
        this.operation = ClientOps.PING;
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
