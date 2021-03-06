/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.developer_viewer;

import colla.appl.developer_viewer.tasks.SentTask;
import colla.appl.developer_viewer.exceptions.DeveloperControllerInitializationException;
import colla.appl.developer_viewer.tasks.SentTasksMonitor;
import colla.appl.developer_viewer.view.CollADeveloperViewerUI;
import colla.kernel.api.*;
import colla.kernel.impl.Task;
import colla.kernel.messages.toClient.ChatDirectMessageMsg;
import colla.kernel.messages.toHost.CancelTask;
import colla.kernel.messages.toHost.DistributedTaskMsg;
import colla.kernel.messages.toHost.DownloadResultMsg;
import colla.kernel.messages.toHost.TaskMessage;
import colla.kernel.messages.toServer.*;
import colla.kernel.util.Debugger;
import colla.kernel.util.TimeAndDate;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.openide.util.Exceptions;

/**
 * Works as union point and controller of the all GUI's
 *
 * @author dmatos
 */
public class DeveloperViewerController extends Observable implements Runnable {

    /**
     *
     * @param usr the user
     * @param serverPort the server port
     * @param serverIP the server ip adress
     * @param contacts contacts of the user
     */
    private DeveloperViewerController(CollAUser usr, int serverPort,
            String serverIP, int secondaryServerPort,
            String secondaryServerIp, HashMap<String, CollAUser> contacts) {
        this.user = usr;
        this.serverIPaddress = serverIP;
        this.serverPortNumber = serverPort;
        this.secondaryServerIP = secondaryServerIp;
        this.secondaryServerPort = secondaryServerPort;
        this.tasksToRun = new ConcurrentLinkedQueue<CollATask>();
        this.taskFile = null;
        this.taskDependencies = new ArrayList<File>();
        this.taskArgs = new ArrayList<File>();
        this.taskResults = new HashMap<String, HashMap<String, CollATask>>();
        this.contacts = contacts;
        this.isDown = false;
        this.devUI = null;
        this.hostsWithScheduledTasks = new HashMap<Long, CollAHost>();
        this.sentTaskMonitor = new SentTasksMonitor(serverIP, serverPort);
        this.scheduleSentTaskMonitor = new Timer();
        this.monitorDelayAndPeriod = new Long(300000); //5 minutes
    }

    /**
     * Initializes a Singleton instance of DeveloperViewerController.
     *
     * @param usr An instance of CollAUser logged in to a CollAServer.
     * @param serverPort port number of the CollAServer.
     * @param serverIP IP address of the CollAServer.
     * @param contacts Contacts received from the CollAServer.
     * @return a instance of DeveloperViewerController.
     */
    public static synchronized DeveloperViewerController setupDeveloperController(
            CollAUser usr, int serverPort, String serverIP,
            int secondaryServerPort, String secondaryServerIp,
            HashMap<String, CollAUser> contacts) {
        if (devController == null) {
            devController = new DeveloperViewerController(usr, serverPort,
                    serverIP, secondaryServerPort, secondaryServerIp, contacts);
        }
        return devController;
    }

    /**
     * Gets a Singleton instance of DeveloperViewerController, but
     * setupDeveloperController must be called prior to any call to this method.
     *
     * @return a Singleton instance of DeveloperViewerController
     * @throws DeveloperControllerInitializationException
     */
    public static synchronized DeveloperViewerController getInstance()
            throws DeveloperControllerInitializationException {
        if (devController == null) {
            throw new DeveloperControllerInitializationException(
                    "DeveloperViewerController has not been intiazlized");
        }
        return devController;
    }

    @Override
    public void run() {
        try {
            microServer = new DevMicroServer(serverIPaddress, serverPortNumber);
            Thread thr = new Thread(microServer);
            thr.start();
            if (devUI != null) {
                devUI.updateAllGroups(user.getGroups());
            }
            this.uploadUserToServer();
            this.retrieveStoredResultsFromServer();
        } catch (DeveloperControllerInitializationException devEx) {
            devEx.printStackTrace();
        }
        this.scheduleSentTaskMonitor.schedule(sentTaskMonitor, monitorDelayAndPeriod,
                monitorDelayAndPeriod);
    }

    /**
     * update a group
     *
     * @param groupName name of the group
     * @param group a map representing the goup
     */
    void refresh_a_group(String groupName, Set<String> group) {
        if (devUI != null) {
            devUI.refresh_a_group(groupName, group);
        }
    }

