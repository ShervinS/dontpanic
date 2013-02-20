package Panic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import net.sourceforge.jdatepicker.JDatePicker;

public class RightPanelView extends JPanel implements ActionListener {
	
	private RightPanelController pc;
	
	private GridBagConstraints c;
	
	private JPanel filler;
	
	private boolean show;
	private Timer t;
	
	private int currentWidth;
	private int currentHeight;
	
	
	public RightPanelView(RightPanelController p) {
		super();
		pc = p;
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.7;
		c.insets = new Insets(0, 5, 5, 5);
		setBackground(new Color(0x000000));
		setPreferredSize(new Dimension(0, 600));
		currentWidth = 50;
		t = new Timer(5, this);
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
	
	public void showPanel(Boolean b) {
		show = b;
		currentHeight = this.getHeight();
		currentWidth = this.getWidth();
		t.stop();
		t.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ((currentWidth < 5 && !show) || (currentWidth > 195 && show)) {
			t.stop();
		}
		else {
			if (show) {
				currentWidth += 5;
			}
			else {
				currentWidth -= 5;
			}
			setPreferredSize(new Dimension(currentWidth, currentHeight));
			revalidate();
			repaint();
		}
	}
	
}
