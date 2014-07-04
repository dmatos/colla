/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.server.secondary;

import colla.appl.server.GUI.CollAServerGUI;
import colla.appl.server.Server;
import colla.kernel.impl.WeightedHost;
import colla.kernel.api.CollAGroup;
import colla.kernel.api.CollAHost;
import colla.kernel.api.CollAMessage;
import colla.kernel.api.CollAUser;
import colla.kernel.messages.toSecondary.UpdateMsg;
import colla.kernel.enumerations.ServerOps;
import colla.kernel.exceptions.server.ServerInitializationException;
import colla.kernel.messages.toServer.ACK;
import colla.kernel.util.Debugger;
import colla.kernel.util.ServerConfReader;
import implementations.sm_kernel.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;
import interfaces.kernel.JCL_result;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author dmatos
 */
public class SecondaryServer {

    public SecondaryServer(){
        
    }
    
    /**
     * Initialize Secondary Server
     * @param hasBackup for future use with many secondary servers
     */
    public SecondaryServer(boolean hasBackup) {        
        this.readServerConfigurations();
        this.hasBacukp = hasBackup;
    }

    public void execute(Socket socket) throws IOException, ClassNotFoundException {
        ServerOps operation;
        UpdateMsg updateMsg;
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        updateMsg = (UpdateMsg) input.readObject();
        operation = (ServerOps) updateMsg.getOperation();
        try {
            switch (operation) {
                case UPDATE_USER:
                    CollAUser user = (CollAUser) updateMsg.getUpdate();
                    this.update(user);
                    break;
                case UPDATE_HOST:
                    CollAHost host = (CollAHost) updateMsg.getUpdate();
                    this.update(host);
                    break;
                case CREATE_GROUP:
                    CollAGroup group = (CollAGroup) updateMsg.getUpdate();
                    String groupName = group.getName();
                    this.update(groupName, group);
                    break;
                case UPDATE_WEIGHTED_HOST:
                    WeightedHost wHost = (WeightedHost) updateMsg.getUpdate();
                    this.update(wHost);
                    break;
                case SIGN_UP:
                    List<String> updates = (List<String>) updateMsg.getUpdate();
                    this.update(updates.get(0), updates.get(1));
                    break;
            }
        } catch (Exception ex) {
            //treat ex
        }
        //send ACK
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        CollAMessage msg = new ACK();
        output.writeObject(msg);
        output.flush();
    }

    public void update(CollAHost host) throws Exception {
        Debugger.debug("updating host");
        Server server = Server.getInstance();
        server.updateHost(host);
    }

    public void update(WeightedHost host) throws Exception {
        Debugger.debug("updating whost");
        Server server = Server.getInstance();
        server.updateWeightedHost(host);
    }
    
    public void update(String username, String pword) throws Exception{
        Debugger.debug("updating pword for: "+username);
        Server server = Server.getInstance();
        server.setUserPassword(username, pword);
    }

    public void update(CollAUser user) throws Exception {
        Debugger.debug("updating user: "+user.getName());
        Server server = Server.getInstance();
        server.updateUser(user);        
    }

    public void update(String groupName, CollAGroup group) throws Exception {
        Debugger.debug("updating group");
        Server server = Server.getInstance();
        server.updateGroup(groupName, group);
    }

    /**
     * Read secondary server IP address and port number and the port number to
     * which this server must listen.
     *
     * @return
     */
    private boolean readServerConfigurations() {
        ServerConfReader reader = new ServerConfReader();
        try {
            reader.parse("server_conf.xml");
            portNumber = reader.getSecondaryPortNumberFromXML();            
            primaryPortNumber = reader.getPortNumberFromXML();
            primaryIPAddres = reader.getIPfromXML();
        } catch (IOException | ParserConfigurationException | SAXException io) {
            Debugger.debug(io);
            return false;
        }
        return true;
    }

    public static void main(String args[]) {
        int timeout = 20000;
        
         for (int i = 0; i < args.length; i++) {          
            if(args[i].equals("--debug")){
                Debugger.setDebugger(true);
            }
        }
       
        SecondaryServer secondServer = new SecondaryServer(false);
        try {
            //setting up server as not primary
            Server.setupServer(timeout, false);
        } catch (IOException ex) {
            Debugger.debug(ex);
            System.err.println("Could not setup Server... shuting down...");
            System.exit(0);
        }

        primaryIsAlive = true;

        //check primary each 10 seconds
        PrimaryConnectionMonitor monitor = new PrimaryConnectionMonitor();
        Long monitorDelayAndPeriod = new Long(5000);
        Timer timer = new Timer();
        timer.schedule(monitor, 0L,
                monitorDelayAndPeriod);

        JCL_facade jcl = JCL_FacadeImpl.getInstance();
        jcl.register(SecondaryServer.class, SecondaryServer.class.getName());
        JCL_result jclr = null;      
        try {
            serverSocket = new ServerSocket(SecondaryServer.portNumber);
            serverSocket.setSoTimeout(0);
            Socket socketAccept;
            while (primaryIsAlive) {
                try {
                    socketAccept = serverSocket.accept();
                    Object[] secondaryArgs = {socketAccept};
                    jcl.execute(SecondaryServer.class.getName(), secondaryArgs);
                    Debugger.debug("primary has sent a msg...");
                } catch (IOException ex) {
                    Debugger.debug(ex);
                }
            }
        } catch (IOException ex) {
            //Logger.getLogger(SecondaryServer.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not open port "
                    + SecondaryServer.portNumber + "... shuting down...");
        }
        timer.cancel();
        
        Debugger.debug("Turning to primary server mode now.");

        Thread thr;
        try {
            Server server = Server.getInstance();
            try {
                server.storeAllServerData();
            } catch (Exception ex) {
                Debugger.debug(ex);
            }
            thr = new Thread(server);            
            CollAServerGUI serverGUI = new CollAServerGUI();
            serverGUI.displayMessage("Listening port number: " + server.getPortNumber());
            server.addObserver(serverGUI);
            thr.start();
            try {
                server.storeAllServerData();
                //server.restoreServerData();
                server.restoreGUI();
                serverGUI.updateClientsTree();
            } catch (Exception ex) {
                Debugger.debug(ex);
            }            
            
        } catch (ServerInitializationException ex) {
            Logger.getLogger(SecondaryServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean primaryIsAlive;
    public static String primaryIPAddres;
    public static int primaryPortNumber;
    public static int portNumber;
    public static ServerSocket serverSocket;
    public boolean hasBacukp;
}