    /**
     * Asks for stored results, if any, from server.
     */
    public void retrieveStoredResultsFromServer() {
        RetrieveStoredResultsMsg outgoing = new RetrieveStoredResultsMsg(
                user.getName());
        try {
            Socket socket = new Socket(InetAddress.getByName(serverIPaddress),
                    serverPortNumber);
            socket.setSoTimeout(timeout);
            ObjectOutputStream output = new ObjectOutputStream(
                    socket.getOutputStream());
            output.writeObject(outgoing);
            output.flush();
            // espera por ACK
            ObjectInputStream input = new ObjectInputStream(
                    socket.getInputStream());
            input.readObject();
            socket.close();
        } catch (ConnectException | SocketTimeoutException ex) {
            Debugger.debug(ex);
            this.exchangeServers();
        } catch (Exception io) {
            // io.printStackTrace();
        }
    }

    /**
     * Prepares a task to be sent.
     *
     * @param taskFile
     * @param attachFiles
     * @param args
     * @param classToExecute
     * @param methodToExecute
     * @param group
     * @param schedule
     */
    private void prepareTaskToSend(File taskFile, ArrayList<File> attachFiles,
            ArrayList<File> args, String classToExecute,
            String methodToExecute, String group, Date schedule,
            Boolean isDistributed) {
        try {
            Task task = new Task();
            task.setOwner(this.user.getName());
            task.setGroup(group);
            task.setTask(taskFile);
            task.setTaskName(taskFile.getName());
            task.setClassToExecute(classToExecute);
            task.setMethodToExecute(methodToExecute);
            task.setSchedule(schedule);
            task.setDistributedMode(isDistributed);
            Iterator<File> dependencyIterator = attachFiles.iterator();
            Iterator<File> parameterIterator = args.iterator();
            while (dependencyIterator.hasNext()) {
                task.addDependency(dependencyIterator.next());
            }
            while (parameterIterator.hasNext()) {
                task.addArgument(parameterIterator.next());
            }
            tasksToRun.add(task);
            Debugger.debug("Task inserida na lista de tasks para ser executada");
        } catch (Exception e) {
            Debugger.debug(e);
        }
    }

    public void setTaskToResend(CollATask task) {
        this.tasksToRun.add(task);
    }

    /**
     * Asks the server for a set of available hosts to execute a task.
     *
     * @param taskFile the task file to be executed.
     * @param attachFiles the attached files of the task.
     * @param args the argument files of task.
     * @param classToExecute class to be executed.
     * @param methodToExecute method to be executed.
     * @param group group to which this task results will be shared.
     */
    public void getAvailableHostsOnServer(File taskFile,
            ArrayList<File> attachFiles, ArrayList<File> args,
            String classToExecute, String methodToExecute, String group,
            Date schedule, boolean isDistributed) {
        try {
            // Insert the taskFile into queue to send when host is arrived.
            this.prepareTaskToSend(taskFile, attachFiles, args, classToExecute,
                    methodToExecute, group, schedule, isDistributed);
            // Creates a socket with server
            // socket.setSoTimeout(timeout);

            /*
             * Creates a CollAMessage to communicate with the server
             */
            GetAvailableHostsMsg outgoing = new GetAvailableHostsMsg();
            outgoing.setSender(this.user.getName());
            outgoing.setGroup(group);
            Socket socket = new Socket(InetAddress.getByName(serverIPaddress),
                    serverPortNumber);
            socket.setSoTimeout(timeout);
            ObjectOutputStream output = new ObjectOutputStream(
                    socket.getOutputStream());
            output.writeObject(outgoing);
            output.flush();
            // espera por ACK
            ObjectInputStream input = new ObjectInputStream(
                    socket.getInputStream());
            input.readObject();
            socket.close();

        } catch (ConnectException | SocketTimeoutException tex) {
            Debugger.debug(tex);
            this.exchangeServers();
            this.getAvailableHostsOnServer(taskFile, attachFiles, args,
                    classToExecute, methodToExecute, group, schedule,
                    isDistributed);
        } catch (IOException io) {
            Debugger.debug(io);
        } catch (ClassNotFoundException cnfe) {
            Debugger.debug(cnfe);
        }
    }// fim do método getAvailableHostsOnServer

