package colla.kernel.api;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * CollAUser is a client who can create groups, join groups, chat, register hosts, submit tasks
 * and start jobs available on CollA.
 *
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public interface CollAUser {
    
	/**
	 * Adds a group to the list of user groups.
	 * @param groupName name of the group
	 * @param group the group itself
	 */
    void addGroup(String groupName, CollAGroup group);

    /**
     * Adds a new host owned by the user to his list of hosts.
     * 
     * @param host a host add
     */
    void addHost(CollAHost host);

    /**
     * Records a job for the current session.
     * @param job
     */
    void addJob(CollAJob job);

    /**
     *
     * @param ses a new session started by the user.
     */
    void addSession(CollASession ses);

    /**
     * Compares two users
     * @param user an user to be compared to one that invokes the method.
     * @return  0 if users are equal, any value different of 0 if not.
     */
    int compareTo(CollAUser user);

    /**
     *
     * @return a map in which dates are keys to user activities.
     */
    HashMap<String, String> getActivities();

    /**
     *
     * @return elapsed time of connection to server.
     */
    String getConnectionTotalTime();

    /**
     *
     * @return a String representing the country of the user.
     */
    String getCountry();

    /**
     *
     * @return user e-mail
     */
    String getEmail();

    /**
     *
     * @return  groups to which user belongs in a map in which the group names are the keys.
     */
    HashMap<String, CollAGroup> getGroups();

    /**
     * @return a set containing all hosts owned by the user.
     */
    Set<CollAHost> getHosts();
    
    /**
     * @param HostName name of the host
     * @return a CollAHost owned by this user with the given name. Returns null if there is none.
     */
    CollAHost getHost(String HostName);

    /**
     *
     * @return IP address as seen by the machine.
     */
    String getIp();

    /**
     *
     * @return last user session.
     */
    CollASession getLastSession();

    /**
     *
     * @return user name
     */
    String getName();

    /**
     *
     * @return port number to which the machine is listening. 
     */
    int getPort();

    /**
     *
     * @return a HashMap containing all user sessions.
     */
    HashMap<String, CollASession> getSessions();

    /**
     *
     * @return if the machine has a public IP accessible over the Internet.
     */
    boolean hasValidIP();

    /**
     * Sets that the machine doesn't have a public IP accessible over the Internet.
     * and may be behind a NAT
     */
    void invalidateIP();

    /**
     *
     * @return true if user is connected to the server, false otherwise
     */
    boolean isOnline();

    /**
     * Stores user activities performed on the server in a map.
     * @param date Date when activity was performed. Dates are the key to retrieve the activities.
     * @param activity A text describing the activity.
     */
    void recordActivities(String date, String activity);

    /**
     *
     * @param map A map of user activities on the server which has dates as keys.
     */
    void setActivities(HashMap<String, String> map);

    /**
     * Sets the date and time when a connection to the server is established.
     */
    void setConnectionInitialized();

    /**
     *
     * @param cntry country of the user
     */
    void setCountry(String cntry);

    /**
     *
     * @param mail user e-mail
     */
    void setEmail(String mail);

    /**
     * Sets a String representing the IP address.
     * @param ip IP address of the machine
     */
    void setIp(String ip);

    /**
     * Sets user name.
     * @param name
     */
    void setName(String name);

    /**
     * Sets connection status to offline.
     */
    void setOffline();

    /**
     * Sets connection status to online.
     */
    void setOnline();

    /**
     * Sets a port number to which the machine will listen if it has a valid IP address.
     * @param port the listening port
     */
    void setPort(int port);

    /**
     * Determines that the user has a public IP accessible over the Internet
     */
    void validateIP();
    
    /**
     * @return a list with user sessions ordered by date
     */
    List<String> getOrderedSessions();
    
    /**
     * 
     * @param company a company to which user belongs
     */
    void setCompany(String company);
    
    /**
     * 
     * @return a company to which user belongs
     */
    String getCompany();
}
