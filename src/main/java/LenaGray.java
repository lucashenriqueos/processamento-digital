import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

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
                    pixels[w * col + row] = Color.BLACK.getRGB();
                }
                else {
                    pixels[w * col + row] = Color.WHITE.getRGB();
                }
            }
        }
        image.setRGB(0, 0, w, h, pixels, 0, w);
        ImageIO.write(image, "PNG", new File("lenaPretoBranco.png"));
    }

    public void metadeFrequencia(){
        System.out.println(image.getData().getNumBands());
    }

	public void lenaCinza() throws IOException {
		int w = image.getWidth(), h = image.getHeight();

        int [] pixels = image.getRGB(0, 0, w, h, null, 0, w);

        for (int row = 0; row < h; row ++){
            for (int col = 0; col < w; col ++){
                int rgb =  image.getRGB(row, col);
                
                int nivel_cinza = (int) (new Color(rgb).getRed() * 0.30 +  new Color(rgb).getGreen() * 0.59 + new Color(rgb).getBlue() * 0.11);
                
                Color cor = new Color(nivel_cinza, nivel_cinza, nivel_cinza);
                
                pixels[w * col + row] = cor.getRGB();
            }
        }
        image.setRGB(0, 0, w, h, pixels, 0, w);
        ImageIO.write(image, "PNG", new File("lenaCinza.png"));		
	}
	
	public void lenaColoridona(BufferedImage referencia) throws IOException{
		int w = image.getWidth(), h = image.getHeight();
		Map<Integer, Color> map = pegarCores(referencia);
		System.out.println("Terminou");

        int [] pixels = image.getRGB(0, 0, w, h, null, 0, w);

        for (int row = 0; row < h; row ++){
            for (int col = 0; col < w; col ++){
                int rgb =  image.getRGB(row, col);                
                Integer nivel_cinza = (int) (new Color(rgb).getRed() * 0.30 +  new Color(rgb).getGreen() * 0.59 + new Color(rgb).getBlue() * 0.11);
                
                pixels[w * col + row] = map.containsKey(nivel_cinza) ? map.get(nivel_cinza).getRGB() : map.get(aproximar(nivel_cinza, map.keySet())).getRGB();
            }
        }
        image.setRGB(0, 0, w, h, pixels, 0, w);
        ImageIO.write(image, "PNG", new File("lena_coloridona.png"));
	}
	
	private Integer aproximar(Integer nivel_cinza, Set<Integer> keySet) {
		for (int i = nivel_cinza, j = 0; i < keySet.size() && j < 20; i++, j++) {
			if(keySet.contains(i))
			{
				return i;
			}
		}
		return null;
	}

	public Map<Integer, Color> pegarCores(BufferedImage referencia){
		int w = referencia.getWidth(), h = referencia.getHeight();
		Map<Integer, Color> map = new HashMap<>();
		
		for (int row = 0; row < h; row ++){
            for (int col = 0; col < w; col ++){
                int rgb =  referencia.getRGB(col, row);
                Integer nivel_cinza = (int) (new Color(rgb).getRed() * 0.30 +  new Color(rgb).getGreen() * 0.59 + new Color(rgb).getBlue() * 0.11);
                Color color = new Color (rgb);
                
                if (!map.containsKey(nivel_cinza)){
                	map.put(nivel_cinza, color);                	
                }
            }
		}
		System.out.println("Terminou Map");
		
		return map;
		
	}
}
