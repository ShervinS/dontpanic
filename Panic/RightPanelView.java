package Panic;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.JDatePicker;

public class RightPanelView extends JPanel {
	
	RightPanelController pc;
	
	GridBagConstraints c;
	
	
	
	public RightPanelView(RightPanelController p) {
		super();
		pc = p;
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
	}
	
	public void gridAdd(int x, int y, Component comp) {
		c.gridx = x;
		c.gridy = y;
		this.add(comp, c);
	}
	
}
