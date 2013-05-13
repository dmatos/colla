/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toClient;

import colla.kernel.api.CollAMessage;
import colla.kernel.enumerations.ClientOps;
import java.io.Serializable;

/**
 *
 * @author bruno
 */
public class ACK implements Serializable, CollAMessage{
   
	private static final long serialVersionUID = 1L;

	public ACK(){
        this.operation = ClientOps.ACK;
    }
    
    @SuppressWarnings("rawtypes")
	@Override
    public Enum getOperation(){
        return this.operation;
    }
    
    @SuppressWarnings("rawtypes")
	private final Enum operation;
}
