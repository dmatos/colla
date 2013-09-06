/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toServer;

import java.io.Serializable;

import colla.kernel.api.CollAMessage;
import colla.kernel.enumerations.ServerOps;

/**
 *
 * @author bruno
 */
@SuppressWarnings("rawtypes")
public class ACK implements Serializable, CollAMessage{
    public ACK(){
        this.operation = ServerOps.ACK;
    }
    
    @Override
    public Enum getOperation(){
        return this.operation;
    }
    
    private final Enum operation;
    private static final long serialVersionUID = 1L;
}
