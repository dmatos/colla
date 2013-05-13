/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toClient;

import colla.kernel.api.CollAGroup;
import colla.kernel.api.CollAMessage;
import colla.kernel.api.CollAUser;
import colla.kernel.enumerations.ClientOps;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Send confirmation of joining a group to client
 * @author dmatos
 */
@SuppressWarnings("rawtypes")
public class JoinAGroupAnswerMsg implements Serializable, CollAMessage{
	
    
    public JoinAGroupAnswerMsg( CollAGroup group ){
        this.operation = ClientOps.RECEIVE_A_GROUP;
        this.group = group;
    }
    
    public void setGroupName(String groupName){
        this.group.setName( groupName );
    }
    
    public String getGroupName(){
        return this.group.getName();
    }

    public CollAGroup getGroup(){
        return group;
    }

    public void setGroup( CollAGroup group ){
        this.group = group;
    }
    
    public void setUsersMap(HashMap<String, CollAUser> usersMap){
       this.usersMap = usersMap;
    }   
    
    public HashMap<String, CollAUser> getUsersMap(){
        return this.usersMap;
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
    private HashMap<String, CollAUser> usersMap;
    private CollAGroup group;
    private static final long serialVersionUID = 1L;
}
