/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toHost;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import colla.kernel.api.CollAMessage;
import colla.kernel.api.CollATask;
import colla.kernel.api.CollAUser;
import colla.kernel.enumerations.HostOps;

/**
 *
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
@SuppressWarnings("rawtypes")
public class TaskMessage implements CollAMessage, Serializable {

    public TaskMessage() {
        this.operation = HostOps.TASK_EXECUTE;
        this.attaches = new ArrayList<byte[]>();
        this.attachNames = new ArrayList<String>();
        this.task = null;
        this.sender = null;
    }

    public void setTask(CollATask task) {
        this.task = task;
    }

    public void setUser(CollAUser usr) {
        this.user = usr;
        this.sender = usr.getName();
    }

    public CollAUser getUser() {
        return this.user;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return this.groupName;
    }

    /**
     * It is necessary because the result should return to all members of the
     * group.
     *
     * @param group
     */
    public void setGroup(HashMap<String, CollAUser> group) {
        this.group = group;
    }

    public HashMap<String, CollAUser> getGroup() {
        return this.group;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return this.sender;
    }

    public CollATask getTask() {
        return this.task;
    }

    public void addAttach(byte[] attach, String attachName) {
        attaches.add(attach);
        attachNames.add(attachName);
    }

    public ArrayList<byte[]> getAttaches() {
        return this.attaches;
    }

    public ArrayList<String> getAttachNames() {
        return this.attachNames;
    }

    @Override
    public Enum getOperation() {
        return this.operation;
    }
    private String groupName;
    private CollATask task;
    private String sender;
    private HashMap<String, CollAUser> group;
    private final Enum operation;
    private ArrayList<byte[]> attaches;
    private ArrayList<String> attachNames;
    private CollAUser user;
    private static final long serialVersionUID = 1L;
}
