/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lucashos.especial.effects;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author curso
 */
public class Transformation {
    BufferedImage image;
    int w, h;
    int[] pixels;

    public Transformation(BufferedImage image) {
        this.image = image;
        this.w = image.getWidth();
        this.h = image.getHeight();
        this.pixels = image.getRGB(0, 0, w, h, null, 0, w);
    }

    public BufferedImage logarithm(){
        int c = 60;
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                int rgb = image.getRGB(col, row);
                Integer nivel_cinza = (int) new Color(rgb).getRed();
                int cinza = (int) Math.round(c * Math.log10(1 + nivel_cinza));

                pixels[w * row + col] = new Color(cinza,cinza,cinza).getRGB();
            }
        }
        image.setRGB(0, 0, w, h, pixels, 0, w);
        return image;
    }

    public BufferedImage potencia(){
        Double c = 1.0/60000;
        int w = image.getWidth(), h = image.getHeight();
        int[] pixels = image.getRGB(0, 0, w, h, null, 0, w);

        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                int rgb = image.getRGB(col, row);
                Integer nivel_cinza = new Color(rgb).getRed();
                int cinza = (int) (c * Math.pow(nivel_cinza, 3));
                cinza = cinza > 255 ? 255 : cinza;
                pixels[w * row + col] = new Color(cinza,cinza,cinza).getRGB();
            }
        }
        image.setRGB(0, 0, w, h, pixels, 0, w);
        return image;
    }

    public BufferedImage fatiamento(int i){
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                int rgb = image.getRGB(col, row);
                Integer nivel_red = new Color(rgb).getRed();
                Integer nivel_green = new Color(rgb).getGreen();
                Integer nivel_blue = new Color(rgb).getBlue();

                char[] bytes = balanceString(Integer.toBinaryString(nivel_red), 8).toCharArray();
                nivel_red =  bytes[i] == '0' ? 0 : 255;

                bytes = balanceString(Integer.toBinaryString(nivel_green), 8).toCharArray();
                nivel_green =  bytes[i] == '0' ? 0 : 255;

                bytes = balanceString(Integer.toBinaryString(nivel_blue), 8).toCharArray();
                nivel_blue =  bytes[i] == '0' ? 0 : 255;

                pixels[w * row + col] = new Color(nivel_red,nivel_green,nivel_blue).getRGB();
            }
        }
        image.setRGB(0, 0, w, h, pixels, 0, w);
        return image;
    }

    public void reconstruir(){

    }

    public void histograma(){
        int[] map = map(256);
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                int rgb = image.getRGB(col, row);
                Integer cinza = new Color(rgb).getRed();
                cinza = (int) Math.round(equalizacao(cinza, map));
                pixels[w * row + col] = new Color(cinza,cinza,cinza).getRGB();
            }
        }
        image.setRGB(0, 0, w, h, pixels, 0, w);
    }

    public void realce(){

    }

    public void senoide(){

    }

    public void FFT1D() {
    }

    public void FFT2D(){

    }

    public void IFFT2D(){

    }

    private static String balanceString(String s, int radix) {
        int diff = radix - s.length();

        if (diff > 0) {
            while (diff-- > 0) {
                s = "0".concat(s);
            }
        }
        return s;
    }



    private Double equalizacao(Integer k, int[] map){
        Double somatoria = 0.;
        for (int i = 0; i <= k; i++) {
            somatoria += prob(i, map);
        }
        return (map.length - 1) * somatoria;
    }

    private Double prob(Integer nivel, int[] map) {
        return (double) map[nivel] / (w * h);
    }

    private int[] map(int niveis) {
        int[] map = new int[niveis];

        for(int i = 0; i < h * w; i++){
            map[new Color(pixels[i]).getRed()]++;
        }
        return map;
    }
}
