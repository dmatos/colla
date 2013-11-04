package colla.appl.developer_viewer;

import colla.appl.developer_viewer.exceptions.DeveloperConfigurationException;
import colla.appl.developer_viewer.view.Client_Login;
import colla.kernel.util.Debugger;
import javax.swing.JOptionPane;

/**
 * Contains the main method to start the DeveloperViewer.
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public class DeveloperViewerStarter{

    /**
     * @param args
*
     * -noX set use of CLI instead of GUI (not implemented yet)
     *
     */
    public static void main( String args[] ){

        boolean useGUI = true;
        Debugger.setDebugger(false);

        for( int i = 0; i < args.length; i++ ){
            if( args[i].equals( "--noX" ) ){
                useGUI = false;
            }
            if( args[i].equals("--debug")){
                Debugger.setDebugger(true);
            } 
        }

        try{
            DevViewerLogin devViewerLogin = new DevViewerLogin();
            devViewerLogin.useGUI( useGUI );

            if( useGUI ){
                Client_Login loginGUI = new Client_Login( devViewerLogin );
                devViewerLogin.addObserver( loginGUI );
            }

            /**
             * In order to attach an observer to DeveloperViewer application,
             * uncomment the following line of code and insert a
             * java.util.Observer as argument. To attach more observers just
             * copy the following line as many times as you want.
             */
            //devViewerLogin.addObserverToDeveloperViewer(arg);
        }catch( DeveloperConfigurationException cex ){
            if( useGUI ){
                JOptionPane.showMessageDialog( null, "Could not read file server_conf.xml.", "Error", JOptionPane.ERROR_MESSAGE );
            }else{
                Debugger.debug(cex.toString());
            }
            System.exit( 1 );
        }
    }
}
