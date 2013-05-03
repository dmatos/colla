/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toServer;

import java.io.Serializable;
import colla.kernel.enumerations.ServerOps;
import colla.kernel.impl.User;
import colla.kernel.api.CollAMessage;
import colla.kernel.api.CollAUser;

/**
 *
 * @author dmatos
 */
public class ClientSignUpMsg implements Serializable, CollAMessage{
    
    public ClientSignUpMsg(){
        this.operation = ServerOps.SIGN_UP;
    }
    
     public ClientSignUpMsg(CollAUser user, String password){
        this.user = user;
        this.password = password;
        this.operation = ServerOps.SIGN_UP;
    }
    
    public void setUser(CollAUser user){
        this.user = user;
    }
    
    public CollAUser getUser(){
        return this.user;
    }
    
    public void setPassword(String password){
        this.password = password;
    }    
    
    public String getPassword(){
        return this.password;
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

    private String sender;
    private final Enum operation;
    private String password;
    private CollAUser user;
}
    
