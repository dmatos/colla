/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testenogui;

import colla.kernel.util.Debugger.DebugInfo;
import java.io.*;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public class CollATesterObserver implements Observer{
    private File saida, exception;
    private FileWriter saidaOut, exceptionOut;
    private PrintWriter saidaPrint, exceptionPrint;

    public CollATesterObserver(){
        saida = new File( "saida.txt" );
        exception = new File("exception.txt");
        try{
            saidaOut = new FileWriter( saida );
            exceptionOut = new FileWriter( exception );
            saidaPrint = new PrintWriter( saidaOut );
            exceptionPrint = new PrintWriter( exceptionOut );
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable subject, Object interest) {
        if(interest instanceof DebugInfo){
            DebugInfo debug = (DebugInfo) interest;
            if(!debug.getInfo().isEmpty()){
                saidaPrint.println(debug.getDebuggedName() + "-" + debug.getInfo());
                saidaPrint.flush();
            }
            if(debug.getException() != null){
                exceptionPrint.println( debug.getDebuggedName() + "-" + debug.getException().toString() );
                for( StackTraceElement element : debug.getException().getStackTrace()){
                    exceptionPrint.println(element.toString());
                }
                    exceptionPrint.flush();
            }
        }
    }
}
