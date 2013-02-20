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
	
	public GridBagConstraints c;
	
	private JPanel filler;
	private boolean show;
	private Timer t;
	
	private int currentWidth;
	private int currentHeight;
	
	/**
	 * Constructor for this view. Will create an empty 
	 * RightPanelView
	 * @param p The controller for this panel
	 */
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
		t = new Timer(1, this);
	}
	
	
	/**
	 * Adds an element to this component
	 * @param x Where in the grid horizontally the component should be added 
	 * @param y Where in the grid vertically the component should be added
	 * @param pad How much padding should be done before the component
	 * @param comp The component to add
	 */
	public void gridAdd(int x, int y, int pad, Component comp) {
		c.insets = new Insets(pad, 5, 0, 5);
		c.gridx = x;
		c.gridy = y;
		this.add(comp, c);
	}
	
	/**
	 * Will pad the RightPanelView to move every other component to the top
	 * @param x The position horizontally in the grid where the padding should be,
	 * 			should be the last.
	 * @param y The position vertically in the gird where the padding should be,
	 * 			should be the last.
	 */
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
	
	/**
	 * Will animate this panel to show, or close
	 * @param b Show if b is true, close if b is false
	 */
	public void showPanel(Boolean b) {
		show = b;
		currentHeight = this.getHeight();
		currentWidth = this.getWidth();
		t.stop();
		t.start();
	}

	@Override
	/**
	 * Shrinks or enlarges this panel by 15 pixels
	 * @param e Event being executed
	 */
	public void actionPerformed(ActionEvent e) {
		if ((currentWidth < 15 && !show) || (currentWidth > 185 && show)) {
			currentWidth = show ? 200 : 0;
			setPreferredSize(new Dimension(currentWidth, currentHeight));
			revalidate();
			repaint();
			t.stop();
		}
		else {
			if (show) {
				currentWidth += 15;
			}
			else {
				currentWidth -= 15;
			}
			setPreferredSize(new Dimension(currentWidth, currentHeight));
			revalidate();
			repaint();
		}
	}
	
}
