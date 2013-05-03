/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toServer;

import java.io.Serializable;
import colla.kernel.api.CollAMessage;
import colla.kernel.api.CollATask;
import colla.kernel.api.CollAUser;
import colla.kernel.enumerations.ServerOps;
import colla.kernel.impl.User;
import java.util.ArrayList;

/**
 *
 * @author dmatos
 */
public final class TransmitTaskMsg implements Serializable, CollAMessage {

    public TransmitTaskMsg() {        
        this.list = new ArrayList<byte[]>();
        this.nameList = new ArrayList<String>();
        this.operation = ServerOps.TRANSMIT_TASK;
        this.task = null;
    }
    public void setTask(CollATask task) {
        this.task = task;
    }

    public CollATask getTask() {
        return this.task;
    }

    public void addAttach(byte[] attachBuffer, String attachName) {        
        list.add(attachBuffer);
        nameList.add(attachName);
    }
    
    public void setGroup(String groupName){
        this.groupName = groupName;
    }
    
    public String getGroup(){
        return this.groupName;
    }

    public void setReceiver(String rcvr) {
        this.receiver = rcvr;
    }
    
    public String getReceiver(){
        return this.receiver;
    }
    
    public void setUser(CollAUser usr){
        this.user = usr;
        this.sender = usr.getName();
    }
    
    public CollAUser getUser(){
        return this.user;
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

    public ArrayList<byte[]> getAttaches() {
        return this.list;
    }

    public ArrayList<String> getAttachNames() {
        return this.nameList;
    }
    
    private String groupName;
    private CollAUser user;
    private ArrayList<byte[]> list;
    private ArrayList<String> nameList;
    private String receiver;
    private String sender;
    private CollATask task;
    private final Enum operation;
}
