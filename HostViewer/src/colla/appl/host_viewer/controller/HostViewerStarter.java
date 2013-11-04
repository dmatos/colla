package colla.appl.host_viewer.controller;

import colla.kernel.util.Debugger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Programa principal que verifica se o host já foi cadastrado anteriormente.
 * Caso ele ainda não tenha sido cadastrado, chamamos a classe HostViewerRegister
 * que irá efetuar seu cadastro. E no caso de ele já ter sido cadastrado
 * chamamos a classe HostViewer que ficará responsável por se conectar ao
 * servidor.
 *
 * @author Bruno
 */
public class HostViewerStarter{

    public static void main( String args[] ){ 
        Debugger.setDebugger(false);
        for(int i = 0; i < args.length; i++){
            if(args[i].equals("--debug")){
                Debugger.setDebugger(true);
            }
        }
        HostViewerRegister register = new HostViewerRegister(); 
        Thread thr = new Thread(register);
        thr.start();             
    }
}