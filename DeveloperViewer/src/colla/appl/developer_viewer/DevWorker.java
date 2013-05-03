/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.developer_viewer;


import colla.kernel.api.*;
import colla.kernel.enumerations.ClientOps;
import colla.kernel.impl.Group;
import colla.kernel.messages.toClient.*;
import colla.kernel.util.Debugger.DebugInfo;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.*;

/**
 *
 * @author bruno
 */
public class DevWorker extends Observable{
    
    public void execute(CollAMessage collAMessage, DevMicroServer devMicroServer){
        // Envia resposta pro cliente de que a mensagem foi recebida e que ele pode encerrar a conexão.        
        this.devViewer = devMicroServer.getDevViewer();
        this.serverIPaddress = devMicroServer.getServerIPaddress();
        this.serverPortNumber = devMicroServer.getServerPortNumber();
        this.user = devMicroServer.getUser();
        this.validIP = user.hasValidIP();
        this.debugInfo = new DebugInfo();
        this.debugInfo.setDebuggedName(user.getName());
        
        ClientOps operation;// operação a ser lida        
        try{
            operation = ( ClientOps ) collAMessage.getOperation();
            //System.err.println(operation.toString());
            switch( operation ){
            case PING: {
               
            }
            break;
            case MESSAGE: {
                ChatDirectMessageMsg incoming = ( ChatDirectMessageMsg ) collAMessage;
                devViewer.showChatMessage( incoming.getSender(), incoming.getMessage() );
            }
            break;
            case RECEIVE_A_GROUP: {
                JoinAGroupAnswerMsg incoming = ( JoinAGroupAnswerMsg ) collAMessage;
                CollAGroup group = incoming.getGroup();
                String groupName = incoming.getGroupName();
                HashMap<String, CollAUser> usersMap = incoming.getUsersMap();
                devViewer.addGroup( groupName, group, usersMap );
                debug( groupName + "accepted" );
            }
            break;
            case LIST_GROUPS: {
                
                GetGroupsAnswerMsg msg = ( GetGroupsAnswerMsg ) collAMessage;
                Set<String> groups = msg.getGroupsSet();
                devViewer.setGroupListResults( groups );
            }
            break;
            case GROUP_CREATION_CONFIRMATION: {
              
                CreateGroupAnswerMsg msg = ( CreateGroupAnswerMsg ) collAMessage;
                String groupName = msg.getGroupName();
                CollAGroup group = new Group( groupName );
                group.addMember( devViewer.getUser().getName() );
                group.addAdmin( devViewer.getUser().getName() );
                if( msg.getConfirmation() ){
                    HashMap<String, CollAUser> usersMap = new HashMap<String, CollAUser>();
                    usersMap.put( this.devViewer.getUser().getName(), this.devViewer.getUser() );
                    devViewer.addGroup( groupName, group, usersMap );
                    devViewer.confirmGroupCreation( true, group );
                    debug( groupName + "criacao confirmada." );
                }else{
                    //if it was not possible to create a group
                    devViewer.confirmGroupCreation( false, group );
                    debug( groupName + "nao criado." );
                }
            }
            break;
            case RECEIVE_HOST: {
                
                SendOwnedHostsMsg msg = ( SendOwnedHostsMsg ) collAMessage;
                LinkedList<CollAHost> list = msg.getHosts();
                for( CollAHost host : list ){
                    devViewer.addHost( host );
                }
                //upload user to update groups about host
                devViewer.uploadUserToServer();
            }
            break;
            case RECEIVE_AVAILABLE_HOSTS: {
               
                SendAvailableHostsMsg msg = ( SendAvailableHostsMsg ) collAMessage;
                LinkedList<CollAHost> hosts = msg.getHosts();
                Long taskID = msg.getTaskID();
                if( hosts.getFirst() != null ){
                    debug( hosts.getFirst().getName() + " is received" );
                }else{
                    debug( "Nenhum host foi recebido" );
                }
                devViewer.sendTaskToRun( hosts, taskID );
            }
            break;
            case RECEIVE_TASK_RESULT: {
               
                TaskResultMsg msg = ( TaskResultMsg ) collAMessage;
                String taskName = msg.getTaskName();
                CollATask result = msg.getResult();
                debug( "receiving result for: " + msg.getGroupName() );
                devViewer.receiveTaskResult( msg.getGroupName(), taskName, result );
            }
            break;
            case RECEIVE_STORED_RESULTS: {
               
                SendStoredResultsMsg msg = ( SendStoredResultsMsg ) collAMessage;
                for( TaskResultMsg message : msg.getResults() ){
                    String taskName = message.getTaskName();
                    CollATask result = message.getResult();
                    devViewer.receiveTaskResult( message.getGroupName(), taskName, result );
                }
            }
            break;
            case TASK_START_NOTIFICATION: {
                
                colla.kernel.messages.toClient.NotifyTaskStarted msg = ( NotifyTaskStarted ) collAMessage;
                debug( "TASK_START_NOTIFICATION no implemented received" );
                devViewer.updateResults( msg.getGroupName(), msg.getTaskName(), msg.getTask() );
            }
            break;
            default:
                
                debug( "Command still not available: " + operation.toString() );
            }// end of switch

//                this.shutdownCounter = 0;
        }catch( Exception e ){
            debug( e );
               //TODO Este restoreMicroServer funciona? Pq essa thread aqui não é finalizada, ela vai continuar no loop.
//                devViewer.restoreMicroServer();
                        //TODO esse shutdownCounter++ funciona? Porque ele não está junto com o catch da excessão SocletException?
//                this.shutdownCounter++;
//                if (this.shutdownCounter == 10) {
//                    JOptionPane.showMessageDialog(null, "Error: connection lost", "Connection error", JOptionPane.ERROR_MESSAGE);
//                    this.devViewer.shutdown();
//                    System.exit(1);
//                }
        }

    }// end method execute

    /**
     * Método para debugar o programa e notificar os Observers.
     *
     * @param info Informação
     * @param ex   Exceprion
     */
    private void debug( String info, Exception ex ){
        /*this.debugInfo.clear();
        this.debugInfo.setInfo( info );
        this.debugInfo.setException( ex );
        this.notifyObservers( this.debugInfo );
        System.out.println( info );
        ex.printStackTrace();*/
    }

    /**
     * Método para debugar o programa e notificar os Observers.
     *
     * @param info Informação
     */
    private void debug( String info ){
        /*this.debugInfo.clear();
        this.debugInfo.setInfo( info );
        this.notifyObservers( this.debugInfo );
        System.out.println( info );*/
    }

    /**
     * Método para debugar o programa e notificar os Observers.
     *
     * @param ex Exception
     */
    private void debug( Exception ex ){
       /* this.debugInfo.clear();
        this.debugInfo.setException( ex );
        this.notifyObservers( this.debugInfo );
        ex.printStackTrace();*/
    }

    @Override
    public void notifyObservers( Object aspect ){
        this.devViewer.notifyObservers( aspect );
    }
    
    private final int timeout = 0;
    String serverIPaddress;
    int serverPortNumber;
    private boolean validIP;
    private DebugInfo debugInfo;
    private CollAUser user;
    private DeveloperViewer devViewer;
}// end of class DevWorker
