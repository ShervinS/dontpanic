package categories;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class ClockView extends JPanel {
	 
	private static final long serialVersionUID = 1L;
	private GridBagConstraints c;

	public ClockView () {
		super();
		setPreferredSize(new Dimension(200,40));
		setMaximumSize(new Dimension(200,40));
		setBackground(new Color(39, 41, 43));
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
	}
}