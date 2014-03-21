/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toSecondary;

import colla.kernel.enumerations.ServerOps;
import colla.kernel.impl.WeightedHost;
import java.io.Serializable;

/**
 *
 * @author dmatos
 */
public class UpdateWeightedHostMsg implements Serializable, UpdateMsg {
    
    public UpdateWeightedHostMsg(WeightedHost wHost){
        this.wHost = wHost;
    }
    
    @Override
    public Enum getOperation(){
        return ServerOps.UPDATE_WEIGHTED_HOST;
    }           

    @Override
    public WeightedHost getUpdate() {
        return this.wHost;
    }
    
    
    private WeightedHost wHost;
    
}
