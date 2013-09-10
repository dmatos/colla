/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.messages.toHost;

import colla.kernel.enumerations.HostOps;

/**
 *
 * @author dmatos
 */
public class DistributedTaskMsg extends TaskMessage{
    
    public DistributedTaskMsg(){
        this.operation = HostOps.TASK_EXECUTE_DISTRIBUTED;
    }
    
    @Override
    public Enum getOperation(){
        return this.operation;
    }
    
    private final Enum operation;
}
