/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.host_viewer.controller;

import colla.appl.host_viewer.exceptions.HostControllerInitializationException;
import colla.appl.host_viewer.view.HostViewerGUI;
import colla.kernel.api.*;
import colla.kernel.messages.toHost.ServerHostLoginAnswer;
import colla.kernel.messages.toHost.TaskMessage;
import colla.kernel.messages.toServer.*;
import colla.kernel.util.Debugger;
import colla.kernel.util.ServerConfReader;
import colla.kernel.util.Treater;
import interfaces.kernel.JCL_result;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * Implements the control point for a Host.
 *
 * @author Bruno
 */
public class HostViewerController implements Runnable {

    private HostViewerController(String serverIP, int serverPort,
            String secondaryServerIP, int secondaryServerPort)
            throws ParserConfigurationException, SAXException, IOException {
        this.storedResults = new HashMap<>();
        
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.secondaryServerIP = secondaryServerIP;
        this.secondaryServerPort = secondaryServerPort;

        // Call the GUI
        HostViewerGUI hostGUI = HostViewerGUI.getInstance();
        hostGUI.setVisible(true);
    }

    public static synchronized HostViewerController setup(String serverIP,
            int serverPort, String secondaryServerIP, int secondaryServerPort) throws ParserConfigurationException, SAXException, IOException {
        if (instance == null) {
            synchronized (HostViewerController.class) {
                if (instance == null) {
                    instance = new HostViewerController(serverIP, serverPort,
                            secondaryServerIP, secondaryServerPort);
                }
            }
        }
        return instance;
    }

    /**
     * Singleton
     *
     * @return an unique instance of HostViewerController
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public synchronized static HostViewerController getInstance() throws HostControllerInitializationException {
        if (instance == null) {
            throw new HostControllerInitializationException("HostViewer has not been initialized");
        }
        return instance;
    }

    /**
     * Connects the host to the server.
     */
    public void login(CollAHost host) {
        this.updateHost(host);
        collAHost.setOnline();
        String hostName = collAHost.getName();
        try {
            HostLoginMsg outgoing = new HostLoginMsg();
            outgoing.setIPAddress(collAHost.getIp());
            outgoing.setHostName(hostName);
            outgoing.setSender(collAHost.getNameUser());
            this.displayStatus("Contacting the server...");
            Socket socket = new Socket(InetAddress.getByName(serverIP),
                    serverPort);
            socket.setSoTimeout(timeout);
            ObjectOutputStream output = new ObjectOutputStream(
                    socket.getOutputStream());
            output.writeObject(outgoing);
            output.flush();
            this.displayStatus("Receiving answer from the server...");
            ObjectInputStream input = new ObjectInputStream(
                    socket.getInputStream());
            ServerHostLoginAnswer incoming = (ServerHostLoginAnswer) input
                    .readObject();
            socket.close();

            // If server says it is connected
            if (incoming.isConnected()) {
                // retorna o usuário com ValidIP checado
                // If host have a valid ip
                if (incoming.isValidIP()) {
                    collAHost.validateIP();
                    collAHost.setIp(incoming.getRealIP());
                } else {
                    collAHost.invalidateIP();
                }
                conexao_estabelecida = true;
            } else {
                conexao_estabelecida = false;
            }
            if (conexao_estabelecida) {
                collAHost.setOnline();
                this.displayStatus(hostName + " is conected!");
            } else {
                collAHost.setOffline();
                this.displayStatus("Conection couldn't be established");
            }

        } catch (IOException ex) {
            Debugger.debug(ex);
            this.exchangeServers();
            conexao_estabelecida = false;
        } catch (ClassNotFoundException e) {
            Treater.treat(e);
            conexao_estabelecida = false;
        }
    }

    /**
     * updates a host info when it logs to the server.
     *
     * @param host host connected to the server to update.
     */
    public void updateHost(CollAHost host) {
        this.collAHost = host;
        if (this.collAHost.hasValidIP()) {
            this.displayStatus("Ip: " + this.collAHost.getIp());
            this.displayStatus("Listening to port: " + this.collAHost.getPort());
        }
    }

    /**
     * Displays a simple sentence on the display.
     *
     * @param s the string to be displayed
     */
    public void displayStatus(String s) {
        HostViewerGUI.getInstance().displayStatus(s);
    }

