package colla.kernel.api;

import colla.kernel.exceptions.server.NonExistentUser;
import colla.kernel.exceptions.server.UserAlreadyExists;
import java.net.Socket;
import java.util.Map;
import java.util.Set;

/**
 * Server management.
 * 
 * @author dmatos
 */
public interface CollAServer {
    
    /**
     *
     * @return the port number to which server is listening
     */
    public int getPortNumber();

    /**
     * A value for timeout should be defined in the constructor.
     * 
     * @return timeout value
     */
    public int getTimeoutValue();

    /**
     *
     * @param userName name of the user
     *
     * @return an user, if any, with the given name, or null if there is none.
     */
    public CollAUser getUser(String userName) throws NonExistentUser;

    /**
     *
     * @return a set with the names of each client signed for the server.
     */
    public Set<String> getUsersSet();

    /**
     * Updates a host data to the server
     *
     * @param host CollAHost to update
     */
    public void updateHost(CollAHost host) throws NonExistentUser;
    
    /**
     * Registers a new client to the server.
     * @param usr Instance of client to register.
     * @throws UserAlreadyExists 
     */
    public void addUser(CollAUser usr) throws UserAlreadyExists;

    /**
     * Updaties data for an existing client.
     * 
     * @param usr client to update
     */
    public void updateUser(CollAUser usr) throws NonExistentUser;
    
    /**
     * Performs server data persistence.
     * 
     * @throws Exception 
     */
    public void storeAllServerData() throws Exception;
    
    /**
     * Sets the user password
     * @param userName key to the password
     * @param password the new password
     */
    public void setUserPassword(String userName, String password);
    
    /**
     * Updates data of a group.
     * @param groupName the name of the group that will be updated
     * @param group the group that will be updated
     */
    public void updateGroup(String groupName, CollAGroup group);
    
    /**
     * @param groupName name of the group to be located.
     * @return the group, if any, with the given name, or null if there is none.
     */
    public CollAGroup getGroup(String groupName);
    
    /**
     * @return a set of names of all the groups registered in the server.
     */
    public Set<String> getAllGroups();
    
    /**
     * Generates an ID to identify an user session
     * @return a long to identify the current session of an user
     */
    public long generateSessionID();
    
    /**
     * Changes the status of every client to offline.
     */
    public void disconnectAllClients();
    
    /**
     * @param userName name of the user
     * @return the password of a client with the given name, or null if there is none.
     */
    public String getUserPassword(String userName);
    
    /**
     * Generates an ID to identify a task
     * @return a long to identify a task
     */
    public Long generateTaskID();
    
    /**
     * 
     * @return a Map of sockets connected to clients that may be either hosts or users.
     * These connections must be kept alive in order to comunicate to those clients.
     */
    public Map<String, Socket> getMappedConnections();
    
    /**
     * Disables the Server.
     */
    public void shutdown();
    
}
