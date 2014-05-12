/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.host_viewer.controller;

import colla.appl.host_viewer.view.HostViewRegisterGUI;
import colla.kernel.api.CollAHost;
import colla.kernel.impl.Host;
import colla.kernel.messages.toHost.ServerHostRegisterAnswerMsg;
import colla.kernel.messages.toServer.HostRegisterMsg;
import colla.kernel.util.Debugger;
import colla.kernel.util.ServerConfReader;
import java.io.*;
import java.net.*;
import java.util.Collections;
import java.util.Enumeration;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * Classe responsável por efetuar o registro do host no servidor.
 *
 * @author Bruno
 */
public class HostViewerRegister implements Runnable {

    public HostViewerRegister() {
        try {
            this.collAHost = new Host();
            ServerConfReader reader = new ServerConfReader();
            reader.parse("server_conf.xml");
            this.serverIP = reader.getIPfromXML();
            this.serverPort = reader.getPortNumberFromXML();
            this.secondaryServerIP = reader.getSecondaryIPAddressFromXML();
            this.secondaryServerPort = reader.getSecondaryPortNumberFromXML();

            /*
             * loop que procura o endereço ip do usuário
             * vasculhando as interfaces de rede
             */
            Enumeration<NetworkInterface> nets =
                    NetworkInterface.getNetworkInterfaces();

            for (NetworkInterface netint : Collections.list(nets)) {
                Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
                for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                    if (!inetAddress.isLoopbackAddress()
                            && !inetAddress.toString().substring(1).startsWith("127")
                            && inetAddress.toString().substring(1).contains(".")) {
                        //setting host IP address
                        collAHost.setIp(inetAddress.toString().substring(1));

                        //getting host MAC address 
                        NetworkInterface network = NetworkInterface.
                                getByInetAddress(inetAddress);

                        byte[] mac = network.getHardwareAddress();

                        System.out.print("Current MAC address : ");

                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < mac.length; i++) {
                            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? ":" : ""));
                        }
                        collAHost.setMAC(sb.toString());
                        Debugger.debug("MAC address: "+collAHost.getMAC());
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Debugger.debug(ex);
        }
    }

    /**
     * Registers the host on server.
     *
     * @param userName the name of the user who owns the host
     * @param pass the password of the user who owns the host
     * @param port the port for register
     */
    public boolean register(String userName, String pass, int port) {
        try {
            String crypt = this.byteArrayToHexString(this.computeHash(pass));

            HostRegisterMsg outgoing = new HostRegisterMsg();

            //enviar user
            outgoing.setUserName(userName);

            //enviar senha encriptada
            outgoing.setUserPass(crypt);

            collAHost.setPort(port);
            //enviar host
            outgoing.setHost(collAHost);

            Socket connect = new Socket(InetAddress.getByName(serverIP), serverPort);
            connect.setSoTimeout(timeout);
            ObjectOutputStream output = new ObjectOutputStream(connect.getOutputStream());
            output.writeObject(outgoing);
            output.flush();

            //receive the host name
            ObjectInputStream input = new ObjectInputStream(connect.getInputStream());
            ServerHostRegisterAnswerMsg incoming = (ServerHostRegisterAnswerMsg) input.readObject();
            connect.close();

            if (incoming.getHostName() == null) {
                Debugger.debug("Invalid username or password");
                return false;
            } else {
                collAHost.setName(incoming.getHostName());
                collAHost.setNameUser(userName);
                HostViewerController hostViewer = HostViewerController.setup(
                        serverIP, serverPort, secondaryServerIP, secondaryServerPort);
                hostViewer.login(collAHost);
                Thread thr = new Thread(hostViewer);
                thr.start();
                this.shutdown();
                if (this.hostRegisterGUI != null) {
                    this.hostRegisterGUI.dispose();
                }
            }
            Debugger.debug("register");
            Debugger.debug("name: " + collAHost.getName() + " nameUser: " + collAHost.getNameUser());

        } catch (SocketException | EOFException | SocketTimeoutException ex) {
            Debugger.debug(ex);
            this.exchangeServers();
            return false;
        } catch (Exception ex) {
            Debugger.debug(ex);
            return false;
        }
        return true;
    }

    //calcular o hexadecimal da senha
    private String byteArrayToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }

    /* método para computar o hash da senha
     * retirado do site http://www.rgagnon.com/javadetails/java-0400.html
     */
    private byte[] computeHash(String x) throws Exception {
        java.security.MessageDigest d;
        d = java.security.MessageDigest.getInstance("SHA-1");
        d.reset();
        d.update(x.getBytes());
        return d.digest();

        /* para usar onde for pegar a senha:
         * String crypt = this.byteArrayToHexString(this.computeHash(senha1));
         */
    }

    public void shutdown() {
        if (this.hostRegisterGUI != null) {
            this.hostRegisterGUI.dispose();
        }
    }

    @Override
    public void run() {
        hostRegisterGUI = new HostViewRegisterGUI(this);
        hostRegisterGUI.setVisible(true);
    }

    private void exchangeServers() {

        String tempIP;
        int tempPort;

        tempIP = this.serverIP;
        tempPort = this.serverPort;

        this.serverIP = this.secondaryServerIP;
        this.serverPort = this.secondaryServerPort;

        this.secondaryServerIP = tempIP;
        this.secondaryServerPort = tempPort;
    }
    private int timeout = 10000;
    private CollAHost collAHost; //usuario que irá logar
    private String serverIP;
    private int serverPort;
    private String secondaryServerIP;
    private int secondaryServerPort;
    private HostViewRegisterGUI hostRegisterGUI;
}