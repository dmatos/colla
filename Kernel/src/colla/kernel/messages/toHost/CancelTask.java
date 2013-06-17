/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toHost;

import colla.kernel.api.CollAMessage;
import colla.kernel.enumerations.HostOps;
import java.io.Serializable;

/**
 * Asks a Host to cancel a scheduled task given a task ID.
 * @author dmatos
 */
public class CancelTask implements CollAMessage, Serializable {

    public CancelTask(Long taskID){
        this.taskID = taskID;
    }
    
    @Override
    public Enum getOperation() {
        return HostOps.TASK_CANCEL;
    }
    
    public long getTaskID(){
        return this.taskID;
    }
    
    private long taskID;
}
