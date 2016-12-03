package org.lucashos.gui.telas;
import javax.swing.JInternalFrame;

import org.lucashos.gui.EditorPDI;

public class Tela extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	public EditorPDI telaPrincipal;

	public Tela(String titulo, EditorPDI telaPrincipal) {
		super(titulo, true, true, true, true);
		setSize(300, 200);
		setVisible(true);
		this.telaPrincipal = telaPrincipal;
		// setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		telaPrincipal.jdPane.add(this);
	}
}
