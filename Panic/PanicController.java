package Panic;

import java.awt.GridBagLayout;

import javax.swing.JFrame;

public class PanicController {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("the frame");
		frame.setContentPane(new MainPanel(new GridBagLayout()));
		
		frame.pack();
		frame.setVisible(true);		
	}

}
