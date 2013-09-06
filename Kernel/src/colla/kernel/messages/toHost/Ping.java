/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toHost;

import colla.kernel.api.CollAMessage;
import colla.kernel.enumerations.HostOps;
import java.io.Serializable;

/**
 *
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
@SuppressWarnings("rawtypes")
public class Ping implements Serializable, CollAMessage{

    @Override
    public Enum getOperation() {
        return HostOps.PING;
    }
    
    private static final long serialVersionUID = 1L;
    
}
