/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.impl;

import colla.kernel.api.CollATicket;
import java.io.Serializable;

/**
 *
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public class Ticket implements CollATicket, Serializable{

	private static final long serialVersionUID = 1L;
	private Integer ticket;
    private Integer hostPort;
    private String hostIPaddress;
    
    public Ticket(){
        this.ticket = -1;
        this.hostPort = -1;
        this.hostIPaddress = "";
    }
    
    @Override
    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    @Override
    public Integer getTicket() {
        return this.ticket;
    }

    @Override
    public void setHostPort(Integer port) {
        this.hostPort = port;
    }

    @Override
    public Integer getHostPort() {
        return this.hostPort;
    }

    @Override
    public void setHostIPaddress(String ipAddress) {
        this.hostIPaddress = ipAddress;
    }

    @Override
    public String getHostIPAddress() {
        return this.hostIPaddress;
    }
    
}
