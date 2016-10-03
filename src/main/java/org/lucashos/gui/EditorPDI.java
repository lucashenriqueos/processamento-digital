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

import org.lucashos.gui.tela.Tela;
import org.lucashos.gui.tela.TelaEx01;
import org.lucashos.gui.tela.TelaEx02;
import org.lucashos.gui.tela.TelaEx03;
import org.lucashos.gui.tela.TelaEx04;

public class EditorPDI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JMenuBar jmPrincipal = new JMenuBar();
	private JMenu jmExercicios = new JMenu("Exercicios");
	private JMenuItem jmEx01 = new JMenuItem("Ex01");
	private JMenuItem jmEx02 = new JMenuItem("Ex02");
	private JMenuItem jmEx03 = new JMenuItem("Ex03");
	private JMenuItem jmEx04 = new JMenuItem("Ex04");
	private JMenuItem jmSair = new JMenuItem("Sair");
	public JDesktopPane jdPane = new JDesktopPane();
	private Tela telaEx01;
	private Tela telaEx02;
	private Tela telaEx03;
	private Tela telaEx04;

	public EditorPDI() {
		getContentPane().add(jdPane);
		jmPrincipal.add(jmExercicios);
		jmExercicios.add(jmEx01);
		jmExercicios.add(jmEx02);
		jmExercicios.add(jmEx03);
		jmExercicios.add(jmEx04);
		jmExercicios.add(jmSair);
		setJMenuBar(jmPrincipal);
		jmSair.addActionListener(this);
		jmEx02.addActionListener(this);
		jmEx01.addActionListener(this);
		jmEx03.addActionListener(this);
		jmEx04.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
	}

	public static void main(String args[]) {
		tryNumbus();
		EditorPDI menu = new EditorPDI();
		menu.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		int returnVal;
		JFileChooser fc;
		
		if (evt.getSource() == jmSair) {
			System.exit(0);
			
		} else if (evt.getSource() == jmEx01) {
			fc = new JFileChooser();
			returnVal = fc.showOpenDialog(this);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				telaEx01 = new TelaEx01("Tela Ex01 - Ler e mostrar imagem", this, file);
				jdPane.moveToFront(telaEx01);
			} else {
				// nada a fazer eu acho!
			}
		} else if (evt.getSource() == jmEx02) {
			fc = new JFileChooser();
			returnVal = fc.showOpenDialog(this);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				telaEx02 = new TelaEx02("Tela Ex02 - ...", this, file);
				jdPane.moveToFront(telaEx02);
			}
			
		} else if (evt.getSource() == jmEx03) {
			fc = new JFileChooser();
			returnVal = fc.showOpenDialog(this);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				telaEx03 = new TelaEx03("Tela Ex03 - ...", this, file);
				jdPane.moveToFront(telaEx03);
			}
			
		} else if (evt.getSource() == jmEx04) {
			fc = new JFileChooser();
			returnVal = fc.showOpenDialog(this);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				telaEx04 = new TelaEx04("Tela Ex04 - ...", this, file);
				jdPane.moveToFront(telaEx04);
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
