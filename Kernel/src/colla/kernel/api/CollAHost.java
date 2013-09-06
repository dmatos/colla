package colla.kernel.api;

import java.util.HashMap;

/**
 * CollAHost is an application to be executed in determined computers which are
 * available to users to run applications and much more. Only registered users can register
 * hosts on the server.
 * 
 * @author Bruno Hott
 */
public interface CollAHost{

    /**
     * Compares two CollAHosts
     *
     * @param host a CollAHost to be compared to the one that invokes this method.
     *
     * @return 1 if the hosts are equal and 0 otherwise
     */
    public abstract int compareTo(CollAHost host );

    /**	
     * @return operating system properties
     */
    public abstract String getSystemProperties();

    /**
     * Determines that the host has a public IP accessible over the Internet
     */
    public abstract void validateIP();

    /**
     * Determines that the machine doesn't have a public IP accessible over the
     * Internet and may be behind a NAT or firewall.
     */
    public abstract void invalidateIP();

    /**
     *
     * @return true if the machine has a public IP accessible over the Internet,
     * otherwise it returns false
     */
    public abstract boolean hasValidIP();

    /**
     * Sets a name for the host
     *
     * @param name
     */
    public abstract void setName( String name );

    /**
     * Sets the name of the host's owner
     *
     * @param nameUser The name of the host's owner
     */
    public abstract void setNameUser( String nameUser );

    /**
     * Sets a String representing the IP address of the machine in which host is running.
     *
     * @param ip IP address of the machine
     */
    public abstract void setIp( String ip );

    /**
     * Sets port number in which the machine will listen if
     * it has a valid IP
     *
     * @param port a port number
     */
    public abstract void setPort( int port );

    /**
     * Sets online status as true
     */
    public abstract void setOnline();

    /**
     * Sets online status as false
     */
    public abstract void setOffline();

    /**
     * Return either true or false for connection status.
     *
     * @return true if online, else return false.
     */
    public abstract boolean IsOnline();

    /**
     * @return name of the host
     */
    public abstract String getName();

    /**
     * @return name of host's owner
     */
    public abstract String getNameUser();

    /**
     *
     * @return IP address previously set. 
     */
    public abstract String getIp();

    /**
     *
     * @return a port number previously set.
     */
    public abstract int getPort();

    /**
     * Sets current time when connection to server is established.
     */
    public abstract void setInicioConexao();

    /**
     * 
     * @return elapsed time of connection to server.
     */
    public abstract String getTempoTotalConexao();

    /**
     * Records CollAHost activities performed on the server. 
     * 
     * @param date date on which the activity was performed 
     * @param activity description of the activity
     */
    public abstract void recordActivities( String date, String activity );

    /**
     *
     * @param map map of activities performed on the server that has their dates as keys.
     */
    public abstract void setActivities( HashMap<String, String> map );

    /**
     *
     * @return a map of activities performed on the server that has their dates as keys.
     */
    public abstract HashMap<String, String> getActivities();

    /**
     * Sets current country of host.
     * @param cntry 
     */
    public abstract void setCountry( String cntry );

    /**
     * 
     * @return current country of host
     */
    public abstract String getCountry();

    /**
     * @return 
     */
    @Override
    public abstract String toString();
}