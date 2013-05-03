/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toClient;

import java.io.Serializable;
import colla.kernel.enumerations.ClientOps;
import colla.kernel.api.CollAMessage;

/**
 * Send a message from a client to another one. 
 * Server acts as an intermediate.
 * @author dmatos
 */
public class ChatDirectMessageMsg implements Serializable, CollAMessage{
    
    public ChatDirectMessageMsg(String sender, String message){
        this.operation = ClientOps.MESSAGE;
        this.sender = sender;
        this.message = message;
    }
    
    public String getMessage(){
        return this.message;
    }
    
    public void setMessage(String msg){
        this.message = msg;
    }
    
    public String getSender(){
        return this.sender;
    }

    @Override
    public Enum getOperation() {
        return this.operation;
    }
    
    public void setSender(String sender) {
        this.sender = sender;
    }    
    
    private final Enum operation;
    private String sender;
    private String message;    
    
}
