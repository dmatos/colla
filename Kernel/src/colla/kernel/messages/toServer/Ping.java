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
public class Ping implements Serializable, CollAMessage{

    @Override
    public Enum getOperation() {
        return ServerOps.PING;
    }
    
}