    /**
     * Send a task to execute in any host.
     *
     * @param hosts a host sent by a server that is available to run tasks.
     * @param taskID a long representing a task ID which must unique to a
     * server.
     *
     */
    public void sendTaskToRun(List<CollAHost> hosts, Long taskID) {
        Socket socket;
        ObjectOutputStream output;
        ObjectInputStream input;
        CollATask task;
        SentTask sentTask;
        if (!tasksToRun.isEmpty()) {
            try {
                // Retrieves the tasks from queue of tasks.
                task = tasksToRun.remove();
                Debugger.debug("tarefa retirada da pilha de tarefas para execução");
                this.displayInfo(task.getTaskName()
                        + " has been successfully sent.");
            } catch (EmptyStackException e) {
                Debugger.debug("list tasksToRun is empty!", e);
                this.displayInfo("Sorry, an error has ocurred. Try again later.");
                return;
            }

            task.setTaskID(taskID);
            sentTask = new SentTask(task);
            sentTask.addHosts(hosts);

        } else {
            Debugger.debug("no task found to send to run");
            return;
        }

        if (this.sendTaskToHost(task, hosts, 0)) {
            // update the sent tasks table
            updateResults(sentTask.getGroup(), sentTask.getTaskName(), sentTask);
            //Debugger.debug("task " + task.getTaskName() + " sent");
        } else if (this.getTaskResult(sentTask.getGroup(), sentTask.getTaskName()) != null) {
            try {
                sentTask.setResult(null);
                updateResults(sentTask.getGroup(), sentTask.getTaskName(), sentTask);
            } catch (Exception ex) {
                Debugger.debug(ex);
            }
        }

        /* Destruindo o objeto task
         * task.clear();
         * task = null;
         */
    }

    /**
     * Try to send a task to at most a host from a list of available hosts.
     *
     * @param task
     * @param hosts
     * @param hostToTry
     * @return
     */
    private boolean sendTaskToHost(CollATask task, List<CollAHost> hosts, int hostToTry) {
        if (!hosts.isEmpty() && hostToTry < hosts.size() && hosts.get(hostToTry) != null) {
            try {
                CollAHost host = hosts.get(hostToTry);
                //trying to send to primary host
                if (host.hasValidIP()) {
                    this.sendTaskToValidIP(task, host);
                } else {
                    this.sendTaskToInvalidIP(task, host);
                    //return true;
                }
                return true;
            } catch (Exception ex) {
                Debugger.debug("Problema com o envio da task para host primario", ex);
                return this.sendTaskToHost(task, hosts, ++hostToTry);
            }
        }// fim do if (!hosts.isEmpty() && hosts.get(0) != null)
        else {
            Debugger.debug("Sorry, there are not available hosts. Try again later.");
            this.displayInfo("Sorry, there are not available hosts. Try again later.");
        }
        return false;
    }

    /**
     * Sends a task to run in a CollAHost which has a valid IP.
     *
     * @param task task to execute
     * @param host host to which a task will be sent
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void sendTaskToValidIP(CollATask task, CollAHost host)
            throws IOException, ClassNotFoundException {
        Socket socket;
        ObjectOutputStream output;
        ObjectInputStream input;
        TaskMessage outgoing;
        // creating a message to send to host
        if (task.isDistributed()) {
            outgoing = new DistributedTaskMsg();
        } else {
            outgoing = new TaskMessage();
        }
        outgoing.setUser(this.user);
        outgoing.setTask(task);
        // grupo ao qual a task será enviada
        HashMap<String, CollAUser> tempGroup = new HashMap<String, CollAUser>();
        tempGroup.put(this.user.getName(), this.user);
        if (user.getGroups().get(task.getGroup()) != null) {
            for (String userName : user.getGroups().get(task.getGroup())
                    .getMembers()) {
                tempGroup.put(userName, contacts.get(userName));
            }
        }

        outgoing.setGroup(tempGroup);
        outgoing.setGroupName(task.getGroup());

        // A host that has a valid IP address must receive a direct connection
        Debugger.debug("enviando task para "+host.getIp()+":"+host.getPort());
        socket = new Socket(InetAddress.getByName(host.getIp()), host.getPort());
        socket.setSoTimeout(timeout);
        output = new ObjectOutputStream(socket.getOutputStream());
        output.writeObject(outgoing);
        output.flush();
        // receives ACK
        Debugger.debug("esperando ACK IP valido...");
        input = new ObjectInputStream(socket.getInputStream());
        input.readObject();
        socket.close();

        this.hostsWithScheduledTasks.put(task.getTaskID(), host);

        Debugger.debug("task" + task.getTaskID() + " sent to host " + host.getName()
                + " (valid ip)");
        notifyObservers(task);
    }

    /**
     * Sends a task to run in a CollAHost which has an invalid IP.
     *
     * @param task task to execute
     * @param host host to which a task will be sent
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void sendTaskToInvalidIP(CollATask task, CollAHost host)
            throws IOException, ClassNotFoundException {
        Socket socket;
        ObjectOutputStream output;
        ObjectInputStream input;
        TransmitTaskMsg outgoing;
        outgoing = new TransmitTaskMsg();
        outgoing.setUser(this.user);
        outgoing.setTask(task);

        outgoing.setGroup(task.getGroup());
        outgoing.setReceiver(host.getName());

        Debugger.debug("abrindo comunicação para enviar tarefa para IP invalido...");
        socket = new Socket(serverIPaddress, serverPortNumber);
        socket.setSoTimeout(timeout);
        output = new ObjectOutputStream(socket.getOutputStream());
        // System.out.println("Sending task on port:" +
        // taskSocket.getLocalPort());
        output.writeObject(outgoing);
        output.flush();
        // espera por ACK
        input = new ObjectInputStream(socket.getInputStream());
        input.readObject();
        socket.close();
        Debugger.debug("task" + task.getTaskID() + " sent to host " + host.getName()
                + "(invalid ip)");
        notifyObservers(task);
    }

    /**
     * Receive a result and update the GUI and task related variables
     *
     * @param groupName group to which the result will be shared
     * @param taskName the name of task
     * @param task the result of task
     */
    public void receiveTaskResult(String groupName, String taskName,
            CollATask task) {
        // user must know by the Developer Viewer GUI that a result has arrived
        String tName = this.generateUniqueTaskName(taskName, task.getTaskID());
        this.updateResults(groupName, taskName, task);
        if (this.devUI != null) {
            this.devUI.displayTaskResultInfo(groupName, tName);
        } else {
            this.displayInfo("Result for " + tName + " is now available");
        }
        Debugger.debug("O resultado da tarefa " + task.getTaskID() + " recebido por "
                + this.user.getName() + " é: " + task.getResult().toString());
        this.notifyObservers(task);
        // System.out.println("The result of task " + taskName + " is: " +
        // task.getResult());
    }

