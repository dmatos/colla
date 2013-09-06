/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toClient;

import colla.kernel.api.CollAHost;
import colla.kernel.api.CollAMessage;
import colla.kernel.enumerations.ClientOps;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * Send an avaylable host to client
 * @author dmatos
 */
@SuppressWarnings("rawtypes")
public class SendAvailableHostsMsg implements Serializable, CollAMessage{
    
    public SendAvailableHostsMsg(Long taskID){
        this.operation = ClientOps.RECEIVE_AVAILABLE_HOSTS;
        this.list = new LinkedList<CollAHost>();
        this.taskID = taskID;
    }
    
    public void addHost(CollAHost host){
        list.add(host);
    }
    
    public LinkedList<CollAHost> getHosts(){
        return this.list;
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
    
    public Long getTaskID(){
        return this.taskID;
    }

    private String sender;
    private final Enum operation;
    private LinkedList<CollAHost> list;  
    private Long taskID;
    private static final long serialVersionUID = 1L;
}
