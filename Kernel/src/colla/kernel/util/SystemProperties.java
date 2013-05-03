/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Enumeration;

/**
 *
 * @author dmatos
 */
public class SystemProperties implements Serializable {

    /**
     * 
     * @return a String containing some system properties
     */
    public String getSystemProperties() {
        String properties = new String("");
//        System.out.println("Total Memory" + Runtime.getRuntime().totalMemory());
//        System.out.println("Free Memory" + Runtime.getRuntime().freeMemory());
        
        properties = "Total Memory: " + Runtime.getRuntime().totalMemory()+"\n";
        properties =  properties + "Free Memory: " + Runtime.getRuntime().freeMemory()+"\n";
        properties = properties + "Avaliable Processors: "+ Runtime.getRuntime().availableProcessors()+"\n";
        properties = properties + "OS: " + System.getProperties().getProperty("os.name") + " "+System.getProperties().getProperty("os.version")+"\n";
        properties = properties + "Arch: " + System.getProperties().getProperty("os.arch") + "\n";
        properties = properties + "Country: " + System.getProperties().getProperty("user.country") + "\n";
       
//        Enumeration<?> e = System.getProperties().propertyNames();
//        while (e.hasMoreElements()) {
//            Object obj = e.nextElement();
//            System.err.println(obj.toString());
//            System.err.println(System.getProperty(obj.toString()));
//            properties = properties + obj.toString()+"\n";
//        }

        return properties;
    }
}
