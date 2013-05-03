/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testenogui;

import java.util.Observer;

/**
 *
 * @author bruno
 */
public class TesteNoGUI{

    public static void main( String[] args ) throws InterruptedException{
        Observer testerObserver = new CollATesterObserver();
        Tester tester;        
        Thread t;
        
        tester = new Tester( 1, true, 691200 );
        tester.addObserver(testerObserver);
        t = new Thread(tester);
        t.start();
//        Pra testes de brinquedo!
        for( int i = 1; i < 3; i++ ){
            tester = new Tester( i, true, 100000000);
            t = new Thread( tester );
            t.start();
        }
        
//        Pra teste capetera
        
//        long hours = 1;
//        long minutes = hours * 60;
//        long seconds = minutes * 60;
//        long lifeTime = seconds * 1000;
//        for( int i = 10; i < 15; i++ ){
//            tester = new Tester( i, true, lifeTime);
//            tester.addObserver(testerObserver);
//            t = new Thread( tester );
//            t.start();
//            Thread.sleep(1000);
//        }
        /*
        hours = 2;
        minutes = hours * 60;
        seconds = minutes * 60;
        lifeTime = seconds * 1000;
        for( int i = 20; i < 30; i++ ){
            tester = new Tester( i, true, lifeTime);
            tester.addObserver(testerObserver);
            t = new Thread( tester );
            t.start();
        }
        
        hours = 3;
        minutes = hours * 60;
        seconds = minutes * 60;
        lifeTime = seconds * 1000;
        for( int i = 30; i < 40; i++ ){
            tester = new Tester( i, true, lifeTime);
            tester.addObserver(testerObserver);
            t = new Thread( tester );
            t.start();
        }
        
        hours = 4;
        minutes = hours * 60;
        seconds = minutes * 60;
        lifeTime = seconds * 1000;
        for( int i = 40; i < 50; i++ ){
            tester = new Tester( i, true, lifeTime);
            tester.addObserver(testerObserver);
            t = new Thread( tester );
            t.start();
        }
        
        hours = 5;
        minutes = hours * 60;
        seconds = minutes * 60;
        lifeTime = seconds * 1000;
        for( int i = 50; i < 60; i++ ){
            tester = new Tester( i, true, lifeTime);
            tester.addObserver(testerObserver);
            t = new Thread( tester );
            t.start();
        }
        
        hours = 6;
        minutes = hours * 60;
        seconds = minutes * 60;
        lifeTime = seconds * 1000;
        for( int i = 60; i < 70; i++ ){
            tester = new Tester( i, true, lifeTime);
            tester.addObserver(testerObserver);
            t = new Thread( tester );
            t.start();
        }
        
        hours = 7;
        minutes = hours * 60;
        seconds = minutes * 60;
        lifeTime = seconds * 1000;
        for( int i = 70; i < 80; i++ ){
            tester = new Tester( i, true, lifeTime);
            tester.addObserver(testerObserver);
            t = new Thread( tester );
            t.start();
        }
        
        hours = 8;
        minutes = hours * 60;
        seconds = minutes * 60;
        lifeTime = seconds * 1000;
        for( int i = 80; i <= 90; i++ ){
            tester = new Tester( i, true, lifeTime);
            tester.addObserver(testerObserver);
            t = new Thread( tester );
            t.start();
        }        
        * 
        */
    }
     
}
