package colla.appl.server;

import colla.appl.server.util.WeightedHost;
import colla.kernel.api.*;
import colla.kernel.exceptions.server.NonExistentUser;
import colla.kernel.exceptions.server.ServerInitializationException;
import colla.kernel.exceptions.server.UserAlreadyExists;
import colla.kernel.messages.toClient.*;
import colla.kernel.util.Debugger;
import colla.kernel.util.LogWriter;
import colla.kernel.util.TimeAndDate;
import implementations.sm_kernel.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;
import interfaces.kernel.JCL_result;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Server is an implementation of CollAServer that uses javaCaLa
 * (http://www.joubertlima.com.br/calapp.html) to work with as many threads as
 * the machine may offers within the Producer/Consumer paradigm.
 *
 * Diogo Matos <dmatos88 at gmail.com>
 */
public class Server extends Observable implements CollAServer, Runnable {

    private Server(int portN, int timeOut) throws IOException {
        this.portNumber = portN;
        this.timeout = timeOut;
        this.hostWeightIncrement = 0L;
        this.userPasswords = new HashMap<>();
        this.usersMap = new HashMap<>();
        this.groupsMap = new HashMap<>();
        this.dateAndTime = new TimeAndDate();
        this.checkOnlineUsers = new Timer();
        this.connectionsMap = new HashMap<>();
        this.sessions = new Long(0);
        this.resultsMap = new HashMap<>();
        this.currentHost = 0;
        this.taskIDs = new Long(0);
        this.weightedHosts = new PriorityQueue<>();
        try {
            this.serverSocket = new ServerSocket(portNumber);
            this.portNumber = this.serverSocket.getLocalPort();
        } catch (IOException io) {
            Debugger.debug("Server couldn't be initialized. Please, check"
                    + " for connections setup and firewalls.", io);
            System.err.println("Server couldn't be initialized.\nPlease,"
                    + " check for connections setup and firewalls.");
            LogWriter.generateLog("Server couldn't be initialized."
                    + "\nPlease, check for connections setup and firewalls.");
            throw new IOException();
        }
        try {
            this.restoreServerData();
        } catch (Exception e) {
            Debugger.debug("Couldn't restore all data", e);
            System.err.println("Couldn't restore all data to the server");
            LogWriter.generateLog("Couldn't restore all data to the server");
        }
        try {
            this.restoreGUI();
        } catch (NonExistentUser nUsr) {
            Debugger.debug("Couldn't set GUI up", nUsr);
            System.err.println("Couldn't set GUI up");
            LogWriter.generateLog("Couldn't set GUI up");
        }
    }

    /**
     * Sets up a Singleton instance of a Server with the given port number to
     * listen and a timeout value. Should be called only once.
     *
     * @param portNumber number to which the server must listen.
     * @param timeOut time, in miliseconds, to set for connection timeout.
     * @return an unique Server instance.
     * @throws IOException if the Server couldn't be set online.
     */
    public static synchronized Server setupServer(int portNumber, int timeOut)
            throws IOException {
        if (serverInstance == null) {
            serverInstance = new Server(portNumber, timeOut);
            return serverInstance;
        }
        return serverInstance;
    }

    /**
     * Returns a Singleton instance of the Server, but setupServer must be
     * called prior to this method, otherwise it will throw a
     * ServerInitializationException.
     *
     * @return a Server instance if it has already been made, null otherwise.
     */
    public static Server getInstance() throws ServerInitializationException {
        if (serverInstance == null) {
            throw new ServerInitializationException("Server not initialized yet,"
                    + " setupServer must be called first.");
        }
        return serverInstance;
    }

    @Override
    public void run() {
        ClientsConnectionMonitor connMonitor = new ClientsConnectionMonitor();
        /* start in 5 minutes (delay) and repeat each 5 minutes (period) */
        Long monitorDelayAndPeriod = new Long(300000);
        this.checkOnlineUsers.schedule(connMonitor, monitorDelayAndPeriod,
                monitorDelayAndPeriod);
        this.active = true;
        // registering consumers in JavaCaLa
        JCL_facade jcl = JCL_FacadeImpl.getInstance();
        jcl.register(ServerWorker.class, ServerWorker.class.getName());
        JCL_result jclr = null;
        Socket socketAccept;
        while (active) {
            String connection_address = "";
            try {
                Debugger.debug("waiting connection...");
                socketAccept = serverSocket.accept();
                connection_address = socketAccept.getInetAddress().toString()
                        .substring(1);
                Object[] args = {socketAccept};
                jcl.execute(ServerWorker.class.getName(), args);
            } catch (IOException io) {
                // treat( "ex! Problema com socket passado pro jcl", io );
            }
        }
    }// fim do método run

    @Override
    public synchronized void addUser(CollAUser usr) throws UserAlreadyExists {

        if (this.usersMap.containsKey(usr.getName())) {
            throw new UserAlreadyExists(usr.getName() + " is already in use.");
        }

        this.usersMap.put(usr.getName(), usr);

        //System.out.println(usersMap.get(usr.getName()).getName() + " adicionado com sucesso");

        try {
            this.storeClientsData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Updates a user on the server, creating a new if it doesn't already exists
     *
     * @param usr
     */
    @Override
    public synchronized void updateUser(CollAUser usr) throws NonExistentUser {
        if (!usersMap.containsKey(usr.getName())) {
            throw new NonExistentUser(usr.getName() + "is not registered");
        }
        /*
         * iterates over the groups of a user to update its status to each group
         */

        CollAUser temp = this.usersMap.get(usr.getName());
        usr.setActivities(temp.getActivities());

        this.usersMap.put(usr.getName(), usr);

        try {
            this.storeClientsData();
        } catch (Exception e) {
            Debugger.debug(e);
        }
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public synchronized void storeAllServerData() throws Exception {
        // store clients
        FileOutputStream f_out = new FileOutputStream("data/clients.data");
        ObjectOutputStream output = new ObjectOutputStream(f_out);
        output.writeObject(this.usersMap);
        output.flush();

        // store clients passwords
        f_out = new FileOutputStream("data/pwords.data");
        output = new ObjectOutputStream(f_out);
        output.writeObject(this.userPasswords);
        output.flush();

        // store groups
        f_out = new FileOutputStream("data/groups.data");
        output = new ObjectOutputStream(f_out);
        output.writeObject(this.groupsMap);
        output.flush();

        // store session IDs
        f_out = new FileOutputStream("data/sessionID.data");
        output = new ObjectOutputStream(f_out);
        output.writeObject(this.sessions);
        output.flush();

        // store taskIDs
        f_out = new FileOutputStream("data/taskIDs.data");
        output = new ObjectOutputStream(f_out);
        output.writeObject(this.taskIDs);
        output.flush();

        f_out.close();
    }

    private synchronized void storeClientsData() throws Exception {
        // store clients
        FileOutputStream f_out = new FileOutputStream("data/clients.data");
        ObjectOutputStream output = new ObjectOutputStream(f_out);
        output.writeObject(this.usersMap);
        output.flush();

        // store clients passwords
        f_out = new FileOutputStream("data/pwords.data");
        output = new ObjectOutputStream(f_out);
        output.writeObject(this.userPasswords);
        output.flush();

        f_out.close();
    }

    private synchronized void storeGroupsData() throws Exception {
        // store groups
        FileOutputStream f_out = new FileOutputStream("data/groups.data");
        ObjectOutputStream output = new ObjectOutputStream(f_out);
        output.writeObject(this.groupsMap);
        output.flush();
        f_out.close();
    }

    private void restoreServerData() throws Exception {

        // create data directory if it does not alredy exist
        new File("data/").mkdir();

        // restore clients
        FileInputStream f_in = new FileInputStream("data/clients.data");
        ObjectInputStream input = new ObjectInputStream(f_in);
        this.usersMap = (HashMap<String, CollAUser>) input
                .readObject();

        // restore clients passwords
        f_in = new FileInputStream("data/pwords.data");
        input = new ObjectInputStream(f_in);
        this.userPasswords = (HashMap<String, String>) input
                .readObject();

        // restore groups
        f_in = new FileInputStream("data/groups.data");
        input = new ObjectInputStream(f_in);
        this.groupsMap = (HashMap<String, CollAGroup>) input
                .readObject();

        // restore session IDs
        f_in = new FileInputStream("data/sessionID.data");
        input = new ObjectInputStream(f_in);
        this.sessions = (Long) input.readObject();

        // restore task IDs
        f_in = new FileInputStream("data/taskIDs.data");
        input = new ObjectInputStream(f_in);
        this.taskIDs = (Long) input.readObject();

        f_in.close();
    }

    private void restoreGUI() throws NonExistentUser {
        for (String userName : this.usersMap.keySet()) {
            CollAUser user = this.usersMap.get(userName);
            this.updateUser(user);
        }
    }
    
    public synchronized void updateWeightedHost(WeightedHost wHost){                
        this.weightedHosts.remove(wHost);
        this.weightedHosts.add(wHost);
    }
    
    public synchronized WeightedHost poolWeightedHost(){
        return this.weightedHosts.poll();
    }

    @Override
    public synchronized void updateHost(CollAHost host) throws NonExistentUser {
        CollAUser user = this.usersMap.get(host.getNameUser());
        user.addHost(host);
        if(!host.IsOnline())
            this.weightedHosts.remove(new WeightedHost(host));
        this.updateUser(user);     
        // notify its observers
        this.setChanged();
        this.notifyObservers();
    }// fim do método updateHost

    /**
     *
     * @return the port in which server is listening
     */
    @Override
    public int getPortNumber() {
        return this.portNumber;
    }

    /**
     *
     * @param userName
     *
     * @return an Client signed for the server with the given userName
     */
    @Override
    public CollAUser getUser(String userName) throws NonExistentUser {
        if (this.usersMap.containsKey(userName)) {
            return usersMap.get(userName);
        } else {
            throw new NonExistentUser("Matches not found for " + userName);
        }
    }

    @Override
    public String getUserPassword(String userName) {
        String password = null;
        if (this.userPasswords.containsKey(userName)) {
            password = this.userPasswords.get(userName);
        }
        return password;
    }

    @Override
    public synchronized void setUserPassword(String userName, String password) {
        this.userPasswords.put(userName, password);
        try {
            this.storeClientsData();
        } catch (Exception e) {
            Debugger.debug(e);
        }
    }

    @Override
    public void updateGroup(String groupName, CollAGroup group) {
        this.groupsMap.put(groupName, group);
        try {
            this.storeGroupsData();
        } catch (Exception e) {
            Debugger.debug(e);
        }
    }

    @Override
    public CollAGroup getGroup(String groupName) {
        if (this.groupsMap.containsKey(groupName)) {
            return this.groupsMap.get(groupName);
        }
        return null;
    }

    @Override
    public Set<String> getAllGroups() {
        return this.groupsMap.keySet();
    }

    /**
     *
     * @return a Set containing the names of the users
     */
    @Override
    public Set<String> getUsersSet() {
        Set<String> usersSet = new TreeSet<>();
        for (String name : usersMap.keySet()) {
            usersSet.add(name);

        }
        return usersSet;
    }

    @Override
    public int getTimeoutValue() {
        return this.timeout;
    }

    @Override
    public synchronized long generateSessionID() {
        this.sessions++;
        return this.sessions.longValue();
    }

    @Override
    public void disconnectAllClients() {
        for (String userName : this.usersMap.keySet()) {
            try {
                CollAUser user = this.usersMap.get(userName);                
                /*for (CollAHost host : user.getHosts()) {
                    host.setOffline();
                }*/
                user.removeAllHosts();
                Socket temp = this.connectionsMap.get(userName);
                if (temp != null && !temp.isClosed()) {
                    temp.close();
                }
                user.setOffline();
            } catch (IOException ex) {
                Debugger.debug(ex);
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
    }

    @Override
    public synchronized Long generateTaskID() {
        this.taskIDs++;
        return this.taskIDs;
    }

    @Override
    public Map<String, Socket> getMappedConnections() {
        return this.connectionsMap;
    }

    /**
     * Maps an open connection with a client which has an invalid IP address so
     * the Server can communicate with this client.
     *
     * @param identifier Alphanumerical identifier of the client.
     * @param socket Socket which is already communicating with the client.
     */
    public void mapConnection(String identifier, Socket socket) {
        this.connectionsMap.put(identifier, socket);
    }

    /**
     *
     * @return a TimeAndDate instance.
     */
    public TimeAndDate getTimeAndDateInstance() {
        return this.dateAndTime;
    }

    /**
     * The returned Socket should not be closed.
     *
     * @param identifier Alphanumerical identifier of the client.
     * @return an socket connected to a client.
     */
    public Socket getAMapedConnection(String identifier) {
        if (this.connectionsMap.containsKey(identifier)) {
            return this.connectionsMap.get(identifier);
        }
        return null;
    }

    /**
     * Removes a mapped connection from the Server.
     *
     * @param identifier Alphanumerical identifier of the client.
     */
    public synchronized void removeAMappedConnection(String identifier) {
        this.connectionsMap.remove(identifier);
    }

    /**
     *
     * @param identifier Result alphanumerical identifier
     * @return a stored Result.
     */
    public ArrayList<TaskResultMsg> getMapedResults(String identifier) {
        if (this.resultsMap.containsKey(identifier)) {
            return this.resultsMap.get(identifier);
        }
        return null;
    }

    @Override
    public void shutdown() {
        try {
            this.disconnectAllClients();
            this.storeAllServerData();
        } catch (Exception e) {
            Debugger.debug(e);
        }
        this.active = false;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            Debugger.debug(e);
        }
        serverInstance = null;
    }

    /**
     * Stores a result to the Server.
     *
     * @param identifier Result alphanumerical identifier.
     * @param resultsArray
     */
    public synchronized void updateResultsMap(String identifier,
            ArrayList<TaskResultMsg> resultsArray) {
        this.resultsMap.put(identifier, resultsArray);
    }

    /**
     * Removes a result stored on the Server.
     *
     * @param identifier
     */
    public synchronized void removeMapedResults(String identifier) {
        this.resultsMap.remove(identifier);
    }

    /**
     * Displays a message in any GUIs registered as Observer.
     *
     * @param message
     */
    public synchronized void displayMessage(String message) {
        this.setChanged();
        notifyObservers(message);
    }
    
    public long getHostWeightIncrement(){
        return this.hostWeightIncrement;
    }
    
    public synchronized void setHostWeightIncrement(long weightIncrement){
        this.hostWeightIncrement = weightIncrement;
    }
    
    private static Server serverInstance = null; // singleton pattern
    private boolean active;
    private Long taskIDs;
    private Long sessions;
    private int portNumber;// port number in which the server listens
    private final int timeout;// timeout for connections
    private ServerSocket serverSocket;
    private HashMap<String, String> userPasswords;
    private HashMap<String, CollAUser> usersMap;
    private HashMap<String, CollAGroup> groupsMap;
    private TimeAndDate dateAndTime;
    private Timer checkOnlineUsers;
    private HashMap<String, Socket> connectionsMap;// map  
    private int currentHost;
    private HashMap<String, ArrayList<TaskResultMsg>> resultsMap; // store 
    private PriorityQueue<WeightedHost> weightedHosts;
    private long hostWeightIncrement;
}
