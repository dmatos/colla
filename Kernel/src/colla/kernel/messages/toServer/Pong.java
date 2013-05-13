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
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
@SuppressWarnings("rawtypes")
public class Pong implements CollAMessage, Serializable{

    @Override
    public Enum getOperation() {
        return ServerOps.PONG;
    }
    
    private static final long serialVersionUID = 1L;
    
}
