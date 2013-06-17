/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.host_viewer.controller;

import colla.kernel.api.CollAMessage;
import java.util.TimerTask;

/**
 * Wait the call from a Timer to execute a Task.
 * 
 * @author dmatos
 */
public class ScheduledTask extends TimerTask {

    public ScheduledTask(final CollAMessage message, final GenericResource resource) {
        this.taskMessage = message;
        this.resource = resource;
    }

    @Override
    public void run() {
        this.resource.putRegister(this.taskMessage);
    }
    
    private final CollAMessage taskMessage;
    private final GenericResource resource;
}
