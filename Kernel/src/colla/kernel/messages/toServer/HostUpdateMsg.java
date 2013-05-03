/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toServer;

import colla.kernel.api.CollAHost;
import colla.kernel.api.CollAMessage;
import colla.kernel.impl.Host;
import colla.kernel.enumerations.ServerOps;
import java.io.Serializable;

/**
 *
 * @author dmatos
 */
public class HostUpdateMsg implements Serializable, CollAMessage {

    public HostUpdateMsg(CollAHost host) {
        this.host = host;
        this.operation = ServerOps.UPDATE_HOST;
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
    
    private String sender;
    private final Enum operation;
    private CollAHost host;
}
