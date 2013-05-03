/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.developer_viewer;

import colla.kernel.api.CollAMessage;
import colla.kernel.api.CollAUser;
import colla.kernel.messages.toClient.ACK;
import colla.kernel.messages.toServer.MapConnection;
import colla.kernel.util.Debugger.DebugInfo;
import implementations.sm_kernel.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;
import java.io.*;
import java.net.*;
import java.util.Observable;

/**
 * Cria um servidor que deixa o cliente disponível para receber solicitações a
 * qualquer momento.
 *
 * @author dmatos
 */
class DevMicroServer extends Observable implements Runnable {

    /**
     * Constroi um micro server com as informações necessárias para seu
     * funcionamento.
     *
     * @param developerViewer the developer viewer linked with this server.
     * @param usr user that owns the microserver.
     * @param ipAddress server ip adress.
     * @param portNumber the server port number.
     */
    public DevMicroServer(DeveloperViewer developerViewer, CollAUser usr, String ipAddress, int portNumber) {
        this.timeout = 90000;
        this.serverIPaddress = ipAddress;
        this.serverPortNumber = portNumber;
        this.devViewer = developerViewer;
        this.active = true;
        this.shutdownCounter = 0;
        this.debugInfo = new DebugInfo();
        this.debugInfo.setDebuggedName(usr.getName());
        this.serverSocket = null;
        this.keepAlive = null;

        if (usr.hasValidIP()) {
            this.validIP = true;
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
                debug("Problema com criação do server socket do micro server", e);
            }
        } else {
            this.validIP = false;
            try {
                /*
                 * asks the server to store and keep this connection alive since
                 * it's not possible open a connection with this socket directly
                 * due to the fact that it is probably behind a NAT
                 */
                MapConnection mapCon = new MapConnection(usr.getName());
                this.keepAlive = new Socket(InetAddress.getByName(serverIPaddress), serverPortNumber);
                ObjectOutputStream output = new ObjectOutputStream(keepAlive.getOutputStream());               
                output.writeObject(mapCon);
                output.flush();
                // espera por ACK
                ObjectInputStream input = new ObjectInputStream(keepAlive.getInputStream());
                input.readObject();
            } catch (Exception e) {
                //debug("Problema com criação do server socket do micro server", e);
                this.notifyObservers("Error: DevMicroServer could not be initilized");
                System.exit(1);
            }
        }// end else

    }// end method

    @Override
    public void run() {
        debug("microserver criado");
        //registering a class in JavaCaLa
        JCL_facade jcl = JCL_FacadeImpl.getInstance();
        jcl.register(DevWorker.class, DevWorker.class.getName());
        Socket connection = null;
        while (active) {
            try {
                debug( "microserver waiting connection..." );
                //if host IP is valid the socketServer is working, else the connection must keep alive
                if (devViewer.getUser().hasValidIP()) {
                    connection = serverSocket.accept();
                }else{
                    connection = keepAlive;
                }
                ObjectInputStream input   = new ObjectInputStream( connection.getInputStream()  );
                CollAMessage collAMessage = ( CollAMessage )input.readObject();
                ObjectOutputStream output = new ObjectOutputStream( connection.getOutputStream() );
                output.writeObject( new ACK() );
                output.flush();
                Object[] args = {collAMessage, this};
                jcl.execute(DevWorker.class.getName(), args);
            } catch (Exception e) {
                debug(e);
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
            debug(io);
        }
    }

private void debug(String info, Exception ex){
       /* this.debugInfo.clear();
        this.debugInfo.setInfo( info );
        this.debugInfo.setException( ex );
        this.notifyObservers( this.debugInfo );
        System.out.println(info);
        ex.printStackTrace();*/
    }
    /**
     * Método para debugar o programa e notificar os Observers.
     * @param info Informação
     */
    private void debug(String info){
       /* this.debugInfo.clear();
        this.debugInfo.setInfo( info );
        this.notifyObservers( this.debugInfo );
        System.out.println(info); */
    }
    
    /**
     * Método para debugar o programa e notificar os Observers.
     * @param ex Exception
     */
    private void debug(Exception ex){
        /*this.debugInfo.clear();
        this.debugInfo.setException( ex );
        this.notifyObservers( this.debugInfo );
        ex.printStackTrace();*/
    }

    public DeveloperViewer getDevViewer(){
        return devViewer;
    }

    public String getServerIPaddress(){
        return serverIPaddress;
    }

    public Integer getServerPortNumber(){
        return serverPortNumber;
    }
    
    public CollAUser getUser(){
        return devViewer.getUser();
    }
    
    
    private Socket keepAlive;
    private String serverIPaddress;
    private Integer serverPortNumber;
    private DeveloperViewer devViewer;
    private boolean active;
    private final boolean validIP;
    private ServerSocket serverSocket;
    private final int timeout;
    private int shutdownCounter;
    private DebugInfo debugInfo;
}