    private String generateUniqueTaskName(String taskName, Long taskID) {
        String tName = "[" + taskID + "]" + taskName;
        return tName;
    }

    /**
     * Update map of task results.
     *
     * @param groupName name of the group sharing the task
     * @param taskName name of the task
     * @param task textual representation of the result
     */
    public void updateResults(String groupName, String taskName,
            CollATask task) {
        HashMap<String, CollATask> taskMap = new HashMap<String, CollATask>();
        if (taskResults.get(groupName) != null) {
            taskMap = taskResults.get(groupName);
        }
        String tName = this
                .generateUniqueTaskName(taskName, task.getTaskID());
        SentTask sentTask = new SentTask(task);
        taskMap.put(tName, sentTask);
        taskResults.put(groupName, taskMap);
        Debugger.debug(tName + " up to date for " + groupName);
        // redo list of tasks on resultWindow
        if (devUI != null) {
            devUI.setListOfTasks(this.getTasks());
        }
    }

    /**
     *
     * @param taskName
     *
     * @return a string represensting an obtained result
     */
    public CollATask getTaskResult(String groupName, String taskName) {
        Map<String, CollATask> taskmap = this.taskResults.get(groupName);
        if (taskmap == null) {
            return null;
        }
        return taskmap.get(taskName);
    }

    /**
     *
     * @return a set containing tasks that have finished and returned results
     */
    public HashMap<String, HashMap<String, CollATask>> getTasks() {
        return this.taskResults;
    }

    /**
     * Calls the method from DeveloperViewerGUI tha displays the groups
     *
     * @param groups
     */
    public void updateGroups(HashMap<String, CollAGroup> groups) {
        if (devUI != null) {
            devUI.updateAllGroups(groups);
        }
    }

    /**
     * Connects to the server to retrieve a list with all the groups on it.
     */
    public void getGroupsListFromServer() {
        try {
            GetGroupsMsg get = new GetGroupsMsg(user.getName());
            Socket socket = new Socket(InetAddress.getByName(serverIPaddress),
                    serverPortNumber);
            socket.setSoTimeout(timeout);
            ObjectOutputStream output = new ObjectOutputStream(
                    socket.getOutputStream());
            output.writeObject(get);
            output.flush();
            // espera por ACK
            ObjectInputStream input = new ObjectInputStream(
                    socket.getInputStream());
            input.readObject();
            socket.close();
        } catch (ConnectException | SocketTimeoutException tex) {
            Debugger.debug(tex);
            this.exchangeServers();
        } catch (IOException io) {
            Debugger.debug(io);
        } catch (Exception e) {
            Debugger.debug(e);
        }
    }

    /**
     * Set the results of a search for listed groups on DeveloperViewerGUI
     *
     * @param allGroups list containing the results of a search
     */
    public void setGroupListResults(Set<String> allGroups) {
        // update group list removing those groups that the user already is a
        // member
        Set<String> groupList = new TreeSet<String>();
        for (String group : allGroups) {
            if (!this.getUser().getGroups().containsKey(group)) {
                groupList.add(group);
            }
        }
        if (devUI != null) {
            devUI.listGroupsToJoin(groupList);
        }
    }

