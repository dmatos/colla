/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.exceptions.server;

/**
 *
 * @author dmatos
 */
public class NonExistentUser extends Exception{
    public NonExistentUser(String msg){
        super(msg);
    }
}
