/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.server;

import colla.appl.server.GUI.CollAServerGUI;

/**
 * Class containing a main method to initialize the Server.
 *
 * @author dmatos
 */
public class ServerStarter {

    /**
     * @todo
     *
     */
    /**
     *
     * @param args
     *
     * server options
     *
     * -t 'timeout' set timeout value to a given 'timeout' 
     * 
     * -p 'number' set port number to a given 'number'
     * 
     * -noX set use of CLI instead of GUI
     *
     */
    public static void main(String[] args) {

        int timeout = 300000;
        int portNumber = 9999;
        Server superServer;
        boolean useGUI = true;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-t") && i + 1 < args.length - 1) {
                try {
                    Integer tout = Integer.parseInt(args[i + 1]);
                    timeout = tout;
                } catch (NumberFormatException ex) {
                    timeout = 300000;
                }
            }
            if (args[i].equals("-p") && i + 1 < args.length - 1) {
                try {
                    Integer p = Integer.parseInt(args[i + 1]);
                    portNumber = p;
                } catch (NumberFormatException ex) {
                    portNumber = 9999;
                }
            }
            if (args[i].equals("-noX")) {
                useGUI = false;
            }
        }



        superServer = new Server(portNumber, timeout);
        Thread thr = new Thread(superServer);
        thr.start();

        if (useGUI) {
            CollAServerGUI serverGUI = new CollAServerGUI(superServer);            
            serverGUI.displayMessage("Listening port number: " + superServer.getPortNumber());            
            superServer.addObserver(serverGUI);            
            serverGUI.updateClientsTree();
        }

    }
}