    /**
     * Send a message to server to create a new group
     *
     * @param groupName
     */
    public void createGroup(String groupName) {
        try {
            AskToCreateGroupMsg add = new AskToCreateGroupMsg(groupName,
                    user.getName());
            Socket socket = new Socket(InetAddress.getByName(serverIPaddress),
                    serverPortNumber);
            socket.setSoTimeout(timeout);
            ObjectOutputStream output = new ObjectOutputStream(
                    socket.getOutputStream());
            output.writeObject(add);
            output.flush();
            // espera por ACK
            ObjectInputStream input = new ObjectInputStream(
                    socket.getInputStream());
            input.readObject();
            socket.close();
        } catch (ConnectException | SocketTimeoutException ex) {
            Debugger.debug(ex);
            this.exchangeServers();
        } catch (Exception io) {
            Debugger.debug(io);
        }
    }

    /**
     * Upload this user to server.
     */
    public void uploadUserToServer() {
        try {
            UpdateUserMsg update = new UpdateUserMsg(user);
            Socket socket = new Socket(InetAddress.getByName(serverIPaddress),
                    serverPortNumber);
            socket.setSoTimeout(timeout);
            ObjectOutputStream output = new ObjectOutputStream(
                    socket.getOutputStream());
            output.writeObject(update);
            output.flush();
            // espera por ACK
            ObjectInputStream input = new ObjectInputStream(
                    socket.getInputStream());
            input.readObject();
            socket.close();
        } catch (ConnectException | SocketTimeoutException ex) {
            Debugger.debug(ex);
            this.exchangeServers();
            this.uploadUserToServer();
        } catch (Exception io) {
            Debugger.debug(io);
        }

    }

    /**
     * Confirm and notify the client if group creation occurred or not.
     *
     * @param answer true if the creation has occured successfully
     * @param group the group created
     */
    public void confirmGroupCreation(boolean answer, CollAGroup group) {
        if (answer) {
            try {
                displayInfo("Name " + group.getName()
                        + " is available. Sending request to server...");
                // socket.setSoTimeout(timeout);
                group.addMember(this.user.getName());
                group.addAdmin(this.user.getName());
                CreateGroupMsg addGroup = new CreateGroupMsg(group);
                Socket socket = new Socket(
                        InetAddress.getByName(serverIPaddress),
                        serverPortNumber);
                socket.setSoTimeout(timeout);
                ObjectOutputStream output = new ObjectOutputStream(
                        socket.getOutputStream());
                output.writeObject(addGroup);
                output.flush();
                // espera por ACK
                ObjectInputStream input = new ObjectInputStream(
                        socket.getInputStream());
                input.readObject();
                socket.close();
                displayInfo("Group " + group.getName() + " has been created.");
            } catch (ConnectException | SocketTimeoutException tout) {
                Debugger.debug("Group " + group.getName()
                        + " could not be created. Connection timeout.", tout);
                displayInfo("Group " + group.getName()
                        + " could not be created. Connection timeout.");
                this.exchangeServers();
            } catch (IOException io) {
                Debugger.debug(group.getName()
                        + " could not be created. Connection lost.", io);
                displayInfo(group.getName()
                        + " could not be created. Connection lost.");
            } catch (ClassNotFoundException cnfe) {
                Debugger.debug(cnfe);
            }
        } else {
            Debugger.debug("Name "
                    + group.getName()
                    + " has already been taken by another group. Please, try another name.");
            this.displayInfo("Name "
                    + group.getName()
                    + " has already been taken by another group. Please, try another name.");
        }
    }

    /**
     * Display an information to the user
     *
     * @param info information to show to client
     */
    public void displayInfo(String info) {
        this.setChanged();
        notifyObservers(info);
    }

    /**
     * Sends a request to join a group.
     *
     * @param groupName the name of the group to join.
     */
    public void joinGroup(String groupName) {
        try {
            JoinAGroupMsg msg = new JoinAGroupMsg();
            msg.setSender(user.getName());
            msg.setGroupName(groupName);
            Socket socket = new Socket(InetAddress.getByName(serverIPaddress),
                    serverPortNumber);
            socket.setSoTimeout(timeout);
            ObjectOutputStream output = new ObjectOutputStream(
                    socket.getOutputStream());
            output.writeObject(msg);
            output.flush();
            // espera por ACK
            ObjectInputStream input = new ObjectInputStream(
                    socket.getInputStream());
            input.readObject();
            socket.close();
            Debugger.debug("A request to join " + groupName + " was sent to its admin");
            this.displayInfo("A request to join " + groupName
                    + " was sent to its admin");
            // devGUI.closeJoinGroupDialog();
        } catch (ConnectException | SocketTimeoutException tout) {
            Debugger.debug("Could not send request to join " + groupName
                    + ". Connection timeout.", tout);
            displayInfo("Could not send request to join " + groupName
                    + ". Connection timeout.");
            this.exchangeServers();
        } catch (IOException io) {
            Debugger.debug("Could not send request to join " + groupName
                    + ". Connection can't be established.", io);
            displayInfo("Could not send request to join " + groupName
                    + ". Connection can't be established.");
        } catch (ClassNotFoundException cnfe) {
            Debugger.debug(cnfe);
        }
    }

