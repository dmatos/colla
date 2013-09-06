/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.developer_viewer.distributed;

import colla.appl.developer_viewer.DeveloperViewerController;
import colla.kernel.api.CollAMessage;
import colla.kernel.api.CollATask;
import colla.kernel.api.CollAUser;
import colla.kernel.api.GenericConsumer;
import colla.kernel.api.GenericResource;
import colla.kernel.messages.toHost.TaskMessage;
import colla.kernel.util.ScheduledTask;
import implementations.util.CoresAutodetect;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

/**
 *
 * @author dmatos
 */
public class DistributedTaskController {

    public DistributedTaskController(DeveloperViewerController devViewer) {
        this.devViewer = devViewer;
        this.timer = new Timer();
        this.scheduleMap = new HashMap<Long, ScheduledTask>();
        int numOfThreads = CoresAutodetect.detect();
        serverThreads = new DeveloperCollAConsumer[numOfThreads];
        serverR = new GenericResource<CollAMessage>();

        for (int i = 0; i < numOfThreads; i++) {
            serverThreads[i] = new DeveloperCollAConsumer<CollAMessage>(serverR, this);
            serverThreads[i].start();
        }
        taskID = 0L;
    }

    public Long executeTask(CollAMessage collAMsg) {
        taskID++;
        TaskMessage taskMessage = (TaskMessage) collAMsg;
        CollATask tempTask = taskMessage.getTask();
        tempTask.setTaskID(taskID);
        taskMessage.setTask(tempTask);
        serverR.putRegister(taskMessage);
        return taskID;
    }

    public synchronized void scheduleTask(CollAMessage collAMessage) {
        Date date = ((TaskMessage) collAMessage).getTask().getSchedule();
        Long taskID = ((TaskMessage) collAMessage).getTask().getTaskID();
        ScheduledTask scheduledTask = new ScheduledTask(collAMessage, this.serverR);
        this.scheduleMap.put(taskID, scheduledTask);
        this.timer.schedule(scheduledTask, date);
    }

    public synchronized void sendResultBack(String groupName, HashMap<String, CollAUser> group, CollATask task, String taskName) {
        devViewer.receiveTaskResult(groupName, taskName, task);
        //@todo enviar resultado para os demais membros do grupo
    }
    
    /*public void cancelScheduledTask(){
     * @todo esquematizar cancelamento de tasks agendadas tanto aqui quanto no HostViewer
    }*/
    
    protected GenericConsumer<CollAMessage>[] serverThreads;
    protected GenericResource<CollAMessage> serverR;
    private static DistributedTaskController instance;
    private DeveloperViewerController devViewer;
    private static Long taskID;
    private Timer timer;
    private Map<Long, ScheduledTask> scheduleMap;
}
