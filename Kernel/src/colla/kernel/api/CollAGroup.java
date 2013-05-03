package colla.kernel.api;

import java.util.List;

/**
 * A CollAGroup is a group of users which can collaborate with each other.
 * Members of a group can chat, share machine resources through the
 * CollAHosts and share research results. The creator of a group should become its administrator.
 * A group may have many administrators and new members must be approved by one of them.
 * 
 * @author Bruno Hott
 */
public interface CollAGroup{
    
    /**
     * @return name of the group
     */
    public String getName();
    
    /**
     * Sets a name for the group
     * @param name name of the group
     */
    public void setName(String name);
    
    /**
     * Adds an administrator to the group
     * @param memberName is the administrator username.
     */
    public boolean addAdmin(String memberName);
    
    /**
     * @return a list containing usernames of administrators of the group
     */
    public List<String> getAdminsList();
    
    /**
     * Removes administrator status of an user.
     * 
     * @param memberName name of the user to remove admin status
	 * @return true if successfully removed and false otherwise
     */
    public boolean removeAdmin(String memberName);
    
    /**
     * Adds a new member to the group
     * @param memberName username of the new member
     * @return true if the member was added and false otherwise
     */
    public boolean addMember(String memberName);
    
    /**
     * Removes a member from the group.
     * @param memberName name of the member
     * @return true if the member was removed and false otherwise
     */
    public boolean removeMember(String memberName);
    
     /**
     * @return a list with the name of each member
     */
    public List<String> getMembers();
    
    /**
     * Adds a member to the waiting list
     * @param memberName name of the member
     * @return true if the member was added and false otherwise
     */
    boolean addMemberToWaitingList(String memberName);
    
    /**
     * @return a list of users who wish to become members of the group.
     */
    List<String> getWaitingList();
    
    /**
     * Removes an user from the waiting list
     * @param memberName the member name
     * @return true if the member was removed and false otherwise
     */
    boolean remMemberFromWaitingList(String memberName);
    
    /**
     * Adds a user to the blocked users list.
     * @param memberName the member name.
     * @return true if the member was added and false otherwise
     */
    boolean addMemberToBlockedList(String memberName);
    
    /**
     * @return the blocked users list
     */
    List<String> getBlockedList();
    
    /**
     * Removes a member from the blocked users list
     * @param memberName name of the member
     * @return true if the member was removed and false otherwise
     */
    boolean remMemberFromBlockedList(String memberName);
    
}
