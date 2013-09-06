/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toClient;

import java.io.Serializable;

import colla.kernel.api.CollAMessage;
import colla.kernel.api.CollATask;
import colla.kernel.enumerations.ClientOps;

/**
 *
 * @author dmatos
 */
@SuppressWarnings("rawtypes")
public final class TaskResultMsg implements Serializable, CollAMessage{

    /**
     * 
     * @param sender 
     * @param taskName
     * @param jclr 
     */
    public TaskResultMsg(String sender,String taskName , CollATask task){
        this.sender = sender;
        this.taskName = taskName;
        this.task = task;
        this.operation = ClientOps.RECEIVE_TASK_RESULT;
        
    }
    
    public void setResult( CollATask task){
        this.task = task;
    }
    
    public CollATask getResult(){
        return this.task;
    }
    
    public void setGroupName(String groupName){
        this.groupName = groupName;
    }
    
    public String getGroupName(){
        return this.groupName;
    }
    
    public String getTaskName(){
        return this.taskName;
    }
    
    @Override
    public Enum getOperation() {
        return this.operation;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return this.sender;
    }

    private String groupName;
    private String sender;
    private final Enum operation;
    private String taskName;
    private CollATask task;
    private static final long serialVersionUID = 1L;
}
