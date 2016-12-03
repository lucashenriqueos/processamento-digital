/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lucashos.lena;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import sun.applet.Main;
import org.lucashos.especial.effects.Transformation;

/**
 *
 * @author curso
 */
public class Processamento {

    public static void main(String[] args) {
        //log();
        //pow();
        jack();
    }
    
    public static void log(){
        save(new Transformation(read("Fig0309-a.png")).logarithm(), "logaritmo");
    }
    
    public static void pow(){
        save(new Transformation(read("Fig0309-a.png")).potencia(), "potencia");   
    }
    
    public static void jack(){
        for(int i = 0; i < 8; i++)
        save(new Transformation(read("yumi.png")).fatiamento(i), "velhojack" + i);   
    }
    
    public static BufferedImage read(String img){
        try {
            String imagePath = "/home/lucas/dev/projetos/processamento/src/resources/";
            return ImageIO.read(new File(imagePath + img));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void save(BufferedImage image, String out){
        try {
            ImageIO.write(image, "PNG", new File(out + ".png"));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
