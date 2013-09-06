/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toClient;

import colla.kernel.api.CollAHost;
import colla.kernel.api.CollAMessage;
import colla.kernel.enumerations.ClientOps;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * Send all hosts of a client to him
 * @author dmatos
 */
@SuppressWarnings("rawtypes")
public class SendOwnedHostsMsg implements Serializable, CollAMessage {

    public SendOwnedHostsMsg() {
        this.operation = ClientOps.RECEIVE_HOST;
        this.list = new LinkedList<CollAHost>();
    }

    public void addHost(CollAHost host) {        
        list.add(host);
    }
    
    public LinkedList<CollAHost> getHosts(){
        return this.list;
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
    private LinkedList<CollAHost> list;
    private static final long serialVersionUID = 1L;
}
