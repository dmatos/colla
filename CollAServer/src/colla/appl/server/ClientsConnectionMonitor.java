package colla.appl.server;

import colla.appl.server.util.WeightedHost;
import colla.kernel.api.CollAHost;
import colla.kernel.api.CollAServer;
import colla.kernel.api.CollAUser;
import colla.kernel.exceptions.server.NonExistentUser;
import colla.kernel.exceptions.server.ServerInitializationException;
import colla.kernel.util.Debugger;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.TimerTask;

/**
 * Checks for client connection status (online/offline).
 *
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public class ClientsConnectionMonitor extends TimerTask {

    public ClientsConnectionMonitor() {
        this.timeout = 5000;
    }

    @Override
    public void run() {
        //System.err.println("Checking connections");
        CollAUser user;
        Socket socket;
        ObjectInputStream input;
        ObjectOutputStream output;
        Long totalWeight = 0L;
        Long totalOfHosts = 0L;
        colla.kernel.messages.toClient.Ping pingUser = new colla.kernel.messages.toClient.Ping(1);
        colla.kernel.messages.toHost.Ping pingHost = new colla.kernel.messages.toHost.Ping();
        try {
            Server server = Server.getInstance();
            for (String username : server.getUsersSet()) {
                try {
                    user = server.getUser(username);
                    for (CollAHost host : user.getHosts()) {
                        //first we try to contact user's hosts, even if user is offline
                        if (host.IsOnline()) {
                            try {
                                if (host.hasValidIP()) {
                                    socket = new Socket(
                                            InetAddress.getByName(host.getIp()),
                                            host.getPort());
                                } else {
                                    socket = server.getMappedConnections().get(
                                            host.getName());
                                }
                                socket.setSoTimeout(timeout);
                                output = new ObjectOutputStream(socket.getOutputStream());
                                Long timestamp = System.nanoTime();
                                output.writeObject(pingHost);
                                output.flush();
                                input = new ObjectInputStream(socket.getInputStream());
                                input.readObject();    
                                host.setRoundTripTime(System.nanoTime() - timestamp);
                                server.updateWeightedHost(new WeightedHost(host));
                                totalWeight += host.getWeight();
                                totalOfHosts += 1L;
                                if (host.hasValidIP()) {
                                    socket.close();
                                }
                            } catch (ClassNotFoundException cnfe) {
                                Debugger.debug(cnfe);
                            } catch (Exception ex) {
                                host.setOffline();
                                server.updateHost(host);
                            }
                        }
                    }
                    if (user.isOnline()) {
                        //the we try to contact the user itself                    
                        try {
                            if (user.hasValidIP()) {
                                socket = new Socket(InetAddress.getByName(
                                        user.getIp()), user.getPort());
                            } else {
                                socket = server.getMappedConnections().get(username);
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
                            server.updateUser(user);
                        }
                    }
                } catch (NonExistentUser nonUser) {
                }
            }
            
            //increment given by average weight
            server.setHostWeightIncrement(totalWeight / totalOfHosts);
            Debugger.debug("Connections checked");            
        } catch (ServerInitializationException ex) {
        }
    }
    private final int timeout;
}
