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
@SuppressWarnings("rawtypes")
public class HostLoginMsg implements CollAMessage, Serializable {

    public HostLoginMsg() {
        this.operation = ServerOps.HOST_LOGIN;
    }

    public HostLoginMsg(String sender, String hostName, String IPAddress) {
        this.sender = sender;
        this.hostName = hostName;
        this.ip = IPAddress;
        this.operation = ServerOps.HOST_LOGIN;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setIPAddress(String ip) {
        this.ip = ip;
    }
    
    public String getIPAddress(){
        return this.ip;
    }

    @Override
    public Enum getOperation() {
        return this.operation;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
    
    public String getHostName(){
        return this.hostName;
    }

    public String getSender() {
        return this.sender;
    }
    
    private String hostName;
    private String ip;
    private String sender;
    private final Enum operation;
    private static final long serialVersionUID = 1L;
}
