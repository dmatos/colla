/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toSecondary;

import colla.kernel.api.CollAGroup;
import colla.kernel.api.CollAMessage;
import colla.kernel.enumerations.ServerOps;
import java.io.Serializable;

/**
 *
 * @author dmatos
 */
public class UpdateGroupMsg implements Serializable, UpdateMsg{

    public UpdateGroupMsg(CollAGroup group){
        this.group = group;
    }
    
    @Override
    public Enum getOperation() {
        return ServerOps.CREATE_GROUP;
    }

    @Override
    public CollAGroup getUpdate() {
        return this.group;
    }
    
    private CollAGroup group;
    
}
