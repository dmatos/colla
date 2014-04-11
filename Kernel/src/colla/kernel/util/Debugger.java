/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.util;

/**
 *
 * @author dmatos
 */
public class Debugger {

    public static void setDebugger(boolean debug) {
        Debugger.debug = debug;
        if(debug) System.out.println("debug active");
        else System.out.println("...");
    }

    public static void debug(String info, Exception ex) {
        if (Debugger.debug) {
            System.out.println(info);
            if(ex == null){
                
            } else ex.printStackTrace();         
        }
    }

    public static void debug(String info) {
        if (Debugger.debug) {
            System.out.println(info);
        }
    }

    public static void debug(Exception ex) {
        if (Debugger.debug && ex != null) {
            ex.printStackTrace();
        }
    }
    
    public static boolean debug = false;
}