    /**
     * set the object of User representing the client
     *
     * @param usr the client
     */
    public void setUser(CollAUser usr) {
        user = usr;
    }

    public CollAUser getUser() {
        return this.user;
    }

    /**
     * Terminates the execution of all threads and terminates all connections.
     */
    public synchronized void shutdown() {
        this.isDown = true;
        user.setOffline();
        try {
            DeveloperViewerDisconnectMsg outgoing = new DeveloperViewerDisconnectMsg(
                    user);
            Socket socket = new Socket(InetAddress.getByName(serverIPaddress),
                    serverPortNumber);
            socket.setSoTimeout(timeout);
            ObjectOutputStream output = new ObjectOutputStream(
                    socket.getOutputStream());
            output.writeObject(outgoing);
            output.flush();
            // espera por ACK
            ObjectInputStream input = new ObjectInputStream(
                    socket.getInputStream());
            input.readObject();
            socket.close();
            // System.out.println("shutdown pela porta: "+socket.getLocalPort());

        } catch (SocketTimeoutException ste) {
            Debugger.debug(ste);
        } catch (IOException io) {
            Debugger.debug(io);
        } catch (ClassNotFoundException exception) {
            Debugger.debug(exception);
        }

        if (microServer != null) {
            microServer.shutdown();
        }
        devController = null;
    }

    /**
     * Gets an contact by an group
     *
     * @param contact the name of contact looked for.
     * @param groupName the name of his group.
     * @return the contact looked for.
     */
    public CollAUser getContactByGroup(String contact, String groupName) {
        return this.contacts.get(contact);
    }

    /**
     * Display a incoming message
     *
     * @param sender name of the sender
     * @param message the message itself
     */
    public void showChatMessage(String sender, String message) {
        if (this.devUI != null) {
            this.devUI.displayChatMessage(sender, message);
        } else {
            this.displayInfo(new TimeAndDate().getHour() + " from: " + sender
                    + "\n" + message + "\n");
        }
    }

    /**
     * Send a message to a contact through server or directly to its address.
     *
     * @param contactName name of contact that will receive a message.
     * @param message the message to send.
     * @param groupName the group to which the user belongs.
     * @return true if message was sent, false otherwise.
     */
    public boolean sendChatMessage(String contactName, String message,
            String groupName) {
        CollAUser contact = this.getContactByGroup(contactName, groupName);
        if (contact != null) {
            if (contact.isOnline()) {
                if (contact.hasValidIP()) {
                    try {
                        ChatDirectMessageMsg messageToSend = new ChatDirectMessageMsg(
                                this.user.getName(), message);
                        Socket socket = new Socket(
                                InetAddress.getByName(contact.getIp()),
                                contact.getPort());
                        socket.setSoTimeout(timeout);
                        ObjectOutputStream output = new ObjectOutputStream(
                                socket.getOutputStream());
                        output.writeObject(messageToSend);
                        output.flush();
                        // espera por ACK
                        ObjectInputStream input = new ObjectInputStream(
                                socket.getInputStream());
                        input.readObject();
                        socket.close();
                        return true;
                    } catch (UnknownHostException uhe) {
                        Debugger.debug(uhe);
                    } catch (IOException io) {
                        Debugger.debug(io);
                    } catch (ClassNotFoundException cnfe) {
                        Debugger.debug(cnfe);
                    }
                } else {
                    try {
                        TransmitChatMsg messageToSend = new TransmitChatMsg(
                                this.user.getName(), contactName, message);
                        Socket socket = new Socket(
                                InetAddress.getByName(serverIPaddress),
                                serverPortNumber);
                        socket.setSoTimeout(timeout);
                        ObjectOutputStream output = new ObjectOutputStream(
                                socket.getOutputStream());
                        output.writeObject(messageToSend);
                        output.flush();
                        // espera ACK
                        ObjectInputStream input = new ObjectInputStream(
                                socket.getInputStream());
                        input.readObject();
                        socket.close();
                        return true;
                    } catch (ConnectException | SocketTimeoutException se) {
                        Debugger.debug(se);
                        this.exchangeServers();
                    } catch (UnknownHostException uhe) {
                        Debugger.debug(uhe);
                    } catch (IOException | ClassNotFoundException io) {
                        Debugger.debug(io);
                    }
                }
            }// end if(contact.isOnline)
        }// end if(contact != null);
        return false;
    }// end of method sendChatMessage

