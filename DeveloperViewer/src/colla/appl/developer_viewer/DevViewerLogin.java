/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.developer_viewer;

import colla.appl.developer_viewer.GUI.DeveloperViewerGUI;
import colla.kernel.api.CollAUser;
import colla.kernel.impl.User;
import colla.kernel.messages.toClient.DeveloperViewerLoginAnswerMsg;
import colla.kernel.messages.toClient.SignUpAnswerMsg;
import colla.kernel.messages.toServer.ClientSignUpMsg;
import colla.kernel.messages.toServer.DeveloperViewerLoginMsg;
import colla.kernel.util.Debugger.DebugInfo;
import colla.kernel.util.NetworkDevices;
import colla.kernel.util.SAXReader;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.openide.util.Exceptions;
import org.xml.sax.SAXException;

/**
 * Classe responsável pelo login do cliente no servidor
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public class DevViewerLogin extends Observable{
   
    public DevViewerLogin() throws ConfigException{
        this.user = new User();
        this.user.setOffline();
        this.connected = false;
        this.devViewerObservers = new LinkedList<Observer>();
        this.useGUI = false;
        if (!readServerConfigurations()) {
            throw new ConfigException();
        }
        try {
            this.restoredData();
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        this.debugInfo = new DebugInfo();
        this.debugInfo.setDebuggedName("DevviewerLogin");
    }

    /**
     * 
     * @return
     * @throws Exception 
     */
    private boolean restoredData() throws Exception{
        //create data directory if it does not alredy exist
        new File("data/").mkdir();

        if (new File("data/config.data").exists()) {
            //restore clients
            FileInputStream f_in = new FileInputStream("data/config.data");
            this.input = new ObjectInputStream(f_in);
            this.config = (ClientConfigurations) this.input.readObject();
            f_in.close();
            this.user.setPort(this.config.getPortNumber());
            return true;
        }
        return false;
    }

    /**
     *
     * @return true if no exception has been caugth, false otherwise
     */
    private boolean retrieveIPaddress() {
        try {
            NetworkDevices netDevices = new NetworkDevices();
            //sets the first IP found
            this.machineIP = netDevices.getIPs().get(0);
            this.user.setIp(this.machineIP);
        } catch (SocketException se) {
            Logger.getLogger(DevViewerLogin.class.getName()).log(Level.SEVERE, "exception while searching machine IP address", se);
            return false;
        }
        return true;
    }  

    public String getServerIPaddress() {
        return this.serverIP;
    }

    public Integer getServerPort() {
        return this.serverPort;
    }

    /**
     *
     * @param b bytecode for password
     * @return hexdecimal formated password
     */
    public String byteArrayToHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * computes hash to encrypt password
     *
     * @return bytecode for password
     */
    public byte[] computeHash(String x) throws Exception {
        java.security.MessageDigest d;
        d = java.security.MessageDigest.getInstance("SHA-1");
        d.reset();
        d.update(x.getBytes());
        return d.digest();
    }

    /**
     * Connects to the server to sign up for him
     *
     * @param password password as the client has typed it
     * @return true if name is available, false otherwise
     * @throws Exception
     */
    public boolean signUpForServer(String password) throws Exception {
        String crypt = this.byteArrayToHexString(this.computeHash(password));
        ClientSignUpMsg signupMsg = new ClientSignUpMsg(user, crypt);
        socket = new Socket(InetAddress.getByName(this.serverIP), this.serverPort);
        socket.setSoTimeout(40000);
        output = new ObjectOutputStream(socket.getOutputStream());
        output.writeObject(signupMsg);
        output.flush();

        input = new ObjectInputStream(socket.getInputStream());
        SignUpAnswerMsg incoming = (SignUpAnswerMsg) input.readObject();
        socket.close();
        return incoming.getConfirmation();
    }

    public CollAUser getUser() {
        return this.user;
    }
    
    /**
     * Envia solicitação para se logar no servidor.
     * @param userName o nome do usuário que deseja se conectar
     * @param password o password do usuário que deseja se conectar
     * @return um DeveloperViewre se conseguiu logar, null caso contrário
     * @throws Exception 
     */
    public DeveloperViewer logInServer(String userName, String password) throws Exception {
        this.retrieveIPaddress();
        HashMap<String, CollAUser> groupMembers;

        /* sends IP address of the machine to let server check if the IP address
         * is valid. A valid IP address means that the machine can receive
         * outside connections. An invalid IP address means that the machine is
         * not reachable from outside.
         */
        String crypt = this.byteArrayToHexString(this.computeHash(password));
        DeveloperViewerLoginMsg outgoing = new DeveloperViewerLoginMsg(userName, crypt, machineIP);
        socket = new Socket(InetAddress.getByName(this.serverIP), this.serverPort);
        socket.setSoTimeout(40000);
        output = new ObjectOutputStream(socket.getOutputStream());
        output.writeObject(outgoing);
        output.flush();
        
        input = new ObjectInputStream(socket.getInputStream());
        DeveloperViewerLoginAnswerMsg incoming = (DeveloperViewerLoginAnswerMsg) input.readObject();
        socket.close();
        //System.out.println("login pela porta: "+sock.getLocalPort());        
        
        if (incoming.getNameConfirmation()) {
            if (incoming.getPassConfirmation()) {
                this.displayMessage("Receiving data from server.");
                groupMembers = incoming.getContacts();
                this.user = incoming.getUser();
                this.user.setIp(this.machineIP);
                DeveloperViewer devViewer = new DeveloperViewer(this.getUser(), this.getServerPort(), this.getServerIPaddress(), groupMembers);
                for(Observer observer : this.devViewerObservers){
                    devViewer.addObserver(observer);
                }
                if(this.useGUI){
                    DeveloperViewerGUI gui = new DeveloperViewerGUI(devViewer, this.user.getName());
                    this.setDeveloperViewerUI(gui);
                    devViewer.addObserver(gui);
                }
                devViewer.setUI(this.devUI);
                Thread thr = new Thread(devViewer);
                thr.start();
                return devViewer;
            }// fim do if
            else{
                this.displayMessage("Error: incorrect username or password");
            }
        }//fim do if
        else {
            this.displayMessage("Error: incorrect username or password");
        }
        return null;
    }// fim do método logInServer

    /**
     * Reads IP address and port number from a xml file to connect with the server 
     *
     * @return true if it was succesfully read, false otherwise
     */
    private boolean readServerConfigurations() {
        SAXReader reader = new SAXReader();
        try {
            reader.parse("server_conf.xml");
            this.serverIP = reader.getIPfromXML();
            this.serverPort = reader.getPortNumberFromXML();
        } catch (IOException io) {
            this.debugInfo.clear();
            this.debugInfo.setException(io);
            this.setChanged();
            this.notifyObservers(this.debugInfo);
            io.printStackTrace();            
            return false;
        } catch (ParserConfigurationException pce) {
            this.debugInfo.clear();
            this.debugInfo.setException(pce);
            this.setChanged();
            this.notifyObservers(this.debugInfo);
            pce.printStackTrace();
            return false;
        } catch (SAXException sax) {
             this.debugInfo.clear();
            this.debugInfo.setException(sax);
            this.setChanged();
            this.notifyObservers(this.debugInfo);
            sax.printStackTrace();
            return false;
        }
        return true;
    }  
    
    
    public void displayMessage(String message){
        this.setChanged();
        this.notifyObservers(message);
    }
    
    
    /**
     * 
     * @param userInterface an UI to be used in DeveloperViewer, not here in this class!
     */
    public void setDeveloperViewerUI(CollADeveloperViewerUI userInterface){
        this.devUI = userInterface;
    }
    
    public void addObserverToDeveloperViewer(Observer observer){
        this.devViewerObservers.add(observer);
    }   
    
    
    /**
     * 
     * @param useUI This application will use some UI if true.
     */
    public void useGUI(boolean useGUI){
        this.useGUI = useGUI;
    }
    
    //Variables declaration
    private String serverIP;    //server IP addres read from server_conf.xml
    private Integer serverPort; //server port number read from server_conf.xml
    private String machineIP;   //IP address of the machine where this code is running
    private CollAUser user;     //the user itself
    private Boolean connected;  //boolean for confirmation of connection with server    
    private Integer portNumber;
    private ClientConfigurations config;
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;    
    private List<Observer> devViewerObservers;
    private CollADeveloperViewerUI devUI = null;
    private boolean useGUI;
    private DebugInfo debugInfo;

}
