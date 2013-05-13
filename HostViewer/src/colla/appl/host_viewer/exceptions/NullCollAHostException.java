package colla.appl.host_viewer.exceptions;

/**
 *
 * @author dmatos
 */
public class NullCollAHostException extends Exception{
    
    public NullCollAHostException(){}
    
    @Override
    public String getMessage(){
        return "A CollAHost must be set first.";
    }
}
