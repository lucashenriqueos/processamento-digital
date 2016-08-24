package Exercicios;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by lucas on 23/08/16.
 */
public class QuadradoColorido {
    private Graphics g;
    private int height, width;
    private BufferedImage bufferedImage;

    public QuadradoColorido(BufferedImage bufferedImage, int width, int height){
        this.width = width;
        this.height = height;
        this.bufferedImage = bufferedImage;
        this.g = this.bufferedImage.getGraphics();
    }

    public void desenha() throws IOException {
        int nWidth = width -1, nHeight = height -1;
        g.setColor(Color.blue);
        g.fillRect(0,0,nWidth/2,nHeight/2);
        g.setColor(Color.GREEN);
        g.fillRect(0,nHeight/2,nWidth/2,nHeight);
        g.setColor(Color.RED);
        g.fillRect(nWidth/2,0,nWidth,nHeight/2);
        g.setColor(Color.YELLOW);
        g.fillRect(nWidth/2,nHeight/2,nWidth,nHeight);

        g.setColor(Color.WHITE);
        g.drawRect(20,20,150,150);
        g.drawRect(0,0,nWidth/2,nHeight/2);
        g.drawRect(0,nHeight/2,nWidth/2,nHeight);
        g.drawRect(nWidth/2,0,nWidth,nHeight/2);
        g.drawRect(nWidth/2,nHeight/2,nWidth,nHeight);

        ImageIO.write(bufferedImage, "PNG", new File("imagem.png"));
    }
}
