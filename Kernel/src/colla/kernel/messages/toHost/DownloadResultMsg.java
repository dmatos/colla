/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toHost;

import colla.kernel.api.CollAMessage;
import colla.kernel.enumerations.HostOps;
import java.io.Serializable;

/**
 *
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
@SuppressWarnings("rawtypes")
public class DownloadResultMsg implements CollAMessage, Serializable{    
	private static final long serialVersionUID = 1L;
	private Integer ticket;

    @Override
    public Enum getOperation() {
        return HostOps.DOWNLOAD_RESULT;
    }
    
    public void setTicket(Integer ticket){
        this.ticket = ticket;               
    }
    
    public Integer getTicket(){
        return this.ticket;
    }
    
    
}
