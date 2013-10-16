/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.host_viewer.controller;

import colla.appl.host_viewer.view.HostViewerGUI;
import colla.kernel.api.*;
import colla.kernel.messages.toHost.ServerHostLoginAnswer;
import colla.kernel.messages.toHost.TaskMessage;
import colla.kernel.messages.toServer.*;
import colla.kernel.util.SAXReader;
import colla.kernel.util.Treater;
import interfaces.kernel.JCL_result;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Implements the control point for a Host.
 *
 * @author Bruno
 */
public class HostViewerController {

    private HostViewerController() {
        this.storedResults = new HashMap<Integer, CollATask>();
        // Read IP
        SAXReader reader = new SAXReader();
        try {
            reader.parse("server_conf.xml");
            serverIPaddress = reader.getIPfromXML();
            serverPortNumber = reader.getPortNumberFromXML();
        } catch (Exception e) {
            Treater.treat(e);
        }

        // Call the GUI
        hostLoginGUI = new HostViewerGUI(this);
        hostLoginGUI.setVisible(true);
    }

    /**
     * Singleton
     * 
     * @return an unique instance of HostViewerController
     */
    public static HostViewerController getInstance() {
        if (instance == null) {
            synchronized (HostViewerController.class) {
                if (instance == null) {
                    instance = new HostViewerController();
                }
            }
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
            Socket socket = new Socket(InetAddress.getByName(serverIPaddress), serverPortNumber);
            socket.setSoTimeout(40000);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(outgoing);
            output.flush();
            this.displayStatus("Receiving answer from the server...");
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            ServerHostLoginAnswer incoming = (ServerHostLoginAnswer) input.readObject();
            socket.close();

            // If server says it is connected
            if (incoming.isConnected()) {
                //retorna o usuário com ValidIP checado
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
            this.microServer = new HostViewerMicroServer(serverIPaddress, serverPortNumber);
            this.microServer.start();

        } catch (Exception e) {
            Treater.treat(e);
            conexao_estabelecida = false;
        }
    }
    
    /**
     * updates a host info when it logs to the server.
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
        this.hostLoginGUI.displayStatus(s);
    }

    /**
     * Sends host status to server
     */
    public void uploadHostToServer() {
        //System.out.println("uploadHostToServer()");
        try {
            HostUpdateMsg outgoing = new HostUpdateMsg(this.collAHost);
            Socket socket = new Socket(InetAddress.getByName(serverIPaddress), serverPortNumber);
            socket.setSoTimeout(10000);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(outgoing);
            output.flush();
            // espera por ACK
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            input.readObject();
            socket.close();
        } catch (Exception io) {
            //io.printStackTrace();
        }
    }

    /**
     * Disconnects from server.
     */
    public void disconnectFromServer() {
        try {
            CollAMessage outgoing = new HostDisconnectMsg(collAHost);
            Socket socket = new Socket(InetAddress.getByName(serverIPaddress), serverPortNumber);
            socket.setSoTimeout(40000);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(outgoing);
            output.flush();
            //espera por ACK
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            input.readObject();
            socket.close();
        } catch (Exception tout) {
            //tout.printStackTrace();
        }
        this.microServer.shutdown();
        System.exit(0);
    }

    /**
     * Stores a result to be downloaded at any time
     *
     * @param task a CollATask to be stored
     * @return a ticket to retrieve the result
     */
    public Integer storeResult(CollATask task, JCL_result jclr) throws Exception {
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
        return serverIPaddress;
    }

    public void setServerIPaddress(String serverIPaddress) {
        this.serverIPaddress = serverIPaddress;
    }

    public int getServerPortNumber() {
        return serverPortNumber;
    }

    public void setServerPortNumber(int serverPortNumber) {
        this.serverPortNumber = serverPortNumber;
    }
    
    /**
     * Cancels a scheduled task.
     * 
     * @param taskID id of a scheduled task to cancel.
     */
    public void cancelScheduledTask(long taskID){
        if(this.microServer.cancelTask(taskID)){
            this.displayStatus("Task: "+ taskID + " has been canceled.");
        } else {
            this.displayStatus("Task: "+ taskID + " could not be canceled.");
        }
    }
    
    /**
     * Schedules a task.
     * 
     * @param taskMessage message containing a task to schedule. 
     */
    public void scheduleTask(TaskMessage taskMessage){
        this.microServer.scheduleTask(taskMessage);
    }
    
    private static HostViewerController instance;
    private CollAHost collAHost;
    private String serverIPaddress;
    private int serverPortNumber;
    private boolean conexao_estabelecida;
    private HostViewerGUI hostLoginGUI;
    private HostViewerMicroServer microServer;
    private Map<Integer, CollATask> storedResults;
    private final int timeout = 40000;
}