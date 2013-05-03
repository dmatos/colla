/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toServer;

import colla.kernel.api.CollAMessage;
import java.io.Serializable;
import colla.kernel.enumerations.ServerOps;
import colla.kernel.api.CollAMessage;

/**
 *
 * @author dmatos
 */
public class TransmitChatMsg implements Serializable, CollAMessage {

    public TransmitChatMsg() {
        super();
        this.operation = ServerOps.TRANSMIT_MESSAGE;
    }

    public TransmitChatMsg(String sndr, String rcvr, String msg) {
        super();
        this.operation = ServerOps.TRANSMIT_MESSAGE;
        this.sender = sndr;
        this.receiver = rcvr;
        this.message = msg;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

   
    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public Enum getOperation() {
        return this.operation;
    }

    
    public String getSender() {
        return this.sender;
    }

    public String getReceiver() {
        return this.receiver;
    }
    
    private final Enum operation;
    private String message;
    private String sender;
    private String receiver;
    private static final long serialVersionUID = 1L;
}
