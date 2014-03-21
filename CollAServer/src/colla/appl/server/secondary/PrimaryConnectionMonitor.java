/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.server.secondary;

import colla.kernel.api.CollAMessage;
import colla.kernel.messages.toServer.Ping;
import colla.kernel.messages.toClient.ACK;
import colla.kernel.util.Debugger;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dmatos
 */
public class PrimaryConnectionMonitor  extends TimerTask{
    
    private int timeout = 10000;

    @Override
    public void run() {
       try{
           Socket socket = new Socket(
                   InetAddress.getByName(SecondaryServer.primaryIPAddres),
                   SecondaryServer.primaryPortNumber);
           socket.setSoTimeout(timeout);
           ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
           CollAMessage msg = new Ping();
           output.writeObject(msg);
           output.flush();
           //read ACK
           ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
           input.readObject();
           Debugger.debug("primary still alive");
       } catch (Exception ex) {
           Debugger.debug("primary is dead");
            SecondaryServer.primaryIsAlive = false;
           try {
               SecondaryServer.serverSocket.close();
           } catch (IOException ioex) {
               //Logger.getLogger(PrimaryConnectionMonitor.class.getName()).log(Level.SEVERE, null, ex1);
               Debugger.debug(ioex);
           }
        }
    }
    
}
