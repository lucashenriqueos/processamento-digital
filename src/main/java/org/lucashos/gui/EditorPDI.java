package org.lucashos.gui;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.lucashos.gui.telas.Tela;
import org.lucashos.gui.telas.lista1.TelaEx01;
import org.lucashos.gui.telas.lista1.TelaEx02;
import org.lucashos.gui.telas.lista1.TelaEx03;
import org.lucashos.gui.telas.lista1.TelaEx04;
import org.lucashos.gui.telas.lista1.TelaEx05;
import org.lucashos.gui.telas.lista1.TelaEx06;
import org.lucashos.gui.telas.lista1.TelaEx07;
import org.lucashos.gui.telas.lista1.TelaEx08;
import org.lucashos.gui.telas.lista2.TelaFatiamento;
import org.lucashos.gui.telas.lista2.TelaHistograma;
import org.lucashos.gui.telas.lista2.TelaLog;
import org.lucashos.gui.telas.lista2.TelaPow;

public class EditorPDI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	/*
	 * Lista 01
	 */
	private JMenuItem jmEx01 = new JMenuItem("Ex01");
	private JMenuItem jmEx02 = new JMenuItem("Ex02");
	private JMenuItem jmEx03 = new JMenuItem("Ex03");
	private JMenuItem jmEx04 = new JMenuItem("Ex04");
	private JMenuItem jmEx05 = new JMenuItem("Ex05");
	private JMenuItem jmEx06 = new JMenuItem("Ex06");
	private JMenuItem jmEx07 = new JMenuItem("Ex07");
	private JMenuItem jmEx08 = new JMenuItem("Ex08");
	private JMenuItem jmSair = new JMenuItem("Sair");

	private JMenuItem jmL2Ex01 = new JMenuItem("01 - Log");
	private JMenuItem jmL2Ex02 = new JMenuItem("02 - Pow");
	private JMenuItem jmL2Ex03 = new JMenuItem("03 - Fatiamento");
	private JMenuItem jmL2Ex04 = new JMenuItem("04 - Reconstruir");
	private JMenuItem jmL2Ex05 = new JMenuItem("05 - Histograma");
	private JMenuItem jmL2Ex06 = new JMenuItem("06 - Realce");
	private JMenuItem jmL2Ex07 = new JMenuItem("07 - Grafico Senoide");
	private JMenuItem jmL2Ex08 = new JMenuItem("08 - FFT 1D");
	private JMenuItem jmL2Ex09 = new JMenuItem("09 - FFT 2D");
	private JMenuItem jmL2Ex10 = new JMenuItem("10 - I_FFT 2D");

	public JDesktopPane jdPane = new JDesktopPane();

	public EditorPDI() {
		getContentPane().add(jdPane);
		JMenuBar jmPrincipal = new JMenuBar();
		lista01(jmPrincipal);
		lista02(jmPrincipal);

		jmPrincipal.add(jmSair);
		jmSair.addActionListener(this);
		setJMenuBar(jmPrincipal);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
	}

	private void lista01(JMenuBar jmPrincipal) {
		JMenu jmExercicios = new JMenu("Lista 01");
		jmPrincipal.add(jmExercicios);
		jmExercicios.add(jmEx01);
		jmExercicios.add(jmEx02);
		jmExercicios.add(jmEx03);
		jmExercicios.add(jmEx04);
		jmExercicios.add(jmEx05);
		jmExercicios.add(jmEx06);
		jmExercicios.add(jmEx07);
		jmExercicios.add(jmEx08);
		jmEx02.addActionListener(this);
		jmEx01.addActionListener(this);
		jmEx03.addActionListener(this);
		jmEx04.addActionListener(this);
		jmEx05.addActionListener(this);
		jmEx06.addActionListener(this);
		jmEx07.addActionListener(this);
		jmEx08.addActionListener(this);
	}

	private void lista02(JMenuBar jmPrincipal) {
		JMenu jmExercicios = new JMenu("Lista 02");
		jmPrincipal.add(jmExercicios);
		jmExercicios.add(jmL2Ex01);
		jmExercicios.add(jmL2Ex02);
		jmExercicios.add(jmL2Ex03);
		jmExercicios.add(jmL2Ex04);
		jmExercicios.add(jmL2Ex05);
		jmExercicios.add(jmL2Ex06);
		jmExercicios.add(jmL2Ex07);
		jmExercicios.add(jmL2Ex08);
		jmExercicios.add(jmL2Ex09);
		jmExercicios.add(jmL2Ex10);
		jmL2Ex01.addActionListener(this);
		jmL2Ex02.addActionListener(this);
		jmL2Ex03.addActionListener(this);
		jmL2Ex04.addActionListener(this);
		jmL2Ex05.addActionListener(this);
		jmL2Ex06.addActionListener(this);
		jmL2Ex07.addActionListener(this);
		jmL2Ex08.addActionListener(this);
		jmL2Ex09.addActionListener(this);
		jmL2Ex10.addActionListener(this);
	}

	public static void main(String args[]) {
		tryNumbus();
		EditorPDI menu = new EditorPDI();
		menu.setVisible(true);
	}

	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == jmSair) {
			System.exit(0);
		} else {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(this);
			File file = null;
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				file = fc.getSelectedFile();
				if (evt.getSource() == jmEx01) {
					Tela telaEx01 = new TelaEx01("Tela Ex01 - Ler e mostrar imagem", this, file);
					jdPane.moveToFront(telaEx01);
				} else if (evt.getSource() == jmEx02) {
					Tela telaEx02 = new TelaEx02("Tela Ex02 - ...", this, file);
					jdPane.moveToFront(telaEx02);
				} else if (evt.getSource() == jmEx03) {
					Tela telaEx03 = new TelaEx03("Tela Ex03 - ...", this, file);
					jdPane.moveToFront(telaEx03);
				} else if (evt.getSource() == jmEx04) {
					Tela telaEx04 = new TelaEx04("Tela Ex04 - ...", this, file);
					jdPane.moveToFront(telaEx04);
				} else if (evt.getSource() == jmEx05) {
					Tela telaEx05 = new TelaEx05("Tela Ex05 - ...", this, file);
					jdPane.moveToFront(telaEx05);
				} else if (evt.getSource() == jmEx06) {
					Tela telaEx06 = new TelaEx06("Tela Ex06 - ...", this, file);
					jdPane.moveToFront(telaEx06);
				} else if (evt.getSource() == jmEx07) {
					Tela telaEx07 = new TelaEx07("Tela Ex07 - ...", this, file);
					jdPane.moveToFront(telaEx07);
				} else if (evt.getSource() == jmEx08) {
					Tela telaEx08 = new TelaEx08("Tela Ex08 - ...", this, file);
					jdPane.moveToFront(telaEx08);
				} else if (evt.getSource() == jmL2Ex01) {
					Tela telaLog = new TelaLog("Tela Log - ...", this, file);
					jdPane.moveToFront(telaLog);
				} else if (evt.getSource() == jmL2Ex02) {
					Tela telaPow = new TelaPow("Tela Pow - ...", this, file);
					jdPane.moveToFront(telaPow);
				} else if (evt.getSource() == jmL2Ex03) {
					Tela telaFatiamento = new TelaFatiamento("Fatiamento - ...", this, file);
					jdPane.moveToFront(telaFatiamento);
				} else if (evt.getSource() == jmL2Ex04) {
					//Tela telaEx08 = new TelaEx08("Tela Ex08 - ...", this, file);
					//jdPane.moveToFront(telaEx08);
				} else if (evt.getSource() == jmL2Ex05) {
					jdPane.moveToFront(new TelaHistograma("Histograma", this, file));
				}
			}
		}
	}

	private static void tryNumbus() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
		}
	}
}
