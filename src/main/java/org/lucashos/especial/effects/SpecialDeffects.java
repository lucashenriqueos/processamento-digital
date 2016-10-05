package org.lucashos.especial.effects;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;

import javax.swing.JFrame;

/**
 * Created by lucas on 23/08/16.
 */
public class SpecialDeffects extends JFrame {
	private static final long serialVersionUID = 5478972601424351307L;
	BufferedImage image;

	public SpecialDeffects(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage arteAbstrata() {
		int w = image.getWidth(), h = image.getHeight();

		int[] pixels = image.getRGB(0, 0, w, h, null, 0, w);
		Random rdm = new Random();

		for (int col = 0; col < w; col++) {
			for (int row = 0; row < h; row++) {
				if (row % 2 == 0) {
					pixels[w * row + col] = new Color(rdm.nextInt(255), col % 255, row % 255).getRGB();
				}
			}
		}
		image.setRGB(0, 0, w, h, pixels, 0, w);

		return image;
	}

	public BufferedImage pretoBranco() {
		int w = image.getWidth(), h = image.getHeight();

		int[] pixels = image.getRGB(0, 0, w, h, null, 0, w);

		for (int col = 0; col < w; col++) {
			for (int row = 0; row < h; row++) {
				int rgb = image.getRGB(row, col);
				if (rgb < (new Color(127, 127, 127).getRGB())) {
					pixels[w * col + row] = Color.BLACK.getRGB();
				} else {
					pixels[w * col + row] = Color.WHITE.getRGB();
				}
			}
		}
		image.setRGB(0, 0, w, h, pixels, 0, w);
		return image;
	}

	public BufferedImage tonsDeCinza() {
		int w = image.getWidth(), h = image.getHeight();

		int[] pixels = image.getRGB(0, 0, w, h, null, 0, w);

		for (int row = 0; row < h; row++) {
			for (int col = 0; col < w; col++) {
				int rgb = image.getRGB(row, col);

				int nivel_cinza = (int) (new Color(rgb).getRed() * 0.30 + new Color(rgb).getGreen() * 0.59
						+ new Color(rgb).getBlue() * 0.11);

				Color cor = new Color(nivel_cinza, nivel_cinza, nivel_cinza);

				pixels[w * col + row] = cor.getRGB();
			}
		}
		image.setRGB(0, 0, w, h, pixels, 0, w);
		return image;
	}

	public BufferedImage tonsDeCinza(int ntons) {
		int limiar = 256 / ntons;

		int w = image.getWidth(), h = image.getHeight();

		int[] pixels = image.getRGB(0, 0, w, h, null, 0, w);

		for (int row = 0; row < h; row++) {
			for (int col = 0; col < w; col++) {
				int rgb = image.getRGB(row, col);
				int newRed = ((new Color(rgb).getRed() / limiar)) * limiar;
				int newGreen = ((new Color(rgb).getGreen() / limiar)) * limiar;
				int newBlue = ((new Color(rgb).getBlue() / limiar)) * limiar;

				newRed = newRed > 255 ? 255 : newRed;
				newGreen = newGreen > 255 ? 255 : newGreen;
				newBlue = newBlue > 255 ? 255 : newBlue;

				int nivel_cinza = (int) (newRed * 0.30 + newGreen * 0.59 + newBlue * 0.11);

				Color cor = new Color(nivel_cinza, nivel_cinza, nivel_cinza);

				pixels[w * col + row] = cor.getRGB();
			}
		}
		image.setRGB(0, 0, w, h, pixels, 0, w);
		return image;
	}

	public BufferedImage tonsDeCinza(String channel) {
		int w = image.getWidth(), h = image.getHeight();

		int[] pixels = image.getRGB(0, 0, w, h, null, 0, w);

		for (int row = 0; row < h; row++) {
			for (int col = 0; col < w; col++) {
				int rgb = image.getRGB(row, col);
				int nivel_cinza = 0;
				if (channel.equals("R")) {
					nivel_cinza = (int) (new Color(rgb).getRed() * 0.30 + new Color(rgb).getRed() * 0.30
							+ new Color(rgb).getRed() * 0.30);
				} else if (channel.equals("G")) {
					nivel_cinza = (int) (new Color(rgb).getGreen() * 0.59);
				} else if (channel.equals("B")) {
					nivel_cinza = (int) (new Color(rgb).getBlue() * 0.11);
				}

				Color cor = new Color(nivel_cinza, nivel_cinza, nivel_cinza);

				pixels[w * col + row] = cor.getRGB();
			}
		}
		image.setRGB(0, 0, w, h, pixels, 0, w);
		return image;
	}

	public BufferedImage resize(Double scale) {
		int scaledH = (int) (image.getHeight() * scale);
		int scaledW = (int) (image.getWidth() * scale);
		int scaledSize = scaledH * scaledW;

		int w = image.getWidth(), h = image.getHeight();

		int[] pixels = image.getRGB(0, 0, w, h, null, 0, w);

		int[] scaledPixels = new int[scaledSize];

		for (int row = 0; row < scaledH; row++) {
			for (int col = 0; col < scaledW; col++) {

				int rgb = image.getRGB(row * h / scaledH, col * w / scaledW);

				scaledPixels[scaledW * col + row] = rgb;
			}
		}

		BufferedImage img = new BufferedImage(scaledW, scaledH, BufferedImage.TYPE_INT_BGR);
		img.setRGB(0, 0, scaledW, scaledH, scaledPixels, 0, scaledW);

		return img;
	}

	public BufferedImage colorize(BufferedImage referencia) {
		int w = image.getWidth(), h = image.getHeight();
		Map<Integer, Color> map = pegarCores(referencia);
		System.out.println("Terminou");

		int[] pixels = image.getRGB(0, 0, w, h, null, 0, w);

		for (int row = 0; row < h; row++) {
			for (int col = 0; col < w; col++) {
				int rgb = image.getRGB(row, col);
				Integer nivel_cinza = (int) (new Color(rgb).getRed() * 0.30 + new Color(rgb).getGreen() * 0.59
						+ new Color(rgb).getBlue() * 0.11);

				pixels[w * col + row] = map.containsKey(nivel_cinza) ? map.get(nivel_cinza).getRGB()
						: map.get(aproximar(nivel_cinza, map.keySet())).getRGB();
			}
		}
		image.setRGB(0, 0, w, h, pixels, 0, w);
		return image;
	}

	@SuppressWarnings("unused")
	private void subtracao() {
		// d(x,y) = f(x,y) - g(x,y)
	}

	@SuppressWarnings(value = { "unused" })
	private void multiplicacao() {
		// d(x,y) = f(x,y) * g(x,y)
	}

	public BufferedImage contraste(Integer K, Integer E) {
		System.out.println("Contraste");
		int w = image.getWidth(), h = image.getHeight();

		int[] pixels = image.getRGB(0, 0, w, h, null, 0, w);

		/*for (int row = 0; row < h; row++) {
			for (int col = 0; col < w; col++) {	
				int rgb = image.getRGB(col, row);
				
				pixels[w * col + row] = 1 / (1 + (K / rgb) ^ E);	
			}
		}*/

		for(int i = 0; i < pixels.length; i++) {
			Double pixel = Double.parseDouble(String.valueOf(pixels[i]));
			pixels[i] = (int) (1 / (1 + Math.pow((K / pixel), E)));
		}
		image.setRGB(0, 0, w, h, pixels, 0, w);
		return image;
	}

	public Map<String, Double> calculaAsParada() {
		Map<String, Double> values = new HashMap<>();
		
		int w = image.getWidth(), h = image.getHeight();

		int[] pixels = image.getRGB(0, 0, w, h, null, 0, w);

		Double media = media(pixels);
		Double variancia = variancia(media, pixels);
		Double desvio = desvio(variancia);
		
		values.put("media", media);
		values.put("variancia", variancia);
		values.put("desvio", desvio);

		return values;
	}

	private Double media(int[] pixels) {
		Double soma = 0.;

		for (int i = 0; i < pixels.length; i++) {
			soma += pixels[i];
		}

		return soma/pixels.length;
	}

	private Double variancia(Double media, int[] pixels) {
		Double ponderado = 0.;
		int n = 1;

		for (int i = 0; i < pixels.length; i++) {
			ponderado += Math.pow((pixels[i] - media),2);
		}

		return ponderado/pixels.length;
	}

	private Double desvio(Double variancia) {
		return Math.sqrt(variancia);
	}

	private Integer aproximar(Integer nivel_cinza, Set<Integer> keySet) {
		for (int i = nivel_cinza, j = 0; i < keySet.size() && j < 20; i++, j++) {
			if (keySet.contains(i)) {
				return i;
			}
		}
		return null;
	}

	private Map<Integer, Color> pegarCores(BufferedImage referencia) {
		int w = referencia.getWidth(), h = referencia.getHeight();
		Map<Integer, Color> map = new HashMap<>();

		for (int row = 0; row < h; row++) {
			for (int col = 0; col < w; col++) {
				int rgb = referencia.getRGB(col, row);
				Integer nivel_cinza = (int) (new Color(rgb).getRed() * 0.30 + new Color(rgb).getGreen() * 0.59
						+ new Color(rgb).getBlue() * 0.11);
				Color color = new Color(rgb);

				if (!map.containsKey(nivel_cinza)) {
					map.put(nivel_cinza, color);
				}
			}
		}
		System.out.println("Terminou Map");

		return map;

	}
}