    /**
     * Adds a new job to client.
     *
     * @param job a CollA aaplication intialized by this client.
     */
    public void addJob(CollAJob job) {
        // System.out.println("adding job");
        user.addJob(job);
    }

    /**
     * Adds a new group on its list of groups.
     *
     * @param groupName the name of group to be added.
     * @param group the group to be added.
     * @param usersMap the users from group.
     */
    public void addGroup(String groupName, CollAGroup group,
            HashMap<String, CollAUser> usersMap) {
        if (!user.getGroups().keySet().contains(groupName)) {
            this.displayInfo("You have been accepted to " + groupName + ".");
        }
        user.addGroup(groupName, group);
        for (String userName : group.getMembers()) {
            this.contacts.put(userName, usersMap.get(userName));
        }
        this.updateGroups(user.getGroups());
    }

    /**
     * Adds a new host on its list of hosts.
     *
     * @param host the host to be added.
     */
    public void addHost(CollAHost host) {
        this.user.addHost(host);
        this.addContact(user);
    }

    public void addContact(CollAUser contact) {
        this.contacts.put(contact.getName(), contact);
    }

    public CollAUser getContact(String name) {
        return this.contacts.get(name);
    }

    private void uploadGroupsToServer(UpdateGroupsMsg msg) {
        if (msg.getGroups().size() > 0) {
            try {
                msg.setManager(this.user.getName());
                Socket socket = new Socket(
                        InetAddress.getByName(serverIPaddress),
                        serverPortNumber);
                socket.setSoTimeout(timeout);
                ObjectOutputStream output = new ObjectOutputStream(
                        socket.getOutputStream());
                output.writeObject(msg);
                output.flush();
                // espera ACK
                ObjectInputStream input = new ObjectInputStream(
                        socket.getInputStream());
                input.readObject();
                socket.close();

            } catch (ConnectException | SocketTimeoutException ex) {
                Debugger.debug(ex);
                this.exchangeServers();
            } catch (IOException io) {
                Debugger.debug(io);
            } catch (ClassNotFoundException cnfe) {
                Debugger.debug(cnfe);
            }
        }// end of if
    }// end of method

    /**
     * Reboot its microServer if the DeveloperViewerController itself is not
     * down.
     */
    public void restoreMicroServer()
            throws DeveloperControllerInitializationException {
        if (!this.isDown) {
            if (microServer != null) {
                microServer.shutdown();
            }
            microServer = new DevMicroServer(serverIPaddress, serverPortNumber);
            Thread thr = new Thread(microServer);
            thr.start();
        }
    }

    /**
     * @todo download result talvez passe a se conectar ao superhost
     *
     * Download a result from a host.
     *
     * @param ticket
     * @return true if the download was done, false otherwise
     */
    public boolean downloadResult(CollATicket ticket, String groupName) {
        try {
            DownloadResultMsg msg = new DownloadResultMsg();
            msg.setTicket(ticket.getTicket());
            Socket socket = new Socket(InetAddress.getByName(ticket
                    .getHostIPAddress()), ticket.getHostPort());
            socket.setSoTimeout(timeout);
            ObjectOutputStream output = new ObjectOutputStream(
                    socket.getOutputStream());
            output.writeObject(msg);
            output.flush();

            ObjectInputStream input = new ObjectInputStream(
                    socket.getInputStream());
            CollATask result = (CollATask) input.readObject();
            socket.close();
            this.receiveTaskResult(groupName, result.getTaskName(), result);
            return true;
        } catch (IOException ex) {
            Debugger.debug(ex);
        } catch (ClassNotFoundException cnfe) {
            Debugger.debug(cnfe);
        }
        return false;
    }

