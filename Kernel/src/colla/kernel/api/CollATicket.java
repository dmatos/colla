package colla.kernel.api;

/**
 * A CollATicket is a key to fetch results of an application from a CollAHost.
 * 
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public interface CollATicket {
    
    /**
     * 
     * @param ticket a numerical key to fetch a result from a CollAHost
     */
    public void setTicket(Integer ticket);
    
    /**
     * 
     * @return a numerical key to fetch a result from a CollAHost.
     */
    public Integer getTicket();
    
    /**
     * Sets port number to which host is listening.
     * @param port port number to which the host is listening.
     */
    public void setHostPort(Integer port);
    
    /**
     * 
     * @return the port number in which the host is listening.
     */
    public Integer getHostPort();
    
    /**
     * 
     * @param ipAddress IP address of the host which stores the result.
     */
    public void setHostIPaddress(String ipAddress);
   
    /**
     * 
     * @return IP address of the host which stores the result.
     */
    public String getHostIPAddress();
        
}
