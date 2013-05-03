/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.util.Debugger;

/**
 *
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public class DebugInfo {

    public String getDebuggedName() {
        return debuggedName;
    }

    public void setDebuggedName(String debuggedName) {
        this.debuggedName = debuggedName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
    
    public void clear(){
        this.exception = null;
        this.info = "";
    }
    
    
    String debuggedName;
    String info;
    Exception exception;    
}
