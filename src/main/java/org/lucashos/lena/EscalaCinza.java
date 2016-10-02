package org.lucashos.lena;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by lucas on 23/08/16.
 */
public class EscalaCinza {
    private BufferedImage bufferedImage;
    private int height, width;
    private Graphics g;

    public EscalaCinza(BufferedImage bufferedImage, int height, int width) {
        this.bufferedImage = bufferedImage;
        this.g = this.bufferedImage.getGraphics();
        this.height = height;
        this.width = width;
    }

    public void desenha() throws IOException {
        int RGB = 255;
        int rgbRatio = 255 / 10;
        int size = 20;

        int x = 0;
        while (RGB > 255/size){
            g.setColor(new Color(RGB, RGB, RGB));
            g.fillRect(x,0,x+size,20);

            x += size;
            RGB -= rgbRatio;
        }

        ImageIO.write(bufferedImage, "PNG", new File("imagem.png"));
    }
}
