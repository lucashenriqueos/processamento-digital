package org.lucashos.lena;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) throws IOException {
        //quadrado();
        //escalaCinza();
        //lenaGrayComQuadrado();
        //lenaGrayAbstrata();
        //lenaPretoBranco();
        //lenaMetadeFrequencia();
    	//lenaCinza();
    	lenaColoridona();
    }

    private static void lenaColoridona() throws IOException {
    	BufferedImage image = ImageIO.read(new File("lena_gray_256.png"));
        new LenaGray(image).lenaColoridona(ImageIO.read(new File("referencia.png")));	
	}

	public static void quadrado () throws IOException {
        int height = 200, width = 200;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        new QuadradoColorido(bufferedImage, height, width).desenha();
    }

    public static void escalaCinza() throws IOException {
        int width = 200, height = 20;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        new EscalaCinza(bufferedImage, height, width).desenha();
    }

    public static void lenaGrayComQuadrado() throws IOException {
        BufferedImage image = ImageIO.read(new File("lena_gray_256.png"));
        new LenaGray(image).retanguloSalva();
    }

    public static void lenaGrayAbstrata() throws IOException {
        BufferedImage image = ImageIO.read(new File("lena_gray_256.png"));
        new LenaGray(image).arteAbstrata();
    }

    public static void lenaPretoBranco() throws IOException {
        BufferedImage image = ImageIO.read(new File("lena_gray_256.png"));
        new LenaGray(image).pretoBranco();
    }

    public static void lenaMetadeFrequencia() throws IOException {
        BufferedImage image = ImageIO.read(new File("lena_gray_256.png"));
        new LenaGray(image).metadeFrequencia();
    }
    
    public static void lenaCinza() throws IOException {
        BufferedImage image = ImageIO.read(new File("lena_color_256.png"));
        new LenaGray(image).lenaCinza();
    }
}
