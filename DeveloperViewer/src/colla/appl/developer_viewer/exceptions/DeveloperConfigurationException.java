/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.developer_viewer.exceptions;

/**
 *
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public class DeveloperConfigurationException extends Exception{    
    
    @Override
    public String toString(){
        return "Could not find file server_conf.xml which must be in the same directory that this application";
    }
}
