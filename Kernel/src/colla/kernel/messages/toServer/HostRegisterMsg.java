/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toServer;

import java.io.Serializable;

import colla.kernel.api.CollAHost;
import colla.kernel.api.CollAMessage;
import colla.kernel.enumerations.ServerOps;

/**
 *
 * @author dmatos
 */
@SuppressWarnings("rawtypes")
public class HostRegisterMsg implements CollAMessage, Serializable {

    public HostRegisterMsg() {
        this.operation = ServerOps.HOST_REGISTER;
    }

    public void setUserName(String userName) {
        this.sender = userName;
    }
    
    public String getUserName(){
        return this.sender;
    }

    public void setUserPass(String userPass) {
        this.pass = userPass;
    }
    
    public String getUserPass(){
        return this.pass;
    }

    public void setHost(CollAHost host) {
        this.host = host;
    }
    
    public CollAHost getHost(){
        return this.host;
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
    
    private String pass;
    private CollAHost host;
    private String sender;
    private final Enum operation;
    private static final long serialVersionUID = 1L;
}
