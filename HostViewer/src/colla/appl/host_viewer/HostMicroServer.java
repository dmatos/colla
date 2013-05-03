/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.host_viewer;

import colla.kernel.api.CollAHost;
import colla.kernel.api.CollAMessage;
import colla.kernel.messages.toClient.ACK;
import colla.kernel.messages.toServer.MapConnection;
import implementations.util.CoresAutodetect;
import java.io.*;
import java.net.*;

/**
 * Cria um servidor que deixa o host disponível para receber solicitações a
 * qualquer momento.
 *
 * @author dmatos
 */
class HostMicroServer extends Thread{

    /**
     * Constroi um micro server com as informações necessárias para seu
     * funcionamento.
     *
     * @param hostViewer       the host viewer linked with this server
     * @param serverIPadress   the server IP address
     * @param serverPortNumber the server port number
     */
    public HostMicroServer( HostViewer hostViewer, String serverIPaddress, int serverPortNumber ){
        this.hostViewer = hostViewer;
        this.active = true;
        this.serverIPaddress = serverIPaddress;
        this.serverPortNumber = serverPortNumber;   
        this.init();
    }// end method constructor
    
    private void init(){
           if( this.hostViewer.getHost().hasValidIP() ){
            try{
                this.serverSocket = new ServerSocket( 8080 );
                CollAHost hostAux = this.hostViewer.getHost();
                hostAux.setPort( serverSocket.getLocalPort() );
                this.hostViewer.setHost( hostAux );
                //System.err.println("ServerSocket criado!");
            }catch( IOException e ){
                //e.printStackTrace();
            }
        }else{
            try{
                MapConnection outgoing = new MapConnection( this.hostViewer.getHost().getName() );
                this.keepAlive = new Socket( InetAddress.getByName( this.serverIPaddress ), serverPortNumber );
                ObjectOutputStream output = new ObjectOutputStream( keepAlive.getOutputStream() );
                //ask the server to put this connection into in a map
                output.writeObject( outgoing );
                output.flush();
                // espera por ACK
                ObjectInputStream input = new ObjectInputStream( keepAlive.getInputStream() );
                input.readObject();
                //System.err.println("Conexão mapeada!");
            }catch( Exception e ){
                //e.printStackTrace();
            }
        }// end else
        this.initialTime = System.nanoTime();

        //Joubert
        this.port = 8080;
        int numOfThreads = CoresAutodetect.detect();
        serverThreads = new CollAConsumer[ numOfThreads ];
        serverR = new GenericResource<CollAMessage>();

        for( int i = 0; i < numOfThreads; i++ ){
            serverThreads[i] = new CollAConsumer<CollAMessage>( serverR , this);
            serverThreads[i].start();
        }
        //System.err.println( "server on port " + port + " with " + numOfThreads + " threads started" );       
    }

    @Override
    public void run(){
        Socket connection;
        this.hostViewer.uploadHostToServer();
        while( active ){
            try{
                //System.out.println( "microserver waiting connection..." );
                //if host IP is valid the socketServer is working, else the connection must keep alive
                if( hostViewer.getHost().hasValidIP() ){
                    connection = serverSocket.accept();
                }else{
                    connection = keepAlive;
                }
                
                ObjectInputStream input = new ObjectInputStream( connection.getInputStream() );
                CollAMessage collAMessage = ( CollAMessage ) input.readObject();
                ObjectOutputStream output = new ObjectOutputStream( connection.getOutputStream() );
                output.writeObject( new ACK() );
                output.flush();
                serverR.putRegister( collAMessage );
            }catch( Exception e ){
                //e.printStackTrace();
            }
        }// end while
    }

    public HostViewer getHostViewer(){
        return hostViewer;
    }

    /**
     * Shuts down the micro server.
     */
    public void shutdown(){
        active = false;
        try{
            if( keepAlive != null && !keepAlive.isClosed() ){
                keepAlive.close();
            }
            if( serverSocket != null && !serverSocket.isClosed() ){
                serverSocket.close();
            }                        
        }catch( IOException io ){
            //io.printStackTrace();
        }        
    }

    public CollAHost getHost(){
        return this.hostViewer.getHost();
    }

    public String getServerIPaddress(){
        return serverIPaddress;
    }

    public int getServerPortNumber(){
        return serverPortNumber;
    }

    private ServerSocket serverSocket; // para conexões quando se tem IP válido
    private Socket keepAlive; // para conexões quando não se tem 
    private volatile HostViewer hostViewer;
    private boolean active;
    private String serverIPaddress;
    private int serverPortNumber;
    //Variáveis do código server multthread do Joubert
    protected GenericConsumer<CollAMessage>[] serverThreads;
    protected GenericResource<CollAMessage> serverR;
    protected int port;
    protected boolean isStopped;
    protected long initialTime;
}