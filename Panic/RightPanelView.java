package Panic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.JDatePicker;

public class RightPanelView extends JPanel {
	
	RightPanelController pc;
	
	GridBagConstraints c;
	
	JPanel filler;
	
	
	public RightPanelView(RightPanelController p) {
		super();
		pc = p;
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.7;
		c.insets = new Insets(0, 5, 5, 5);
		setBackground(new Color(0x000000));
	}
	
	public void gridAdd(int x, int y, int pad, Component comp) {
		c.insets = new Insets(pad, 5, 0, 5);
		c.gridx = x;
		c.gridy = y;
		this.add(comp, c);
	}
	
	public void pad(int x, int y) {
		if (filler != null) {
			remove(filler);
		}
		c.gridx = x;
		c.gridy = y;
		GridBagConstraints fill = (GridBagConstraints) c.clone();
		fill.weighty = 1;
		filler = new JPanel();
		filler.setBackground(this.getBackground());
		add(filler, fill);
	}
	
}
