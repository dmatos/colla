/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.host_viewer.controller;

/**
 * Programa principal que verifica se o host já foi cadastrado anteriormente.
 * Caso ele ainda não tenha sido cadastrado, chamamos a classe HostViewerRegister
 * que irá efetuar seu cadastro. E no caso de ele já ter sido cadastrado
 * chamamos a classe HostViewer que ficará responsável por se conectar ao
 * servidor.
 *
 * @author Bruno
 */
public class HostViewerStarter{

    public static void main( String args[] ){
        HostViewerRegister register = new HostViewerRegister();    
    }
}