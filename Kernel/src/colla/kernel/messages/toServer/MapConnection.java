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
public class MapConnection implements Serializable, CollAMessage{
    
    public MapConnection(){       
        this.operation = ServerOps.MAP_THE_CONNECTION;
    }
    
    public MapConnection(String userName){             
        this.sender = userName;
        this.operation = ServerOps.MAP_THE_CONNECTION;
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
}
