/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toSecondary;

import colla.kernel.api.CollAHost;
import colla.kernel.enumerations.ServerOps;
import colla.kernel.impl.Host;
import java.io.Serializable;

/**
 *
 * @author dmatos
 */
public class UpdateHostMsg implements Serializable, UpdateMsg {

    public UpdateHostMsg(CollAHost host) {
        this.host = host;
    }

    @Override
    public Enum getOperation() {
        return ServerOps.UPDATE_HOST;
    }

    @Override
    public CollAHost getUpdate() {
        return this.host;
    }
    
    private CollAHost host;
}