    /**
     * Sends host status to server
     */
    public void uploadHostToServer() {
        try {
            HostUpdateMsg outgoing = new HostUpdateMsg(this.collAHost);
            Socket socket = new Socket(InetAddress.getByName(serverIP),
                    serverPort);
            socket.setSoTimeout(10000);
            ObjectOutputStream output = new ObjectOutputStream(
                    socket.getOutputStream());
            output.writeObject(outgoing);
            output.flush();
            // wait ACK
            ObjectInputStream input = new ObjectInputStream(
                    socket.getInputStream());
            input.readObject();
            socket.close();
        } catch (IOException ex) {
            Debugger.debug(ex);
            this.exchangeServers();
        } catch (ClassNotFoundException io) {
            Debugger.debug(io);
        }
    }

    public boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    /**
     * Disconnects from server.
     */
    public void shutdown() {
        try {
            CollAMessage outgoing = new HostDisconnectMsg(collAHost);
            Socket socket = new Socket(InetAddress.getByName(serverIP),
                    serverPort);
            socket.setSoTimeout(40000);
            ObjectOutputStream output = new ObjectOutputStream(
                    socket.getOutputStream());
            output.writeObject(outgoing);
            output.flush();
            // espera por ACK
            ObjectInputStream input = new ObjectInputStream(
                    socket.getInputStream());
            input.readObject();
            socket.close();


        } catch (Exception tout) {
            Debugger.debug(tout);
        }
        try {
            HostViewerMicroServer.getInstance().shutdown();
        } catch (Exception ex) {
            Debugger.debug(ex);
        }

        this.deleteDir(new File("../temp_files/"));

        HostViewerController.instance = null;
    }

    /**
     * Stores a result to be downloaded at any time
     *
     * @param task a CollATask to be stored
     * @return a ticket to retrieve the result
     */
    public Integer storeResult(CollATask task, JCL_result jclr)
            throws Exception {
        task.setResult(jclr);
        Integer ticket = this.storedResults.size() + 1;
        this.storedResults.put(ticket, task);
        return ticket;
    }

    /**
     * Gets an Stored Result
     *
     * @param ticket a key to get back the result
     * @return a CollATask containing task informations and the result
     */
    public CollATask getStoredResult(Integer ticket) {
        return this.storedResults.get(ticket);
    }

    public CollAHost getHost() {
        return collAHost;
    }

    public String getServerIPaddress() {
        return serverIP;
    }

    /*public void setServerIPaddress(String serverIPaddress) {
     this.serverIPaddress = serverIPaddress;
     }*/
    public int getServerPortNumber() {
        return serverPort;
    }

    /*public void setServerPortNumber(int serverPortNumber) {
     this.serverPortNumber = serverPortNumber;
     }*/
    /**
     * Cancels a scheduled task.
     *
     * @param taskID id of a scheduled task to cancel.
     */
    public void cancelScheduledTask(long taskID) {
        try {
            HostViewerMicroServer.getInstance().cancelTask(taskID);
            this.displayStatus("Task: " + taskID + " has been canceled.");
        } catch (Exception ex) {
            this.displayStatus("Task: " + taskID + " could not be canceled.");
            Debugger.debug(ex);
        }
    }

    /**
     * Schedules a task.
     *
     * @param taskMessage message containing a task to schedule.
     */
    public void scheduleTask(TaskMessage taskMessage) {
        try {
            HostViewerMicroServer.getInstance().scheduleTask(taskMessage);
        } catch (Exception ex) {
            Debugger.debug(ex);
        }
    }

    public void updateHostWeight(Long taskTotalTime) {
        this.collAHost.increaseTaskCounter(taskTotalTime);
        this.uploadHostToServer();
    }

    @Override
    public void run() {
        try {
            HostViewerMicroServer microServer = HostViewerMicroServer.getInstance();
            Thread thr = new Thread(microServer);
            thr.start();
        } catch (Exception ex) {
            Debugger.debug(ex);
        }
    }

    public void reinitializeMicroServer() {
        HostViewerMicroServer microServer;
        try {
            microServer = HostViewerMicroServer.getInstance();
            Thread thr = new Thread(microServer);
            thr.start();
        } catch (HostControllerInitializationException ex) {
            Debugger.debug(ex);
        }
    }

    public void exchangeServers() {

        String tempIP;
        int tempPort;

        tempIP = this.serverIP;
        tempPort = this.serverPort;

        this.serverIP = this.secondaryServerIP;
        this.serverPort = this.secondaryServerPort;

        this.secondaryServerIP = tempIP;
        this.secondaryServerPort = tempPort;
    }
    
    private static HostViewerController instance = null;
    private CollAHost collAHost;
    private String serverIP;
    private int serverPort;
    private String secondaryServerIP;
    private int secondaryServerPort;
    private boolean conexao_estabelecida;
    private Map<Integer, CollATask> storedResults;
    private final int timeout = 40000;
}