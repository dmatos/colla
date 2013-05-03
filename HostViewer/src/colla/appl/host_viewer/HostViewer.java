/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.host_viewer;

import colla.appl.host_viewer.GUI.HostViewerGUI;
import colla.kernel.api.*;
import colla.kernel.messages.toHost.ServerHostLoginAnswer;
import colla.kernel.messages.toServer.*;
import colla.kernel.util.SAXReader;
import colla.kernel.util.Treater;
import interfaces.kernel.JCL_result;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @todo Logger para todos os exception e mensagens importantes para debug dadas
 * por print
 */
/**
 * Classe responsável pelo login e por diversas funcionalidades do host.
 *
 * @author Bruno
 */
public class HostViewer {

    /**
     * Cria um novo HostViewer e inicializa as suas informações.
     *
     * @param host o CollAHost vinculado ao HostViewer
     */
    public HostViewer(CollAHost host) {
        this.me = host;
        this.timeout = 40000;
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
     * Connects the host on server
     */
    public void login() {
        me.setOnline();
        String hostName = me.getName();
        try {
            HostLoginMsg outgoing = new HostLoginMsg();
            outgoing.setIPAddress(me.getIp());
            outgoing.setHostName(hostName);
            outgoing.setSender(me.getNameUser());
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
                    me.validateIP();
                    me.setIp(incoming.getRealIP());
                } else {
                    me.invalidateIP();
                }
                conexao_estabelecida = true;

            } else {
                conexao_estabelecida = false;
            }
            if (conexao_estabelecida) {
                me.setOnline();
                this.displayStatus(hostName + " is conected!");
            } else {
                me.setOffline();
                this.displayStatus("Conection couldn't be established");
            }
            this.microServer = new HostMicroServer(this, serverIPaddress, serverPortNumber);
            this.microServer.start(); 
            
        } catch (Exception e) {
            Treater.treat(e);
            conexao_estabelecida = false;
        }        
    }

    /**
     * Sets host
     *
     * @param host the host
     */
    public void setHost(CollAHost host) {
        this.me = host;
        if (this.me.hasValidIP()) {
            this.displayStatus("Ip: " + this.me.getIp());
            this.displayStatus("Listening to port: " + this.me.getPort());
        }
    }

    /**
     * Displays a simple sentence on the display
     *
     * @param s the string to be displayed
     */
    public void displayStatus(String s) {
        this.hostLoginGUI.displayStatus(s);
    }

    /**
     * Sends the information from host to server
     */
    public void uploadHostToServer() {
        //System.out.println("uploadHostToServer()");
        try {
            HostUpdateMsg outgoing = new HostUpdateMsg(this.me);
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
     * Disconects the host from server.
     */
    public void disconnectFromServer() {
        try {
            CollAMessage outgoing = new HostDisconnectMsg(me);
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
        return me;
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
    private CollAHost me;
    private String serverIPaddress;
    private int serverPortNumber;
    private boolean conexao_estabelecida;
    private HostViewerGUI hostLoginGUI;
    private HostMicroServer microServer;
    private Map<Integer, CollATask> storedResults;
    private final int timeout;
}