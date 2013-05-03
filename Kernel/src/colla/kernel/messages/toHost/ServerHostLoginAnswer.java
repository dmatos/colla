/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toHost;

import colla.kernel.api.CollAMessage;
import java.io.Serializable;

/**
 *
 * @author dmatos
 */
public class ServerHostLoginAnswer implements CollAMessage, Serializable {

    public ServerHostLoginAnswer(Boolean isConnected, Boolean isValidIP, String realIP) {
        this.operation = null;
        this.connected = isConnected;
        this.validIP = isValidIP;
        this.realIP = realIP;
    }

    public void setIsConnected(Boolean isConnected) {
        this.connected = isConnected;
    }

    public Boolean isConnected() {
        return this.connected;
    }

    public void setIsValidIp(Boolean isValidIP) {
        this.validIP = isValidIP;
    }

    public Boolean isValidIP() {
        return this.validIP;
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
    
    public String getRealIP() {
        return realIP;
    }

    public void setRealIP(String realIP) {
        this.realIP = realIP;
    }
    
    private Boolean connected;
    private Boolean validIP;
    private String sender;
    private final Enum operation;
    private String realIP;

    
}
