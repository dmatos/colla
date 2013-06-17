/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.host_viewer.controller;

import colla.kernel.api.CollAHost;
import colla.kernel.api.CollAMessage;
import colla.kernel.messages.toClient.ACK;
import colla.kernel.messages.toHost.TaskMessage;
import colla.kernel.messages.toServer.MapConnection;
import implementations.util.CoresAutodetect;
import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

/**
 * Cria um servidor que deixa o host disponível para receber solicitações a
 * qualquer momento.
 *
 * @author dmatos
 */
class HostViewerMicroServer extends Thread {

    /**
     * Constroi um micro server com as informações necessárias para seu
     * funcionamento.
     *
     * @param serverIPadress the server IP address
     * @param serverPortNumber the server port number
     */
    public HostViewerMicroServer(String serverIPaddress, int serverPortNumber) {
        this.active = true;
        this.serverIPaddress = serverIPaddress;
        this.serverPortNumber = serverPortNumber;
        this.timer = new Timer();
        this.scheduleMap = new HashMap<Long, ScheduledTask>();
        this.init();
    }

    private void init() {
        if (HostViewerController.getInstance().getHost().hasValidIP()) {
            try {
                this.serverSocket = new ServerSocket(8080);
                CollAHost hostAux = HostViewerController.getInstance().getHost();
                hostAux.setPort(serverSocket.getLocalPort());
                HostViewerController.getInstance().setHost(hostAux);
                //System.err.println("ServerSocket criado!");
            } catch (IOException e) {
                //e.printStackTrace();
            }
        } else {
            try {
                MapConnection outgoing = new MapConnection(HostViewerController.getInstance().getHost().getName());
                this.keepAlive = new Socket(InetAddress.getByName(this.serverIPaddress), serverPortNumber);
                ObjectOutputStream output = new ObjectOutputStream(keepAlive.getOutputStream());
                //ask the server to put this connection into in a map
                output.writeObject(outgoing);
                output.flush();
                // espera por ACK
                ObjectInputStream input = new ObjectInputStream(keepAlive.getInputStream());
                input.readObject();
                //System.err.println("Conexão mapeada!");
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }// end else
        this.initialTime = System.nanoTime();

        //Joubert
        this.port = 8080;
        int numOfThreads = CoresAutodetect.detect();
        serverThreads = new CollAConsumer[numOfThreads];
        serverR = new GenericResource<CollAMessage>();

        for (int i = 0; i < numOfThreads; i++) {
            serverThreads[i] = new CollAConsumer<CollAMessage>(serverR, this);
            serverThreads[i].start();
        }
        //System.err.println( "server on port " + port + " with " + numOfThreads + " threads started" );       
    }

    @Override
    public void run() {
        Socket connection;
        HostViewerController.getInstance().uploadHostToServer();
        while (active) {
            try {
                //System.out.println( "microserver waiting connection..." );
                //if host IP is valid the socketServer is working, else the connection must be kept alive
                if (HostViewerController.getInstance().getHost().hasValidIP()) {
                    connection = serverSocket.accept();
                } else {
                    connection = keepAlive;
                }

                ObjectInputStream input = new ObjectInputStream(connection.getInputStream());
                CollAMessage collAMessage = (CollAMessage) input.readObject();
                ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
                output.writeObject(new ACK());
                output.flush();
                
                serverR.putRegister(collAMessage);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }// end while
    }

    /**
     * Shuts down the micro server.
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
            //io.printStackTrace();
        }
    }

    public String getServerIPaddress() {
        return serverIPaddress;
    }

    public int getServerPortNumber() {
        return serverPortNumber;
    }

    /**
     * Cancels a scheduled task.
     *
     * @param taskID id of a scheduled task to cancel.
     * @return true if the task exists and was canceled, otherwise returns
     * false.
     */
    public boolean cancelTask(long taskID) {
        boolean success = false;
        if (scheduleMap.containsKey(taskID)) {
            success = scheduleMap.get(taskID).cancel();
            if (success) {
                scheduleMap.remove(taskID);
            }
        }
        return success;
    }

    /**
     * Schedules a task.
     *
     * @param taskMessage a message containing a task to schedule.
     */
    public void scheduleTask(TaskMessage collAMessage) {
        Date date = ((TaskMessage) collAMessage).getTask().getSchedule();
        Long taskID = ((TaskMessage) collAMessage).getTask().getTaskID();
        ScheduledTask scheduledTask = new ScheduledTask(collAMessage, this.serverR);
        this.scheduleMap.put(taskID, scheduledTask);
        this.timer.schedule(scheduledTask, date);
    }
    private ServerSocket serverSocket; // para conexões quando se tem IP válido
    private Socket keepAlive; // para conexões quando não se tem 
    private boolean active;
    private String serverIPaddress;
    private int serverPortNumber;
    private Timer timer;
    private Map<Long, ScheduledTask> scheduleMap;
    //Variáveis do código server multthread do Joubert
    protected GenericConsumer<CollAMessage>[] serverThreads;
    protected GenericResource<CollAMessage> serverR;
    protected int port;
    protected boolean isStopped;
    protected long initialTime;
}