/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toServer;

import colla.kernel.messages.toClient.*;
import colla.kernel.api.CollAMessage;
import colla.kernel.enumerations.ClientOps;
import colla.kernel.enumerations.ServerOps;
import java.io.Serializable;

/**
 *
 * @author bruno
 */
public class ACK implements Serializable, CollAMessage{
    public ACK(){
        this.operation = ServerOps.ACK;
    }
    
    @Override
    public Enum getOperation(){
        return this.operation;
    }
    
    private final Enum operation;
}
