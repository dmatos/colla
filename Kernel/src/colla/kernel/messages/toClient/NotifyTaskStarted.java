/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toClient;

import colla.kernel.api.CollAMessage;
import colla.kernel.api.CollATask;
import colla.kernel.enumerations.ClientOps;
import java.io.Serializable;

/**
 *
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public class NotifyTaskStarted implements CollAMessage, Serializable{

    public NotifyTaskStarted(CollATask task){
        this.operation = ClientOps.TASK_START_NOTIFICATION;
        this.task = task;
    }
    
    public void setTaskName(String name){
        this.taskName =  name;
    }
    
    public CollATask getTask(){
        return this.task;
    }
    
    public String getTaskName(){
        return this.taskName;
    }
    
    public void setGroupName(String name){
        this.group = name;
    }
    
    public String getGroupName(){
        return this.group;
    }
    
    @Override
    public Enum getOperation() {
        return this.operation;
    }
 
    private final Enum operation;
    private String taskName;
    private String group;
    CollATask task;
}
