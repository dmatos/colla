/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.host_viewer;

import colla.kernel.api.CollAHost;
import colla.kernel.impl.Host;

/**
 * Programa principal que verifica se o host já foi cadastrado anteriormente.
 * Caso ele ainda não tenha sido cadastrado, chamamos a classe HostViewRegister
 * que irá efetuar seu cadastro. E no caso de ele já ter sido cadastrado
 * chamamos a classe HostViewer que ficará responsável por se conectar ao
 * servidor.
 *
 * @author Bruno
 */
public class HostViewerStarter{

    public static void main( String args[] ){
        me = new Host();
//        Debug;
//        me.setName( "bruno_0" );
        if( me.getName() != null ){
            hostViewer = new HostViewer( me );
        }else{
            register = new HostViewRegister( me );
        }

    }

    public static void login( CollAHost me ){            
        hostViewer = new HostViewer( me );
        hostViewer.login();
    }

    private static HostViewRegister register;
    private static HostViewer hostViewer;
    private static CollAHost me;
}