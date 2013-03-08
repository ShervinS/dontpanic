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
		//setBackground(new Color(0xdddddd));
		setPreferredSize(new Dimension(200,30));
		setMaximumSize(new Dimension(200,30));
		setBackground(new Color(10, 91, 195));
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
	}
}