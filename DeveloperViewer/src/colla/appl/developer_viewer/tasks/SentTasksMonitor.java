/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.developer_viewer.tasks;

import colla.appl.developer_viewer.DeveloperViewerController;
import colla.kernel.api.CollATask;
import colla.kernel.messages.toHost.ListOnlineHostsMsg;
import colla.kernel.messages.toServer.RetrieveOnlineHostsMsg;
import colla.kernel.util.Debugger;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;

/**
 *
 * @author dmatos
 */
public class SentTasksMonitor extends TimerTask {

    public SentTasksMonitor(String serverIP, int serverPortNumber) {
        this.serverIP = serverIP;
        this.serverPortNumber = serverPortNumber;
    }

    private List<SentTask> retrieveSentTasks(DeveloperViewerController devControl) {
        ArrayList<SentTask> taskList = new ArrayList<SentTask>(10);
        HashMap<String, HashMap<String, CollATask>> tasksByGroup = devControl.getTasks();
        for (String groupKey : tasksByGroup.keySet()) {
            HashMap<String, CollATask> taskMap = tasksByGroup.get(groupKey);
            for (CollATask task : taskMap.values()) {
                taskList.add((SentTask) task);
            }
        }
        return taskList;
    }

    @Override
    public void run() {
        DeveloperViewerController devControl;
        ObjectOutputStream output;
        ObjectInputStream input;
        List onlineHosts;
        try {
            devControl = DeveloperViewerController.getInstance();
            List<SentTask> taskList = this.retrieveSentTasks(devControl);
            if (!taskList.isEmpty()) {
                RetrieveOnlineHostsMsg msg = new RetrieveOnlineHostsMsg(devControl.getUser().getName());
                Socket socket = new Socket(InetAddress.getByName(serverIP), serverPortNumber);
                output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(msg);
                output.flush();
                input = new ObjectInputStream(socket.getInputStream());
                ListOnlineHostsMsg onlineHostsMsg = (ListOnlineHostsMsg) input.readObject();
                socket.close();
                onlineHosts = onlineHostsMsg.getHosts();                
                for (SentTask task : taskList) {
                    if (!task.isFinished()) {
                        if (!onlineHosts.contains(task.getActualHost())) {
                            task.getNextHost();                            
                            devControl.setTaskToResend(task.getEmbededTask());
                            devControl.sendTaskToRun(task.getHostsList(), task.getTaskID());
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Debugger.debug(ex);
        }
    }
    private String serverIP;
    private int serverPortNumber;
}
