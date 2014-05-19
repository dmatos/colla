/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.developer_viewer;

import colla.appl.developer_viewer.exceptions.DeveloperControllerInitializationException;
import colla.kernel.api.CollAMessage;
import colla.kernel.api.CollAUser;
import colla.kernel.messages.toClient.ACK;
import colla.kernel.messages.toServer.MapConnection;
import colla.kernel.util.Debugger;
import implementations.sm_kernel.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;
import java.io.*;
import java.net.*;
import java.util.Observable;

/**
 * Initializes a micro server so the client will be available to receive
 * messages at any time.
 *
 * @author dmatos
 */
class DevMicroServer extends Observable implements Runnable {

    /**
     *
     * Initializes a micro server so the client will be available to receive
     * messages at any time.
     *
     * @param developerViewer the developer viewer linked with this server.
     * @param usr user that owns the microserver.
     * @param ipAddress server ip adress.
     * @param portNumber the server port number.
     */
    public DevMicroServer(String ipAddress, int portNumber)
            throws DeveloperControllerInitializationException {
        DeveloperViewerController devViewer =
                DeveloperViewerController.getInstance();
        CollAUser usr = devViewer.getUser();
        if (usr.hasValidIP()) {
            this.validIP = true;
        } else {
            this.validIP = false;
        }
        this.timeout = 10000;
        this.active = true;
        this.shutdownCounter = 0;
        this.serverSocket = null;
        this.keepAlive = null;
        this.initialize(this.validIP);
    }// end method

    private void initialize(boolean validIP) throws DeveloperControllerInitializationException {
        DeveloperViewerController devViewer =
                DeveloperViewerController.getInstance();
        CollAUser usr = devViewer.getUser();
        if (validIP) {
            try {
                if (devViewer.getUser().getPort() > 0) {
                    this.serverSocket = new ServerSocket(devViewer.getUser().getPort());
                } else {
                    this.serverSocket = new ServerSocket(8084);
                    usr.setPort(serverSocket.getLocalPort());
                }
                devViewer.setUser(usr);
                devViewer.uploadUserToServer();
            } catch (IOException e) {
                Debugger.debug("Problema com criação do server socket do micro server", e);
            }
        } else {
            try {
                /*
                 * asks the server to store and keep this connection alive since
                 * it's not possible open a connection with this socket directly
                 * due to the fact that it is probably behind a NAT
                 */
                MapConnection mapCon = new MapConnection(usr.getName());
                this.keepAlive = new Socket(InetAddress.getByName(
                        devViewer.getServerIPAddress()),
                        devViewer.getServerPortNumber());
                ObjectOutputStream output = new ObjectOutputStream(keepAlive.getOutputStream());
                output.writeObject(mapCon);
                output.flush();
                // espera por ACK
                ObjectInputStream input = new ObjectInputStream(keepAlive.getInputStream());
                input.readObject();
            } catch (SocketException | EOFException | SocketTimeoutException ex) {
                Debugger.debug(ex);
                this.shutdown();
                devViewer.exchangeServers();
                devViewer.reinitializeMicroServer();
            } catch (IOException ioEx) {
                Debugger.debug("Problema com criação do server socket do micro server", ioEx);
                this.notifyObservers("Error: DevMicroServer could not be initilized");
                //System.exit(1);
                devViewer.shutdown();
            } catch (ClassNotFoundException cnfEx) {
                Debugger.debug("Problema com criação do server socket do micro server", cnfEx);
                this.notifyObservers("Error: DevMicroServer could not be initilized");
                //System.exit(1);
                devViewer.shutdown();
            }
        }// end else
    }

    @Override
    public void run() {
        Debugger.debug("microserver criado");
        //registering a class in JavaCaLa
        JCL_facade jcl = JCL_FacadeImpl.getInstance();
        jcl.register(DevWorker.class, DevWorker.class.getName());
        Socket connection = null;
        while (active) {
            try {
                Debugger.debug("microserver waiting connection...");
                //if host IP is valid the socketServer is working, else the connection must keep alive
                try {
                    DeveloperViewerController devViewer = DeveloperViewerController.getInstance();
                    if (devViewer.getUser().hasValidIP()) {
                        connection = serverSocket.accept();
                    } else {
                        connection = keepAlive;
                    }
                } catch (DeveloperControllerInitializationException devEx) {
                }
                ObjectInputStream input = new ObjectInputStream(connection.getInputStream());
                CollAMessage collAMessage = (CollAMessage) input.readObject();
                ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
                output.writeObject(new ACK());
                output.flush();
                Object[] args = {collAMessage, this};
                jcl.execute(DevWorker.class.getName(), args);
            } catch (EOFException | ConnectException | SocketTimeoutException ex) {
                Debugger.debug(ex);
                try {
                    DeveloperViewerController devViewer = DeveloperViewerController.getInstance();
                    for (int i = 10; i > 0; i -= 2) {
                        devViewer.displayInfo("Reconnecting to server in " + i + " seconds");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex1) {
                            Debugger.debug(ex1);
                        }
                    }
                    Debugger.debug(ex);
                    devViewer.displayInfo("Reconnecting now...");
                    this.shutdown();
                    devViewer.exchangeServers();
                    devViewer.reinitializeMicroServer();
                } catch (DeveloperControllerInitializationException ex1) {
                    Debugger.debug(ex);
                }
            } catch (Exception e) {
                Debugger.debug(e);
            }
        }// end while
    }// end method run

    /**
     * Shutdown the micro server.
     */
    public void shutdown() {
        active = false;
        try {
            if (keepAlive != null && !keepAlive.isClosed()) {
                keepAlive.close();
            }
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException io) {
            Debugger.debug(io);
        }
    }
    private Socket keepAlive;
    private boolean active;
    private final boolean validIP;
    private ServerSocket serverSocket;
    private final int timeout;
    private int shutdownCounter;
    //private DebugInfo debugInfo;
}
