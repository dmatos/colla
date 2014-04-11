package colla.appl.developer_viewer;

import colla.appl.developer_viewer.exceptions.DeveloperConfigurationException;
import colla.appl.developer_viewer.view.CollADeveloperViewerUI;
import colla.appl.developer_viewer.view.DeveloperViewerGUI;
import colla.kernel.api.CollAUser;
import colla.kernel.impl.User;
import colla.kernel.messages.toClient.DeveloperViewerLoginAnswerMsg;
import colla.kernel.messages.toClient.SignUpAnswerMsg;
import colla.kernel.messages.toServer.ClientSignUpMsg;
import colla.kernel.messages.toServer.DeveloperViewerLoginMsg;
import colla.kernel.util.Debugger;
import colla.kernel.util.NetworkDevices;
import colla.kernel.util.ServerConfReader;
import java.io.*;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeoutException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * This class deals with the communications between server and client during
 * login.
 *
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public class DevViewerLogin extends Observable {

    public DevViewerLogin() throws DeveloperConfigurationException {
        this.timeout = 10000;
        this.user = new User();
        this.user.setOffline();
        this.connected = false;
        this.devViewerObservers = new LinkedList<Observer>();
        this.useGUI = false;
        if (!readServerConfigurations()) {
            throw new DeveloperConfigurationException();
        }
        try {
            this.restoredData();
        } catch (Exception ex) {
            Debugger.debug(ex);
        }
    }

    /**
     * Restores previously saved configurations data.
     *
     * @return if data was successfully restored.
     * @throws Exception
     */
    private boolean restoredData() throws Exception {
        //creates a data directory if it does not alredy exists       
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
     * Retrieves the IP address for the established connection.
     *
     * @return if it was able to retrieve the IP address from the network card.
     */
    private boolean retrieveIPaddress() {
        try {
            NetworkDevices netDevices = new NetworkDevices();
            //sets the first IP found
            this.machineIP = netDevices.getIPs().get(0);
            this.user.setIp(this.machineIP);
        } catch (SocketException se) {
            Debugger.debug("exception while searching machine IP address", se);
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
    private String byteArrayToHexString(byte[] b) {
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
    private byte[] computeHash(String x) throws Exception {
        java.security.MessageDigest d;
        d = java.security.MessageDigest.getInstance("SHA-1");
        d.reset();
        d.update(x.getBytes());
        return d.digest();
    }

    /**
     * Connects to the server to sign up a client.
     *
     * @param password password as the client has typed it
     * @return true if username is available, false otherwise
     * @throws Exception
     */
    public boolean signUpForServer(String password) throws IOException, ClassNotFoundException, Exception {
        try {
            String crypt = this.byteArrayToHexString(this.computeHash(password));
            ClientSignUpMsg signupMsg = new ClientSignUpMsg(user, crypt);
            socket = new Socket(InetAddress.getByName(this.serverIP), this.serverPort);
            socket.setSoTimeout(timeout);
            output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(signupMsg);
            output.flush();

            input = new ObjectInputStream(socket.getInputStream());
            SignUpAnswerMsg incoming = (SignUpAnswerMsg) input.readObject();
            socket.close();
            return incoming.getConfirmation();
        } catch (ConnectException | TimeoutException ce) {
            this.exchangeServers();
            throw ce;
        }
    }

    public CollAUser getUser() {
        return this.user;
    }

    /**
     * Connects to the server to execute a login request.
     *
     * @param userName
     * @param password
     * @return a DeveloperViewerController instance if logged in, otherwise
     * returns null.
     * @throws Exception
     */
    public DeveloperViewerController logInServer(String userName, 
            String password) throws IOException, ClassNotFoundException,
            Exception {
        Debugger.debug("logInServer...");
        this.retrieveIPaddress();
        HashMap<String, CollAUser> groupMembers;
        /* sends IP address of the machine to let server check if the IP address
         * is valid. A valid IP address means that the machine can receive
         * outside connections. An invalid IP address means that the machine is
         * not reachable from outside.
         */
        try {
            String crypt = this.byteArrayToHexString(this.computeHash(password));
            DeveloperViewerLoginMsg outgoing = new DeveloperViewerLoginMsg(userName, crypt, machineIP);
            Debugger.debug("Connecting to server at: " + serverIP+" "+serverPort);
            socket = new Socket(InetAddress.getByName(this.serverIP), this.serverPort);
            socket.setSoTimeout(timeout);
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
                    DeveloperViewerController devViewer = 
                            DeveloperViewerController.setupDeveloperController(
                            this.getUser(), this.getServerPort(), 
                            this.getServerIPaddress(), this.secondaryServerPort, 
                            this.secondaryServerIP, groupMembers);
                    for (Observer observer : this.devViewerObservers) {
                        devViewer.addObserver(observer);
                    }
                    if (this.useGUI) {
                        DeveloperViewerGUI gui = DeveloperViewerGUI.getInstance(this.user.getName());
                        this.setDeveloperViewerUI(gui);
                        devViewer.addObserver(gui);
                    }
                    devViewer.setUI(this.devUI);
                    Thread thr = new Thread(devViewer);
                    thr.start();
                    return devViewer;
                }// fim do if
                else {
                    this.displayMessage("Error: incorrect username or password");
                    Debugger.debug("incorrect password");
                }
            }//fim do if
            else {
                this.displayMessage("Error: incorrect username or password");
                Debugger.debug("incorrect username");
            }
        } catch (ConnectException | TimeoutException ex) {
            Debugger.debug(ex);
            this.exchangeServers();
            throw ex;
        }

        return null;
    }// fim do método logInServer

    /**
     * Reads IP address and port number from a xml file to connect with the
     * server.
     *
     * @return true if it was succesfully read, false otherwise
     */
    private boolean readServerConfigurations() {
        ServerConfReader reader = new ServerConfReader();
        try {
            reader.parse("server_conf.xml");
            this.secondaryServerIP = reader.getSecondaryIPAddressFromXML();
            this.secondaryServerPort = reader.getSecondaryPortNumberFromXML();
            this.serverIP = reader.getIPfromXML();
            this.serverPort = reader.getPortNumberFromXML();
        } catch (IOException io) {
            Debugger.debug(io);
            return false;
        } catch (ParserConfigurationException pce) {
            Debugger.debug(pce);
            return false;
        } catch (SAXException sax) {
            Debugger.debug(sax);
            return false;
        }
        return true;
    }

    /**
     * Exhibits a message to the user.
     *
     * @param message message to exhibit.
     */
    public void displayMessage(String message) {
        this.setChanged();
        this.notifyObservers(message);
    }

    /**
     *
     * @param userInterface an UI to be used in DeveloperViewerController, not
     * here in this class!
     */
    public void setDeveloperViewerUI(CollADeveloperViewerUI userInterface) {
        this.devUI = userInterface;
    }

    public void addObserverToDeveloperViewer(Observer observer) {
        this.devViewerObservers.add(observer);
    }

    /**
     *
     * @param useGUI This application will use some UI if true.
     */
    public void useGUI(boolean useGUI) {
        this.useGUI = useGUI;
    }

    /**
     * The IP address is collected from the machine and shouldn't change.
     *
     * @param user
     */
    public void setUser(CollAUser user) {
        user.setIp(this.user.getIp());
        this.user = user;
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
    //Variables declaration
    private String serverIP;    //server IP addres read from server_conf.xml
    private Integer serverPort; //server port number read from server_conf.xml
    private Integer secondaryServerPort;
    private String secondaryServerIP;
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
    private final int timeout;
}
