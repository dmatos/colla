/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toServer;

import java.io.Serializable;
import colla.kernel.enumerations.ServerOps;
import colla.kernel.api.CollAMessage;

/**
 *
 * @author dmatos
 */
public class GetAvailableHostsMsg implements Serializable, CollAMessage {

    public GetAvailableHostsMsg() {
        this.operation = ServerOps.GET_AVAILABLE_HOSTS;
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
    
       public String getGroup(){
        return group;
    }

    public void setGroup( String group ){
        this.group = group;
    }
    private String sender;
    private final Enum operation;
    private String group;
 

}
