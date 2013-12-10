/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.server;

import colla.kernel.api.*;
import colla.kernel.enumerations.ActivityID;
import colla.kernel.enumerations.ServerOps;
import colla.kernel.exceptions.server.NonExistentUser;
import colla.kernel.exceptions.server.ServerInitializationException;
import colla.kernel.exceptions.server.UserAlreadyExists;
import colla.kernel.impl.*;
import colla.kernel.messages.toClient.*;
import colla.kernel.messages.toHost.*;
import colla.kernel.messages.toServer.*;
import colla.kernel.util.Debugger;
import colla.kernel.util.TimeAndDate;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Consumer of sockets provided by Server.
 *
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public class ServerWorker {

    public void execute(Socket s) throws ServerInitializationException {
        Socket socket = s;
        ServerOps operation = ServerOps.PING;
        CollAMessage collAMessage;
        Server server = Server.getInstance();
        try {
            socket.setSoTimeout(server.getTimeoutValue());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            collAMessage = (CollAMessage) input.readObject();
            operation = (ServerOps) collAMessage.getOperation();
            //System.err.println(operation.toString());
            switch (operation) {
                case GET_LIST_OF_ONLINE_HOSTS:{
                    RetrieveOnlineHostsMsg incomingMsg = (RetrieveOnlineHostsMsg) collAMessage;
                    ListOnlineHostsMsg outgoingMsg = this.listOnlineHosts();
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(outgoingMsg);
                    output.flush();                    
                }
                break;
                case HOST_LOGIN: {
                    // reading messages
                    HostLoginMsg msg = (HostLoginMsg) collAMessage;
                    String hostName = msg.getHostName();
                    String hostIp = msg.getIPAddress();
                    String sender = msg.getSender();

                    try {
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
                        //server.displayMessage("Host " + hostName + " is connected");
                        Debugger.debug("Host " + hostName + " is connected");

                        // Send true to host if connected
                        ServerHostLoginAnswer outgoing = new ServerHostLoginAnswer(host.IsOnline(), host.hasValidIP(), host.getIp());
                        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                        output.writeObject(outgoing);
                        output.flush();
                        this.sendHostUpdateToOwner(host, host.getNameUser());
                    } catch (NonExistentUser nExUsr) {
                        // TODO fazer caso em que houve falha na conexão do host
                    }
                }// end of case HOST_LOGIN
                break;
                case HOST_REGISTER: {
                    // reading messages
                    HostRegisterMsg msg = (HostRegisterMsg) collAMessage;
                    String userName = msg.getSender();
                    String pass = msg.getUserPass();
                    CollAHost host = msg.getHost();

                    // mensagem que será enviada como resposta ao host
                    ServerHostRegisterAnswerMsg outgoing = new ServerHostRegisterAnswerMsg();

                    //check passwords
                    String pass2 = server.getUserPassword(userName);
                    if (pass.equals(pass2)) {
                        try {
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
                            //server.displayMessage("Host " + hostName + ", owned by: " + userName + ", was succefully registered");
                            Debugger.debug("Host " + hostName + ", owned by: " + userName + ", was succefully registered");
                            this.sendHostUpdateToOwner(host, userName);
                        } catch (NonExistentUser nExUsr) {
                            outgoing.setHostName(null);
                            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                            output.writeObject(outgoing);
                            output.flush();
                            Debugger.debug("password inserido pelo host é inválido");
                        }
                    } else {
                        outgoing.setHostName(null);
                        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                        output.writeObject(outgoing);
                        output.flush();
                        Debugger.debug("password inserido pelo host é inválido");
                    }
                }// end of case HOST_REGISTER
                break;
                case CREATE_GROUP: {
                    try {
                        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                        output.writeObject(new colla.kernel.messages.toClient.ACK());
                        output.flush();
                        //try to create a new group for an user
                        AskToCreateGroupMsg msg = (AskToCreateGroupMsg) collAMessage;
                        String adminName = msg.getAdmin();
                        String groupName = msg.getGroupName();
                        CollAUser usr = server.getUser(adminName);
                        this.addGroup(usr, groupName);
                    } catch (NonExistentUser nExUsr) {
                        nExUsr.printStackTrace();
                    }
                }// fim so case CREATE_GROUP
                break;
                case CONFIRM_ADD_GROUP: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(new colla.kernel.messages.toClient.ACK());
                    output.flush();
                    CreateGroupMsg msg = (CreateGroupMsg) collAMessage;
                    String groupName = msg.getGroupName();
                    CollAGroup group = msg.getGroup();
                    server.updateGroup(groupName, group);
                }
                break;
                case UPDATE_GROUPS: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(new colla.kernel.messages.toClient.ACK());
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
                    CollAMessage response;
                    //System.out.println(userName + " for login");
                    response = this.clientLogin(userName, password, ipAsClientSeesIt, socket);
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(response);
                    output.flush();
                    try {
                        CollAUser user = server.getUser(userName);
                        this.updateUser(user);
                        this.sendMemberUpdateToGroups(userName);
                    } catch (NonExistentUser nExUsr) {
                        nExUsr.printStackTrace();
                    }
                    // System.out.println(socketAccept.getInetAddress().toString());
                    // System.out.println(socketAccept.getPort());
                }
                break; //finishes LOGIN
                case MAP_THE_CONNECTION: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(new colla.kernel.messages.toClient.ACK());
                    output.flush();
                    /* working put the socket of some client/host into a map of
                     * sockets for invalid IP's
                     */
                    MapConnection msg = (MapConnection) collAMessage;
                    String nome = msg.getSender();
                    server.mapConnection(nome, socket);
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
                    /*try {                        
                        //records user activity                                                              
                        System.out.println(server.getUsersSet().size() + "usuarios");
                        System.out.println(server.getUser(user.getName()).getName() + " registrado com sucesso");
                    } catch (NonExistentUser ex) {
                        ex.printStackTrace();
                    }*/
                    this.recordActivities(user, ActivityID.SIGNUP, null);
                }
                break;//finishes SIGN_UP
                case GET_GROUPS: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(new colla.kernel.messages.toClient.ACK());
                    output.flush();
                    GetGroupsMsg msg = (GetGroupsMsg) collAMessage;
                    String userName = msg.getSender();
                    try {
                        CollAUser user = server.getUser(userName);
                        this.sendGroupsToClient(user);
                    } catch (NonExistentUser nExUsr) {
                        nExUsr.printStackTrace();
                    }
                }
                break;//finishes GET_GROUPS
                case DISCONNECT: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(new colla.kernel.messages.toClient.ACK());
                    output.flush();
                    DeveloperViewerDisconnectMsg msg = (DeveloperViewerDisconnectMsg) collAMessage;
                    CollAUser user = msg.getUser();
                    try {
                        CollAUser tempUser = server.getUser(user.getName());
                        this.clientDisconnect(user, tempUser);
                    } catch (NonExistentUser nExUsr) {
                        nExUsr.printStackTrace();
                    }
                }
                break;//finishes DISCONNECT
                case JOIN_A_GROUP: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(new colla.kernel.messages.toClient.ACK());
                    output.flush();
                    JoinAGroupMsg msg = (JoinAGroupMsg) collAMessage;
                    String userName = msg.getSender();
                    String groupName = msg.getGroupName();
                    Debugger.debug("server: join group request from "+userName);
                    this.requestToJoinGroup(userName, groupName);
                    this.sendGroupUpdateToAdmins(groupName, userName);
                }//finishes JOIN_A_GROUP
                break;
                case TRANSMIT_MESSAGE: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(new colla.kernel.messages.toClient.ACK());
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
                    output.writeObject(new colla.kernel.messages.toClient.ACK());
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
                    output.writeObject(new colla.kernel.messages.toClient.ACK());
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
                    output.writeObject(new colla.kernel.messages.toClient.ACK());
                    output.flush();
                    UpdateUserMsg msg = (UpdateUserMsg) collAMessage;
                    CollAUser usr = msg.getUser();
                    try {
                        this.updateUser(usr);
                        this.sendMemberUpdateToGroups(usr.getName());
                    } catch (NonExistentUser nExUsr) {
                        nExUsr.printStackTrace();
                    }
                }
                break;
                case UPDATE_HOST: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(new colla.kernel.messages.toClient.ACK());
                    output.flush();
                    HostUpdateMsg msg = (HostUpdateMsg) collAMessage;
                    //System.out.println("Host update");
                    CollAHost host = msg.getHost();
                    try {
                        this.updateHost(host);
                    } catch (NonExistentUser nExUsr) {
                        nExUsr.printStackTrace();
                    }
                }
                break;
                case GET_AVAILABLE_HOSTS: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(new colla.kernel.messages.toClient.ACK());
                    output.flush();
                    GetAvailableHostsMsg msg = (GetAvailableHostsMsg) collAMessage;
                    String group = msg.getGroup();
                    this.sendAvailableHostsToClient(msg.getSender(), group);
                }
                break;
                case PING: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(new colla.kernel.messages.toClient.ACK());
                    output.flush();
                }
                break;
                case HOST_DISCONNECT: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(new colla.kernel.messages.toHost.ACK());
                    output.flush();
                    HostDisconnectMsg msg = (HostDisconnectMsg) collAMessage;
                    CollAHost host = msg.getHost();
                    this.hostDisconnect(host);
                }
                break;
                case GET_STORED_RESULTS: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(new colla.kernel.messages.toHost.ACK());
                    output.flush();
                    RetrieveStoredResultsMsg msg = (RetrieveStoredResultsMsg) collAMessage;
                    String userName = msg.getSender();
                    this.sendStoredResults(userName);
                }
                break;
                case TRANSMIT_START_NOTIFICATION: {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(new colla.kernel.messages.toClient.ACK());
                    output.flush();
                    TransmitStartNotification msg = (TransmitStartNotification) collAMessage;
                    this.transmitStartNotification(msg.getGroupName(), msg.getTaskName(), msg.getTask());
                }
                break;
                default:
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(new colla.kernel.messages.toClient.ACK());
                    output.flush();
                    server.displayMessage("Operation not supported yet: " + operation.toString());
                    Debugger.debug("Operation not supported yet: " + operation.toString());
            }// end of switch
        }// end of the try
        catch (SocketTimeoutException se) {
            server.displayMessage("Connection timeout from IP" + socket.getInetAddress().toString());
            server.displayMessage("Operation: " + operation.toString());
            Debugger.debug(se);
        } catch (SocketException se) {
            if (socket != null) {
                server.displayMessage("Some problem has occured in the connection with " + socket.getInetAddress().toString().substring(1)
                        + " on port " + socket.getPort());
                Debugger.debug("Some problem has occured in the connection with " + socket.getInetAddress().toString().substring(1)
                        + " on port " + socket.getPort(), se);
            } else {
                server.displayMessage("Some problem has occured in the connections");
                Debugger.debug("Some problem has occured in the connections", se);
            }
        } catch (IOException ioe) {
            server.displayMessage("Some problem has occured in the connections");
            Debugger.debug("Some problem has occured in the connections", ioe);
        } catch (ClassNotFoundException cnfe) {
            server.displayMessage("Some problem has occured in the connections");
            Debugger.debug("Some problem has occured in the connections", cnfe);
        }
        
    }// end method execute

    /**
     * Record activities on the server for a client
     *
     * @param user an CollAUser
     * @param activity the activity id that will be recorded
     * @param outro an optional activity description
     */
    private void recordActivities(CollAUser user, ActivityID activity, String outro) {
        try {
            Server server = Server.getInstance();

            HashMap<String, String> record;
            String pastActivities;
            if (outro == null) {
                outro = "";
            }
            record = user.getActivities();
            TimeAndDate timeDate = server.getTimeAndDateInstance();
            pastActivities = record.get(timeDate.getSimpleDate());
            if (pastActivities == null) {
                pastActivities = timeDate.getHour() + ": " + activity.getID() + ": " + activity.getActivity() + " " + outro + "\n";
                user.recordActivities(timeDate.getSimpleDate(), pastActivities);

            } else {
                pastActivities = timeDate.getHour() + ": " + activity.getID() + ": " + activity.getActivity() + " " + outro + "\n";
                user.recordActivities(timeDate.getSimpleDate(), pastActivities);
            }

            try {
                updateUser(user);
            } catch (NonExistentUser nExUsr) {
                Debugger.debug(nExUsr);
            }
        } catch (ServerInitializationException serEx) {
            Debugger.debug(serEx);
        }

    }

    /**
     * Gathers all contacts of an user in its groups in a map
     *
     * @param userName name of the user
     * @return a map of name to user
     */
    private HashMap<String, CollAUser> getUserContacts(String userName) throws NonExistentUser {
        HashMap<String, CollAUser> contacts = new HashMap<String, CollAUser>();
        try {
            Server server = Server.getInstance();
            CollAUser tmp = server.getUser(userName);
            for (String groupName : tmp.getGroups().keySet()) {
                for (String name : tmp.getGroups().get(groupName).getMembers()) {
                    contacts.put(name, server.getUser(name));
                }
            }
        } catch (ServerInitializationException serEx) {
            Debugger.debug(serEx);
        }
        return contacts;
    }

    /**
     * Record activities on the server for a host
     *
     * @param host an CollAHost
     * @param activity the activity id that will be recorded
     * @param outro an optional activity description
     */
    private void recordActivities(CollAHost host, ActivityID activity, String outro) throws NonExistentUser {
        try {
            Server server = Server.getInstance();
            CollAUser usr = server.getUser(host.getNameUser());
            HashMap<String, String> record;
            String pastActivities;
            if (outro == null) {
                outro = "";
            }
            record = host.getActivities();

            TimeAndDate timeDate = server.getTimeAndDateInstance();

            pastActivities = record.get(timeDate.getSimpleDate());
            if (pastActivities == null) {
                pastActivities = timeDate.getHour() + ": " + activity.getID() + ": " + activity.getActivity() + outro + "\n";
                host.recordActivities(timeDate.getSimpleDate(), pastActivities);
            } else {
                pastActivities = timeDate.getHour() + ": " + activity.getID() + ": " + activity.getActivity() + outro + "\n";
                host.recordActivities(timeDate.getSimpleDate(), pastActivities);
            }
            usr.addHost(host);
            server.updateUser(usr);
        } catch (ServerInitializationException serEx) {
            Debugger.debug(serEx);
        }

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
        try {
            Server server = Server.getInstance();
            CollAGroup group = server.getGroup(groupName);
            for (String userName : group.getMembers()) {
                try {
                    CollAUser user = server.getUser(userName);
                    // Somente usuários que estão online e não possuem IP válido.
                    if (!user.getName().equals(task.getOwner()) && user.isOnline() && !user.hasValidIP()) {
                        try {
                            Socket s = server.getAMapedConnection(user.getName());
                            if (s != null) {
                                NotifyTaskStarted msg = new NotifyTaskStarted(task);
                                msg.setGroupName(groupName);
                                msg.setTaskName(taskName);
                                ObjectOutputStream output = new ObjectOutputStream(s.getOutputStream());
                                output.writeObject(msg);
                                output.flush();
                                //espera pelo ACK
                                ObjectInputStream input = new ObjectInputStream(s.getInputStream());
                                input.readObject();
                            }
                        } catch (Exception e) {
                            Debugger.debug(e);
                        }
                    }// end if
                } catch (NonExistentUser nExUser) {
                    Debugger.debug(nExUser);
                }
            }// end for
        } catch (ServerInitializationException serEx) {
            Debugger.debug(serEx);
        }
    }// end method 

    /**
     * updates the user on the server
     *
     * @param usr
     */
    private void updateUser(CollAUser usr) throws NonExistentUser {
        try {
            Server server = Server.getInstance();
            server.updateUser(usr);
        } catch (ServerInitializationException serEx) {
            Debugger.debug(serEx);
        }
    }

    /**
     * Updates a host on server
     *
     * @param host
     */
    private void updateHost(CollAHost host) throws NonExistentUser {
        try {
            Server server = Server.getInstance();
            server.updateHost(host);
        } catch (ServerInitializationException serEx) {
            Debugger.debug(serEx);
        }
    }

    /**
     * Sends a update of the user to all its groups.
     *
     * @param userName user name to be updated.
     */
    private void sendMemberUpdateToGroups(String userName) {
        try {
            Server server = Server.getInstance();

            CollAUser usr = server.getUser(userName);
            if (usr.getGroups() != null) {
                Set<String> groupsSet = usr.getGroups().keySet();
                for (String groupName : groupsSet) {
                    sendGroupUpdateToMembers(groupName, usr.getName());
                }
            }
        } catch (ServerInitializationException | NonExistentUser e) {
            Debugger.debug(e);
        }
    }

    /**
     * Sends a update of the group to all its members.
     *
     * @param groupName name of the group
     * @param userName name of the user
     */
    private void sendGroupUpdateToMembers(String groupName, String userName) {
        try {
            Server server = Server.getInstance();
            CollAGroup groupUpdated = server.getGroup(groupName);
            HashMap<String, CollAUser> usersMap = new HashMap<>();
            for (String memberName : groupUpdated.getMembers()) {
                try {
                    usersMap.put(memberName, server.getUser(memberName));
                } catch (NonExistentUser nExUsr) {
                    Debugger.debug(nExUsr);
                }
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
                                ObjectInputStream inputAux = new ObjectInputStream(aux.getInputStream());
                                inputAux.readObject();
                                aux.close();
                            } catch (IOException | ClassNotFoundException ex) {
                                Debugger.debug(ex);
                            }
                        } // se usuário não possui ip válido
                        else if (!tempUser.hasValidIP() && server.getAMapedConnection(memberName) != null) {
                            try {
                                Socket aux = server.getAMapedConnection(memberName);
                                JoinAGroupAnswerMsg sendGroup = new JoinAGroupAnswerMsg(groupUpdated);
                                sendGroup.setUsersMap(usersMap);
                                ObjectOutputStream outputAux = new ObjectOutputStream(aux.getOutputStream());
                                outputAux.writeObject(sendGroup);
                                outputAux.flush();
                                //wait for ACK
                                ObjectInputStream inputAux = new ObjectInputStream(aux.getInputStream());
                                inputAux.readObject();
                            } catch (IOException | ClassNotFoundException io) {
                                Debugger.debug(io);
                            }
                        }
                    } else if (!tempUser.isOnline()) {
                        tempUser.addGroup(groupName, groupUpdated);
                        try {
                            server.updateUser(tempUser);
                        } catch (NonExistentUser ex) {
                            Logger.getLogger(ServerWorker.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }// end if

            }// end for
        } catch (ServerInitializationException servEx) {
            Debugger.debug(servEx);
        }
    }// end method

    /**
     * Sends a update of the group to all its admins
     *
     * @param groupName name of the group
     * @param username an CollAUser
     */
    private void sendGroupUpdateToAdmins(String groupName, String username) {
        try {
            Server server = Server.getInstance();
            CollAGroup groupUpdated = server.getGroup(groupName);
            HashMap<String, CollAUser> adminsMap = new HashMap<>();
            HashMap<String, CollAUser> membersMap = new HashMap<>();            
            for (String adminName : groupUpdated.getAdminsList()) {
                try {
                    adminsMap.put(adminName, server.getUser(adminName));
                } catch (NonExistentUser ex) {
                    Logger.getLogger(ServerWorker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
            for (String usrName : groupUpdated.getMembers()) {
                try {
                    membersMap.put(usrName, server.getUser(usrName));
                } catch (NonExistentUser ex) {
                    Logger.getLogger(ServerWorker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            for (String adminName : adminsMap.keySet()) {
                CollAUser temp = adminsMap.get(adminName);
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
                                ObjectInputStream inputAux = new ObjectInputStream(aux.getInputStream());
                                inputAux.readObject();
                                aux.close();
                            } catch (Exception ex) {
                                Debugger.debug("An error ocurred while sending group " + groupName + " to its admins", ex);
                                server.displayMessage("An error ocurred while sending group " + groupName + " to its admins");
                            }
                        }//end if
                        // se administrador não possui IP válido
                        else if (!temp.hasValidIP() && server.getAMapedConnection(adminName) != null) {
                            try {
                                Socket aux = server.getAMapedConnection(adminName);
                                JoinAGroupAnswerMsg sendGroup = new JoinAGroupAnswerMsg(groupUpdated);
                                sendGroup.setUsersMap(membersMap);
                                ObjectOutputStream outputAux = new ObjectOutputStream(aux.getOutputStream());
                                outputAux.writeObject(sendGroup);
                                outputAux.flush();
                                // espera por ACK
                                ObjectInputStream inputAux = new ObjectInputStream(aux.getInputStream());
                                inputAux.readObject();
                            } catch (Exception io) {
                                Debugger.debug("An error ocurred while sending group " + groupName + " to its admins", io);
                                server.displayMessage("An error ocurred while sending group " + groupName + " to its admins");
                            }
                        }// end if
                    }// end if
                    else if (!temp.isOnline()) {      
                        temp.addGroup(groupName, groupUpdated);
                        try {
                            server.updateUser(temp);
                        } catch (NonExistentUser ex) {
                           Debugger.debug(ex);
                        }
                    }
            }// end for
        } catch (ServerInitializationException servEx) {
            Debugger.debug(servEx);
        }
    }// end method

    /**
     *
     * @return the port in which the ServerSocket is listening
     */
    private int getPortNumber() {
        int portNumber = 0;
        try {
            Server server = Server.getInstance();
            portNumber = server.getPortNumber();
        } catch (ServerInitializationException servEx) {
            Debugger.debug(servEx);
        }
        return portNumber;
    }

    /**
     *
     * @param userName
     *
     * @return an object of User
     */
    private CollAUser getUser(String userName) throws NonExistentUser {
        CollAUser user = null;
        try {
            Server server = Server.getInstance();
            user = server.getUser(userName);
        } catch (ServerInitializationException servEx) {
            Debugger.debug(servEx);
        }
        return user;

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
        try {
            Server server = Server.getInstance();
            Socket socket = server.getAMapedConnection(receiver);
            ObjectOutputStream output;
            ObjectInputStream input;
            try {
                // se destinatário está online
                if (socket != null && server.getUser(receiver).isOnline()) {
                    ChatDirectMessageMsg msg = new ChatDirectMessageMsg(sender, message);
                    output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(msg);
                    output.flush();
                    //espera por ACK
                    input = new ObjectInputStream(socket.getInputStream());
                    input.readObject();
                } // se destinatário não está online enviamos uma mensagem para quem enviou a mensagem.
                else {
                    CollAUser user = getUser(sender);
                    if (user.hasValidIP()) {
                        socket = new Socket(user.getIp(), user.getPort());
                    } else {
                        socket = server.getAMapedConnection(sender);
                    }
                    ChatDirectMessageMsg msg = new ChatDirectMessageMsg("From Server:", receiver + " is offline");
                    output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(msg);
                    output.flush();
                    //espera por ACK
                    input = new ObjectInputStream(socket.getInputStream());
                    input.readObject();
                    if (user.hasValidIP()) {
                        socket.close();
                    }
                }
            } catch (Exception io) {
                Debugger.debug("Error: could not transmit message from " + sender + " to " + receiver, io);
                server.displayMessage("Error: could not transmit message from " + sender + " to " + receiver);
            }
        } catch (ServerInitializationException servEx) {
            Debugger.debug(servEx);
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
        Debugger.debug("Retransmit with group " + group);
        Debugger.debug("Sender = " + sender + " Receiver = " + receiver);
        ObjectOutputStream output;
        ObjectInputStream input;
        TaskMessage outgoing;
        try {
            Server server = Server.getInstance();
            Socket socket = server.getAMapedConnection(receiver);
            if (socket != null) {
                if (task.isDistributed()) {
                    outgoing = new DistributedTaskMsg();
                } else {
                    outgoing = new TaskMessage();
                }
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
                input = new ObjectInputStream(socket.getInputStream());
                input.readObject();
            } else {
                Debugger.debug("Socket null");
                /*
                 * TODO Fazer o caso do host estar offline. Retornar uma
                 * mensagem ao usuário, procurar outro host o servidor mesmo e
                 * repassar a task. 
                 * A questão é: continuaremos suportando hosts com ip invalido?
                 */
            }
        } catch (Exception e) {
            Debugger.debug("Problema com a retransmissão da task", e);
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
        ObjectOutputStream output;
        ObjectInputStream input;
        TaskResultMsg outgoing = new TaskResultMsg(sender, taskName, result);
        outgoing.setGroupName(group);
        try {
            Server server = Server.getInstance();
            Socket socket = server.getAMapedConnection(receiver.getName());
            // se usuário está online
            if (socket != null && server.getUser(receiver.getName()).isOnline()) {
                Debugger.debug("Sending a task_result to " + receiver.getName());
                output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(outgoing);
                output.flush();
                // espera por ACK
                input = new ObjectInputStream(socket.getInputStream());
                input.readObject();
            } else {
                //store result in server until client come back online
                ArrayList<TaskResultMsg> resultsArray = server.getMapedResults(receiver.getName());
                if (resultsArray == null) {
                    resultsArray = new ArrayList<TaskResultMsg>();
                }
                resultsArray.add(outgoing);
                server.updateResultsMap(receiver.getName(), resultsArray);
            }
        } catch (Exception io) {
            Debugger.debug("Error: could not transmit message from " + sender + " to " + receiver, io);
        }
    }// end method

    /**
     * Gets all hosts from an determined group.
     *
     * @param groupName The group from which to get the hosts
     * @return a List containing the online hosts belonging to the group
     */
    private List<CollAHost> getHostsFromGroup(String groupName) {
        List<CollAHost> HostsFromGroup = new ArrayList<CollAHost>();
        try {
            Server server = Server.getInstance();
            CollAGroup cGroup = server.getGroup(groupName);
            List<String> userNames = cGroup.getMembers();
            //System.out.println("Users from group " + groupName + ": " + userNames);
            Iterator<String> userNamesIt = userNames.iterator();
            /*
             * For each user I get your hosts and add in the list of hosts
             */
            while (userNamesIt.hasNext()) {
                try {
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
                } catch (NonExistentUser ex) {
                    Logger.getLogger(ServerWorker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }// end while
        } catch (ServerInitializationException servEx) {
            Debugger.debug(servEx);
        }
        return HostsFromGroup;
    }// end method

    /**
     * Gets all hosts online.
     *
     * @return a List containing the online hosts.
     */
    private List<CollAHost> getHostsOnline() {
        List<CollAHost> hostsOnline = new ArrayList<CollAHost>();
        try {
            Server server = Server.getInstance();
            Set<String> userNames = server.getUsersSet();
//        System.out.println("Users from group " + groupName + ": " + userNames);
            Iterator<String> userNamesIt = userNames.iterator();
            /* For each user I get its hosts and add in the list of hosts
             */
            while (userNamesIt.hasNext()) {
                try {
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
                } catch (NonExistentUser nExUsr) {
                    Debugger.debug(nExUsr);
                }

            }// end while
        } catch (ServerInitializationException servEx) {
            Debugger.debug(servEx);
        }
        return hostsOnline;
    }// end method

    /**
     * Send a host to a client for execution of tasks.
     *
     * @param user the client
     * @param group the group to which the user will send the request
     */
    private void sendAvailableHostsToClient(String name, String group) {
        Socket socket;
        try {
            Server server = Server.getInstance();
            SendAvailableHostsMsg outgoing = new SendAvailableHostsMsg(server.generateTaskID());
            CollAUser user = server.getUser(name);
            // Get both primary and secondary hosts at random
            CollAHost primary = null;
            CollAHost secondary = null;
            List<CollAHost> hostsOnline = getHostsOnline();
            if (hostsOnline.size() > 0) {
                int i = (int) (Math.random() * hostsOnline.size());
                primary = hostsOnline.get(i);
                int secondaryIndex = (i+1) % hostsOnline.size();
                if( secondaryIndex != i)
                    secondary = hostsOnline.get(secondaryIndex);                    
            }
            outgoing.addHost(primary);
            outgoing.addHost(secondary);
            // If user don't have a valid IP we use the socket in connectionsMap
            if (!user.hasValidIP()) {
                socket = server.getAMapedConnection(user.getName());

            } else {
                socket = new Socket(InetAddress.getByName(user.getIp()), user.getPort());
                socket.setSoTimeout(server.getTimeoutValue());
            }
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(outgoing);
            output.flush();
            //espera por ACK
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            input.readObject();
            if (user.hasValidIP()) {
                socket.close();
            }
            Debugger.debug("Host " + primary.getName() + " has been sent to user " + user.getName());
        } catch (Exception ex) {
            Debugger.debug("Error: could not retrive hosts to " + name, ex);
        }
    }
    
    /**
     * 
     * @return a CollAMsg listing all online hosts
     */
    public ListOnlineHostsMsg listOnlineHosts(){
        ListOnlineHostsMsg msg = new ListOnlineHostsMsg();
        
        for(CollAHost host : this.getHostsOnline()){
            msg.addHost(host);
        }
        return msg;
    }
    
    private long generateSeassionID(Long id) {
        return id;
    }

    /**
     *
     * @return a Set containing the names of the users
     */
    private Set<String> getUsersSet() {
        Set<String> userSet = new TreeSet<String>();
        try {
            Server server = Server.getInstance();
            userSet.addAll(server.getUsersSet());
        } catch (ServerInitializationException servEx) {
            Debugger.debug(servEx);
        }
        return userSet;
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
    private CollAMessage clientLogin(String userName, String password, String ipAsClientSeesIt, Socket socket) {
        DeveloperViewerLoginAnswerMsg answer = new DeveloperViewerLoginAnswerMsg();
        try {
            Server server = Server.getInstance();
            CollAUser user = server.getUser(userName);
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
            /**
             * @debug user.validateIP(); user.setIp("127.0.0.1"); /*
             */
            // verifies the passwords send true if the password is corretct
            if (server.getUserPassword(user.getName()) != null
                    && password.equals(server.getUserPassword(user.getName()))) {
                user.setOnline();
                user.setConnectionInitialized();
                //invalidate port to avoid connections while login is not complete
                user.setPort(-1);
                Session newSession = new Session();
                newSession.setSessionID(server.generateSessionID());
                user.addSession(newSession);
                //server.displayMessage("User " + user.getName() + " has connected");
                Debugger.debug("User " + user.getName() + " has connected");
                answer.confirmPassword(true);
                answer.setUser(server.getUser(user.getName()));
                recordActivities(user, ActivityID.LOGIN, null);
                answer.setContacts(this.getUserContacts(user.getName()));
            } else {
                answer.confirmPassword(false);
            }
        } catch (NonExistentUser nExUsr) {
            Debugger.debug(nExUsr);
            answer.confirmUserName(false);
        } catch (ServerInitializationException servEx) {
            Debugger.debug(servEx);
        }

        return answer;
    }// end method

    /**
     * Add a client to a group waiting list
     *
     * @param userName name of client asking to join
     * @param groupName name of the group
     */
    private void requestToJoinGroup(String userName, String groupName) {
        try {
            Server server = Server.getInstance();
            CollAGroup group = server.getGroup(groupName);
            group.addMemberToWaitingList(userName);
            server.updateGroup(groupName, group);
        } catch (ServerInitializationException servInit) {
            Debugger.debug(servInit);
        }
    }

    /**
     * Disconnect client and update it on server
     *
     * @param user User that comes from client that is disconnecting
     * @param temp User that was stored on users map
     */
    private void clientDisconnect(CollAUser user, CollAUser temp) {
        try {
            Server server = Server.getInstance();
            for (CollAHost host : temp.getHosts()) {
                user.addHost(host);
            }
            user.setPort(-1);
            user.setOffline();
            //server.displayMessage("User " + user.getName() + " has disconnected");
            Debugger.debug("User " + user.getName() + " has disconnected");
            try {
                updateUser(user);
                sendMemberUpdateToGroups(user.getName());
                recordActivities(user, ActivityID.DISCONNECT, "\nConnection total time: " + user.getConnectionTotalTime());
            } catch (NonExistentUser nExUsr) {
                Debugger.debug(nExUsr);
            }
            server.removeAMappedConnection(user.getName());
        } catch (ServerInitializationException servInit) {
            Debugger.debug(servInit);
        }
    }

    /**
     * Set a host as offline
     *
     * @param host host disconnecting from server
     */
    private void hostDisconnect(CollAHost host) {
        try {
            Server server = Server.getInstance();
            host.setPort(-1);
            host.setOffline();
            //server.displayMessage("Host " + host.getName() + " has disconnected");
            Debugger.debug("Host " + host.getName() + " has disconnected");
            try {
                updateHost(host);
            } catch (NonExistentUser nExUsr) {
                Debugger.debug(nExUsr);
            }
        } catch (ServerInitializationException servInit) {
            Debugger.debug(servInit);
        }
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
        try {
            Server server = Server.getInstance();
            if (server.getUserPassword(user.getName()) != null) {
                //true if the name is not available
                response.nameAlreadyInUse(Boolean.TRUE);
            } else {
                //false if the name is available
                response.nameAlreadyInUse(Boolean.FALSE);
                try {
                    server.addUser(user);
                } catch (UserAlreadyExists usrAEx) {
                    //true if the name is not available
                    response.nameAlreadyInUse(Boolean.TRUE);
                    Debugger.debug(usrAEx);
                }
                //reads crypted passaword
                server.setUserPassword(user.getName(), pass);
            }
        } catch (ServerInitializationException servInit) {
            Debugger.debug(servInit);
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
        ObjectInputStream input;
        try {
            Server server = Server.getInstance();
            if (usr.hasValidIP()) {
                socket = new Socket(InetAddress.getByName(usr.getIp()), usr.getPort());
                socket.setSoTimeout(server.getTimeoutValue());
            } else {
                socket = server.getAMapedConnection(usr.getName());
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
            input = new ObjectInputStream(socket.getInputStream());
            input.readObject();
            if (usr.hasValidIP()) {
                socket.close();
            }           
            
        } catch (Exception io) {
            Debugger.debug("Error while creating new group. Client: " + usr.getName() + "\n"
                    + "Client IP Address: " + usr.getIp(), io);
        }
    }// end method

    /**
     * Envia os grupos que o cliente pertence para o cliente.
     *
     * @param user user that will receive the groups
     */
    private void sendGroupsToClient(CollAUser user) {
        TreeSet<String> groupsSet = new TreeSet<>();
        GetGroupsAnswerMsg response = new GetGroupsAnswerMsg();
        Socket socketAux;
        try {
            Server server = Server.getInstance();
            Set<String> groups = server.getAllGroups();
            for (String i : groups) {
                groupsSet.add(i);
            }
            if (user.hasValidIP()) {
                socketAux = new Socket(InetAddress.getByName(user.getIp()), user.getPort());
                socketAux.setSoTimeout(server.getTimeoutValue());

            } else {
                socketAux = server.getAMapedConnection(user.getName());
            }
            response.setGroupsSet(groupsSet);
            ObjectOutputStream output = new ObjectOutputStream(socketAux.getOutputStream());
            output.writeObject(response);
            output.flush();
            // espera por ACK
            ObjectInputStream input = new ObjectInputStream(socketAux.getInputStream());
            input.readObject();
            if (user.hasValidIP()) {
                socketAux.close();
            }
        } catch (ServerInitializationException | IOException | ClassNotFoundException ex) {
            Debugger.debug("Error: couldn't send list of groups to client " + user.getName()
                    + " at " + user.getIp(), ex);
        }
    }// end method

    /**
     * Vincula um host a um cliente.
     *
     * @param host o hot para ser vinculado.
     * @param userName o nome do usuário que terá o host vinculado.
     */
    private void hostAdd(Host host, String userName) throws NonExistentUser {
        try {
            Server server = Server.getInstance();
            CollAUser user = server.getUser(userName);
            CollAHost temp = user.getHost(host.getName());
            // If host is already in mapHosts
            if (temp != null) {
                host.setActivities(temp.getActivities());
            }
            //Find User and add his host        
            user.addHost(host);
            this.updateUser(user);
        } catch (ServerInitializationException servEx) {
            Debugger.debug(servEx);
        }
    }

    /**
     * Envia um resultado guardado ao cliente.
     *
     * @param userName o nome do cliente que receberá o resultado.
     */
    private void sendStoredResults(String userName) {
        try {
            Server server = Server.getInstance();
            CollAUser user = server.getUser(userName);
            if (user.getPort() > 0) {
                try {
                    SendStoredResultsMsg outgoing = new SendStoredResultsMsg(server.getMapedResults(userName));
                    if (outgoing.getResults() != null) {
                        Socket socket;
                        ObjectOutputStream output;
                        ObjectInputStream input;
                        if (user.hasValidIP()) {
                            socket = new Socket(InetAddress.getByName(user.getIp()), user.getPort());
                        } else {
                            socket = server.getAMapedConnection(userName);
                        }
                        output = new ObjectOutputStream(socket.getOutputStream());
                        output.writeObject(outgoing);
                        output.flush();
                        // espera por ACK
                        input = new ObjectInputStream(socket.getInputStream());
                        input.readObject();
                        if (user.hasValidIP()) {
                            socket.close();
                        }
                        //discards stored result                        
                        server.removeMapedResults(userName);
                    } // otherwise there is nothing to send to client
                } catch (IOException | ClassNotFoundException ex) {
                    server.displayMessage("Couldn't send stored results to " + userName);
                    Debugger.debug("Couldn't send stored results to " + userName, ex);
                }
            }// end if
        } catch (NonExistentUser | ServerInitializationException nExUsr) {
            Debugger.debug(nExUsr);
        }
    }// end method

    /**
     * Busca os usuários cadastrados em um grupo determinado.
     *
     * @param groupName o nome do grupo a ser pesquisado
     * @return os usuários do grupo
     */
    private HashMap<String, CollAUser> getUsersFromGroup(String groupName) {
        HashMap<String, CollAUser> group = new HashMap<>();
        return group;
    }

    private void updateGroups(List<CollAGroup> groups) {
        try {
            Server server = Server.getInstance();
            for (CollAGroup group : groups) {
                server.updateGroup(group.getName(), group);
                this.sendGroupUpdateToMembers(group.getName(), "0");
            }
        } catch (ServerInitializationException servEx) {
            Debugger.debug(servEx);
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
            Server server = Server.getInstance();
            if (user.isOnline()) {
                if (user.hasValidIP()) {
                    socket = new Socket(InetAddress.getByName(user.getIp()), user.getPort());
                } else {
                    socket = server.getAMapedConnection(userName);
                }
                SendOwnedHostsMsg msg = new SendOwnedHostsMsg();
                msg.addHost(host);
                output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(msg);
                output.flush();
                // espera por ACK
                input = new ObjectInputStream(socket.getInputStream());
                input.readObject();
                if (user.hasValidIP()) {
                    socket.close();
                }
            }
        } catch (NonExistentUser | ServerInitializationException | IOException | ClassNotFoundException io) {
            Debugger.debug("Problema com o envio do Host Update to Owner", io);
        }
    }// end method    
}