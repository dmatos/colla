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
public class RetrieveStoredResultsMsg implements CollAMessage, Serializable {

    public RetrieveStoredResultsMsg(String userName) {
        this.sender = userName;
        this.operation = ServerOps.GET_STORED_RESULTS;
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
}
