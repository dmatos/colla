/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toHost;

import colla.kernel.api.CollAMessage;
import java.io.Serializable;
import colla.kernel.enumerations.ServerOps;
import colla.kernel.api.CollAMessage;

/**
 *
 * @author dmatos
 */
public class ServerHostRegisterAnswerMsg implements CollAMessage, Serializable {

    public ServerHostRegisterAnswerMsg() {
        this.operation = null;
    }

    public ServerHostRegisterAnswerMsg(String hostName) {
        this.operation = null;
        this.hostName = hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostName() {
        return this.hostName;
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
    private String hostName;
    private String sender;
    private final Enum operation;
}