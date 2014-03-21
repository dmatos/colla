/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toSecondary;

import colla.kernel.api.CollAUser;
import colla.kernel.enumerations.ServerOps;
import java.io.Serializable;

/**
 *
 * @author dmatos
 */
public class UpdateUserMsg implements Serializable, UpdateMsg{
    
    public UpdateUserMsg(CollAUser user){
        this.user = user;
    }
    
    @Override
    public Enum getOperation(){
        return ServerOps.UPDATE_USER;
    }

    @Override
    public CollAUser getUpdate() {
        return this.user;
    }
    
    private CollAUser user;
}
