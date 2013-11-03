/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toHost;

import colla.kernel.api.CollAMessage;
import colla.kernel.enumerations.HostOps;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dmatos
 */
public class RegisterFileMsg implements CollAMessage, Serializable{

    public RegisterFileMsg(TaskMessage taskMsg){
        this.taskMsg = taskMsg;
    }
    
    public TaskMessage getTaskMsg(){
        return this.taskMsg;
    }
    
    @Override
    public Enum getOperation() {
        return HostOps.REGISTER_FILE;
    }
    
    private TaskMessage taskMsg;
    
}
