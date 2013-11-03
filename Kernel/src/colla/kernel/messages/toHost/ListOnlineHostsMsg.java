/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toHost;

import colla.kernel.api.CollAHost;
import colla.kernel.api.CollAMessage;
import colla.kernel.enumerations.HostOps;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author dmatos
 */
public class ListOnlineHostsMsg implements Serializable, CollAMessage{
    
    public ListOnlineHostsMsg(){
        this.operation = HostOps.RECEIVE_ONLINE_HOSTS;
        this.list = new LinkedList<CollAHost>();
    }
    
    public void addHost(CollAHost host){
        list.add(host);
    }
    
    public LinkedList<CollAHost> getHosts(){
        return this.list;
    }    
    
    @Override
    public Enum getOperation() {
        return this.operation;
    }

    
    private final Enum operation;
    private LinkedList<CollAHost> list;      
    private static final long serialVersionUID = 1L;
}
