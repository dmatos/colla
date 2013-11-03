/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author dmatos
 */
public class LogWriter {
    public static void generateLog(String arg) {
        BufferedWriter writer = null;
        try {
            //create a temporary file
            String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            File logFile = new File(timeLog+".log");

            // This will output the full path where the file will be written to...
            System.out.println(logFile.getCanonicalPath());

            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write(arg);
        } catch (Exception e) {
            //e.printStackTrace();
            System.err.println("Could not write log file.");
        } finally {
            try {
                // Close the writer regardless of what happens
                if(writer != null)
                    writer.close();
            } catch (Exception e) {
            }
        }
    }
}