    /**
     * Adds new accepted members and blocks those refused.
     *
     * @param acceptedMembers a list of accepted members.
     * @param refusedMembers a list of refused members.
     */
    public void manageGroups(HashMap<String, List<String>> acceptedMembers,
            HashMap<String, List<String>> refusedMembers) {
        boolean isModified = false;
        UpdateGroupsMsg msg = new UpdateGroupsMsg();
        for (String groupName : this.getUser().getGroups().keySet()) {
            CollAGroup group = this.getUser().getGroups().get(groupName);
            if (acceptedMembers.get(groupName) != null
                    && acceptedMembers.get(groupName).size() > 0) {
                isModified = true;
                for (String memberName : acceptedMembers.get(groupName)) {
                    group.addMember(memberName);
                    group.remMemberFromWaitingList(memberName);
                }
            }

            if (refusedMembers.get(groupName) != null
                    && refusedMembers.get(groupName).size() > 0) {
                isModified = true;
                for (String memberName : refusedMembers.get(groupName)) {
                    group.remMemberFromWaitingList(memberName);
                }
            }
            if (isModified) {
                this.user.addGroup(groupName, group);
                msg.addGroup(group);
            }
            isModified = false;
        }
        // send updated groups to server
        this.uploadGroupsToServer(msg);
    }

    /**
     * Connects to a host to cancel a scheduled task.
     *
     * @param groupName group selected for the scheduled task
     * @param taskName name of the scheduled task
     * @return true if the schedule has been canceled, false otherwise
     */
    public boolean cancelScheduledTask(String groupName, String taskName) {
        boolean success = false;
        CollATask task = this.getTaskResult(groupName, taskName);
        if (task == null) {
            return false;
        }
        Long taskID = task.getTaskID();
        if (this.hostsWithScheduledTasks.containsKey(taskID)) {
            this.cancelScheduledTask(hostsWithScheduledTasks.get(taskID),
                    taskID);
            this.hostsWithScheduledTasks.remove(taskID);
            this.taskResults.get(groupName).remove(taskName);
            success = true;
        }
        return success;
    }

    /**
     * Connects to a host to cancel a scheduled task.
     *
     * @param host a host to which a task has been scheduled
     * @param taskID id of the scheduled task
     */
    private void cancelScheduledTask(CollAHost host, Long taskID) {
        CancelTask msg = new CancelTask(taskID);
        Socket socket;
        try {
            socket = new Socket(InetAddress.getByName(host.getIp()),
                    host.getPort());
            socket.setSoTimeout(timeout);
            ObjectOutputStream output = new ObjectOutputStream(
                    socket.getOutputStream());
            output.writeObject(msg);
            output.flush();
            // receives ACK
            ObjectInputStream input = new ObjectInputStream(
                    socket.getInputStream());
            input.readObject();
            socket.close();
        } catch (IOException ex) {
            Debugger.debug(ex);
        } catch (ClassNotFoundException ex) {
            Debugger.debug(ex);
        }
    }

    @Override
    public void notifyObservers(Object interest) {
        this.setChanged();
        super.notifyObservers(interest);
    }

    /**
     * It's optional because a DeveloperViewerController shall work fine without
     * any UI.
     *
     * @param userInterface
     */
    public void setUI(CollADeveloperViewerUI userInterface) {
        this.devUI = userInterface;
    }

    public String getServerIPAddress() {
        return this.serverIPaddress;
    }

    public int getServerPortNumber() {
        return this.serverPortNumber;
    }

    public void reinitializeMicroServer() {
        try {
            microServer = new DevMicroServer(serverIPaddress, serverPortNumber);
            Thread thr = new Thread(microServer);
            thr.start();
        } catch (DeveloperControllerInitializationException ex) {
            Debugger.debug(ex);
        }
    }

    /**
     * Change to secondary server in case of the primary has failed (fault
     * tolerance).
     */
    public void exchangeServers() {

        String tempIP;
        int tempPort;

        tempIP = this.serverIPaddress;
        tempPort = this.serverPortNumber;

        this.serverIPaddress = this.secondaryServerIP;
        this.serverPortNumber = this.secondaryServerPort;

        this.secondaryServerIP = tempIP;
        this.secondaryServerPort = tempPort;
    }
    private static DeveloperViewerController devController = null;
    private boolean isDown;
    private final int timeout = 10000;
    private DevMicroServer microServer;
    private CollADeveloperViewerUI devUI;
    private int serverPortNumber;
    private String serverIPaddress;
    private Integer secondaryServerPort;
    private String secondaryServerIP;
    private CollAUser user;
    /*
     * Sending a Task
     */
    private Queue<CollATask> tasksToRun;
    private File taskFile;
    private List<File> taskDependencies;
    private List<File> taskArgs;
    private String classToHostExecute;
    private String methodToExecute;
    private HashMap<String, CollAUser> contacts;
    private HashMap<String, HashMap<String, CollATask>> taskResults;
    private HashMap<Long, CollAHost> hostsWithScheduledTasks;
    private SentTasksMonitor sentTaskMonitor;
    private Timer scheduleSentTaskMonitor;
    private Long monitorDelayAndPeriod;
}
