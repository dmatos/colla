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
    }

    public static void debug(String info, Exception ex) {
        if (debug) {
            System.out.println(info);
            ex.printStackTrace();
        }
    }

    public static void debug(String info) {
        if (debug) {
            System.out.println(info);
        }
    }

    public static void debug(Exception ex) {
        if (debug) {
            ex.printStackTrace();
        }
    }
    
    private static boolean debug = false;
}
