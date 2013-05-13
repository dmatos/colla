/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toClient;

import java.io.Serializable;
import java.util.HashMap;

import colla.kernel.api.CollAMessage;
import colla.kernel.api.CollAUser;

/**
 * Confirm login on the DeveloperViewer to client
 * @author dmatos
 */
@SuppressWarnings("rawtypes")
public class DeveloperViewerLoginAnswerMsg implements Serializable, CollAMessage {

    public DeveloperViewerLoginAnswerMsg() {
        this.operation = null;
        groupMembers = new HashMap<String, CollAUser>();
    }

    public void confirmUserName(Boolean confirmName) {
        this.confirmName = confirmName;
    }
    
    public Boolean getNameConfirmation(){
        return this.confirmName;
    }

    public void confirmPassword(Boolean confirmPass) {
        this.confirmPass = confirmPass;
    }
    
    public Boolean getPassConfirmation(){
        return this.confirmPass;
    }

    public void setUser(CollAUser usr) {
        this.user = usr;
    }
    
    public CollAUser getUser(){
        return this.user;
    }
    
    public void setContacts(HashMap<String, CollAUser> contacts){
        this.groupMembers = contacts;
    }    
    
    public HashMap<String, CollAUser> getContacts(){
        return this.groupMembers;
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
    
    private HashMap<String, CollAUser> groupMembers;
    private Boolean confirmName;
    private Boolean confirmPass;
    private String sender;
    private final Enum operation;
    private CollAUser user;
    private static final long serialVersionUID = 1L;
}
