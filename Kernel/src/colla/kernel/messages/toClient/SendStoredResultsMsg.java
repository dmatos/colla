/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toClient;

import colla.kernel.api.CollAMessage;
import colla.kernel.enumerations.ClientOps;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author dmatos
 */
public class SendStoredResultsMsg implements CollAMessage, Serializable{

    public SendStoredResultsMsg(ArrayList<TaskResultMsg> results){
        this.operation = ClientOps.RECEIVE_STORED_RESULTS;
        this.results = results;
    }    
    
    public ArrayList<TaskResultMsg> getResults(){
        return this.results;
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
    private ArrayList<TaskResultMsg> results;
    
}
