package colla.appl.server;

import colla.kernel.api.*;
import colla.kernel.messages.toClient.SendOwnedHostsMsg;
import colla.kernel.messages.toClient.TaskResultMsg;
import colla.kernel.util.TimeAndDate;
import static colla.kernel.util.Treater.treat;
import implementations.sm_kernel.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;
import interfaces.kernel.JCL_result;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * SuperServer is an implementation of CollAServer that uses javaCaLa (http://www.joubertlima.com.br/calapp.html)
 * to work with as many threads as the machine may offers within the Producer/Consumer paradigm.
 * 
 * Diogo Matos <dmatos88 at gmail.com>
 */
public class SuperServer extends Observable implements CollAServer, Runnable {

    /**
     * 
     * @param portN   port number to which the server must listen.
     * @param timeOut time, in miliseconds, to set timeout for connections. 
     */
    public SuperServer(int portN, int timeOut) {
        this.portNumber = portN;
        this.timeout = timeOut;
        this.userPasswords = new ConcurrentHashMap<String, String>();
        this.usersMap = new ConcurrentHashMap<String, CollAUser>();
        this.groupsMap = new ConcurrentHashMap<String, CollAGroup>();
        this.dateAndTime = new TimeAndDate();
        this.checkOnlineUsers = new Timer();        
        this.connectionsMap = new ConcurrentHashMap<String, Socket>();               
        this.sessions = new Long(0);
        this.resultsMap = new ConcurrentHashMap<String, ArrayList<TaskResultMsg>>();
        this.currentHost = 0;
        this.taskIDs = new Long(0);
        try {
            this.serverSocket = new ServerSocket(portNumber);
        } catch (IOException io) {
            treat( "Server couldn't be initialized. Please, check for connections setup and firewalls.", io );
            JOptionPane.showMessageDialog(null, "Server couldn't be initialized.\nPlease, check for connections setup and firewalls.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        try {
            this.restoreServerData();
        } catch (Exception e) {
            treat( "Couldn't restore all data", e );
            JOptionPane.showMessageDialog(null, "Couldn't restore all data", "Restoring data", JOptionPane.INFORMATION_MESSAGE);
        }

        this.restoreGUI();       
    }

    @Override
    public void run() {
        ConnectionMonitor connMonitor = new ConnectionMonitor(this);
        Long monitorDelayAndPeriod = new Long(300000); //start in 5 minutes (delay) and repeat each 5 minutes (period)
        this.checkOnlineUsers.schedule(connMonitor, monitorDelayAndPeriod, monitorDelayAndPeriod);

        //registering consumers in JavaCaLa
        JCL_facade jcl = JCL_FacadeImpl.getInstance();
        jcl.register(SuperServerWorker.class, SuperServerWorker.class.getName());
        JCL_result jclr = null;
        Socket socketAccept;
        while (true) {
            String connection_address = "";
            try {
                treat("waiting connection...");
                socketAccept = serverSocket.accept();
                connection_address = socketAccept.getInetAddress().toString().substring(1);
                Object[] args = {socketAccept, this};
                jcl.execute(SuperServerWorker.class.getName(), args);
            } catch(IOException io){
                //treat( "ex! Problema com socket passado pro jcl", io );
            }
        }
    }// fim do método run

    /**
     * Updates a user on the server, creating a new if it doesn't already exists
     *
     * @param usr
     */
    @Override
    public synchronized void updateUser(CollAUser usr) {
        
        /* iterates over the groups of a user
         * to update its status to each group
         */
        if (this.usersMap.containsKey(usr.getName())) {
            CollAUser temp = this.usersMap.get(usr.getName());
            usr.setActivities(temp.getActivities());
        }
        this.usersMap.put(usr.getName(), usr);        

        try {
            this.storeClientsData();
        } catch (Exception e) {
            treat( e );
        }
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public synchronized void storeAllServerData() throws Exception {
        //store clients
        FileOutputStream f_out = new FileOutputStream("data/clients.data");
        ObjectOutputStream output = new ObjectOutputStream(f_out);
        output.writeObject(this.usersMap);
        output.flush();

        //store clients passwords
        f_out = new FileOutputStream("data/pwords.data");
        output = new ObjectOutputStream(f_out);
        output.writeObject(this.userPasswords);
        output.flush();

        //store groups
        f_out = new FileOutputStream("data/groups.data");
        output = new ObjectOutputStream(f_out);
        output.writeObject(this.groupsMap);
        output.flush();

        //store session IDs
        f_out = new FileOutputStream("data/sessionID.data");
        output = new ObjectOutputStream(f_out);
        output.writeObject(this.sessions);
        output.flush();
        
        //store taskIDs
        f_out = new FileOutputStream("data/taskIDs.data");
        output = new ObjectOutputStream(f_out);
        output.writeObject(this.taskIDs);
        output.flush();
        
        f_out.close();
    }

    private synchronized void storeClientsData() throws Exception {
        //store clients
        FileOutputStream f_out = new FileOutputStream("data/clients.data");
        ObjectOutputStream output = new ObjectOutputStream(f_out);
        output.writeObject(this.usersMap);
        output.flush();

        //store clients passwords
        f_out = new FileOutputStream("data/pwords.data");
        output = new ObjectOutputStream(f_out);
        output.writeObject(this.userPasswords);
        output.flush();
        
        f_out.close();
    }

    private synchronized void storeGroupsData() throws Exception {
        //store groups
        FileOutputStream f_out = new FileOutputStream("data/groups.data");
        ObjectOutputStream output = new ObjectOutputStream(f_out);
        output.writeObject(this.groupsMap);
        output.flush();
        f_out.close();
    }

    private void restoreServerData() throws Exception {

        //create data directory if it does not alredy exist
        new File("data/").mkdir();

        //restore clients
        FileInputStream f_in = new FileInputStream("data/clients.data");
        ObjectInputStream input = new ObjectInputStream(f_in);
        this.usersMap = (ConcurrentHashMap<String, CollAUser>) input.readObject();

        //restore clients passwords
        f_in = new FileInputStream("data/pwords.data");
        input = new ObjectInputStream(f_in);
        this.userPasswords = (ConcurrentHashMap<String, String>) input.readObject();

        //restore groups
        f_in = new FileInputStream("data/groups.data");
        input = new ObjectInputStream(f_in);
        this.groupsMap = (ConcurrentHashMap<String, CollAGroup>) input.readObject();

        //restore session IDs
        f_in = new FileInputStream("data/sessionID.data");
        input = new ObjectInputStream(f_in);
        this.sessions = (Long) input.readObject();
        
        //restore task IDs
        f_in = new FileInputStream("data/taskIDs.data");
        input = new ObjectInputStream(f_in);
        this.taskIDs = (Long) input.readObject();
        
        
        f_in.close();
    }
    
    private void restoreGUI(){
        for(String userName : this.usersMap.keySet()){
            CollAUser user = this.usersMap.get(userName);
            this.updateUser(user);
        }
    }

    @Override
    public synchronized void updateHost(CollAHost host) {
        CollAUser user = this.usersMap.get(host.getNameUser());
        user.addHost(host);
        this.updateUser(user);
        //send host to its onwer
        SendOwnedHostsMsg msg = new SendOwnedHostsMsg();
        msg.addHost(host);
        if (user.isOnline()) {
            try {
                if (user.hasValidIP()) {
                    Socket s = new Socket(InetAddress.getByName(user.getIp()), user.getPort());
                    s.setSoTimeout(timeout);
                    ObjectOutputStream output = new ObjectOutputStream(s.getOutputStream());
                    output.writeObject(msg);
                    output.flush();
                    // espera por ACK
                    ObjectInputStream input = new ObjectInputStream(s.getInputStream());
                    input.readObject();
                    s.close();
                } else {
                    Socket s = connectionsMap.get(user.getName());
                    if (s != null) {
                        ObjectOutputStream output = new ObjectOutputStream(s.getOutputStream());
                        output.writeObject(msg);
                        output.flush();
                        // espera por ACK
                        ObjectInputStream input = new ObjectInputStream(s.getInputStream());
                        input.readObject();
                    }
                }
            } catch (Exception e) {
                treat( e );
            }
        }// end if
        //notify its observers
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
    public CollAUser getUser(String userName) {
        return usersMap.get(userName);
    }
    
    
    @Override
    public String getUserPassword(String userName) {
        return this.userPasswords.get(userName);
    }

    @Override
    public synchronized void setUserPassword(String userName, String password) {
        this.userPasswords.put(userName, password);
        try {
            this.storeClientsData();
        } catch (Exception e) {
            treat( e );
        }
    }

    @Override
    public void updateGroup(String groupName, CollAGroup group) {
        this.groupsMap.put(groupName, group);
        try {
            this.storeGroupsData();
        } catch (Exception e) {
            treat( e );
        }
    }

    @Override
    public CollAGroup getGroup(String groupName) {
        return this.groupsMap.get(groupName);
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
    public synchronized Set<String> getUsersSet() {
        Set<String> usersSet = new TreeSet<String>();
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
    public long generateSessionID(){
        this.sessions++;
        return this.sessions.longValue();
    }
    
    @Override
    public void disconnectAllClients(){
        for(String userName: this.usersMap.keySet()){
            try {
                CollAUser user = this.usersMap.get(userName);
                for(CollAHost host: user.getHosts()){
                    host.setOffline();
                }
                Socket temp = this.connectionsMap.get(userName);
                if(temp != null && !temp.isClosed()){
                    temp.close();
                }                
                user.setOffline();
            } catch (IOException ex) {
                treat( ex );
                Logger.getLogger(SuperServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public Long generateTaskID() {
        this.taskIDs++;
        return this.taskIDs;
    }  
    
    @Override
    public Map<String, Socket> getMappedConnections(){
        return this.connectionsMap;
    }
    
    
    /**
     * Send a string with some message to display out to users
     *
     * @param message
     */
    public synchronized void displayMessage(String message) {
        this.setChanged();
        notifyObservers(message);
    }   
    
    
    protected volatile Long taskIDs;
    protected volatile Long sessions;
    protected final int portNumber;// port number in which the server listens
    protected final int timeout;// timeout for connections
    protected ServerSocket serverSocket;
    private ConcurrentHashMap<String, String> userPasswords;
    private ConcurrentHashMap<String, CollAUser> usersMap;
    private ConcurrentHashMap<String, CollAGroup> groupsMap;
    protected volatile TimeAndDate dateAndTime;
    private Timer checkOnlineUsers;
    protected ConcurrentHashMap<String, Socket> connectionsMap;// map clients/hosts with invalid IP's
    protected volatile int currentHost;
    protected ConcurrentHashMap<String, ArrayList<TaskResultMsg>> resultsMap; // store results for offline clients until they connet again
}//fim da classe SuperServer
