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
@SuppressWarnings("rawtypes")
public class DeveloperViewerLoginMsg implements Serializable, CollAMessage {

    public DeveloperViewerLoginMsg() {
                this.operation =  ServerOps.LOGIN;
       
    }

    public DeveloperViewerLoginMsg(String userName, String password, String ip) {
       
        this.operation =  ServerOps.LOGIN;
        this.sender = userName;
        this.password = password;
        this.ip = ip;
    }

    public void setUserName(String userName) {
        this.sender = userName;
    }

    public void setIP(String ip) {
        this.ip = ip;
    }
    
    public String getIP(){
        return this.ip;
    }

    public void setPassword(String password) {
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
    
    private final Enum operation;
    private String sender;
    String password;
    String userName;
    String ip;
    private static final long serialVersionUID = 1L;
}
