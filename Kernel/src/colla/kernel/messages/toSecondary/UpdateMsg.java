/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toSecondary;

import colla.kernel.api.CollAMessage;
import java.io.Serializable;

/**
 *
 * @author dmatos
 */
public interface UpdateMsg{   
    
    public Object getUpdate();
    
    public Enum getOperation();
    
}
