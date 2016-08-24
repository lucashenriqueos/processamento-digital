package Exercicios;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by lucas on 23/08/16.
 */
public class LenaGray extends JFrame{
    BufferedImage image;
    Graphics g;

    public LenaGray(BufferedImage image) {
        this.image = image;
        this.g = image.getGraphics();
    }

    private void plotImage(BufferedImage imagem){
        String infoImagem =  "Dimensões: " + imagem.getWidth() + "x" + imagem.getHeight() + "Bandas: " + imagem.getRaster().getNumBands();

        ImageIcon icon = new ImageIcon(imagem);
        JLabel label = new JLabel(icon);
        JFrame frame = new JFrame("Display da imagem");
        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(new JScrollPane(label),BorderLayout.CENTER);
        container.add(new JLabel(infoImagem), BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(imagem.getWidth()+20, imagem.getHeight()+50);

        frame.setVisible(true);
    }

    public void desenha(){
        plotImage(image);
    }

    public void retanguloSalva() throws IOException {
        g.setColor(Color.WHITE);
        g.drawRect(20,20,100,100);
        
        ImageIO.write(image, "PNG", new File("lena_mudada.png"));

        plotImage(image);
    }

    public void arteAbstrata() throws IOException {
        int w = image.getWidth(), h = image.getHeight();

        int [] pixels = image.getRGB(0, 0, w, h, null, 0, w);
        Random rdm = new Random();

        for (int col = 0; col < w; col ++){
            for (int row = 0; row < h; row ++){
                    if (row % 2 == 0){
                        pixels[w * row + col] = new Color(rdm.nextInt(255), col % 255, row % 255).getRGB();
                    }
            }
        }
        image.setRGB(0, 0, w, h, pixels, 0, w);
        ImageIO.write(image, "PNG", new File("lenaAbstrata.png"));
    }

    public void pretoBranco() throws IOException {
        int w = image.getWidth(), h = image.getHeight();

        int [] pixels = image.getRGB(0, 0, w, h, null, 0, w);

        for (int col = 0; col < w; col ++){
            for (int row = 0; row < h; row ++){
                int rgb =  image.getRGB(row, col);
                if (rgb < (new Color(127,127,127).getRGB())){
                    pixels[w * row + col] = Color.BLACK.getRGB();
                }
                else {
                    pixels[w * row + col] = Color.WHITE.getRGB();
                }
            }
        }
        image.setRGB(0, 0, w, h, pixels, 0, w);
        ImageIO.write(image, "PNG", new File("lenaPretoBranco.png"));
    }

    public void metadeFrequencia(){
        System.out.println(image.getData().getNumBands());
    }
}
