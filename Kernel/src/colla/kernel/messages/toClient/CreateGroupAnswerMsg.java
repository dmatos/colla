/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toClient;

import java.io.Serializable;
import colla.kernel.enumerations.ClientOps;
import colla.kernel.api.CollAMessage;

/**
 * Confirm to client the creation of a group
 * @author dmatos
 */
public class CreateGroupAnswerMsg implements CollAMessage, Serializable {    
        
    public CreateGroupAnswerMsg(String groupName, Boolean confirmation){
        this.operation = ClientOps.GROUP_CREATION_CONFIRMATION;
        this.groupName = groupName;
        this.confirmation = confirmation;
    }
    
    public String getGroupName(){
        return this.groupName;
    }
    
    public boolean getConfirmation(){
        return this.confirmation;
    }

    @SuppressWarnings("rawtypes")
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

    private boolean confirmation;
    private String groupName;
    private String sender;
    @SuppressWarnings("rawtypes")
	private final Enum operation;
 	private static final long serialVersionUID = 1L;
}
