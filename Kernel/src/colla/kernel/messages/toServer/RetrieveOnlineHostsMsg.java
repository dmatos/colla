/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toServer;

import colla.kernel.api.CollAMessage;
import colla.kernel.enumerations.ServerOps;
import java.io.Serializable;

/**
 *
 * @author dmatos
 */
public class RetrieveOnlineHostsMsg implements CollAMessage, Serializable{
    
    public RetrieveOnlineHostsMsg(String sender){
        this.sender = sender;
    }

    String getSender(){
        return this.sender;
    }
    
    @Override
    public Enum getOperation() {
        return ServerOps.GET_LIST_OF_ONLINE_HOSTS;
    }   
    
    private String sender;
    
}
