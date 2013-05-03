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
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public class Ping implements CollAMessage, Serializable{
    private long id;
    
    public Ping(long id){
        this.id = id;
    }
    
    public long getID(){
        return this.id;
    }

    @Override
    public Enum getOperation() {
        return ClientOps.PING;
    }
    
}
