/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.host_viewer;

import colla.appl.host_viewer.GUI.HostViewRegisterGUI;
import colla.kernel.api.CollAHost;
import colla.kernel.messages.toHost.ServerHostRegisterAnswerMsg;
import colla.kernel.messages.toServer.HostRegisterMsg;
import colla.kernel.util.SAXReader;
import java.io.*;
import java.net.*;
import java.util.Collections;
import java.util.Enumeration;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * Classe responsável por efetuar o registro do host no servidor.
 * @author Bruno
 */
public class HostViewRegister{

    public HostViewRegister( CollAHost host ){
        try{
            this.me = host;
            SAXReader reader = new SAXReader();
            reader.parse( "server_conf.xml" );
            serverIP = reader.getIPfromXML();
            serverPort = reader.getPortNumberFromXML();

            /*
             * loop que procura o endereço ip do usuário
             * vasculhando as interfaces de rede
             */
            Enumeration<NetworkInterface> nets =
                                          NetworkInterface.getNetworkInterfaces();

            for( NetworkInterface netint : Collections.list( nets ) ){
                Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
                for( InetAddress inetAddress : Collections.list( inetAddresses ) ){
                    if( !inetAddress.isLoopbackAddress()
                        && !inetAddress.toString().substring( 1 ).startsWith( "127" )
                        && inetAddress.toString().substring( 1 ).contains( "." ) ){
                        me.setIp( inetAddress.toString().substring( 1 ) );
                    }
                }
            }
            hostRegisterGUI = new HostViewRegisterGUI( this );
            hostRegisterGUI.setVisible( true );            
        }catch( ParserConfigurationException ex ){
            //ex.printStackTrace();
        }catch( SAXException ex ){
            //ex.printStackTrace();
        }catch( IOException ex ){
            //ex.printStackTrace();
        }
    }

    /**
     * Registers the host on server.
     * @param userName the name of the user who owns the host
     * @param pass the pass of the user who owns the host
     * @param port the port for register
     */
    public boolean register( String userName, String pass, int port ){
        try{
            String crypt = this.byteArrayToHexString( this.computeHash( pass ) );

            HostRegisterMsg outgoing = new HostRegisterMsg();

            //enviar user
            outgoing.setUserName( userName );

            //enviar senha encriptada
            outgoing.setUserPass( crypt );

            me.setPort( port );
            //enviar host
            outgoing.setHost( me );

            Socket connect = new Socket( InetAddress.getByName( serverIP ), serverPort );
            connect.setSoTimeout( 0 );
            ObjectOutputStream output = new ObjectOutputStream( connect.getOutputStream() );
            output.writeObject( outgoing );
            output.flush();                      
            
            //receive the host name
            ObjectInputStream input = new ObjectInputStream( connect.getInputStream() );
            ServerHostRegisterAnswerMsg incoming = ( ServerHostRegisterAnswerMsg ) input.readObject();
            connect.close();
            
            if(incoming.getHostName() == null){
                //System.err.println("Invalid username or password");
                return false;
            } else {
                me.setName( incoming.getHostName() );
                me.setNameUser( userName );
                this.hostRegisterGUI.dispose();
                HostViewerStarter.login(me);
            }
           // System.err.println("register");
           // System.err.println("name: " + me.getName() + " nameUser: " + me.getNameUser());
            
        }catch( Exception ex ){
            //ex.printStackTrace();
            return false;
        }
        return true;
    }

//calcular o hexadecimal da senha
    public String byteArrayToHexString( byte[] b ){
        StringBuffer sb = new StringBuffer( b.length * 2 );
        for( int i = 0; i < b.length; i++ ){
            int v = b[i] & 0xff;
            if( v < 16 ){
                sb.append( '0' );
            }
            sb.append( Integer.toHexString( v ) );
        }
        return sb.toString().toUpperCase();
    }

    /* método para computar o hash da senha
     * retirado do site http://www.rgagnon.com/javadetails/java-0400.html
     */
    public byte[] computeHash( String x ) throws Exception{
        java.security.MessageDigest d;
        d = java.security.MessageDigest.getInstance( "SHA-1" );
        d.reset();
        d.update( x.getBytes() );
        return d.digest();

        /* para usar onde for pegar a senha:
         * String crypt = this.byteArrayToHexString(this.computeHash(senha1));
         */
    }

    private CollAHost me; //usuario que irá logar
    private String serverIP;
    private int serverPort;
    private HostViewRegisterGUI hostRegisterGUI;
}