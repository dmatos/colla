/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.util;

/**
 * Classe para tratar das exceções capturadas.
 * @author bruno
 */
public class Treater{
    public static void treat(Exception e){
        //Por enquanto, somente um printStackTrace será chamado.
        //e.printStackTrace();
    }
    public static void treat(String s){
        //System.out.println(s);
    }
    public static void treat(String s, Exception e){
        //System.out.println(s);
        //Por enquanto, somente um printStackTrace será chamado.
        //e.printStackTrace();
    }
}// fim da classe Treater
