package details;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Clock extends JPanel {
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.setColor(Color.white);
		g.fillRect(1, 1, 190, 200);
	}
}
