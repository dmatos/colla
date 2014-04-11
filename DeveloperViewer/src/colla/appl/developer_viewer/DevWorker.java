/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.developer_viewer;

import colla.appl.developer_viewer.exceptions.DeveloperControllerInitializationException;
import colla.kernel.api.*;
import colla.kernel.enumerations.ClientOps;
import colla.kernel.impl.Group;
import colla.kernel.messages.toClient.*;
import colla.kernel.util.Debugger;
import java.util.*;

/**
 *
 * @author bruno
 */
public class DevWorker extends Observable {

    public void execute(CollAMessage collAMessage, DevMicroServer devMicroServer)
            throws DeveloperControllerInitializationException {
        // Envia resposta pro cliente de que a mensagem foi recebida e que ele pode encerrar a conexão.        
        DeveloperViewerController devViewer = DeveloperViewerController.getInstance();

        ClientOps operation;// operação a ser lida        
        try {
            operation = (ClientOps) collAMessage.getOperation();
            Debugger.debug(operation.toString());
            switch (operation) {
                case PING: {
                }
                break;
                case MESSAGE: {
                    ChatDirectMessageMsg incoming = (ChatDirectMessageMsg) collAMessage;
                    devViewer.showChatMessage(incoming.getSender(), incoming.getMessage());
                    /*@todo persistir mensagens e recuperar historico ao abrir
                     * janela para conversa com contatos
                     */
                }
                break;
                case RECEIVE_A_GROUP: {
                    JoinAGroupAnswerMsg incoming = (JoinAGroupAnswerMsg) collAMessage;
                    CollAGroup group = incoming.getGroup();
                    String groupName = incoming.getGroupName();
                    HashMap<String, CollAUser> usersMap = incoming.getUsersMap();
                    devViewer.addGroup(groupName, group, usersMap);
                    Debugger.debug(groupName + "accepted");
                }
                break;
                case LIST_GROUPS: {

                    GetGroupsAnswerMsg msg = (GetGroupsAnswerMsg) collAMessage;
                    Set<String> groups = msg.getGroupsSet();
                    devViewer.setGroupListResults(groups);
                }
                break;
                case GROUP_CREATION_CONFIRMATION: {

                    CreateGroupAnswerMsg msg = (CreateGroupAnswerMsg) collAMessage;
                    String groupName = msg.getGroupName();
                    CollAGroup group = new Group(groupName);
                    group.addMember(devViewer.getUser().getName());
                    group.addAdmin(devViewer.getUser().getName());
                    if (msg.getConfirmation()) {
                        HashMap<String, CollAUser> usersMap = new HashMap<String, CollAUser>();
                        usersMap.put(devViewer.getUser().getName(), devViewer.getUser());
                        devViewer.addGroup(groupName, group, usersMap);
                        devViewer.confirmGroupCreation(true, group);
                        Debugger.debug(groupName + "criacao confirmada.");
                    } else {
                        //if it was not possible to create a group
                        devViewer.confirmGroupCreation(false, group);
                        Debugger.debug(groupName + "nao criado.");
                    }
                }
                break;
                case RECEIVE_HOST: {

                    SendOwnedHostsMsg msg = (SendOwnedHostsMsg) collAMessage;
                    LinkedList<CollAHost> list = msg.getHosts();
                    for (CollAHost host : list) {
                        devViewer.addHost(host);
                    }
                    //upload user to update groups about host
                    devViewer.uploadUserToServer();
                }
                break;
                case RECEIVE_AVAILABLE_HOSTS: {

                    SendAvailableHostsMsg msg = (SendAvailableHostsMsg) collAMessage;
                    LinkedList<CollAHost> hosts = msg.getHosts();
                    Long taskID = msg.getTaskID();
                    if (hosts.getFirst() != null) {
                        Debugger.debug(hosts.getFirst().getName() + " is received");
                    } else {
                        Debugger.debug("Nenhum host foi recebido");
                    }
                    devViewer.sendTaskToRun(hosts, taskID);
                }
                break;
                case RECEIVE_TASK_RESULT: {

                    TaskResultMsg msg = (TaskResultMsg) collAMessage;
                    String taskName = msg.getTaskName();
                    CollATask result = msg.getResult();
                    Debugger.debug("receiving result for: " + msg.getGroupName());
                    devViewer.receiveTaskResult(msg.getGroupName(), taskName, result);
                }
                break;
                case RECEIVE_STORED_RESULTS: {

                    SendStoredResultsMsg msg = (SendStoredResultsMsg) collAMessage;
                    for (TaskResultMsg message : msg.getResults()) {
                        String taskName = message.getTaskName();
                        CollATask result = message.getResult();
                        devViewer.receiveTaskResult(message.getGroupName(), taskName, result);
                    }
                }
                break;
                case TASK_START_NOTIFICATION: {

                    colla.kernel.messages.toClient.NotifyTaskStarted msg = (NotifyTaskStarted) collAMessage;
                    Debugger.debug("TASK_START_NOTIFICATION no implemented received");
                    devViewer.updateResults(msg.getGroupName(), msg.getTaskName(), msg.getTask());
                }
                break;
                default:

                    Debugger.debug("Command still not available: " + operation.toString());
            }// end of switch
        } catch (Exception e) {
            Debugger.debug(e);
        }

    }// end method execute

    @Override
    public void notifyObservers(Object aspect) {
        try {
            DeveloperViewerController devViewer = DeveloperViewerController.getInstance();
            devViewer.notifyObservers(aspect);
        } catch (DeveloperControllerInitializationException devEx) {
            Debugger.debug(devEx);
        }
    }
    private final int timeout = 10000;
}// end of class DevWorker
