/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.server;

import colla.kernel.api.*;
import colla.kernel.enumerations.ActivityID;
import colla.kernel.enumerations.ServerOps;
import colla.kernel.impl.*;
import colla.kernel.messages.toClient.*;
import colla.kernel.messages.toHost.*;
import colla.kernel.messages.toServer.*;
import static colla.kernel.util.Treater.*;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Consumer of sockets provided by Server.
 *
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public class ServerWorker {        

    public void execute(Socket s, Server srvr) {
        this.server = srvr;
        Socket socket = s;
        ServerOps operation = ServerOps.PING;
        CollAMessage collAMessage;
        try {
            socket.setSoTimeout(server.getTimeoutValue());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            collAMessage = (CollAMessage) input.readObject();
            operation = (ServerOps) collAMessage.getOperation();
            //System.err.println(operation.toString());
            switch (operation) {
                case HOST_LOGIN: {
                    // reading messages
                    HostLoginMsg msg = (HostLoginMsg) collAMessage;  
                    String hostName = msg.getHostName();
                    String hostIp = msg.getIPAddress();
                    String sender = msg.getSender();
                    CollAHost host = server.getUser(sender).getHost(hostName);
                    
                    /* checks if the host is valid ip on the internet or if you
                     * are behind a NAT
                     */
                    if (socket.getInetAddress().toString().substring(1).equals(hostIp)) {
                        host.validateIP();
                    } else {
                        host.setIp(socket.getInetAddress().toString().substring(1));
                        host.invalidateIP();
                    }
                    /*@debug
                    host.validateIP();
                    host.setIp("127.0.0.1");
                    /**/

                    host.setOnline();
                    host.setInicioConexao();
                    server.updateHost(host);
                    server.displayMessage("Host " + hostName + " is connected");
                    treat("Host " + hostName + " is connected");

                    // Send true to host if connected
                    ServerHostLoginAnswer outgoing = new ServerHostLoginAnswer(host.IsOnline(), host.hasValidIP(), host.getIp());
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(outgoing);
                    output.flush();
                    this.sendHostUpdateToOwner(host, host.getNameUser());
                    // TODO Bruno: fazer caso em que houve falha na conexão
                }// end of case HOST_LOGIN
                break;
                case HOST_REGISTER: {
                    // reading messages
                    HostRegisterMsg msg = ( HostRegisterMsg ) collAMessage;
                    String userName = msg.getSender();
                    String pass = msg.getUserPass();
                    CollAHost host = msg.getHost();                    

                    // mensagem que será enviada como resposta ao host
                    ServerHostRegisterAnswerMsg outgoing = new ServerHostRegisterAnswerMsg();
                    
                    //check passwords
                    String pass2 = server.getUserPassword(userName);
                    if (pass.equals(pass2)) {
                        // gera nome do host e o envia de volta
                        CollAUser user = server.getUser(userName);
                        String hostName = Integer.toString(user.getHosts().size());
                        hostName = userName + "_" + hostName;
                        outgoing.setHostName(hostName);
                        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                        output.writeObject(outgoing);
                        output.flush();
                        
                        //Adiciona o host nos dados do servidor
                        host.setName(hostName);
                        host.setNameUser(userName);
                        this.recordActivities(host, ActivityID.SIGNUP, "");
                        server.updateHost(host);
                        server.notifyObservers();
                        server.displayMessage("Host " + hostName + ", owned by: " + userName + ", was succefully registered");
                        treat("Host " + hostName + ", owned by: " + userName + ", was succefully registered");
                        this.sendHostUpdateToOwner(host, userName);
                    } else {
                        outgoing.setHostName(null);
                        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                        output.writeObject(outgoing);
                        output.flush();
                        treat("password inserido pelo host é inválido");
                    }
                }// end of case HOST_REGISTER
                break;
                case CREATE_GROUP: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject( new colla.kernel.messages.toClient.ACK() );
                    output.flush();
                    //try to create a new group for an user
                    AskToCreateGroupMsg msg = (AskToCreateGroupMsg) collAMessage;
                    String adminName = msg.getAdmin();
                    String groupName = msg.getGroupName();
                    CollAUser usr = server.getUser(adminName);
                    this.addGroup(usr, groupName);
                }// fim so case CREATE_GROUP
                break;
                case CONFIRM_ADD_GROUP: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject( new colla.kernel.messages.toClient.ACK() );
                    output.flush();
                    CreateGroupMsg msg = (CreateGroupMsg) collAMessage;
                    String groupName = msg.getGroupName();
                    CollAGroup group = msg.getGroup();
                    server.updateGroup(groupName, group);
                }
                break;
                case UPDATE_GROUPS: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject( new colla.kernel.messages.toClient.ACK() );
                    output.flush();
                    UpdateGroupsMsg msg = (UpdateGroupsMsg) collAMessage;
                    List<CollAGroup> groups = msg.getGroups();
                    this.updateGroups(groups);                    
                }
                break;
                case LOGIN: {
                    // verifies nickname and password
                    DeveloperViewerLoginMsg msg = (DeveloperViewerLoginMsg) collAMessage;
                    String userName = msg.getSender();
                    String password = msg.getPassword();
                    String ipAsClientSeesIt = msg.getIP();
                    CollAUser user = server.getUser(userName);
                    CollAMessage response;
                    response = this.clientLogin(user, password, ipAsClientSeesIt, socket);
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(response);
                    output.flush();
                    this.updateUser(user);
                    this.sendMemberUpdateToGroups(user.getName());
                    // System.out.println(socketAccept.getInetAddress().toString());
                    // System.out.println(socketAccept.getPort());
                }
                break; //finishes LOGIN
                case MAP_THE_CONNECTION: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject( new colla.kernel.messages.toClient.ACK() );
                    output.flush();
                    /* working put the socket of some client/host into a map of
                     * sockets for invalid IP's
                     */
                    MapConnection msg = (MapConnection) collAMessage;
                    String nome = msg.getSender();
                    server.connectionsMap.put(nome, socket);
                }
                ;
                break; //finishes map the connection
                case SIGN_UP: {
                    //cheks if the name is avaliable
                    ClientSignUpMsg msg = (ClientSignUpMsg) collAMessage;
                    // System.out.println(socketAccept.getInetAddress().toString());
                    CollAUser user = msg.getUser();
                    String pass = msg.getPassword();
                    CollAMessage answer = this.clientSignUp(user, pass);
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(answer);
                    output.flush();
                    //this.closeSocket(socketAccept);
                    //records user activity
                    this.recordActivities(user, ActivityID.SIGNUP, null);
                }
                break;//finishes SIGN_UP
                case GET_GROUPS: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject( new colla.kernel.messages.toClient.ACK() );
                    output.flush();
                    GetGroupsMsg msg = (GetGroupsMsg) collAMessage;
                    String userName = msg.getSender();
                    CollAUser user = server.getUser(userName);
                    this.sendGroupsToClient(user);
                }
                break;//finishes GET_GROUPS
                case DISCONNECT: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject( new colla.kernel.messages.toClient.ACK() );
                    output.flush();
                    DeveloperViewerDisconnectMsg msg = (DeveloperViewerDisconnectMsg) collAMessage;
                    CollAUser user = msg.getUser();
                    CollAUser tempUser = server.getUser(user.getName());
                    this.clientDisconnect(user, tempUser);
                }
                break;//finishes DISCONNECT
                case JOIN_A_GROUP: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject( new colla.kernel.messages.toClient.ACK() );
                    output.flush();
                    JoinAGroupMsg msg = (JoinAGroupMsg) collAMessage;
                    String userName = msg.getSender();
                    String groupName = msg.getGroupName();
                    CollAUser user = server.getUser(userName);
                    this.requestToJoinGroup(user.getName(), groupName);
                    this.sendGroupUpdateToAdmins(groupName, user);
                }//finishes JOIN_A_GROUP
                break;
                case TRANSMIT_MESSAGE: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject( new colla.kernel.messages.toClient.ACK() );
                    output.flush();
                    TransmitChatMsg msg = (TransmitChatMsg) collAMessage;
                    String sender = msg.getSender();
                    String receiver = msg.getReceiver();
                    String message = msg.getMessage();
                    this.retransmitMessage(sender, receiver, message);
                }
                break;
                case TRANSMIT_TASK: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject( new colla.kernel.messages.toClient.ACK() );
                    output.flush();
                    TransmitTaskMsg msg = (TransmitTaskMsg) collAMessage;
                    String sender = msg.getSender();
                    String receiver = msg.getReceiver();
                    String group = msg.getGroup();
                    CollATask task = msg.getTask();
                    ArrayList<byte[]> atachBuffers = msg.getAttaches();
                    ArrayList<String> attachNames = msg.getAttachNames();
                    this.retransmitTask(group, sender, receiver, task, atachBuffers, attachNames);
                }
                break;
                case TRANSMIT_TASK_RESULT: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject( new colla.kernel.messages.toClient.ACK() );
                    output.flush();
                    TransmitResultMsg msg = (TransmitResultMsg) collAMessage;
                    String sender = msg.getSender();
                    CollAUser receiver = msg.getReceiver();
                    String taskName = msg.getTaskName();
                    CollATask result = msg.getResult();
                    String groupName = msg.getGroupName();
                    this.retransmitTaskResult(sender, receiver, groupName, taskName, result);
                }
                break;
                case UPDATE_USER: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject( new colla.kernel.messages.toClient.ACK() );
                    output.flush();
                    UpdateUserMsg msg = (UpdateUserMsg) collAMessage;
                    CollAUser usr = msg.getUser();
                    this.updateUser(usr);                    
                    this.sendMemberUpdateToGroups(usr.getName());
                }
                break;
                case UPDATE_HOST: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject( new colla.kernel.messages.toClient.ACK() );
                    output.flush();
                    HostUpdateMsg msg = (HostUpdateMsg) collAMessage;
                    //System.out.println("Host update");
                    CollAHost host = msg.getHost();
                    this.updateHost(host);
                }
                break;
                case GET_AVAILABLE_HOSTS: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject( new colla.kernel.messages.toClient.ACK() );
                    output.flush();
                    GetAvailableHostsMsg msg = (GetAvailableHostsMsg) collAMessage;
                    CollAUser user = server.getUser(msg.getSender());
                    String group = msg.getGroup();
                    this.sendAvailableHostsToClient(user, group);
                }
                break;
                case PING: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject( new colla.kernel.messages.toClient.ACK() );
                    output.flush();
                }
                break;
                case HOST_DISCONNECT: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject( new colla.kernel.messages.toHost.ACK() );
                    output.flush();
                    HostDisconnectMsg msg = (HostDisconnectMsg) collAMessage;
                    CollAHost host = msg.getHost();
                    this.hostDisconnect(host);
                }
                break;
                case GET_STORED_RESULTS: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject( new colla.kernel.messages.toHost.ACK() );
                    output.flush();
                    RetrieveStoredResultsMsg msg = (RetrieveStoredResultsMsg) collAMessage;
                    String userName = msg.getSender();
                    this.sendStoredResults(userName);
                }
                break;
                case TRANSMIT_START_NOTIFICATION: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject( new colla.kernel.messages.toClient.ACK() );
                    output.flush();
                    TransmitStartNotification msg = (TransmitStartNotification) collAMessage;
                    this.transmitStartNotification(msg.getGroupName(), msg.getTaskName(), msg.getTask());
                }
                break;
                default:
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject( new colla.kernel.messages.toClient.ACK() );
                    output.flush();
                    server.displayMessage("Operation not supported yet: " + operation.toString());
                    treat("Operation not supported yet: " + operation.toString());
            }// end of switch
        }// end of the try
        catch (SocketTimeoutException se) {
            server.displayMessage("Connection timeout from IP" + socket.getInetAddress().toString());
            server.displayMessage("Operation: " + operation.toString());
            treat( se );
        } catch (SocketException se) {
            if (socket != null) {
                server.displayMessage("Some problem has occured in the connection with " + socket.getInetAddress().toString().substring(1)
                                            +" on port "+socket.getPort());
                treat("Some problem has occured in the connection with " + socket.getInetAddress().toString().substring(1)
                      + " on port "+socket.getPort(),  se );
            } else {
                server.displayMessage("Some problem has occured in the connections");
                treat("Some problem has occured in the connections", se);
            }
        } catch (IOException ioe) {
            server.displayMessage("Some problem has occured in the connections");
            treat( "Some problem has occured in the connections", ioe );
        } catch (ClassNotFoundException cnfe) {
            server.displayMessage("Some problem has occured in the connections");
            treat( "Some problem has occured in the connections", cnfe );
        } catch (Exception e) {
            try {
                treat( e );
                this.server.displayMessage("An unexpected behavior ocurred with the server. Storing data...");
                //this.server.disconnectAllClients();
                this.server.storeAllServerData();
                this.server.displayMessage("Done! Now the server can be closed for repairs");
            } catch (Exception storeException) {
                this.server.displayMessage("Couldn't store all data");
               treat( storeException );
            }
        }
    }// end method execute

    /**
     * Record activities on the server for a client
     * @param user an CollAUser
     * @param activity the activity id that will be recorded
     * @param outro an optional activity description
     */
    private void recordActivities(CollAUser user, ActivityID activity, String outro) {
        HashMap<String, String> record;
        String pastActivities;
        if (outro == null) {
            outro = "";
        }
        record = user.getActivities();
        pastActivities = record.get(server.dateAndTime.getSimpleDate());
        if (pastActivities == null) {
            pastActivities = server.dateAndTime.getHour() + ": " + activity.getID() + ": " + activity.getActivity() + " " + outro + "\n";
            user.recordActivities(server.dateAndTime.getSimpleDate(), pastActivities);

        } else {
            pastActivities = server.dateAndTime.getHour() + ": " + activity.getID() + ": " + activity.getActivity() + " " + outro + "\n";
            user.recordActivities(server.dateAndTime.getSimpleDate(), pastActivities);
        }
        updateUser(user);
    }

    /**
     * Gathers all contacts of an user in its groups in a map
     *
     * @param userName name of the user
     * @return a map of name to user
     */
    private HashMap<String, CollAUser> getUserContacts(String userName) {
        HashMap<String, CollAUser> contacts = new HashMap<String, CollAUser>();
        CollAUser tmp = this.server.getUser(userName);
        for (String groupName : tmp.getGroups().keySet()) {
            for (String name : tmp.getGroups().get(groupName).getMembers()) {
                contacts.put(name, this.server.getUser(name));
            }
        }
        return contacts;
    }

    /**
     * Record activities on the server for a host
     * @param host an CollAHost
     * @param activity the activity id that will be recorded
     * @param outro an optional activity description
     */
     
    private void recordActivities(CollAHost host, ActivityID activity, String outro) {
        HashMap<String, String> record;
        String pastActivities;
        if (outro == null) {
            outro = "";
        }
        record = host.getActivities();
        pastActivities = record.get(server.dateAndTime.getSimpleDate());
        if (pastActivities == null) {
            pastActivities = server.dateAndTime.getHour() + ": " + activity.getID() + ": " + activity.getActivity() + outro + "\n";
            host.recordActivities(server.dateAndTime.getSimpleDate(), pastActivities);
        } else {
            pastActivities = server.dateAndTime.getHour() + ": " + activity.getID() + ": " + activity.getActivity() + outro + "\n";
            host.recordActivities(server.dateAndTime.getSimpleDate(), pastActivities);
        }
        CollAUser usr = server.getUser(host.getNameUser());
        usr.addHost(host);
        server.updateUser(usr);

    }

    /**
     * Send a notification about a task that started to all members of the group
     * with invalid IPs.
     *
     * @param groupName name of the group.
     * @param taskName name of the task.
     * @param task the task.
     */
    private void transmitStartNotification(String groupName, String taskName, CollATask task) {
        CollAGroup group = server.getGroup(groupName);
        for (String userName : group.getMembers()) {
            CollAUser user = server.getUser(userName);
            // Somente usuários que estão online e não possuem IP válido.
            if (!user.getName().equals(task.getOwner()) && user.isOnline() && !user.hasValidIP()) {
                try {
                    Socket s = server.connectionsMap.get(user.getName());
                    if (s != null) {
                        NotifyTaskStarted msg = new NotifyTaskStarted(task);
                        msg.setGroupName(groupName);
                        msg.setTaskName(taskName);
                        ObjectOutputStream output = new ObjectOutputStream(s.getOutputStream());
                        output.writeObject(msg);
                        output.flush();
                        //espera pelo ACK
                        ObjectInputStream  input  = new ObjectInputStream (s.getInputStream() );
                        input.readObject();
                    }
                } catch (Exception e) {
                   treat( e );
                }
            }// end if
        }// end for
    }// end method 

    /**
     * updates the user on the server
     *
     * @param usr
     */
    private void updateUser(CollAUser usr) {
        server.updateUser(usr);
    }

    /**
     * Updates a host on server
     *
     * @param host
     */
    private void updateHost(CollAHost host) {
        server.updateHost(host);
    }

    /**
     * Sends a update of the user to all its groups.
     *
     * @param userName  user name to be updated.
     */
    private void sendMemberUpdateToGroups(String userName) {
        CollAUser usr = this.server.getUser(userName);
        try {
            if (usr.getGroups() != null) {
                Set<String> groupsSet = usr.getGroups().keySet();
                for (String groupName : groupsSet) {
                    sendGroupUpdateToMembers(groupName, usr.getName());
                }
            }
        } catch (Exception e) {
            treat( e );
        }
    }

    /**
     * Sends a update of the group to all its members.
     *
     * @param groupName name of the group
     * @param userName name of the user
     */
    private void sendGroupUpdateToMembers(String groupName, String userName) {
        CollAGroup groupUpdated = server.getGroup(groupName);
        HashMap<String, CollAUser> usersMap = new HashMap<String, CollAUser>();
        for (String memberName : groupUpdated.getMembers()) {
            usersMap.put(memberName, this.server.getUser(memberName));
        }
        for (String memberName : usersMap.keySet()) {
            CollAUser tempUser = usersMap.get(memberName);
            if (!userName.equals(memberName)) {
                if (tempUser.isOnline()) {
                    //se usuário possui um ip válido
                    if (tempUser.hasValidIP() && tempUser.getPort() > 0) {
                        try {
                            Socket aux = new Socket(InetAddress.getByName(tempUser.getIp()), tempUser.getPort());
                            aux.setSoTimeout(40000);
                            JoinAGroupAnswerMsg sendGroup = new JoinAGroupAnswerMsg(groupUpdated);
                            sendGroup.setUsersMap(usersMap);
                            ObjectOutputStream outputAux = new ObjectOutputStream(aux.getOutputStream());
                            outputAux.writeObject(sendGroup);
                            outputAux.flush();
                            //wait for ACK
                            ObjectInputStream  inputAux  = new ObjectInputStream (aux.getInputStream ());
                            inputAux.readObject();
                            aux.close();
                        } catch (Exception ex) {
                            treat( ex );
                        }
                    }
                    // se usuário não possui ip válido
                    else if (!tempUser.hasValidIP() && server.connectionsMap.containsKey(memberName)) {
                        try {
                            Socket aux = server.connectionsMap.get(memberName);                                                        
                            JoinAGroupAnswerMsg sendGroup = new JoinAGroupAnswerMsg(groupUpdated);
                            sendGroup.setUsersMap(usersMap);
                            ObjectOutputStream outputAux = new ObjectOutputStream(aux.getOutputStream());
                            outputAux.writeObject(sendGroup);
                            outputAux.flush();
                            //wait for ACK
                            ObjectInputStream  inputAux  = new ObjectInputStream (aux.getInputStream ());
                            inputAux.readObject();
                        } catch (Exception io) {
                            treat( io );
                        }
                    }
                } else if (!tempUser.isOnline()) {
                    tempUser.addGroup(groupName, groupUpdated);
                    server.updateUser(tempUser);
                }
            }// end if

        }// end for
    }// end method

    /**
     * Sends a update of the group to all its admins
     *
     * @param groupName name of the group
     * @param user an CollAUser
     */
    private void sendGroupUpdateToAdmins(String groupName, CollAUser user) {
        CollAGroup groupUpdated = server.getGroup(groupName);
        HashMap<String, CollAUser> adminsMap = new HashMap<String, CollAUser>();
        HashMap<String, CollAUser> membersMap = new HashMap<String, CollAUser>();
        for (String adminName : groupUpdated.getAdminsList()) {
            adminsMap.put(adminName, this.server.getUser(adminName));
        }
        for (String usrName : groupUpdated.getMembers()) {
            membersMap.put(usrName, this.server.getUser(usrName));
        }
        for (String userName : adminsMap.keySet()) {
            CollAUser temp = adminsMap.get(userName);
            if (!user.getName().equals(temp.getName())) {
                // se administrador está online
                if (temp.isOnline()) {
                    // se administrador possui IP válido
                    if (temp.hasValidIP() && temp.getPort() > 0) {
                        try {
                            Socket aux = new Socket(InetAddress.getByName(temp.getIp()), temp.getPort());
                            aux.setSoTimeout(40000);
                            JoinAGroupAnswerMsg sendGroup = new JoinAGroupAnswerMsg(groupUpdated);
                            sendGroup.setUsersMap(membersMap);
                            ObjectOutputStream outputAux = new ObjectOutputStream(aux.getOutputStream());
                            outputAux.writeObject(sendGroup);
                            outputAux.flush();
                            // espera por ACK
                            ObjectInputStream  inputAux  = new ObjectInputStream (aux.getInputStream ());
                            inputAux.readObject();
                            aux.close();
                           
                        } catch (Exception ex) {
                            treat("An error ocurred while sending group " + groupName + " to its admins", ex);
                            this.server.displayMessage("An error ocurred while sending group " + groupName + " to its admins");
                        }
                    }//end if
                    // se administrador não possui IP válido
                    else if (!temp.hasValidIP() && server.connectionsMap.containsKey(userName)) {
                        try {
                            Socket aux = server.connectionsMap.get(userName);
                            JoinAGroupAnswerMsg sendGroup = new JoinAGroupAnswerMsg(groupUpdated);
                            sendGroup.setUsersMap(membersMap);
                            ObjectOutputStream outputAux = new ObjectOutputStream(aux.getOutputStream());
                            outputAux.writeObject(sendGroup);
                            outputAux.flush();
                            // espera por ACK
                            ObjectInputStream  inputAux  = new ObjectInputStream (aux.getInputStream ());
                            inputAux.readObject();
                        } catch (Exception io) {
                            treat("An error ocurred while sending group " + groupName + " to its admins", io);
                            this.server.displayMessage("An error ocurred while sending group " + groupName + " to its admins");
                        }
                    }// end if
                }// end if
                else if (!temp.isOnline()) {
                    temp.addGroup(groupName, groupUpdated);
                    server.updateUser(temp);
                }
            }// end if
        }// end for
    }// end method

    /**
     *
     * @return the port in which the ServerSocket is listening
     */
    private int getPortNumber() {
        return server.portNumber;
    }

    /**
     *
     * @param userName
     *
     * @return an object of User
     */
    private CollAUser getUser(String userName) {
        return server.getUser(userName);

    }

    /**
     * Receives a message from a client and retransmit it to another client with
     * an invalid IP
     *
     * @param sender client that has sent sending the message
     * @param receiver client that has will receive the message
     * @param message the message itself
     */
    private void retransmitMessage(String sender, String receiver, String message) {
        Socket socket = server.connectionsMap.get(receiver);
        ObjectOutputStream output;
        ObjectInputStream  input;
        try {
            // se destinatário está online
            if (socket != null && server.getUser(receiver).isOnline()) {
                ChatDirectMessageMsg msg = new ChatDirectMessageMsg(sender, message);
                output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(msg);
                output.flush();
                //espera por ACK
                input  = new ObjectInputStream (socket.getInputStream ());
                input.readObject();
            }
            // se destinatário não está online enviamos uma mensagem para quem enviou a mensagem.
            else {
                CollAUser user = getUser( sender );
                if(user.hasValidIP())
                    socket = new Socket( user.getIp(), user.getPort() );    
                else
                    socket = server.connectionsMap.get(sender);
                ChatDirectMessageMsg msg = new ChatDirectMessageMsg("From Server:", receiver + " is offline");
                output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(msg);
                output.flush();
                //espera por ACK
                input  = new ObjectInputStream (socket.getInputStream ());
                input.readObject();
                if(user.hasValidIP())
                    socket.close();
            }
        } catch (Exception io) {
            treat("Error: could not transmit message from " + sender + " to " + receiver, io);
            server.displayMessage("Error: could not transmit message from " + sender + " to " + receiver);
        }
    }// end of method

    /**
     * Receives a task from a client and retransmit it to an host
     *
     * @param sender client that has sent sending the task
     * @param receiver host that has will receive the task
     * @param taskBuffer the task itself
     * @param taskName name of the task
     * @param attachBuffers some attaches over the task will work on
     * @param attachNames name of the attaches
     */
    private void retransmitTask(String group, String sender, String receiver, CollATask task, ArrayList<byte[]> attachBuffers, ArrayList<String> attachNames) {
        treat("Retransmit with group "+group);
        treat("Sender = " + sender + " Receiver = " + receiver);
        Socket socket = server.connectionsMap.get(receiver);
        ObjectOutputStream output;
        ObjectInputStream  input;
        try {
            if (socket != null) {
                TaskMessage outgoing = new TaskMessage();
                outgoing.setUser(server.getUser(sender));
                outgoing.setSender(sender);
                outgoing.setGroupName(group);
                //create a map<string, user> necessary to not overcharge the server
                HashMap<String, CollAUser> tempGroup = new HashMap<String, CollAUser>();
                for (String userName : server.getGroup(group).getMembers()) {
                    tempGroup.put(userName, server.getUser(userName));
                }
                outgoing.setGroup(tempGroup);
                outgoing.setTask(task);
                for (int i = 0; i < attachBuffers.size(); i++) {
                    outgoing.addAttach(attachBuffers.get(i), attachNames.get(i));
                }
                output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(outgoing);
                output.flush();
                //espera por ACK                
                input  = new ObjectInputStream (socket.getInputStream ());
                input.readObject();                 
            } else {
                treat("Socket null");
                /*
                 * TODO Fazer o caso do host estar offline. Retornar uma
                 * mensagem ao usuário, procurar outro host o servidor mesmo e
                 * repassar a task. Sei lá!
                 */
            }
        } catch (Exception e) {
            treat("Problema com a retransmissão da task", e);
        }
    }

    /**
     * Receives a task result from a host and retransmit it to an client
     *
     * @param sender host that has sent sending the task result
     * @param receiver client that has will receive the task result
     * @param taskName name of the task
     * @param jclr the result of the task
     */
    private void retransmitTaskResult(String sender, CollAUser receiver, String group, String taskName, CollATask result) {
        //System.err.println(receiver.getName());
        Socket socket = server.connectionsMap.get(receiver.getName());
        ObjectOutputStream output;
        ObjectInputStream  input;
        TaskResultMsg outgoing = new TaskResultMsg(sender, taskName, result);
        outgoing.setGroupName(group);
        try {
            // se usuário está online
            if (socket != null && server.getUser(receiver.getName()).isOnline()) {
                treat("Sending a task_result to " + receiver.getName());
                output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(outgoing);
                output.flush();
                // espera por ACK
                input  = new ObjectInputStream (socket.getInputStream ());
                input.readObject();
            } else {
                //store result in server until client come back online
                ArrayList<TaskResultMsg> resultsArray = server.resultsMap.get(receiver.getName());
                if (resultsArray == null) {
                    resultsArray = new ArrayList<TaskResultMsg>();
                }
                resultsArray.add(outgoing);
                server.resultsMap.put(receiver.getName(), resultsArray);
            }
        } catch (Exception io) {
            treat("Error: could not transmit message from " + sender + " to " + receiver, io);
            server.displayMessage("Error: could not transmit message from " + sender + " to " + receiver);
        }
    }// end method

    /**
     * Gets all hosts from an determined group.
     * @param groupName The group from which to get the hosts
     * @return a List containing the online hosts belonging to the group
     */
    private List<CollAHost> getHostsFromGroup(String groupName) {
        CollAGroup cGroup = server.getGroup(groupName);
        List<String> userNames = cGroup.getMembers();
        //System.out.println("Users from group " + groupName + ": " + userNames);
        Iterator<String> userNamesIt = userNames.iterator();
        List<CollAHost> HostsFromGroup = new ArrayList<CollAHost>();
        /*
         * For each user I get your hosts and add in the list of hosts
         */
        while (userNamesIt.hasNext()) {
            CollAUser auxUser = server.getUser(userNamesIt.next());
            Set<CollAHost> hostNames = auxUser.getHosts();
            //System.out.println("Hosts from user " + auxUser.getName() + ": " + hostNames);
            Iterator<CollAHost> hostNamesIt = hostNames.iterator();
            while (hostNamesIt.hasNext()) {
                CollAHost auxHost = hostNamesIt.next();
                //System.out.println("Host " + auxHost.getName() + " is Online? " + auxHost.IsOnline());
                if (auxHost.IsOnline()) {
                    HostsFromGroup.add(auxHost);
                }
            }// end while
        }// end while
        return HostsFromGroup;
    }// end method

    /**
     * Gets all hosts online.
     * @return a List containing the online hosts.
     */
    private List<CollAHost> getHostsOnline() {
        Set<String> userNames = server.getUsersSet();
//        System.out.println("Users from group " + groupName + ": " + userNames);
        Iterator<String> userNamesIt = userNames.iterator();
        List<CollAHost> hostsOnline = new ArrayList<CollAHost>();
        /*
         * For each user I get your hosts and add in the list of hosts
         */
        while (userNamesIt.hasNext()) {
            CollAUser auxUser = server.getUser(userNamesIt.next());
            Set<CollAHost> hostNames = auxUser.getHosts();
//            System.out.println("Hosts from user " + auxUser.getName() + ": " + hostNames);
            Iterator<CollAHost> hostNamesIt = hostNames.iterator();
            while (hostNamesIt.hasNext()) {
                CollAHost auxHost = hostNamesIt.next();
//                System.out.println("Host " + auxHost.getName() + " is Online? " + auxHost.IsOnline());
                if (auxHost.IsOnline()) {
                    hostsOnline.add(auxHost);
                }
            }//end while
        }// end while
        return hostsOnline;
    }// end method
    /**
     * Send an host to a client for execution of tasks
     *
     * @param user the client
     * @param group the group to which the user will send the request
     */
    private void sendAvailableHostsToClient(CollAUser user, String group) {
        Socket socket;
        SendAvailableHostsMsg outgoing = new SendAvailableHostsMsg(this.server.generateTaskID());
        try {
            // Get a random host
            CollAHost h = null;
            List<CollAHost> hostsOnline = getHostsOnline();
            if (hostsOnline.size() > 0) {
                int i = (int) (Math.random() * hostsOnline.size());
                h = hostsOnline.get(i);
            }
            outgoing.addHost(h);
            // If user don't have a valid IP we use the socket in connectionsMap
            if (!user.hasValidIP()) {
                socket = server.connectionsMap.get(user.getName());

            } else {
                socket = new Socket(InetAddress.getByName(user.getIp()), user.getPort());
                socket.setSoTimeout(server.timeout);
            }
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(outgoing);
            output.flush();
            //espera por ACK
            ObjectInputStream  input  = new ObjectInputStream (socket.getInputStream() );
            input.readObject();
            if(user.hasValidIP())
                socket.close();
            treat("Host " + h.getName() + " is sended to user " + user.getName());
        } catch (Exception ex) {
            server.displayMessage("Error: could not retrive hosts to " + user.getName() + " at " + user.getIp());
            treat("Error: could not retrive hosts to " + user.getName() + " at " + user.getIp(), ex);
        }
    }

    private long generateSeassionID(Long id) {
        return id;
    }

    /**
     *
     * @return a Set containing the names of the users
     */
    private Set<String> getUsersSet() {
        return server.getUsersSet();
    }

    /**
     * Execute an attempt of login for a client.
     *
     * @param user the client. Migth be null if the username of this client
     * wasn't found
     * @param password password received from client
     * @param ipAsClientSeesIt IP Address as it is seen by the client host
     * @param socket an socket from client.
     * @return a message to send to client to confirm login
     */
    private CollAMessage clientLogin(CollAUser user, String password, String ipAsClientSeesIt, Socket socket) {
        DeveloperViewerLoginAnswerMsg answer = new DeveloperViewerLoginAnswerMsg();
        if (user == null || server.getUser(user.getName()) == null) {
            answer.confirmUserName(false);
        } else {
            answer.confirmUserName(true);
            /*
             * checks if its both a valid and private IP address over Internet,
             * or behind some NAT
             */
             if (socket.getInetAddress().toString().substring(1).equals(ipAsClientSeesIt)) {
                user.validateIP();
            } else {
                user.setIp(socket.getInetAddress().toString().substring(1));
                user.invalidateIP();
            }
            /**@debug
              user.validateIP();
              user.setIp("127.0.0.1");
             /**/
            // verifies the passwords send true if the password is corretct
            if( server.getUserPassword( user.getName() ) != null
                && password.equals( server.getUserPassword( user.getName() ) ) ){
                user.setOnline();
                user.setConnectionInitialized();
                //invalidate port to avoid connections while login is not complete
                user.setPort(-1);
                Session newSession = new Session();
                newSession.setSessionID(this.server.generateSessionID());
                user.addSession(newSession);                
                server.displayMessage("User " + user.getName() + " has connected");
                treat("User " + user.getName() + " has connected");
                answer.confirmPassword(true);
                answer.setUser(server.getUser(user.getName()));
                recordActivities(user, ActivityID.LOGIN, null);
                answer.setContacts(this.getUserContacts(user.getName()));                
            } else {
                answer.confirmPassword(false);
            }
        }// end else
        return answer;
    }// end method

    /**
     * Add a client to a group waiting list
     *
     * @param userName name of client asking to join
     * @param groupName name of the group
     */
    private void requestToJoinGroup(String userName, String groupName) {
        CollAGroup group = this.server.getGroup(groupName);
        group.addMemberToWaitingList(userName);
        this.server.updateGroup(groupName, group);
    }

    /**
     * Disconnect client and update it on server
     *
     * @param user User that comes from client that is disconnecting
     * @param temp User that was stored on users map
     */
    private void clientDisconnect(CollAUser user, CollAUser temp) {
        for (CollAHost host : temp.getHosts()) {
            user.addHost(host);
        }
        user.setPort(-1);
        user.setOffline();
        server.displayMessage("User " + user.getName() + " has disconnected");
        treat("User " + user.getName() + " has disconnected");
        updateUser(user);
        sendMemberUpdateToGroups(user.getName());
        recordActivities(user, ActivityID.DISCONNECT, "\nConnection total time: " + user.getConnectionTotalTime());
        server.connectionsMap.remove(user.getName());
    }

    /**
     * Set a host as offline
     *
     * @param host host disconnecting from server
     */
    private void hostDisconnect(CollAHost host) {
        host.setPort(-1);
        host.setOffline();
        server.displayMessage("Host " + host.getName() + " has disconnected");
        treat("Host " + host.getName() + " has disconnected");
        updateHost(host);
    }

    /**
     * Attempt of a client of sign up for the server
     *
     * @param user the client
     * @param pass password sent by client
     *
     * @return response a message to send to the client
     */
    private CollAMessage clientSignUp(CollAUser user, String pass) {

        SignUpAnswerMsg response = new SignUpAnswerMsg();
        if (server.getUserPassword(user.getName()) != null) {
            //true if the name is not available
            response.confirmUserName(Boolean.TRUE);
        } else {
            //false if the name is available
            response.confirmUserName(Boolean.FALSE);
            updateUser(user);
            //reads crypted passaword
            server.setUserPassword(user.getName(), pass);
        }
        return response;
    }

    /**
     * Add a group to the server as a request of some client.
     *
     * @param usr the client
     * @param groupName name of the group to create
     */
    private void addGroup(CollAUser usr, String groupName) {
        Socket socket;
        ObjectOutputStream output;
        ObjectInputStream  input;
        try {
            if (usr.hasValidIP()) {
                socket = new Socket(InetAddress.getByName(usr.getIp()), usr.getPort());
                socket.setSoTimeout(server.timeout);
            } else {
                socket = server.connectionsMap.get(usr.getName());
            }
            CreateGroupAnswerMsg response;
            if (server.getGroup(groupName) != null) {
                //returns that the name already has been taken
                response = new CreateGroupAnswerMsg(groupName, false);
            } else {
                //returns that the name is available
                response = new CreateGroupAnswerMsg(groupName, true);
            }
            output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(response);
            output.flush();
            // espera por ACK
            input  = new ObjectInputStream (socket.getInputStream() );
            input.readObject();
            if(usr.hasValidIP())
                socket.close();
        } catch (Exception io) {
            server.displayMessage("Error while creating new group.\n Client: " + usr.getName() + "\n"
                    + "Client IP Address: " + usr.getIp());
            treat("Error while creating new group. Client: " + usr.getName() + "\n"
                    + "Client IP Address: " + usr.getIp(), io);
        }
    }// end method

    /**
     * Envia os grupos que o cliente pertence para o cliente.
     * @param user user that will receive the groups
     */
    private void sendGroupsToClient(CollAUser user) {
        Set<String> groups = server.getAllGroups();
        TreeSet<String> groupsSet = new TreeSet<String>();
        GetGroupsAnswerMsg response = new GetGroupsAnswerMsg();
        Socket socketAux;
        try {
            for (String i : groups) {
                groupsSet.add(i);
            }
            if (user.hasValidIP()) {
                socketAux = new Socket(InetAddress.getByName(user.getIp()), user.getPort());
                socketAux.setSoTimeout(server.timeout);

            } else {
                socketAux = server.connectionsMap.get(user.getName());
            }
            response.setGroupsSet(groupsSet);
            ObjectOutputStream output = new ObjectOutputStream(socketAux.getOutputStream());
            output.writeObject(response);
            output.flush();
            // espera por ACK
            ObjectInputStream  input  = new ObjectInputStream (socketAux.getInputStream() );
            input.readObject();
            if(user.hasValidIP())
                socketAux.close();            
        } catch (Exception ex) {
            server.displayMessage("Error: couldn't send list of groups to client " + user.getName()
                    + " at " + user.getIp());
            treat("Error: couldn't send list of groups to client " + user.getName()
                    + " at " + user.getIp(), ex);
        }
    }// end method

    /**
     * Vincula um host a um cliente.
     * @param host o hot para ser vinculado.
     * @param userName o nome do usuário que terá o host vinculado.
     */
    private void hostAdd(Host host, String userName) {
        CollAUser user = server.getUser(userName);
        CollAHost temp = user.getHost(host.getName());
        // If host is already in mapHosts
        if (temp != null) {
            host.setActivities(temp.getActivities());
        }
        //Find User and add his host        
        user.addHost(host);
        this.updateUser(user);
    }

    /**
     * Envia um resultado guardado ao cliente.
     * @param userName o nome do cliente que receberá o resultado.
     */
    private void sendStoredResults( String userName ){
        CollAUser user = server.getUser( userName );
        if( user.getPort() > 0 ){
            try{
                SendStoredResultsMsg outgoing = new SendStoredResultsMsg( server.resultsMap.get( userName ) );
                if( outgoing.getResults() != null ){
                    Socket socket;
                    ObjectOutputStream output;
                    ObjectInputStream input;
                    if( user.hasValidIP() ){
                        socket = new Socket( InetAddress.getByName( user.getIp() ), user.getPort() );
                    }else{
                        socket = server.connectionsMap.get( userName );
                    }
                    output = new ObjectOutputStream( socket.getOutputStream() );
                    output.writeObject( outgoing );
                    output.flush();
                    // espera por ACK
                    input = new ObjectInputStream( socket.getInputStream() );
                    input.readObject();
                    if( user.hasValidIP() )
                        socket.close();
                    //discards stored result
                    server.resultsMap.remove( userName );
                } // otherwise there is nothing to send to client
            }catch( Exception ex ){
                this.server.displayMessage( "Couldn't send stored results to " + userName );
                treat( "Couldn't send stored results to " + userName, ex );
            }
        }// end if
    }// end method

    /**
     * Busca os usuários cadastrados em um grupo determinado.
     * @param groupName o nome do grupo a ser pesquisado
     * @return os usuários do grupo
     */
    private HashMap<String, CollAUser> getUsersFromGroup(String groupName) {
        HashMap<String, CollAUser> group = new HashMap<String, CollAUser>();
        return group;
    }

    private void updateGroups(List<CollAGroup> groups) {
        for (CollAGroup group : groups) {
            server.updateGroup(group.getName(), group);
            this.sendGroupUpdateToMembers(group.getName(), "0");
        }
    }

    /**
     * Sends an updated host to its owner
     *
     * @param host an host that has been registered to the server
     * @param userName name of the owner of the host
     */
    private void sendHostUpdateToOwner(CollAHost host, String userName) {
        try {
            CollAUser user = this.getUser(userName);
            Socket socket;
            ObjectOutputStream output;
            ObjectInputStream input;
            if (user.isOnline()) {
                if (user.hasValidIP())
                    socket = new Socket(InetAddress.getByName(user.getIp()), user.getPort());
                else
                    socket = this.server.connectionsMap.get(userName);
                SendOwnedHostsMsg msg = new SendOwnedHostsMsg();
                msg.addHost( host );
                output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject( msg );
                output.flush();
                // espera por ACK
                input = new ObjectInputStream(socket.getInputStream());
                input.readObject();
                if(user.hasValidIP())
                    socket.close();
            }
        } catch (Exception io) {
            treat("Problema com o envio do Host Update to Owner", io);
        }
    }// end method
    //Variables
    private volatile Server server;
}