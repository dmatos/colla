/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toServer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import colla.kernel.api.CollAGroup;
import colla.kernel.api.CollAMessage;
import colla.kernel.enumerations.ServerOps;

/**
 *
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
@SuppressWarnings("rawtypes")
public class UpdateGroupsMsg implements CollAMessage, Serializable{

    public UpdateGroupsMsg(){
        this.groups = new ArrayList<CollAGroup>();
    }
    
    public void addGroup(CollAGroup group){
        this.groups.add(group);
    }
    
    public List<CollAGroup> getGroups(){
        return this.groups;
    }
    
    public void setManager(String usrName){
        this.userName = usrName;
    }
    
    public String getManager(){
        return this.userName;
    }
    
    @Override
    public Enum getOperation() {
        return ServerOps.UPDATE_GROUPS;
    }
    
    private List<CollAGroup> groups;
    private String userName;
    private static final long serialVersionUID = 1L;
}
