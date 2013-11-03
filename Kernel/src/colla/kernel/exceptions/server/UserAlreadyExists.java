/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.exceptions.server;

/**
 *
 * @author dmatos
 */
public class UserAlreadyExists extends Exception{
    public UserAlreadyExists(String msg){
        super(msg);
    }
}
