package org.lucashos;

import Exercicios.EscalaCinza;
import Exercicios.LenaGray;
import Exercicios.QuadradoColorido;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //quadrado();
        //escalaCinza();
        //lenaGrayComQuadrado();
        //lenaGrayAbstrata();
        //lenaPretoBranco();
        lenaMetadeFrequencia();
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
}
