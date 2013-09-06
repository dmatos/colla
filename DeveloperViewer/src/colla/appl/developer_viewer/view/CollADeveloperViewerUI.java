/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.developer_viewer.view;

import colla.kernel.api.CollAGroup;
import colla.kernel.api.CollATask;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public interface CollADeveloperViewerUI{
    
    public void displayTaskResultInfo(String groupname, String taskname);
    
    public void displayChatMessage(String sender, String message);
    
    public void updateAllGroups(final HashMap<String, CollAGroup> groups);
    
    public void setListOfTasks(HashMap<String, HashMap<String, CollATask>> tasks);
    
    public void listGroupsToJoin(Set<String> groups);
    
    public void refresh_a_group(String groupName, Set<String> group);
    
    public void displayMessage(String message);
}
