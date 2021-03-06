/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toClient;

import java.io.Serializable;

import colla.kernel.api.CollAMessage;

/**
 * Send confirmation of signup to a client
 * @author dmatos
 */
@SuppressWarnings("rawtypes")
public class SignUpAnswerMsg implements Serializable, CollAMessage {

    public SignUpAnswerMsg() {
        this.operation = null;
    }

    public void nameAlreadyInUse(Boolean confirmName) {
        this.confirmName = confirmName;
    }
    
    public Boolean getConfirmation(){
        return this.confirmName;
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
    private Boolean confirmName;
    private static final long serialVersionUID = 1L;
}
