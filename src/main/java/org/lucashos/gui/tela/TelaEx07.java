package org.lucashos.gui.tela;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.lucashos.especial.effects.SpecialDeffects;
import org.lucashos.gui.EditorPDI;

public class TelaEx07 extends Tela implements ActionListener {
	private static final long serialVersionUID = 1L;
	private File file;
	private BufferedImage imagem;
	private JButton btnAcao;
	private JButton btnAcao2;
	SpecialDeffects special;

	public TelaEx07(String titulo, EditorPDI telaPrincipal, File file) {
		super(titulo, telaPrincipal);
		this.file = file;

		try {
			imagem = ImageIO.read(file);
			special = new SpecialDeffects(imagem);

			String infoImagem = "Dimens�es: " + imagem.getWidth() + "x" + imagem.getHeight() + "Bandas: "
					+ imagem.getRaster().getNumBands();

			prepareLayout(infoImagem, imagem);

		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAcao) {
			System.out.println("Tela08");

			Map<String, Double> values = special.calculaAsParada();
			
			String message = "Valores:\nMedia: " + String.format("%.2f", values.get("media")) 
						+ "\nVariancia: " + String.format("%.2f", values.get("variancia"))
						+ "\nDesvio: " + String.format("%.2f", values.get("desvio"));

			JOptionPane.showMessageDialog(this, message);

			this.repaint();
			System.out.println("OK!");

		} else if (e.getSource() == btnAcao2) {
			final JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();

				try {
					ImageIO.write(imagem, "PNG", file);
					JOptionPane.showMessageDialog(this, "OK!");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(this, "Erro: " + e1.getMessage());
				}
			} else {
				//
			}
		}
	}

	void prepareLayout(String infoImagem, BufferedImage img) {
		ImageIcon icone = new ImageIcon(img);
		JLabel labImagem = new JLabel(icone);

		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(new JScrollPane(labImagem), BorderLayout.CENTER);
		contentPane.add(new JLabel(infoImagem), BorderLayout.NORTH);

		btnAcao = new JButton("Calcular");
		btnAcao.addActionListener(this);
		btnAcao2 = new JButton("Salvar Imagem");
		btnAcao2.addActionListener(this);

		JPanel painel = new JPanel();
		painel.add(btnAcao);
		painel.add(btnAcao2);
		contentPane.add(painel, BorderLayout.SOUTH);

		this.setSize(img.getWidth() + 30, img.getHeight() + 90);

	}
}