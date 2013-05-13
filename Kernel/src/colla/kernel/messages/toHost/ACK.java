/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toHost;

import java.io.Serializable;

import colla.kernel.api.CollAMessage;
import colla.kernel.enumerations.HostOps;

/**
 *
 * @author bruno
 */
public class ACK implements Serializable, CollAMessage{
    public ACK(){
        this.operation = HostOps.ACK;
    }
    
    @SuppressWarnings("rawtypes")
	@Override
    public Enum getOperation(){
        return this.operation;
    }
    
    @SuppressWarnings("rawtypes")
	private final Enum operation;    
 	private static final long serialVersionUID = 1L;
}
