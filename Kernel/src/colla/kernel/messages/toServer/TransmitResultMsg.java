/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toServer;

import colla.kernel.api.CollAMessage;
import colla.kernel.api.CollATask;
import colla.kernel.api.CollAUser;
import colla.kernel.enumerations.ServerOps;
import interfaces.kernel.JCL_result;
import java.io.Serializable;

/**
 *
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public class TransmitResultMsg implements Serializable, CollAMessage{

    public TransmitResultMsg(CollAUser client, String taskName, CollATask task){
        this.operation = ServerOps.TRANSMIT_TASK_RESULT;
        this.task = task;
        this.taskName = taskName;
        this.user = client;
    }
    
    public String getTaskName(){
        return this.taskName;
    }
    
    public void  setGroupName(String groupName){
        this.groupName = groupName;
    }
    
    public String getGroupName(){
        return this.groupName;
    }
    
    public void setReceiver(CollAUser rcvr){
        this.user = rcvr;
    }
    
    public CollAUser getReceiver(){
        return this.user;
    }
    
    public CollATask getResult(){
        return this.task;
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
    private final Enum operation;
    private String sender;    
    private CollAUser user;
    private String taskName;
    private CollATask task;
}
