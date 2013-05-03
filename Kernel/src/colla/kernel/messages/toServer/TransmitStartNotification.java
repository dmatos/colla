/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toServer;

import colla.kernel.api.CollAMessage;
import colla.kernel.api.CollATask;
import colla.kernel.enumerations.ServerOps;
import java.io.Serializable;

/**
 *
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public class TransmitStartNotification implements CollAMessage, Serializable{

    public TransmitStartNotification(CollATask task){
        this.operation = ServerOps.TRANSMIT_START_NOTIFICATION;
        this.task = task;
    }
    
    public CollATask getTask(){
        return this.task;
    }
    
    public void setTaskName(String name){
        this.taskName =  name;
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
    private CollATask task;
    
}
