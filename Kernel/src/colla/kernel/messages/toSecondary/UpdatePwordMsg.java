/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toSecondary;

import colla.kernel.enumerations.ServerOps;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dmatos
 */
public class UpdatePwordMsg implements Serializable, UpdateMsg{

    
    public UpdatePwordMsg(String username, String pword){
        this.updates = new ArrayList<>(2);
        this.updates.add(0, username);
        this.updates.add(1, pword);
    }
    
    @Override
    public Object getUpdate() {
        return this.updates;
    }

    @Override
    public Enum getOperation() {
        return ServerOps.SIGN_UP;
    }
    
    List<String> updates;
}
