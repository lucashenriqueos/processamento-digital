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

public class TelaEx08 extends Tela implements ActionListener {
	private static final long serialVersionUID = 1L;
	private File file;
	private BufferedImage imagem;
	private JButton btnAcao;
	private JButton btnAcao2;
	SpecialDeffects special;

	public TelaEx08(String titulo, EditorPDI telaPrincipal, File file) {
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

			/*Map<String, Integer> values = multipleInput();

			special.contraste(values.get("K"), values.get("E"));*/
			
			special.contraste(127, 5);

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

	private Map<String, Integer> multipleInput(){
		Map<String, Integer> map = new HashMap<>();
		
		JTextField kField = new JTextField(5);
		JTextField eField = new JTextField(5);

	    JPanel myPanel = new JPanel();
	    myPanel.add(new JLabel("K:"));
	    myPanel.add(kField);
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("E:"));
	    myPanel.add(eField);

	    int result = JOptionPane.showConfirmDialog(null, myPanel, 
	               "Please Enter K and E Values", JOptionPane.OK_CANCEL_OPTION);
	    if (result == JOptionPane.OK_OPTION) {
	       System.out.println("K value: " + kField.getText());
	       System.out.println("E value: " + eField.getText());
	       
	       map.put("K", Integer.parseInt(kField.getText()));
	       map.put("E", Integer.parseInt(eField.getText()));
	    }
		
		return map;
	}

	void prepareLayout(String infoImagem, BufferedImage img) {
		ImageIcon icone = new ImageIcon(img);
		JLabel labImagem = new JLabel(icone);

		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(new JScrollPane(labImagem), BorderLayout.CENTER);
		contentPane.add(new JLabel(infoImagem), BorderLayout.NORTH);

		btnAcao = new JButton("Efeito Especial!");
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