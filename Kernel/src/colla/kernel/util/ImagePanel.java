/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author dmatos
 */
public class ImagePanel extends JPanel{
    
	private static final long serialVersionUID = 1L;
	private Image image;
    
    public ImagePanel(){
        this.image = null;
    }
    
    public ImagePanel(String imageSource){
        ImageIcon temp = new ImageIcon(getClass().getResource(imageSource));
        image = temp.getImage();
    }
    
    public void setBackGround(String imageSource){
        ImageIcon temp = new ImageIcon(getClass().getResource(imageSource));
        image = temp.getImage();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if(image != null){
            g2d.drawImage(image, 0, 0,this.getWidth(), this.getHeight(), this);        
        }
    }
    
}
