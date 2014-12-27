package org.raydelto.redirector.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NumericKeyListener extends KeyAdapter{
	@Override
	public void keyTyped(KeyEvent e) {
		if((int)e.getKeyChar() <48 || (int)e.getKeyChar() > 57){
			e.consume();
		}
	}
}
