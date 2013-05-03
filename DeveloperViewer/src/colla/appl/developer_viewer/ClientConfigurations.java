
package colla.appl.developer_viewer;

import java.io.Serializable;

/**
 * @author Diogo Matos <dmatos88 at gmail.com>
 * @version 1.0
 *
 * This class is responsable to configure portnumber and
 * the directory where the results of submitted tasks must be saved
 */
public class ClientConfigurations implements Serializable{
    private Integer portNumber;            
    private String resultsStoreDir;
    private String nonFileResultsDir;
    
    /**
     *
     */
    public ClientConfigurations(){
        this.portNumber = -1;
        this.resultsStoreDir = "results/";
        this.nonFileResultsDir = "results/objects/";
    }
    
    /**
     * 
     * @param port port number set by client
     */
    public void setPortNumber(Integer port){
        this.portNumber = port;
    }
    
    /**
     * 
     * @return port number set by client
     */
    public Integer getPortNumber(){
        return this.portNumber;
    }
    
    /**
     * method to set the task result directory. Only results of file type
     * will be saved
     *
     * @param dir where CollA must save the task result
     */
    public void setResultDir(String dir){
        this.resultsStoreDir = dir;
    }
    
    /**
     * gets the definied result directory, where files are saved
     * @return the directory path
     */
    public String getResulstDir(){
        return this.resultsStoreDir;
    }
    
    /**
     * method to set the task result directory. Only results of non file type
     * will be saved
     *
     * @param dir the directory path
     */
    public void setNonFileResultDir(String dir){
        this.nonFileResultsDir = dir;
    }
    
    /**
     * gets the directory path
     * @return the directory path
     */
    public String getNonFileResulstDir(){
        return this.nonFileResultsDir;
    }
}
