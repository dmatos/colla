/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.server;

import colla.kernel.api.CollAHost;
import colla.kernel.api.CollAServer;
import colla.kernel.api.CollAUser;
import colla.kernel.messages.toClient.Ping;
import colla.kernel.messages.toServer.Pong;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.TimerTask;

/**
 *
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public class ConnectionMonitor extends TimerTask {

    public ConnectionMonitor(CollAServer server) {
        this.server = server;
        this.timeout = 45000;
    }

    @Override
    public void run() {
        //System.err.println("Checking connections");
        CollAUser user;
        Socket socket;
        ObjectInputStream input;
        ObjectOutputStream output;
        colla.kernel.messages.toClient.Ping pingUser = new colla.kernel.messages.toClient.Ping(1);
        colla.kernel.messages.toHost.Ping pingHost = new colla.kernel.messages.toHost.Ping();
        for (String username : this.server.getUsersSet()) {
            user = this.server.getUser(username);
            if (user != null) {
                for (CollAHost host : user.getHosts()) {
                    //first we try to contact user's hosts, even if user is offline
                    if (host.IsOnline()) {
                        try {
                            if (host.hasValidIP()) {
                                socket = new Socket(InetAddress.getByName(host.getIp()), host.getPort());
                            } else {
                                socket = this.server.getMappedConnections().get(host.getName());
                            }
                            socket.setSoTimeout(timeout);
                            output = new ObjectOutputStream(socket.getOutputStream());                           
                            output.writeObject(pingHost);
                            output.flush();
                            input = new ObjectInputStream(socket.getInputStream());
                            input.readObject();
                            if (host.hasValidIP()) {
                                socket.close();
                            }
                        } catch (ClassNotFoundException cnfe) {
                            //cnfe.printStackTrace();
                        } catch (Exception ex) {
                            host.setOffline();
                            this.server.updateHost(host);
                        }
                    }
                }
                if (user.isOnline()) {
                    //the we try to contact the user itself                    
                    try {
                        if (user.hasValidIP()) {
                            socket = new Socket(InetAddress.getByName(user.getIp()), user.getPort());
                        } else {
                            socket = this.server.getMappedConnections().get(username);
                        }
                        socket.setSoTimeout(timeout);
                        output = new ObjectOutputStream(socket.getOutputStream());
                        output.writeObject(pingUser);
                        output.flush();
                        input = new ObjectInputStream(socket.getInputStream());
                        input.readObject();
                        if (user.hasValidIP()) {
                            socket.close();
                        }
                    } catch (ClassNotFoundException cnfe) {
                        //cnfe.printStackTrace();
                    } catch (Exception ex) {
                        user.setOffline();
                        this.server.updateUser(user);
                    }
                }
            }
        }
        //System.err.println("Connections checked");
    }
    private CollAServer server;
    private final int timeout;
}
