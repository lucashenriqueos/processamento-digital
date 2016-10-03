package org.lucashos.gui.tela;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.lucashos.especial.effects.SpecialDeffects;
import org.lucashos.gui.EditorPDI;

public class TelaEx01 extends Tela implements ActionListener {
	private static final long serialVersionUID = 1L;
	private File file;
	private BufferedImage imagem;
	private JButton btnAcao;
	private JButton btnAcao2;
	SpecialDeffects deffs;

	public TelaEx01(String titulo, EditorPDI telaPrincipal, File file) {
		super(titulo, telaPrincipal);
		this.file = file;
		try {
			imagem = ImageIO.read(file);
			deffs = new SpecialDeffects(imagem);
			String infoImagem = "Dimensões: " + imagem.getWidth() + "x" + imagem.getHeight() + "Bandas: "
					+ imagem.getRaster().getNumBands();
			ImageIcon icone = new ImageIcon(imagem);
			JLabel labImagem = new JLabel(icone);
			
			Container contentPane = this.getContentPane();
			contentPane.setLayout(new BorderLayout());
			contentPane.add(new JScrollPane(labImagem), BorderLayout.CENTER);
			contentPane.add(new JLabel(infoImagem), BorderLayout.NORTH);
			
			JPanel painel = new JPanel();
			btnAcao = new JButton("Efeito Especial");
			btnAcao.addActionListener(this);
			btnAcao2 = new JButton("Salvar Imagem");
			btnAcao2.addActionListener(this);
			painel.add(btnAcao);
			painel.add(btnAcao2);
			contentPane.add(painel, BorderLayout.SOUTH);
			
			this.setSize(imagem.getWidth() + 30, imagem.getHeight() + 90);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAcao) {
			
			deffs.arteAbstrata();
			
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
}